package com.bbstudios.reyting.kaf;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.reyting.R;

public class kafholder extends RecyclerView.ViewHolder {
    TextView kafady,faady,tbal;
    public kafholder(@NonNull View itemView) {
        super(itemView);
        kafady=itemView.findViewById(R.id.sutun1);
        faady=itemView.findViewById(R.id.sutun2);
        tbal=itemView.findViewById(R.id.sutun3);
    }
}
