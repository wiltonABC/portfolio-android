package br.com.wiltoncosta.portfolio_mobile.web;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Date;

import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.jsonadapter.DateLongFormatTypeAdapter;
import br.com.wiltoncosta.portfolio_mobile.model.Profile;

public class ProfileWebClient extends WebClient {

    private Context context;

    public ProfileWebClient(Context context) {

        this.context = context;

    }

    public Profile getProfileById(long id) throws java.io.IOException {

        String json = getJsonData(context.getString(R.string.backend_url) + "/profiles/" + id);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateLongFormatTypeAdapter())
                .create();

        Profile profile = gson.fromJson(json, Profile.class);

        return profile;

    }

}
