package com.euprava.euprava.existdb;

import com.euprava.euprava.util.ExistDBAuthenticationUtilities;
import com.euprava.euprava.util.XUpdateTemplate;
import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Service;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;
import org.xmldb.api.modules.XUpdateQueryService;

import javax.xml.transform.OutputKeys;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExistDBManager {

    public void openConnection() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        Class<?> cl = Class.forName(ExistDBAuthenticationUtilities.loadProperties().driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
    }

    public void closeConnection(Collection collection, XMLResource resource) throws XMLDBException {
        if (collection != null) {
            collection.close();
        }
        if (resource != null) {
            ((EXistResource) resource).freeResources();
        }
    }

    public void store(String collectionId, String documentId, String xmlString) throws Exception  {
        openConnection();
        Collection col = null;
        XMLResource res = null;
        try {
            col = getOrCreateCollection(collectionId, 0);
            res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
            res.setContent(xmlString);
            col.storeResource(res);
        } finally {
            closeConnection(col, res);
        }
    }

    private static Collection getOrCreateCollection(String collectionUri) throws XMLDBException, IOException {
        return getOrCreateCollection(collectionUri, 0);
    }

    private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException, IOException {

        Collection col = DatabaseManager.getCollection(ExistDBAuthenticationUtilities.loadProperties().uri + collectionUri,
                ExistDBAuthenticationUtilities.loadProperties().user,
                ExistDBAuthenticationUtilities.loadProperties().password);

        // create the collection if it does not exist
        if(col == null) {

            if(collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }

            String pathSegments[] = collectionUri.split("/");

            if(pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for(int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/" + pathSegments[i]);
                }

                Collection startCol = DatabaseManager.getCollection(ExistDBAuthenticationUtilities.loadProperties().uri + path,
                        ExistDBAuthenticationUtilities.loadProperties().user,
                        ExistDBAuthenticationUtilities.loadProperties().password);

                if (startCol == null) {
                    // child collection does not exist

                    String parentPath = path.substring(0, path.lastIndexOf("/"));

                    Collection parentCol = DatabaseManager.getCollection(ExistDBAuthenticationUtilities.loadProperties().uri + parentPath,
                            ExistDBAuthenticationUtilities.loadProperties().user,
                            ExistDBAuthenticationUtilities.loadProperties().password);

                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");

                    System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);

                    col.close();
                    parentCol.close();

                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
        } else {
            return col;
        }
    }

    public XMLResource load(String collectionId, String documentId) throws Exception {
        openConnection();
        Collection collection = null;
        XMLResource resource =  null;
        try {
            collection = DatabaseManager.getCollection(ExistDBAuthenticationUtilities.loadProperties().uri + collectionId,
                    ExistDBAuthenticationUtilities.loadProperties().user,
                    ExistDBAuthenticationUtilities.loadProperties().password);
            collection.setProperty(OutputKeys.INDENT, "yes");
            resource = (XMLResource) collection.getResource(documentId);
        } catch (Exception e) {
            closeConnection(collection, resource);
        }
        return resource;    }

    public List<Resource> executeQuery(String collectionUri, String targetNamespace, String query) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        openConnection();
        Collection collection = null;
        List<Resource> resources = new ArrayList<Resource>();

        try {
            collection = DatabaseManager.getCollection(ExistDBAuthenticationUtilities.loadProperties().uri + collectionUri,
                    ExistDBAuthenticationUtilities.loadProperties().user,
                    ExistDBAuthenticationUtilities.loadProperties().password);
            collection.setProperty(OutputKeys.INDENT, "yes");

            // get an instance of xquery service
            XQueryService xqueryService = (XQueryService) collection.getService("XQueryService", "1.0");
            xqueryService.setProperty("indent", "yes");

            // make the service aware of namespaces
            xqueryService.setNamespace("b", targetNamespace);

            CompiledExpression compiledXquery = xqueryService.compile(query);
            ResourceSet result = xqueryService.execute(compiledXquery);

            ResourceIterator i = result.getIterator();
            Resource res = null;

            while(i.hasMoreResources()) {
                try {
                    res = i.nextResource();
                    resources.add(res);
                    System.out.println(res.getContent());

                } finally {

                    // don't forget to cleanup resources
                    try {
                        ((EXistResource)res).freeResources();
                    } catch (XMLDBException xe) {
                        xe.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            closeConnection(collection, null);
        }
        return resources;
    }

    public void updateRequestToApproved(String collectionUri, String documentId) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        openConnection();
        Collection collection = null;
        XMLResource resource =  null;

        try {
            collection = DatabaseManager.getCollection(ExistDBAuthenticationUtilities.loadProperties().uri + collectionUri,
                    ExistDBAuthenticationUtilities.loadProperties().user,
                    ExistDBAuthenticationUtilities.loadProperties().password);
            collection.setProperty(OutputKeys.INDENT, "yes");

            XUpdateQueryService xupdateService = (XUpdateQueryService) collection.getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");

            xupdateService.updateResource(documentId, String.format(XUpdateTemplate.getUpdateExpression("http://euprava.euprava.com/model/a1Sertifikat"), "/obrazacA1/Status", "Prihvacen"));

        } catch (Exception e) {
            closeConnection(collection, resource);
        }
    }

    public void updateRequestToDeclined(String collectionUri, String documentId) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        openConnection();
        Collection collection = null;
        XMLResource resource =  null;

        try {
            collection = DatabaseManager.getCollection(ExistDBAuthenticationUtilities.loadProperties().uri + collectionUri,
                    ExistDBAuthenticationUtilities.loadProperties().user,
                    ExistDBAuthenticationUtilities.loadProperties().password);
            collection.setProperty(OutputKeys.INDENT, "yes");

            XUpdateQueryService xupdateService = (XUpdateQueryService) collection.getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");

            xupdateService.updateResource(documentId, String.format(XUpdateTemplate.getUpdateExpression("http://euprava.euprava.com/model/a1Sertifikat"), "/obrazacA1/Status", "Odbijen"));

        } catch (Exception e) {
            closeConnection(collection, resource);
        }
    }
}
