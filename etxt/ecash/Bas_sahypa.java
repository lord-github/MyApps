package com.bbstudios.ecash;


import android.app.Dialog;
import java.net.URL;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bbstudios.ecash.optional.banAdapter;
import com.bbstudios.ecash.optional.bann;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Bas_sahypa extends AppCompatActivity {
    RecyclerView recyclerView;
    List<bann> slidersss;
    Dialog dialog;
    String URL1="192.168.1.109";
    View bottomSheet1;
    String TAG="glavnaya";
    GifImageView reklama;
    BottomSheetDialog bottomSheetDialog1;
    ImageView dialogcyk,loginimage;
    EditText login,password,Ipsalgy;
    Button buttongir,cykk,OKBUTTON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bas_sahypa);
        slidersss=new ArrayList<>();
        bottomSheet1= LayoutInflater.from(Bas_sahypa.this).
                inflate(R.layout.ipcontainer,findViewById(R.id.modalBottomSheetContainer1));
        bottomSheetDialog1=new BottomSheetDialog(Bas_sahypa.this);
        bottomSheetDialog1.setContentView(bottomSheet1);
        OKBUTTON=bottomSheet1.findViewById(R.id.kabulet);
        Ipsalgy=bottomSheet1.findViewById(R.id.serwerIP);
        reklama=findViewById(R.id.reklama);
        Ipsalgy.setFilters(new InputFilter[]{
                (source, start, end, dest, dstart, dend) -> {
                    for (int i = start; i < end; i++) {
                        char c = source.charAt(i);
                        if (!Character.isDigit(c) && c != '.') {
                            return "";
                        }
                    }
                    return null;
                }
        });

        OKBUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ipsalgy.length()>0){
                    URL1=Ipsalgy.getText().toString();
                    if (bottomSheetDialog1.isShowing()){
                        bottomSheetDialog1.dismiss();
                    }
                }
            }
        });
        reklama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bottomSheetDialog1.isShowing()){
                    bottomSheetDialog1.show();
                }
            }
        });

        slidersss.add(new bann("1","Ýokary tejribeli hünärmenler"));
        slidersss.add(new bann("2","Taýyn makalalary satmak"));
        slidersss.add(new bann("4","Öz şertleriňizde iş dörediň"));
        slidersss.add(new bann("3","Web sahypanyň işjeňligini artdyryň"));
        recyclerView=findViewById(R.id.karusel);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        dialog=new Dialog(Bas_sahypa.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setContentView(R.layout.logindialog);
        dialog.setCancelable(false);
        dialogcyk=dialog.findViewById(R.id.loginclose);
        login=dialog.findViewById(R.id.editlogin);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        password=dialog.findViewById(R.id.editpass);
        buttongir=dialog.findViewById(R.id.ulgamagir);
        cykk=dialog.findViewById(R.id.gotoregisty);
        cykk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
                Intent intent=new Intent(Bas_sahypa.this, reg.class);
                intent.putExtra("ip",URL1);
                startActivity(intent);

            }
        });

        buttongir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (login.getText().length()>0 && password.getText().length()>0){

                    med(login.getText().toString().toString(),password.getText().toString().trim());

                } else {
                    Toast.makeText(getApplicationContext(),"Meýdanlary dolduryň", Toast.LENGTH_LONG).show();
                }
            }
        });
        loginimage=findViewById(R.id.loginimage);
        loginimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!dialog.isShowing()){
                    dialog.show();
                }
            }
        });
        dialogcyk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new banAdapter(slidersss,Bas_sahypa.this));

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentPos = linearLayoutManager.findFirstVisibleItemPosition();
                int nextPos = (currentPos + 1) % slidersss.size(); // циклическая прокрутка
                recyclerView.smoothScrollToPosition(nextPos);
                recyclerView.postDelayed(this, 5000); // Прокрутка каждые 2 секунды

            }
        },5000);
    }

    public void g2(View view){
        startActivity(new Intent(Bas_sahypa.this, addpage.class));
    }
    public void g3(View view){
        startActivity(new Intent(Bas_sahypa.this, ulanakt.class));
    }

    public void regac(View view){
        Intent intent=new Intent(Bas_sahypa.this, reg.class);
        intent.putExtra("ip",URL1);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }
    private boolean isValidIPAddress(String ip) {
        String ipPattern =
                "^((25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\.){3}" +
                        "(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)$";
        return ip.matches(ipPattern);
    }

    public void med(String uid,String pass){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    Log.e(TAG, "onPostExecute: "+s );
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    if(ja.length()>0){
                        for (int i=0;i<ja.length();i++) {
                            jo = ja.getJSONObject(i);
                            String tip=jo.getString("tip");
                            String uid=jo.getString("tb");
                            String fio=jo.getString("fio");
                            Log.e(TAG, "Netije : tip["+tip+"] - Name = "+fio );
                            if (tip.equals("0")){
                                Intent ulan=new Intent(Bas_sahypa.this, ulanakt.class);
                                ulan.putExtra("uid",uid);
                                ulan.putExtra("fio",fio);
                                ulan.putExtra("ip",URL1);
                                if (dialog.isShowing()){
                                    dialog.dismiss();
                                }
                                startActivity(ulan);
                            } else {
                                Intent admin=new Intent(Bas_sahypa.this,addpage.class);
                                admin.putExtra("ip",URL1);
                                admin.putExtra("uid",uid);
                                startActivity(admin);
                            }
                        }

                    }
                    else
                    {

                    }

                }catch (Exception e){

                }
                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL ur1=new URL(strings[0]);
                    HttpURLConnection conn= (HttpURLConnection) ur1.openConnection();
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer data=new StringBuffer();
                    String line;
                    while ((line=br.readLine())!=null)
                    {
                        data.append(line+"\n");
                    }
                    br.close();
                    return  data.toString();
                }catch (Exception e){
                    return e.getMessage();
                }
            }
        }
        FromMysql fromMysql=new FromMysql();
        fromMysql.execute("http://"+URL1+"/etm/login.php?l="+uid+"&p="+pass);
        Log.e(TAG, "http://"+URL1+"/etm/login.php?l="+uid+"&p="+pass);
    }


}