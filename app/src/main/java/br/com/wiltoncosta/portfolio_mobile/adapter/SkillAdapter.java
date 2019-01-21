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
import br.com.wiltoncosta.portfolio_mobile.model.Skill;
import br.com.wiltoncosta.portfolio_mobile.model.SkillCategory;

public class SkillAdapter extends BaseAdapter {

    public static final int ITEM_TYPE_SKILL_CATEGORY = 0;
    public static final int ITEM_TYPE_SKILL = 1;

    private Context context;
    private List skillList;

    public SkillAdapter(Context context, List skillList) {
        this.context = context;
        this.skillList = skillList;
    }

    @Override
    public int getCount() {
        return skillList.size();
    }

    @Override
    public Object getItem(int position) {
        return skillList.get(position);
    }

    @Override
    public long getItemId(int position) {
        long id;
        if (getItemViewType(position) == ITEM_TYPE_SKILL_CATEGORY)
            id = ((SkillCategory)skillList.get(position)).getIdSkillCategory();
        else
            id = ((Skill)skillList.get(position)).getIdSkill();

        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Get layout inflater
        LayoutInflater inflater = LayoutInflater.from(context);

        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();

            switch (getItemViewType(position)) {
                case ITEM_TYPE_SKILL_CATEGORY:
                    //Category Layout
                    convertView = inflater.inflate(R.layout.list_item_skill_category, null);
                    holder.textView = convertView.findViewById(R.id.skillCategoryName);
                    holder.imageView = convertView.findViewById(R.id.categoryImage);
                    break;
                case ITEM_TYPE_SKILL:
                    //Skill Layout
                    convertView = inflater.inflate(R.layout.list_item_skill, null);
                    holder.textView = convertView.findViewById(R.id.skillName);
                    break;

            }
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        switch (getItemViewType(position)) {
            case ITEM_TYPE_SKILL_CATEGORY:
                //Category Layout
                SkillCategory skillCategory = (SkillCategory)skillList.get(position);
                holder.textView.setText(skillCategory.getName());

                Glide
                        .with(this.context)
                        .load(this.context.getString(R.string.backend_root_url) + skillCategory.getImage())
                        .into(holder.imageView);

                break;
            case ITEM_TYPE_SKILL:
                //Skill Layout
                Skill skill = (Skill)skillList.get(position);
                holder.textView.setText(skill.getName());
                break;

        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return skillList.get(position) instanceof SkillCategory ? ITEM_TYPE_SKILL_CATEGORY : ITEM_TYPE_SKILL;
    }

    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    public void setSkillList(List skillList) {
        this.skillList = skillList;
    }
}
