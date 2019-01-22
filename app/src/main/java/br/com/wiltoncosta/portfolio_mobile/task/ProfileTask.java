package br.com.wiltoncosta.portfolio_mobile.task;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.View;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.lang.ref.WeakReference;

import br.com.wiltoncosta.portfolio_mobile.AboutMeFragment;
import br.com.wiltoncosta.portfolio_mobile.PortfolioActivity;
import br.com.wiltoncosta.portfolio_mobile.helper.PortfolioHelper;
import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.model.Profile;
import br.com.wiltoncosta.portfolio_mobile.web.ProfileWebClient;

public class ProfileTask extends AsyncTask<Void, Void, Profile> {

    private WeakReference<Context> context;

    public ProfileTask (Context context) {
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
    protected Profile doInBackground(Void... params) {
        //Get profile data from backend
        ProfileWebClient profileWebClient = new ProfileWebClient(this.context.get());
        Profile profile = null;
        try {
            profile = profileWebClient.getProfileById(1L);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return profile;
    }

    @Override
    protected void onPostExecute(Profile profile) {
        Context context = this.context.get();
        if (context != null) {

            final PortfolioActivity portfolioActivity = (PortfolioActivity) context;

            //Could not get profile. Abort activity
            if (profile == null) {
                AlertDialog alert = new AlertDialog.Builder(context)
                        .setTitle(context.getString(R.string.errorDialogTitle))
                        .setMessage(context.getString(R.string.errorGettingData, context.getString(R.string.profile)))
                        .setNeutralButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                portfolioActivity.finish();
                            }
                        })
                        .show();

                return;

            }

            //Update activity
            PortfolioHelper helper = portfolioActivity.getHelper();
            helper.updateProfile(profile);

            //Update About Fragment
            AboutMeFragment aboutMeFragment = (AboutMeFragment) portfolioActivity.getSupportFragmentManager().findFragmentByTag("aboutFragment");
            if (aboutMeFragment.getHelper() != null)
                aboutMeFragment.getHelper().updateAbout(profile);

            //Download profile image from Url and update the ImageView
            Glide
                .with(context)
                .load(context.getString(R.string.backend_root_url) + profile.getImage())
                .into(helper.getImage());

            //Store returned profile in the Portfolio Activity for further use
            portfolioActivity.setProfile(profile);

            helper.getLayoutLoading().setVisibility(View.GONE);
        }
    }
}
