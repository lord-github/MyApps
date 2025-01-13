package com.bbstudios.ecash.optional;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.ecash.R;

public class statHolder extends RecyclerView.ViewHolder {
    TextView textView1,textView2,textView3,textView4;
    Button button;
    public statHolder(@NonNull View itemView) {
        super(itemView);
        textView1=itemView.findViewById(R.id.ya1);
        textView2=itemView.findViewById(R.id.ya2);
        textView3=itemView.findViewById(R.id.ya3);
        textView4=itemView.findViewById(R.id.ya4);
        button=itemView.findViewById(R.id.bat1);

    }
}
