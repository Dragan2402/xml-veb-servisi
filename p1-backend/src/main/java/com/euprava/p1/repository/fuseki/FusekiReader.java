package com.euprava.p1.repository.fuseki;

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
    private static final String GRAPH_URI = "p1_metadata";

    private FusekiReader() {

    }

    public static ArrayList<String> executeQuery(Map<String, String> params) throws IOException {
        FusekiAuthenticationUtilities.ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();
        String sparqlQueryTemplate = readFile(QUERY_FILE, StandardCharsets.UTF_8);
//        String sparqlQuery = StringSubstitutor.replace(sparqlQueryTemplate, params, "{{", "}}");
        String sparqlQuery = "";
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();

        String varName;
        RDFNode varValue;
        ArrayList<String> found = new ArrayList<>();
        while (results.hasNext()) {
            QuerySolution querySolution = results.next();
            Iterator<String> variableBindings = querySolution.varNames();

            while (variableBindings.hasNext()) {
                varName = variableBindings.next();
                varValue = querySolution.get(varName);

                if (varName.contains("naziv")) {
                    String value = varValue.toString();
                    found.add(value);
                }
            }
        }
        ResultSetFormatter.outputAsXML(System.out, results);
        query.close();
        return found;
    }

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
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
