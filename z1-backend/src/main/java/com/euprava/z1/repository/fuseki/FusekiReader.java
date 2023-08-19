package com.euprava.z1.repository.fuseki;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class FusekiReader {
    private static final String QUERY_FILE = "src/main/resources/sparql/sparql.rq";
    private static final String GRAPH_URI = "z1_metadata";

    private FusekiReader() {

    }

    public static ResultSet readRDFWithQuery(String sparqlQueryCondition) throws IOException {
        FusekiAuthenticationUtilities.ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();
        System.out.println("[INFO] Selecting the triples from the named graph \"" + GRAPH_URI + "\".");
        String sparqlQuery;
        sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint +"/"+ GRAPH_URI, sparqlQueryCondition);
        System.out.println(sparqlQuery);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        return query.execSelect();
    }

    public static String readMetadataAsRDF(String sparqlCondition) throws IOException {
        FusekiAuthenticationUtilities.ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();
        String sparqlQuery = SparqlUtil.constructData(conn.dataEndpoint + "/" + GRAPH_URI, sparqlCondition);
        try (QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery)) {
            Model model = query.execConstruct();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            model.write(out, SparqlUtil.NTRIPLES);
            return out.toString();
        }
    }

    public static String readMetadataAsJSON(String sparqlCondition) throws IOException {
        FusekiAuthenticationUtilities.ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();
        String sparqlQuery = SparqlUtil.constructData(conn.dataEndpoint + "/" + GRAPH_URI, sparqlCondition);
        try (QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery)) {
            Model model = query.execConstruct();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            model.write(out, SparqlUtil.RDF_JSON);
            return out.toString();
        }
    }
}
