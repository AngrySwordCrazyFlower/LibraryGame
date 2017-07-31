package com.example.crazyflower.librarygame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CrazyFlower on 2017/7/24.
 */

public class RankAdapter extends ArrayAdapter<Rank> {

    private int resourceId;

    public RankAdapter(Context context, int textViewResourceId, List<Rank> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Rank rank = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView rankNum = (TextView) view.findViewById(R.id.rank_num);
        TextView rankName = (TextView) view.findViewById(R.id.rank_name);
        TextView rankGrade = (TextView) view.findViewById(R.id.rank_grade);
        rankNum.setText(rank.getNum() + " . ");
        rankName.setText(rank.getName());
        rankGrade.setText(rank.getGrade() + " ");
        return view;
    }
}
