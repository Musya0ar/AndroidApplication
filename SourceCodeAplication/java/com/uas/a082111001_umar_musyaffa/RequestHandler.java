package com.uas.a082111001_umar_musyaffa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RequestHandler {

    public String sendGet(String requestUrl) throws Exception {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set request method to GET
        connection.setRequestMethod("GET");

        // Get the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Close connection
        connection.disconnect();

        return response.toString();
    }

    public String sendPost(String requestUrl, Map<String, String> postDataParams) throws Exception {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set request method to POST
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Write data (if any)
        if (postDataParams != null && !postDataParams.isEmpty()) {
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, String> param : postDataParams.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(param.getKey());
                postData.append('=');
                postData.append(param.getValue());
            }
            byte[] postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8);
            connection.getOutputStream().write(postDataBytes);
        }

        // Get the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Close connection
        connection.disconnect();

        return response.toString();
    }
}
