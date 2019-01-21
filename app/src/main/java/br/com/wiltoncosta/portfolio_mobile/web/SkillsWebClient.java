package br.com.wiltoncosta.portfolio_mobile.web;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.jsonadapter.DateLongFormatTypeAdapter;
import br.com.wiltoncosta.portfolio_mobile.model.Skill;

public class SkillsWebClient {

    private Context context;

    public SkillsWebClient(Context context) {

        this.context = context;

    }

    public List<Skill> getSkillsByProfileId(long id) throws MalformedURLException, java.io.IOException {

        URL url = new URL(context.getString(R.string.backend_url) + "/profiles/" + id + "/skills");
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

        List<Skill> skills = gson.fromJson(json, new TypeToken<List<Skill>>(){}.getType());

        return skills;

    }
}
