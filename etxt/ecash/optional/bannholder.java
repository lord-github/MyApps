package com.bbstudios.ecash.optional;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.ecash.R;

public class bannholder extends RecyclerView.ViewHolder {
    RelativeLayout banrel;
    ImageView banimage;
    TextView bantext;
    public bannholder(@NonNull View itemView) {
        super(itemView);
        banrel=itemView.findViewById(R.id.bannerback);
        banimage=itemView.findViewById(R.id.slidersurat);
        bantext=itemView.findViewById(R.id.yazgy2);
    }
}
