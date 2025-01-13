package com.bbstudios.ecash.optional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.ecash.R;

import java.util.List;

public class sAdapter extends RecyclerView.Adapter<sHolder> {
    Context context;
    List<statItem> statItems;

    public sAdapter(Context context, List<statItem> statItems) {
        this.context = context;
        this.statItems = statItems;
    }

    @NonNull
    @Override
    public sHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new sHolder(LayoutInflater.from(context).inflate(R.layout.statistika,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull sHolder holder, int position) {
        String nam=statItems.get(position).ady;
        String sene=statItems.get(position).sene;
        Integer mycolor=statItems.get(position).backra;
        holder.name_z.setText(nam);
        holder.sene.setText(sene);
        holder.baa.setBackgroundColor(context.getResources().getColor(mycolor));
    }

    @Override
    public int getItemCount() {
        return statItems.size();
    }
}
