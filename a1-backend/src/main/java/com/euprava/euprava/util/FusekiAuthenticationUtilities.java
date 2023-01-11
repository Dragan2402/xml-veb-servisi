package com.euprava.euprava.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FusekiAuthenticationUtilities {
    static public class ConnectionProperties {

        public String endpoint;
        public String dataset;

        public String queryEndpoint;
        public String updateEndpoint;
        public String dataEndpoint;
        public String username;
        public String password;


        public ConnectionProperties(Properties props) {
            super();
            dataset = props.getProperty("conn.dataset").trim();
            endpoint = props.getProperty("conn.endpoint").trim();

            username = props.getProperty("conn.username").trim();
            password = props.getProperty("conn.password").trim();

            queryEndpoint = (endpoint + "/" + dataset + "/" + props.getProperty("conn.query")).trim();
            updateEndpoint = (endpoint + "/" + dataset + "/" + props.getProperty("conn.update")).trim();
            dataEndpoint = (endpoint + "/" + dataset + "/" + props.getProperty("conn.data")).trim();

            System.out.println("[INFO] Parsing connection properties:");
            System.out.println("[INFO] Query endpoint: " + queryEndpoint);
            System.out.println("[INFO] Update endpoint: " + updateEndpoint);
            System.out.println("[INFO] Graph store endpoint: " + dataEndpoint);
        }
    }

    /**
     * Read the configuration properties for the example.
     *
     * @return the configuration object
     */
    public static ConnectionProperties loadProperties() throws IOException {
        String resourceName = "fuseki.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        }
        return new ConnectionProperties(props);
    }

    /**
     * Read a resource for an example.
     *
     * @param fileName
     *            the name of the resource
     * @return an input stream for the resource
     * @throws IOException
     */
    public static InputStream openStream(String fileName) throws IOException {
        return FusekiAuthenticationUtilities.class.getClassLoader().getResourceAsStream(fileName);
    }
}
