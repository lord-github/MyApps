package com.bbstudios.ecash.optional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.ecash.R;

import java.util.List;

public class banAdapter extends RecyclerView.Adapter<bannholder> {
    List<bann> banns;
    Context context;
    public banAdapter(List<bann> banns, Context context) {
        this.banns = banns;
        this.context = context;
    }

    @NonNull
    @Override
    public bannholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new bannholder(LayoutInflater.from(context).inflate(R.layout.slides,
                parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull bannholder holder, int position) {
        if (banns.get(position).id.equals("1")){
            holder.banimage.setImageDrawable(context.getResources().getDrawable(R.drawable.ban1));
            holder.banrel.setBackground(context.getResources().getDrawable(R.drawable.banner11));
            holder.bantext.setText(banns.get(position).yazgy);
        }

        if (banns.get(position).id.equals("2")){
            holder.banrel.setBackground(context.getResources().getDrawable(R.drawable.banner2));
            holder.banimage.setImageDrawable(context.getResources().getDrawable(R.drawable.ban2));
            holder.bantext.setText(banns.get(position).yazgy);
        }

        if (banns.get(position).id.equals("3")){
            holder.bantext.setText(banns.get(position).yazgy);
            holder.banimage.setImageDrawable(context.getResources().getDrawable(R.drawable.yokary));
            holder.banrel.setBackground(context.getResources().getDrawable(R.drawable.banner3));
        }


        if (banns.get(position).id.equals("4")){
            holder.bantext.setText(banns.get(position).yazgy);
            holder.banimage.setImageDrawable(context.getResources().getDrawable(R.drawable.lgg));
            holder.banrel.setBackground(context.getResources().getDrawable(R.drawable.bangok));
        }
    }

    @Override
    public int getItemCount() {
        return banns.size();
    }
}