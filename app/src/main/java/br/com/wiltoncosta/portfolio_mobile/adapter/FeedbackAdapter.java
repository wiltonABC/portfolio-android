package br.com.wiltoncosta.portfolio_mobile.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.model.Feedback;

public class FeedbackAdapter extends BaseAdapter {

    private List<Feedback> feedbacks;
    private Context context;

    public FeedbackAdapter(List<Feedback> feedbacks, Context context) {
        this.feedbacks = feedbacks;
        this.context = context;
    }


    @Override
    public int getCount() {
        return feedbacks.size();
    }

    @Override
    public Feedback getItem(int position) {
        return feedbacks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return feedbacks.get(position).getIdFeedback();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_feedback, null);

            holder.textViewFeedback = convertView.findViewById(R.id.textFeedback);
            holder.textViewAuthor = convertView.findViewById(R.id.textAuthorCompany);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Feedback feedback = feedbacks.get(position);

        holder.textViewFeedback.setText(feedback.getText());
        holder.textViewAuthor.setText(feedback.getAuthor() + " - " + feedback.getCompany());

        return convertView;

    }


    public static class ViewHolder {
        public TextView textViewFeedback;
        public TextView textViewAuthor;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
