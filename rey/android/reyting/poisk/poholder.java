package com.bbstudios.reyting.poisk;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.reyting.R;

public class poholder extends RecyclerView.ViewHolder {
    TextView textView;
    public poholder(@NonNull View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.gozlegady);
    }
}
