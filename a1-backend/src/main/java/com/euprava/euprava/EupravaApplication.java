package com.euprava.euprava;


import org.apache.jena.fuseki.build.FusekiConfig;
import org.apache.jena.fuseki.main.FusekiServer;
import org.apache.jena.fuseki.system.FusekiLogging;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.tdb.TDBFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EupravaApplication {

    public static void main(String[] args) {

        SpringApplication.run(EupravaApplication.class, args);

//        Dataset ds = DatasetFactory.createTxnMem() ;
//        FusekiServer fusekiServer = FusekiServer.create()
//                .port(3031)
//                .add("/a1",ds)
//                .build();
//        FusekiLogging.setLogging();
//        fusekiServer.start();
    }

}
