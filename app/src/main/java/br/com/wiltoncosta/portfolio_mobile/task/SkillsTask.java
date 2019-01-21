package br.com.wiltoncosta.portfolio_mobile.task;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import br.com.wiltoncosta.portfolio_mobile.PortfolioActivity;
import br.com.wiltoncosta.portfolio_mobile.PortfolioHelper;
import br.com.wiltoncosta.portfolio_mobile.SkillsFragment;
import br.com.wiltoncosta.portfolio_mobile.datahandler.SkillsDataHandler;
import br.com.wiltoncosta.portfolio_mobile.model.Skill;
import br.com.wiltoncosta.portfolio_mobile.web.SkillsWebClient;

public class SkillsTask extends AsyncTask<Void, Void, List<Skill>> {

    private WeakReference<Context> context;

    public SkillsTask(Context context) {
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
    protected List<Skill> doInBackground(Void... voids) {
        //Get profile skills data from backend
        SkillsWebClient skillsWebClient = new SkillsWebClient(this.context.get());
        List<Skill> skills = null;
        try {
            skills = skillsWebClient.getSkillsByProfileId(1L);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return skills;
    }

    @Override
    protected void onPostExecute(List<Skill> skills) {
        if (this.context.get() != null) {
            final PortfolioActivity portfolioActivity = (PortfolioActivity) context.get();


            //Could not get profile. Abort application
            if (skills == null) {
                AlertDialog alert = new AlertDialog.Builder(this.context.get())
                        .setTitle("Application Error")
                        .setMessage("Error getting skills data! Please, check your internet connection!")
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
            SkillsFragment skillsFragment = (SkillsFragment) portfolioActivity.getSupportFragmentManager().findFragmentByTag("skillsFragment");
            skillsFragment.getHelper().updateSkills(SkillsDataHandler.getCategoriesAndSkillsList(skills));

            portfolioActivity.getHelper().getLayoutLoading().setVisibility(View.GONE);
        }
    }
}
