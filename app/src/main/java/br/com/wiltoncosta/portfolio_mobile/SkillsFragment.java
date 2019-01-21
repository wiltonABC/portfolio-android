package br.com.wiltoncosta.portfolio_mobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.wiltoncosta.portfolio_mobile.task.SkillsTask;


public class SkillsFragment extends Fragment {

    private SkillsHelper helper;

    public SkillsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skills, container, false);

        helper = new SkillsHelper(this.getContext(),view);

        SkillsTask skillsTask = new SkillsTask(this.getContext());
        skillsTask.execute();

        return view;
    }


    public SkillsHelper getHelper() {
        return helper;
    }

}
