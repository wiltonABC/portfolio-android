package br.com.wiltoncosta.portfolio_mobile.web;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WebClient {

    private static final int CONNECTION_TIMEOUT = 7000;
    private static final int READ_TIMEOUT = 7000;

    protected int postJsonData(String resourceUrl, String jsonData) throws IOException {

        URL url = new URL(resourceUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        int responseCode = -1;

        try {
            //Post method
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);

            //Sending data to WebAPI
            connection.setDoOutput(true);
            connection.setChunkedStreamingMode(0);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(jsonData);

            writer.flush();
            writer.close();
            os.close();

            responseCode = connection.getResponseCode();

        } finally {
            connection.disconnect();
        }

        return responseCode;

    }

    protected String getJsonData(String resourceUrl) throws IOException {

        URL url = new URL(resourceUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Accept","application/json");
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);

        connection.connect();

        Scanner scanner = null;
        String json = "";

        try {
            scanner = new Scanner(connection.getInputStream());

            while (scanner.hasNextLine()) {
                json += scanner.nextLine();
            }
        } finally {
            if (scanner != null)
                scanner.close();
        }

        return json;
    }

}
