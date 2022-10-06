package com.slockey.fantasticoctopotato.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpResponse;

public class WebClientStreamingService {

    private final WebClientStreamingConnection webClientStreamingConnection;
    private HttpResponse connection;
    private BufferedReader bufferedReader;
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;

    public WebClientStreamingService() {
        webClientStreamingConnection = new WebClientStreamingConnection();
    }

    public BufferedReader connectToStream() throws Exception {
        if (connection == null) {
            try {
                connection = webClientStreamingConnection.createConnection();
                int responseCode = connection.getStatusLine().getStatusCode();
                if (responseCode >= 200 && responseCode <= 299) {
                    inputStream = connection.getEntity().getContent();
                    inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    bufferedReader = new BufferedReader(inputStreamReader);
                    return bufferedReader;
                } else {
                    System.out.printf("Unable to stream data -> code: {} msg: {}%n", responseCode,
                            connection.getEntity());
                    throw new Exception(
                            "Response code :: " + responseCode + " Response msg :: " + connection.getEntity());
                }
            } catch (Exception e) {
                System.out.println(
                        "IOException occurred while getting inputStream from api connection!!");
                throw new Exception(
                        "IOException occurred while getting inputStream " + "from api connection!!",
                        e);
            }
        }
        return bufferedReader;
    }

    public String getStreamMessage(BufferedReader reader) {
        try {
            String message =  reader.readLine();
            if (connection == null){
                System.out.println("IOException occurred while reading api bufferedReader");
            }
            if (message != null) {
                System.out.println(String.format("successfully read the messages {}" + message));
                return message;
            } else{
                return "";
            }
        } catch (IOException e) {
            System.out.println("IOException occurred while reading api bufferedReader");
        }
        return null;
    }

    public void closeStream() throws Exception {

        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            // reset connection
            this.connection = null;
            this.inputStreamReader = null;
            this.inputStream = null;
        } catch (IOException ex) {
            System.out.println("IOException occurred while closing api bufferedReader");
            throw new Exception(
                    "IOException occurred while closing " + "api bufferedReader", ex);
        } catch (Exception ex) {
            System.out.println(String.format("exception occurred while closing stream {}", ex.getMessage()));
        }
    }

}
