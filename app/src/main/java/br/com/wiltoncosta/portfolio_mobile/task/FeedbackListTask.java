package br.com.wiltoncosta.portfolio_mobile.task;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import br.com.wiltoncosta.portfolio_mobile.FeedbackFragment;
import br.com.wiltoncosta.portfolio_mobile.FeedbacksListFragment;
import br.com.wiltoncosta.portfolio_mobile.PortfolioActivity;
import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.model.Feedback;
import br.com.wiltoncosta.portfolio_mobile.web.FeedbackWebClient;

public class FeedbackListTask extends AsyncTask<Void, Void, List<Feedback>> {

    private WeakReference<Context> context;

    public FeedbackListTask(Context context) {
        this.context = new WeakReference<>(context);
    }

    @Override
    protected List<Feedback> doInBackground(Void... voids) {
        //Get profile feedbacks data from backend
        FeedbackWebClient feedbackWebClient = new FeedbackWebClient(this.context.get());
        List<Feedback> feedbacks = null;
        try {
            feedbacks = feedbackWebClient.getFeedbacksByProfileId(1L);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return feedbacks;
    }

    @Override
    protected void onPostExecute(List<Feedback> feedbacks) {
        if (context.get() != null) {
            PortfolioActivity portfolioActivity = (PortfolioActivity) context.get();

            //Update Feedbacks Fragment
            FeedbackFragment feedBackFragment = (FeedbackFragment) portfolioActivity.getSupportFragmentManager().findFragmentByTag("feedbackFragment");
            FeedbacksListFragment feedbackListFragment = (FeedbacksListFragment) feedBackFragment.getChildFragmentManager().findFragmentById(R.id.feedbacksListFragment);

            feedbackListFragment.updateFeedbacks(feedbacks);
        }
    }
}
