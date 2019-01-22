package br.com.wiltoncosta.portfolio_mobile.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.lang.ref.WeakReference;

import br.com.wiltoncosta.portfolio_mobile.R;
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
        Context context = this.context.get();
        if (context != null) {
            if (responseCode != 201) {
                Toast.makeText(context,context.getString(R.string.errorSendingData,context.getString(R.string.message)), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context,context.getString(R.string.dataSuccessfullySent,context.getString(R.string.messageCapital)), Toast.LENGTH_LONG).show();
            }
        }
    }
}
