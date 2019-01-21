package br.com.wiltoncosta.portfolio_mobile.task;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.View;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import br.com.wiltoncosta.portfolio_mobile.PortfolioActivity;
import br.com.wiltoncosta.portfolio_mobile.PortfolioHelper;
import br.com.wiltoncosta.portfolio_mobile.WorkFragment;
import br.com.wiltoncosta.portfolio_mobile.WorkHelper;
import br.com.wiltoncosta.portfolio_mobile.model.WorkDone;
import br.com.wiltoncosta.portfolio_mobile.web.WorkWebClient;

public class WorkTask extends AsyncTask<Void, Void, List<WorkDone>> {

    private WeakReference<Context> context;

    public WorkTask(Context context) {
        this.context = new WeakReference<>(context);
    }

    @Override
    protected void onPreExecute() {
        if (this.context.get() != null) {
            PortfolioActivity portfolioActivity = (PortfolioActivity) this.context.get();
            PortfolioHelper helper = portfolioActivity.getHelper();

            helper.getLayoutLoading().setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected List<WorkDone> doInBackground(Void... voids) {
        //Get profile work data from backend
        WorkWebClient workWebClient = new WorkWebClient(this.context.get());
        List<WorkDone> workList = null;
        try {
            workList = workWebClient.getWorkByProfileId(1L);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return workList;
    }

    @Override
    protected void onPostExecute(List<WorkDone> workDoneList) {
        if (context.get() != null) {
            final PortfolioActivity portfolioActivity = (PortfolioActivity) context.get();

            //Could not get profile. Abort application
            if (workDoneList == null) {
                AlertDialog alert = new AlertDialog.Builder(this.context.get())
                        .setTitle("Application Error")
                        .setMessage("Error getting work data! Please, check your internet connection!")
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                portfolioActivity.finish();
                            }
                        })
                        .show();

                return;

            }

            //Update Skills Fragment
            WorkFragment workFragment = (WorkFragment) portfolioActivity.getSupportFragmentManager().findFragmentByTag("workFragment");
            workFragment.getHelper().updateWorkDone(workDoneList);

            portfolioActivity.getHelper().getLayoutLoading().setVisibility(View.GONE);
        }
    }
}
