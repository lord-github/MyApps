package com.bbstudios.ecash.optional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.ecash.R;

import java.util.List;

public class ipAdapter extends RecyclerView.Adapter<ipholder> {
    Context context;
    List<yekeip> yekeips;

    public ipAdapter(Context context, List<yekeip> yekeips) {
        this.context = context;
        this.yekeips = yekeips;
    }

    @NonNull
    @Override
    public ipholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ipholder(LayoutInflater.from(context).inflate(R.layout.ipsanaw,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ipholder holder, int position) {
        holder.ipadd.setText(yekeips.get(position).ip);
    }

    @Override
    public int getItemCount() {
        return yekeips.size();
    }
}
