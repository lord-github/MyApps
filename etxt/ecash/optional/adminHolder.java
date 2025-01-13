package com.bbstudios.ecash.optional;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.ecash.R;

public class adminHolder extends RecyclerView.ViewHolder {
    RelativeLayout relativeLayout;
    TextView nzadaca,namefio;
    public adminHolder(@NonNull View itemView) {
        super(itemView);
        relativeLayout=itemView.findViewById(R.id.zaID);
        nzadaca=itemView.findViewById(R.id.nzada);
        namefio=itemView.findViewById(R.id.nfio);
    }
}
