package br.com.wiltoncosta.portfolio_mobile.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.lang.ref.WeakReference;

import br.com.wiltoncosta.portfolio_mobile.model.Message;
import br.com.wiltoncosta.portfolio_mobile.web.ContactWebClient;

public class MessageTask extends AsyncTask<Message, Void, Integer> {

    private WeakReference<Context> context;

    public MessageTask (Context context) {
        this.context = new WeakReference<>(context);
    }

    @Override
    protected Integer doInBackground(Message... messages) {
        ContactWebClient contactWebClient = new ContactWebClient(context.get());
        int responseCode = -1;
        try {
            responseCode = contactWebClient.sendMessage(messages[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseCode;
    }

    @Override
    protected void onPostExecute(Integer responseCode) {
        if (this.context.get() != null) {
            if (responseCode != 201) {
                Toast.makeText(this.context.get(),"Error sending message!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this.context.get(),"Message successfully sent!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
