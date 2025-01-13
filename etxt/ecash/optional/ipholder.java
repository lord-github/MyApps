package com.bbstudios.ecash.optional;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.ecash.R;

public class ipholder extends RecyclerView.ViewHolder {
    TextView ipadd;
    public ipholder(@NonNull View itemView) {
        super(itemView);
        ipadd=itemView.findViewById(R.id.yekeip);
    }
}
