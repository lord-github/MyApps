package com.bbstudios.ecash.optional;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.ecash.R;

import java.util.List;

public class jynsAdapter extends RecyclerView.Adapter<jynsHolder> {
    Context context;
    List<jyns> jyns;
    int row_index=0;


    public jynsAdapter(Context context, List<jyns> jyns) {
        this.context = context;
        this.jyns = jyns;
    }
    @NonNull
    @Override
    public jynsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new jynsHolder(LayoutInflater.from(context).inflate(R.layout.jyns,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull jynsHolder holder, int position) {
        String ady=jyns.get(position).yazgy;
        holder.textView.setText(ady);
        if (ady.contains("kek")){
            holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.male));
        } else {
            holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.female_user_52px));
        }
        holder.saylalayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;
                notifyDataSetChanged();
            }
        });

        if (row_index==position){
            jynsid(String.valueOf(position));
            holder.saylalayout.setBackgroundColor(context.getColor(R.color.renk2));
        }else {
            holder.saylalayout.setBackgroundColor(context.getColor(R.color.white));
        }
    }
    private void jynsid(String valueOf) {
        Intent intent=new Intent("prenod");
        intent.putExtra("jynsid",valueOf);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }


    @Override
    public int getItemCount() {
        return 0;
    }
}
