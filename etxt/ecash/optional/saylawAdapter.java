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

public class saylawAdapter extends RecyclerView.Adapter<saylawholder>{
    Context context;
    List<saylawbir> saylawbirs;
    int row_index=0;

    public saylawAdapter(Context context, List<saylawbir> saylawbirs) {
        this.context = context;
        this.saylawbirs = saylawbirs;
    }

    @NonNull
    @Override
    public saylawholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new saylawholder(LayoutInflater.from(context).inflate(R.layout.saybir,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull saylawholder holder, int position) {
        if (saylawbirs.get(position).id.equals("1"))
        {
            holder.yaz.setText("Men ýerine ýetiriji");
            holder.surr.setImageDrawable(context.getResources().getDrawable(R.drawable.client));
        }
        else
        {

            holder.yaz.setText("Men iş beriji");
            holder.surr.setImageDrawable(context.getResources().getDrawable(R.drawable.author));
        }
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;
                notifyDataSetChanged();
            }
        });
        if (row_index==position){
            okla2(String.valueOf(position));
            holder.relativeLayout.setBackgroundColor(context.getColor(R.color.renk2));
        } else {
            holder.relativeLayout.setBackgroundColor(context.getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return saylawbirs.size();
    }
    private void okla2(String valueOf) {
        Intent intent=new Intent("ktoya");
        intent.putExtra("tipid",valueOf);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
