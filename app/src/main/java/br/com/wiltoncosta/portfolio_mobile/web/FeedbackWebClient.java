package br.com.wiltoncosta.portfolio_mobile.web;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.jsonadapter.DateLongFormatTypeAdapter;
import br.com.wiltoncosta.portfolio_mobile.model.Feedback;

public class FeedbackWebClient {

    private Context context;

    public FeedbackWebClient(Context context) {

        this.context = context;

    }

    public int sendFeedback(Feedback feedback) throws IOException {
        URL url = new URL(context.getString(R.string.backend_url) + "/feedbacks");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        int responseCode = -1;

        try {
            //Post method
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json");

            //Sending data to WebAPI
            connection.setDoOutput(true);
            connection.setChunkedStreamingMode(0);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Date.class, new DateLongFormatTypeAdapter())
                    .create();

            String json = gson.toJson(feedback);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(json);

            writer.flush();
            writer.close();
            os.close();

            responseCode = connection.getResponseCode();

        } finally {
            connection.disconnect();
        }

        return responseCode;
    }

    public List<Feedback> getFeedbacksByProfileId(long id) throws IOException {

        URL url = new URL(context.getString(R.string.backend_url) + "/profiles/" + id + "/feedbacks");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Accept","application/json");
        connection.setConnectTimeout(7000);
        connection.setReadTimeout(7000);

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

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateLongFormatTypeAdapter())
                .create();

        List<Feedback> feedbacks = gson.fromJson(json, new TypeToken<List<Feedback>>(){}.getType());

        return feedbacks;
    }

}
