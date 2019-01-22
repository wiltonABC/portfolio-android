package br.com.wiltoncosta.portfolio_mobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.wiltoncosta.portfolio_mobile.helper.FeedbackHelper;


public class FeedbackFragment extends Fragment {

    private FeedbackHelper helper;

    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        helper = new FeedbackHelper(this.getContext(), view);

        return view;
    }

}
