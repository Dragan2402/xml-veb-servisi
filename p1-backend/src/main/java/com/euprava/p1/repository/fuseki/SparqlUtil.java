package com.euprava.p1.repository.fuseki;

public class SparqlUtil {
    private static final String DROP_ALL = "DROP ALL";
    private static final String DROP_GRAPH_TEMPLATE = "DROP GRAPH <%s>";
    private static final String UPDATE_TEMPLATE = "INSERT DATA { %s }";
    private static final String UPDATE_TEMPLATE_NAMED_GRAPH = "INSERT DATA { GRAPH <%1$s> { %2$s } }";
    private static final String SELECT_NAMED_GRAPH_TEMPLATE = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\nSELECT DISTINCT * FROM <%1$s> WHERE { %2$s }";
    private static final String CONSTRUCT_NAMED_GRAPH_TEMPLATE = "CONSTRUCT FROM <%1$s> WHERE { %2$s }";
    private static final String SELECT_COUNT_TEMPLATE = "SELECT (COUNT(?s) AS ?count) FROM <%1$s> WHERE { %2$s }";
    public static final String NTRIPLES = "N-TRIPLES";
    public static final String RDF_JSON = "RDF/JSON";

    public static String dropAll() {
        return DROP_ALL;
    }

    public static String dropGraph(String graphURI) {
        return String.format(DROP_GRAPH_TEMPLATE, graphURI);
    }

    public static String insertData(String ntriples) {
        return String.format(UPDATE_TEMPLATE, ntriples);
    }

    public static String insertData(String graphURI, String ntriples) {
        return String.format(UPDATE_TEMPLATE_NAMED_GRAPH, graphURI, ntriples);
    }

    public static String selectData(String graphURI, String sparqlCondition) {
        return String.format(SELECT_NAMED_GRAPH_TEMPLATE, graphURI, sparqlCondition);
    }

    public static String selectCount(String graphURI, String sparqlCondition) {
        return String.format(SELECT_COUNT_TEMPLATE, graphURI, sparqlCondition);
    }

    public static String constructData(String graphURI, String sparqlCondition) {
        return String.format(CONSTRUCT_NAMED_GRAPH_TEMPLATE, graphURI, sparqlCondition);
    }
}
