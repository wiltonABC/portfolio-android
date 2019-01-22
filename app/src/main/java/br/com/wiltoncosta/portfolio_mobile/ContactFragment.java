package br.com.wiltoncosta.portfolio_mobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.wiltoncosta.portfolio_mobile.helper.ContactHelper;

public class ContactFragment extends Fragment {

    private ContactHelper helper;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        helper = new ContactHelper(this.getContext(), view);

        return view;
    }

}
