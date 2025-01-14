package com.bbstudios.reyting.poisk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.reyting.R;
import com.google.android.material.transition.Hold;

import java.util.List;

public class poAdapter extends RecyclerView.Adapter<poholder> {
    List<poitem> poitems;
    Context context;

    public poAdapter(List<poitem> poitems, Context context) {
        this.poitems = poitems;
        this.context = context;
    }

    @NonNull
    @Override
    public poholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new poholder(LayoutInflater.from(context).inflate(R.layout.yekegoz,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull poholder holder, int position) {
        holder.textView.setText(poitems.get(position).ady);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, holder.textView.getText().toString(), Toast.LENGTH_SHORT).show();
                okla(poitems.get(position).id,holder.textView.getText().toString(),poitems.get(position).kk);
            }
        });
    }

    @Override
    public int getItemCount() {
        return poitems.size();
    }

    private void okla(String id,String ady ,String kk) {
        Intent intent=new Intent("custom-message5");
        intent.putExtra("tip","1");
        intent.putExtra("id",id);
        intent.putExtra("kk",kk);
        intent.putExtra("ady",ady);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }
}
