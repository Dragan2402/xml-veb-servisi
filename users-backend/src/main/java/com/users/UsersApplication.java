package com.users;

import org.apache.jena.fuseki.main.FusekiServer;
import org.apache.jena.fuseki.system.FusekiLogging;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersApplication {

    public static void main(String[] args) {

        SpringApplication.run(UsersApplication.class, args);

        Dataset ds = DatasetFactory.createTxnMem() ;
        Dataset dsUser = DatasetFactory.createTxnMem() ;
        FusekiServer fusekiServer = FusekiServer.create()
                .port(3031)
                .add("/a1",ds)
                .add("/user",dsUser)
                .build();
        FusekiLogging.setLogging();
        fusekiServer.start();
    }

}
