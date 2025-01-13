package com.bbstudios.ecash.optional;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.ecash.R;

public class saylawholder extends RecyclerView.ViewHolder {
    TextView yaz;
    ImageView surr;
    RelativeLayout relativeLayout;
    public saylawholder(@NonNull View itemView) {
        super(itemView);
        surr=itemView.findViewById(R.id.surat1);
        relativeLayout=itemView.findViewById(R.id.rell);
        yaz=itemView.findViewById(R.id.yazgy1);
    }
}
