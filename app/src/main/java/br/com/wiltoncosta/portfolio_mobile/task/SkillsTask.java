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
import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.helper.PortfolioHelper;
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
        Context context = this.context.get();
        if (context != null) {
            final PortfolioActivity portfolioActivity = (PortfolioActivity) context;


            //Could not get skills. Abort activity
            if (skills == null) {
                AlertDialog alert = new AlertDialog.Builder(context)
                        .setTitle(context.getString(R.string.errorDialogTitle))
                        .setMessage(context.getString(R.string.errorGettingData, context.getString(R.string.skills)))
                        .setNeutralButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
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
