package br.com.wiltoncosta.portfolio_mobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.wiltoncosta.portfolio_mobile.helper.WorkHelper;
import br.com.wiltoncosta.portfolio_mobile.task.WorkTask;

public class WorkFragment extends Fragment {

    private WorkHelper helper;

    public WorkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_work, container, false);

        helper = new WorkHelper(this.getContext(), view);

        WorkTask workTask = new WorkTask(this.getContext());
        workTask.execute();

        return view;
    }

    public WorkHelper getHelper() {
        return this.helper;
    }

}
