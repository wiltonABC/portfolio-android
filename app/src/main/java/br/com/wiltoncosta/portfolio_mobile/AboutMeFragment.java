package br.com.wiltoncosta.portfolio_mobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.wiltoncosta.portfolio_mobile.helper.AboutMeHelper;
import br.com.wiltoncosta.portfolio_mobile.model.Profile;


public class AboutMeFragment extends Fragment {

    private AboutMeHelper helper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_me, container, false);

        this.helper = new AboutMeHelper(view);

        Profile profile = ((PortfolioActivity)getActivity()).getProfile();

        if (profile != null) {
            helper.updateAbout(profile);
        }

        return view;
    }

    public AboutMeHelper getHelper() {
        return helper;
    }

}
