package com.bbstudios.ecash.optional;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.ecash.R;

public class jynsHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView textView;
    RelativeLayout saylalayout;
    public jynsHolder(@NonNull View itemView) {
        super(itemView);
        saylalayout=itemView.findViewById(R.id.saylalayout);
        textView=itemView.findViewById(R.id.yazgy2);
        imageView=itemView.findViewById(R.id.surat2);
    }
}
