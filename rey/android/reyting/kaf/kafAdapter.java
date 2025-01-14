package com.bbstudios.reyting.kaf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.reyting.R;

import java.util.List;

public class kafAdapter extends RecyclerView.Adapter<kafholder> {
   List<kafitem> list;
   Context context;

    public kafAdapter(List<kafitem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public kafholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new kafholder(LayoutInflater.from(context).inflate(R.layout.kaflayout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull kafholder holder, int position) {
            holder.kafady.setText(list.get(position).kady);
        holder.faady.setText(list.get(position).fady);
        holder.tbal.setText(list.get(position).bal);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
