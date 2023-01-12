package com.euprava.euprava.rdf;

import com.euprava.euprava.util.FusekiAuthenticationUtilities;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;

import java.io.IOException;

public class FusekiReader {


    public static ResultSet readRDFWithQuery(String graphUri, String sparqlQueryCondition) throws IOException {
        FusekiAuthenticationUtilities.ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();
        System.out.println("[INFO] Selecting the triples from the named graph \"" + graphUri + "\".");
        String sparqlQuery;

        sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint +"/"+ graphUri, sparqlQueryCondition);

        System.out.println(sparqlQuery);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        return query.execSelect();
    }
}
