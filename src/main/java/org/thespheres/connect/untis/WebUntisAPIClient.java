/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thespheres.connect.untis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.thespheres.connect.untis.model.APIRequest;
import org.thespheres.connect.untis.model.APIRequest.TimetableType;
import org.thespheres.connect.untis.model.BaseAPIResponse;
import org.thespheres.connect.untis.model.EnumOrdinalSerializer;

/**
 *
 * @author boris.heithecker@gmx.net
 */
public class WebUntisAPIClient {

    private static final Logger logger = Logger.getLogger("web-untis-api-client");
    private final Gson gson;
    private final CloseableHttpClient httpclient;
    private final String href;
    private final BasicCookieStore cookieStore;
    private final BasicHttpContext httpContext;

    public WebUntisAPIClient(final String href) {
        this.href = href;
        final GsonBuilder gb = new GsonBuilder();
        this.gson = gb
                .registerTypeAdapter(TimetableType.class, new EnumOrdinalSerializer<>())
                .create();
        this.cookieStore = new BasicCookieStore();
        this.httpContext = new BasicHttpContext();
        this.httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
        this.httpclient = buildHttpClient();
    }

    public <R extends BaseAPIResponse> R postRequest(final APIRequest req, final Class<R> returnType) throws IOException {
        return (R) postRequestImpl(req, returnType, false);
    }

    public String readRequest(final APIRequest req) throws IOException {
        return (String) postRequestImpl(req, null, true);
    }

    private <R extends BaseAPIResponse> Object postRequestImpl(final APIRequest req, final Class<R> returnType, final boolean raw) throws IOException {
        final HttpPost httpPost = new HttpPost(href);
        final String reqString = gson.toJson(req); //jsonb.toJson(req);
        final StringEntity entity = new StringEntity(reqString, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        final Object response;
        final long start = System.currentTimeMillis();
        try (final CloseableHttpResponse resp = httpclient.execute(httpPost, httpContext)) {
            final long dur = System.currentTimeMillis() - start;
            final HttpEntity re = resp.getEntity();
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.
            //System.out.println(rr.toString());
            if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                resp.close();
                throw new IOException("Unexpected response status: " + resp.getStatusLine() + " at: ");
            }
            if (!raw) {
                try (final InputStream is = re.getContent(); final Reader r = new InputStreamReader(is)) {
                    response = gson.fromJson(r, returnType);
                    Optional.ofNullable(((R) response).getError())
                            .ifPresent(e -> logger.log(Level.WARNING, e.toString()));
                }
            } else {
                response = EntityUtils.toString(re);
            }
            EntityUtils.consume(re);
            logger.log(Level.FINE, "Time: {0}", dur);
        } catch (final Exception ex) {
            if (ex instanceof IOException) {
                throw (IOException) ex;
            }
            throw new IOException(ex);
        }
        return response;
    }

    static CloseableHttpClient buildHttpClient() {
        final HttpClientBuilder builder = HttpClients.custom();
        return builder.build();
    }

}
