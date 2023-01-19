package com.users.existdb;

import com.users.model.user.User;
import com.users.util.XUpdateTemplate;
import com.users.util.ExistDBAuthenticationUtilities;
import com.users.util.exception.customExceptions.ObjectNotFoundException;
import com.users.util.exception.customExceptions.UserAlreadyExistsException;
import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Service;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
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

    public User getUserByEmail(String email, boolean existing) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        openConnection();
        Collection collection = null;
        User user = null;
        try {
            // get the collection
            collection = DatabaseManager.getCollection(ExistDBAuthenticationUtilities.loadProperties().uri + "/db/users",
                    ExistDBAuthenticationUtilities.loadProperties().user,
                    ExistDBAuthenticationUtilities.loadProperties().password);
            if (collection == null) {
                collection = getOrCreateCollection("/db/users");
            }
            collection.setProperty(OutputKeys.INDENT, "yes");


            // get an instance of xpath query service
            XPathQueryService xpathService = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");

            // make the service aware of namespaces, using the default one
            xpathService.setNamespace("users", "http://users.com/model/user");

            String xpathExp = "//users:user[users:email[text()='"+email+"']]";

            // execute xpath expression
            System.out.println("[INFO] Invoking XPath query service for: " + xpathExp);
            ResourceSet result = xpathService.query(xpathExp);

            // handle the results
            System.out.println("[INFO] Handling the results... ");

            ResourceIterator i = result.getIterator();
            Resource res = i.nextResource();

            if (existing && res == null){
                throw new ObjectNotFoundException("User with provided email does not exist.");
            }
            if (!existing && res != null){
                throw new UserAlreadyExistsException("User with provided email already exists.");
            }
            if (!existing && res == null){
                return null;
            }
            JAXBContext context = JAXBContext.newInstance(User.class);
            Unmarshaller u = context.createUnmarshaller();
            System.out.println(res.getContent());
            String str = res.getContent().toString();
            user = (User) u.unmarshal(new StreamSource(new StringReader(str)));
            ((EXistResource)res).freeResources();


        } catch (XMLDBException e) {
            throw new ObjectNotFoundException("XMLDB User with provided email does not exist.");
        } catch (JAXBException e) {
            e.printStackTrace();
        } finally {
            closeConnection(collection, null);
        }
        return user;
    }
}
