package com.euprava.z1.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class ExistDBAuthenticationUtilities {
    //private static String connectionUri = "xmldb:exist://localhost:8082/exist/xmlrpc";

    /**
     * Connection parameters.
     */
    static public class ConnectionProperties {

        public String host;
        public int port = -1;
        public String user;
        public String password;
        public String driver;
        public String uri;

        public ConnectionProperties(Properties props) {
            super();

            user = props.getProperty("conn.user").trim();
            password = props.getProperty("conn.password").trim();

            host = props.getProperty("conn.host").trim();
            port = Integer.parseInt(props.getProperty("conn.port"));

            uri = "xmldb:exist://localhost:8085/exist/xmlrpc";

            driver = props.getProperty("conn.driver").trim();
        }
    }

    /**
     * Read the configuration properties for the example.
     *
     * @return the configuration object
     */
    public static ConnectionProperties loadProperties() throws IOException {
        String resourceName = "application.properties"; // could also be a constant
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
     * @param fileName the name of the resource
     * @return an input stream for the resource
     * @throws IOException
     */
    public static InputStream openStream(String fileName) throws IOException {
        return ExistDBAuthenticationUtilities.class.getClassLoader().getResourceAsStream(fileName);
    }


}
