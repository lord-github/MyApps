package com.bbstudios.ecash.optional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.ecash.R;

import java.util.List;

public class statAdapter extends RecyclerView.Adapter<statHolder> {
    Context context;
    List<stat1> stat1s;

    public statAdapter(Context context, List<stat1> stat1s) {
        this.context = context;
        this.stat1s = stat1s;
    }
    @NonNull
    @Override
    public statHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new statHolder(LayoutInflater.from(context).inflate(R.layout.stati,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull statHolder holder, int position) {
        if (stat1s.get(position).tip.equals("0")){
            holder.button.setVisibility(View.INVISIBLE);
        }
        holder.textView1.setText(stat1s.get(position).ya1);
        holder.textView2.setText(stat1s.get(position).ya2);
        holder.textView3.setText(stat1s.get(position).ya3);
        holder.textView4.setText(stat1s.get(position).ya4);

    }

    @Override
    public int getItemCount() {
        return stat1s.size();
    }
}
