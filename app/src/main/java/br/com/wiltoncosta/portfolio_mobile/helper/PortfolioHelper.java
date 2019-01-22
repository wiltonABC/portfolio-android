package br.com.wiltoncosta.portfolio_mobile.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.com.wiltoncosta.portfolio_mobile.PortfolioActivity;
import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.model.Profile;

public class PortfolioHelper {

    TextView name;
    TextView mainActivity;
    ImageView image;
    ProgressBar progressBar;
    RelativeLayout layoutLoading;

    FragmentManager fragmentManager;

    public PortfolioHelper (PortfolioActivity portfolioActivity) {

        this.name = portfolioActivity.findViewById(R.id.name);
        this.mainActivity = portfolioActivity.findViewById(R.id.mainActivity);
        this.image = portfolioActivity.findViewById(R.id.profileImage);
        this.fragmentManager =  portfolioActivity.getSupportFragmentManager();
        this.progressBar = portfolioActivity.findViewById(R.id.progressBar);
        this.layoutLoading = portfolioActivity.findViewById(R.id.layoutLoading);
    }

    public void updateProfile(Profile profile) {
        this.name.setText(profile.getShortName());
        this.mainActivity.setText(profile.getMainActivity());
    }

    public ImageView getImage() {
        return image;
    }

    public void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction tx = fragmentManager.beginTransaction();

        tx.replace(R.id.contentPlaceHolder, fragment, tag);

        tx.addToBackStack(null);

        tx.commit();

    }

    public ProgressBar getProgressBar() {
        return this.progressBar;
    }

    public RelativeLayout getLayoutLoading() {
        return this.layoutLoading;
    }

}
