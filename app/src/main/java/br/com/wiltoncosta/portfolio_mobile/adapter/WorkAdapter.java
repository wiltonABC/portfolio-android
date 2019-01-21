package br.com.wiltoncosta.portfolio_mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.model.WorkDone;

public class WorkAdapter extends BaseAdapter {

    private List<WorkDone> workList;
    private Context context;

    public WorkAdapter(Context context, List<WorkDone> workList) {
        this.workList = workList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.workList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.workList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.workList.get(position).getIdWorkDone();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_work, null);

            holder.imageView = convertView.findViewById(R.id.workImage);
            holder.textViewName = convertView.findViewById(R.id.textWork);
            holder.textViewDescription = convertView.findViewById(R.id.textWorkDescription);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        WorkDone workDone = workList.get(position);

        holder.textViewName.setText(workDone.getName());
        holder.textViewDescription.setText(workDone.getDescription());

        Glide
            .with(this.context)
            .load(this.context.getString(R.string.backend_root_url) + workDone.getImage())
            .into(holder.imageView);

        return convertView;
    }

    public static class ViewHolder {
        public ImageView imageView;
        public TextView textViewName;
        public TextView textViewDescription;
    }

    public void setWorkList(List<WorkDone> workDoneList) {
        this.workList = workDoneList;
    }
}
