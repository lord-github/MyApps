package com.bbstudios.ecash.optional;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.ecash.R;

import java.util.List;

public class adminAdapter extends RecyclerView.Adapter<adminHolder> {
    Context context;
    List<adminitem> adminitems;

    public adminAdapter(Context context, List<adminitem> adminitems) {
        this.context = context;
        this.adminitems = adminitems;
    }

    @NonNull
    @Override
    public adminHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new adminHolder(LayoutInflater.from(context).inflate(R.layout.adminin,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull adminHolder holder, int position) {
    holder.namefio.setText(adminitems.get(position).yeryetady);
    holder.nzadaca.setText(adminitems.get(position).zaAdy);
    holder.relativeLayout.setBackgroundColor(context.getResources().getColor(adminitems.get(position).renk));
    holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.e("TAG", "onClick: "+adminitems.get(position).zaid+"->"+
                    adminitems.get(position).userid );
        gormek(adminitems.get(position).userid,adminitems.get(position).zaid);
        }
    });
    }

    @Override
    public int getItemCount() {
        return adminitems.size();
    }

    private void gormek(String uID,String zID) {
        Intent intent=new Intent("gorjek");
        intent.putExtra("uID",uID);
        intent.putExtra("zID",zID);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
