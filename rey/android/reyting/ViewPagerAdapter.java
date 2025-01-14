package com.bbstudios.reyting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    String id,ip;
Context context;
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, String id,String ip) {
        super(fragmentActivity);
        this.id = id;
        context=fragmentActivity;
        this.ip=ip;
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
      switch (position){
          case  0:
             Fragment fragment=new ballFragment();
              Bundle bundle = new Bundle();
              bundle.putString("fc", id);
              bundle.putString("ip", ip);
              fragment.setArguments(bundle);

              return fragment;
          case 1:
              Fragment lider=new liderFragment();
              Bundle bundle2 = new Bundle();
              bundle2.putString("fc", id);
              bundle2.putString("ip", ip);
              lider.setArguments(bundle2);
              return lider;
          case 2:
              Fragment f2=new kafFragment();
              Bundle bundle3 = new Bundle();
              bundle3.putString("fc", id);
              bundle3.putString("ip", ip);
              f2.setArguments(bundle3);
              return f2;
          default:
              Fragment fragment2=new ballFragment();
              Bundle bundle22 = new Bundle();
              bundle22.putString("fc", id);
              bundle22.putString("ip", ip);
              fragment2.setArguments(bundle22);

              return fragment2;

      }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
