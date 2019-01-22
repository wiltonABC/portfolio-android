package br.com.wiltoncosta.portfolio_mobile.helper;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.adapter.SkillAdapter;

public class SkillsHelper {

    private ListView skillsListView;
    private Context context;
    private SkillAdapter skillAdapter;

    public SkillsHelper(Context context, View view) {
        this.context = context;
        this.skillsListView = view.findViewById(R.id.skillsList);
        this.skillAdapter = new SkillAdapter(context, new ArrayList());
        this.skillsListView.setAdapter(skillAdapter);
    }

    public void updateSkills(List skillsList) {
        skillAdapter.setSkillList(skillsList);
        skillAdapter.notifyDataSetChanged();
    }

}
