package com.users.rdf;


import com.users.util.FusekiAuthenticationUtilities;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FusekiWriter {

    public static void saveRDF(ByteArrayInputStream rdfTriples, String graphUri) throws IOException {
        FusekiAuthenticationUtilities.ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();

        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(conn.username, conn.password);
        provider.setCredentials(AuthScope.ANY, credentials);

        BasicHttpContext context = new BasicHttpContext();
        context.setAttribute(ClientContext.CREDS_PROVIDER, provider);

        CloseableHttpClient client = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();

        Model model = ModelFactory.createDefaultModel();
        model.read(rdfTriples, "");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        model.write(out, SparqlUtil.NTRIPLES);
        model.write(System.out, SparqlUtil.RDF_XML);

        String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + "/" + graphUri,
                out.toString());

        UpdateRequest update = UpdateFactory.create(sparqlUpdate);
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint, client, context);
        processor.execute();
    }
}
