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
import java.net.URL;
import java.util.Date;
import java.util.List;

import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.jsonadapter.DateLongFormatTypeAdapter;
import br.com.wiltoncosta.portfolio_mobile.model.Feedback;

public class FeedbackWebClient extends WebClient {

    private Context context;

    public FeedbackWebClient(Context context) {

        this.context = context;

    }

    public int sendFeedback(Feedback feedback) throws IOException {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateLongFormatTypeAdapter())
                .create();

        String json = gson.toJson(feedback);


        return postJsonData(context.getString(R.string.backend_url) + "/feedbacks", json);
    }

    public List<Feedback> getFeedbacksByProfileId(long id) throws IOException {

        String json = getJsonData(context.getString(R.string.backend_url) + "/profiles/" + id + "/feedbacks");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateLongFormatTypeAdapter())
                .create();

        List<Feedback> feedbacks = gson.fromJson(json, new TypeToken<List<Feedback>>(){}.getType());

        return feedbacks;
    }

}
