package com.bbstudios.ecash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;
import com.bbstudios.ecash.optional.jynsAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.bbstudios.ecash.optional.jyns;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.ecash.optional.saylawAdapter;
import com.bbstudios.ecash.optional.saylawbir;

import java.util.ArrayList;
import java.util.List;

public class reg extends AppCompatActivity {
    RecyclerView recyclerView,jynsy;
    List<saylawbir> saylawbirs;
    List<jyns> jynsList;
    String usertip,jyns,faa,logg,pass,phone;
    EditText faapole,loggpole,passpolr,passrepeatpole,phonepole;
    Button regist;
    WebView sendReq;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reg);
        saylawbirs=new ArrayList<>();
        Intent intent=getIntent();
        url=intent.getStringExtra("ip");
        faapole=findViewById(R.id.faa);
        loggpole=findViewById(R.id.login);
        passpolr=findViewById(R.id.password);
        passrepeatpole=findViewById(R.id.repeatpassword);
        phonepole=findViewById(R.id.phone);
        regist=findViewById(R.id.okbutton);
        sendReq=findViewById(R.id.webinsert);
        jynsList=new ArrayList<>();
        jynsList.add(new jyns("Erkek"));
        jynsList.add(new jyns("Aýal"));
        jynsy=findViewById(R.id.jynsy2);
        LocalBroadcastManager.getInstance(getApplicationContext()).
                registerReceiver(broadcastReceiverid,new IntentFilter("ktoya"));
        LocalBroadcastManager.getInstance(getApplicationContext()).
                registerReceiver(broadcastReceiverjyns,new IntentFilter("prenod"));

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( phonepole.getText().length()>0 &&  loggpole.getText().length()>0 && faapole.getText().toString().length()>0)
                {
                    if (passpolr.getText().toString().equals(passrepeatpole.getText().toString())){

                        pass=passrepeatpole.getText().toString().trim();
                        faa=faapole.getText().toString().trim();
                        logg=loggpole.getText().toString().trim();
                        phone=phonepole.getText().toString().toString();
                        sendReq.loadUrl(url+"/etm/reguser.php?login="+logg+"&fio="+faa+"&tip="+usertip+"&jns="+jyns+"&number="+phone+"&pass="+pass);
                        Log.e("TAG", url+"/etm/reguser.php?login="+logg+"&fio="+faa+"&tip="+usertip+"&jns="+jyns+"&number="+phone);
                        Toast.makeText(getApplicationContext(),"Ulgama giriň",Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),"Parollar gabat gelenok",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        recyclerView=findViewById(R.id.saylaw);
        saylawbirs=new ArrayList<>();
        jynsy.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        jynsy.setAdapter(new jynsAdapter(reg.this,jynsList));
        saylawbirs.add(new saylawbir("1",""));
        saylawbirs.add(new saylawbir("2",""));
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(new saylawAdapter(getApplicationContext(),saylawbirs));
    }
    public  BroadcastReceiver broadcastReceiverid=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            usertip=intent.getStringExtra("tipid");
            Log.e("TAG", "onReceive: usertip"+usertip );
        }
    };

    public BroadcastReceiver broadcastReceiverjyns=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            jyns=intent.getStringExtra("jynsid");
        }
    };

    }
