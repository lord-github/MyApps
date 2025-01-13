package com.bbstudios.ecash.optional;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.ecash.R;

public class sHolder extends RecyclerView.ViewHolder {
    RelativeLayout baa;
    TextView name_z,sene;

    public sHolder(@NonNull View itemView) {
        super(itemView);
        baa=itemView.findViewById(R.id.renkmeydan);
        name_z=itemView.findViewById(R.id.namez);
        sene=itemView.findViewById(R.id.dates);
    }
}
