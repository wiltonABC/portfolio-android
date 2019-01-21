package br.com.wiltoncosta.portfolio_mobile.web;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.jsonadapter.DateLongFormatTypeAdapter;
import br.com.wiltoncosta.portfolio_mobile.model.Profile;

public class ProfileWebClient {

    private Context context;

    public ProfileWebClient(Context context) {

        this.context = context;

    }

    public Profile getProfileById(long id) throws MalformedURLException, java.io.IOException {

        URL url = new URL(context.getString(R.string.backend_url) + "/profiles/" + id);
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

        Profile profile = gson.fromJson(json, Profile.class);

        return profile;

    }

}
