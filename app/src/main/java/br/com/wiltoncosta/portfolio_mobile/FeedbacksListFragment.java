package br.com.wiltoncosta.portfolio_mobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.wiltoncosta.portfolio_mobile.adapter.FeedbackAdapter;
import br.com.wiltoncosta.portfolio_mobile.model.Feedback;
import br.com.wiltoncosta.portfolio_mobile.task.FeedbackListTask;

public class FeedbacksListFragment extends Fragment {

    private FeedbackAdapter feedbackAdapter;
    private ListView listView;

    public FeedbacksListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedbacks_list, container, false);

        this.feedbackAdapter = new FeedbackAdapter(new ArrayList<Feedback>(), this.getContext());
        this.listView = view.findViewById(R.id.listFeedbacks);

        listView.setAdapter(feedbackAdapter);

        FeedbackListTask feedbackListTask = new FeedbackListTask(this.getContext());
        feedbackListTask.execute();

        return view;
    }
    public void updateFeedbacks(List<Feedback> feedbacks) {
        feedbackAdapter.setFeedbacks(feedbacks);
        feedbackAdapter.notifyDataSetChanged();
    }


}
