package br.com.wiltoncosta.portfolio_mobile.web;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.jsonadapter.DateLongFormatTypeAdapter;
import br.com.wiltoncosta.portfolio_mobile.model.Message;

public class ContactWebClient {

    private Context context;

    public ContactWebClient(Context context) {

        this.context = context;

    }

    public int sendMessage(Message message) throws IOException {
        URL url = new URL(context.getString(R.string.backend_url) + "/messages");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        int responseCode = -1;

        try {
            //Post method
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setConnectTimeout(7000);
            connection.setReadTimeout(7000);

            //Sending data to WebAPI
            connection.setDoOutput(true);
            connection.setChunkedStreamingMode(0);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Date.class, new DateLongFormatTypeAdapter())
                    .create();

            String json = gson.toJson(message);

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
}
