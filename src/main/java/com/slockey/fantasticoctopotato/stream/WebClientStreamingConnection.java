package com.slockey.fantasticoctopotato.stream;

import java.io.IOException;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tomcat.util.codec.binary.Base64;

public class WebClientStreamingConnection {

    private static final String BASIC = "Basic ";
    private static final String COLON = ":";


    public HttpResponse createConnection() throws Exception {

        try {
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .setSocketTimeout(5000)
                    .setCookieSpec(CookieSpecs.STANDARD)
                    .build();

            CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config)
                    .build();

            HttpGet getRequest = new HttpGet("http://localhost:8080/api/otherPotatoStream");
            getRequest.setHeader(HttpHeaders.AUTHORIZATION, createAuthHeader());

            return client.execute(getRequest);
        } catch (IOException e) {
            System.out.println(String.format("IOException occurred while reconnecting Stream :: EXc msg :: {}", e.getMessage()));
            throw new Exception("api connection error :: ", e);
        }
        // below lines added for testing purpose to debug we will remove once we find the root cause
        catch (Exception ex) {
            throw new Exception("exception occurred while connecting", ex);
        }
    }

    private String createAuthHeader() {
        String authToken = "user" + COLON + "pass";
        return BASIC + Base64.encodeBase64String(authToken.getBytes());
    }
}
