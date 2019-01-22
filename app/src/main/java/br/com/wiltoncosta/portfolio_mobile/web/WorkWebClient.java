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
import br.com.wiltoncosta.portfolio_mobile.model.WorkDone;

public class WorkWebClient extends WebClient {

    private Context context;

    public WorkWebClient(Context context) {

        this.context = context;

    }

    public List<WorkDone> getWorkByProfileId(long id) throws MalformedURLException, java.io.IOException {

        String json = getJsonData(context.getString(R.string.backend_url) + "/profiles/" + id + "/work-done");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateLongFormatTypeAdapter())
                .create();

        List<WorkDone> workList = gson.fromJson(json, new TypeToken<List<WorkDone>>(){}.getType());

        return workList;

    }

}
