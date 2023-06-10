package com.euprava.p1.repository.exist;

import org.exist.xmldb.EXistResource;
import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import javax.xml.transform.OutputKeys;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@Component
public class ExistManager {
    private static final String TARGET_NAMESPACE = "http://euprava.com/p1/model";
    private static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS + "\" xmlns=\"" + TARGET_NAMESPACE + "\">"
            + "<xu:update select=\"%1$s\">%2$s</xu:update>"
            + "</xu:modifications>";
    private static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS + "\" xmlns=\"" + TARGET_NAMESPACE + "\">"
            + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
            + "</xu:modifications>";

    @Autowired
    AuthenticationManager authMgr;

    public void store(String collectionUri, String documentId, String xmlString) throws XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        openConnection();
        Collection collection = null;
        XMLResource resource = null;

        try {
            collection = getOrCreateCollection(collectionUri);
            resource = (XMLResource) collection.createResource(documentId, XMLResource.RESOURCE_TYPE);

            resource.setContent(xmlString);
            collection.storeResource(resource);
        } finally {
            closeConnection(collection, resource);
        }
    }

    public XMLResource load(String collectionUri, String documentId) throws XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        openConnection();
        Collection collection = null;
        XMLResource resource =  null;

        try {
            collection = DatabaseManager.getCollection(authMgr.getUri() + collectionUri,
                    authMgr.getUser(),
                    authMgr.getPassword());
            collection.setProperty(OutputKeys.INDENT, "yes");
            resource = (XMLResource) collection.getResource(documentId);
        } catch (Exception e) {
            closeConnection(collection, resource);
        }
        return resource;
    }

    public String getLastId(String collectionUri) throws XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        openConnection();
        Collection collection = null;
        String[] resources = new String[0];

        try {
            collection = DatabaseManager.getCollection(authMgr.getUri() + collectionUri,
                    authMgr.getUser(),
                    authMgr.getPassword());
            collection.setProperty(OutputKeys.INDENT, "yes");
            resources = collection.listResources();
            resources = Arrays.stream(resources).sorted((id1, id2) -> {
                String[] id1Tokens = id1.split("-");
                String[] id2Tokens = id2.split("-");
                int id1Token;
                int id2Token;

                if (id1Tokens[1].equals(id2Tokens[1])) {
                    id1Token = Integer.parseInt(id1Tokens[0]);
                    id2Token = Integer.parseInt(id2Tokens[0]);
                } else {
                    id1Token = Integer.parseInt(id1Tokens[1]);
                    id2Token = Integer.parseInt(id2Tokens[1]);
                }

                return Integer.compare(id1Token, id2Token);
            }).toArray(String[]::new);
        } catch (Exception e) {
            if (collection != null) {
                collection.close();
            }
        }
        return resources.length == 0 ? null : resources[resources.length - 1];
    }

    public void openConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, XMLDBException {
        Class<?> aClass = Class.forName(authMgr.getDriver());
        Database database = (Database) aClass.getDeclaredConstructor().newInstance();
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

    public Collection getOrCreateCollection(String collectionUri) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0);
    }

    private Collection getOrCreateCollection(String collectionUri, int pathOffset) throws XMLDBException {
        Collection collection = DatabaseManager.getCollection(authMgr.getUri() + collectionUri, authMgr.getUser(), authMgr.getPassword());
        if (collection != null) {
            return collection;
        }

        if (collectionUri.startsWith("/")) {
            collectionUri = collectionUri.substring(1);
        }
        String[] pathSegments = collectionUri.split("/");

        if (pathSegments.length > 0) {
            StringBuilder path = new StringBuilder();
            for (int i = 0; i <= pathOffset; i++) {
                path.append("/").append(pathSegments[i]);
            }

            Collection startCollection = DatabaseManager.getCollection(authMgr.getUri() + path, authMgr.getUser(), authMgr.getPassword());
            if (startCollection == null) {
                String parentPath = path.substring(0, path.lastIndexOf("/"));
                Collection parentCollection = DatabaseManager.getCollection(authMgr.getUri() + parentPath, authMgr.getUser(), authMgr.getPassword());
                CollectionManagementService service = (CollectionManagementService) parentCollection.getService("CollectionManagementService", "1.0");
                collection = service.createCollection(pathSegments[pathOffset]);
                collection.close();
                parentCollection.close();
            } else {
                startCollection.close();
            }
        }

        return getOrCreateCollection(collectionUri, ++pathOffset);
    }
}
