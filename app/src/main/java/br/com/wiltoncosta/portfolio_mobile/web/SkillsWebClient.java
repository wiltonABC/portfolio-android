package br.com.wiltoncosta.portfolio_mobile.web;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.Date;
import java.util.List;

import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.jsonadapter.DateLongFormatTypeAdapter;
import br.com.wiltoncosta.portfolio_mobile.model.Skill;

public class SkillsWebClient extends WebClient {

    private Context context;

    public SkillsWebClient(Context context) {

        this.context = context;

    }

    public List<Skill> getSkillsByProfileId(long id) throws java.io.IOException {

        String json = getJsonData(context.getString(R.string.backend_url) + "/profiles/" + id + "/skills");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateLongFormatTypeAdapter())
                .create();

        List<Skill> skills = gson.fromJson(json, new TypeToken<List<Skill>>(){}.getType());

        return skills;

    }
}
