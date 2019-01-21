package br.com.wiltoncosta.portfolio_mobile;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.wiltoncosta.portfolio_mobile.adapter.WorkAdapter;
import br.com.wiltoncosta.portfolio_mobile.model.WorkDone;

public class WorkHelper {

    private ListView workListView;
    private Context context;
    private WorkAdapter workAdapter;

    public WorkHelper(Context context, View view) {
        this.context = context;
        this.workListView = view.findViewById(R.id.workList);
        this.workAdapter = new WorkAdapter(context, new ArrayList<WorkDone>());
        this.workListView.setAdapter(workAdapter);
    }

    public void updateWorkDone(List<WorkDone> workDoneList) {
        workAdapter.setWorkList(workDoneList);
        workAdapter.notifyDataSetChanged();
    }

}
