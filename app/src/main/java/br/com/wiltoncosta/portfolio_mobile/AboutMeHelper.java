package br.com.wiltoncosta.portfolio_mobile;

import android.view.View;
import android.widget.TextView;

import br.com.wiltoncosta.portfolio_mobile.model.Profile;

public class AboutMeHelper {

    TextView about;

    public AboutMeHelper(View view) {

        this.about = view.findViewById(R.id.about);

    }

    public void updateAbout (Profile profile) {
        this.about.setText(profile.getAbout());
    }

}
