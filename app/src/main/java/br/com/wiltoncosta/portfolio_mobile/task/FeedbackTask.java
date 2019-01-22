package br.com.wiltoncosta.portfolio_mobile.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.lang.ref.WeakReference;

import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.model.Feedback;
import br.com.wiltoncosta.portfolio_mobile.web.FeedbackWebClient;

public class FeedbackTask extends AsyncTask<Feedback, Void, Integer> {

    private WeakReference<Context> context;

    public FeedbackTask (Context context) {
        this.context = new WeakReference<>(context);
    }

    @Override
    protected Integer doInBackground(Feedback... feedbacks) {
        FeedbackWebClient feedbackWebClient = new FeedbackWebClient(context.get());
        int responseCode = -1;
        try {
            responseCode = feedbackWebClient.sendFeedback(feedbacks[0]);
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
                Toast.makeText(context,context.getString(R.string.errorSendingData, context.getString(R.string.feedback)), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context,context.getString(R.string.dataSuccessfullySent,context.getString(R.string.feedbackCapital)), Toast.LENGTH_LONG).show();
            }
        }
    }

}
