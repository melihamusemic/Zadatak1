package com.example.zadatak1.BoxHandling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.zadatak1.R;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Box> mData;
    private LayoutInflater mInflater;

    MyRecyclerViewAdapter(Context context, List<Box> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.box_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = mData.get(position).getName();
        int count = mData.get(position).getCount();
        int width = mData.get(position).getWidth();
        int height = mData.get(position).getHeight();

        LinearLayout wrap = holder.view.findViewById(R.id.wrap_content);
        ViewGroup.LayoutParams layoutParams = wrap.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        wrap.setLayoutParams(layoutParams);

        holder.nameView.setText("Name: " + name);
        holder.countView.setText("Count: " + String.valueOf(count));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    Box getItem(int id) {
        return mData.get(id);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView nameView;
        TextView countView;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.box_item);
            nameView = view.findViewById(R.id.box_name);
            countView = view.findViewById(R.id.box_count);
        }
    }
}
