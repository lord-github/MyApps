package com.bbstudios.ecash;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Build;
import android.widget.TextView;
import android.widget.Toast;

import com.bbstudios.ecash.optional.adminAdapter;
import com.bbstudios.ecash.optional.adminitem;
import com.bbstudios.ecash.optional.stat1;
import com.bbstudios.ecash.optional.statAdapter;
import com.bbstudios.ecash.yumuslar.Googlemaps;
import com.bbstudios.ecash.yumuslar.Startmess;
import com.bbstudios.ecash.yumuslar.tmcars;
import com.bbstudios.ecash.yumuslar.Beletvideo;
import com.bbstudios.ecash.yumuslar.yandex;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class addpage extends AppCompatActivity {
    ArrayList<String> IDS,Names,timess;
    FragmentManager fragmentManager;
    List<adminitem> adminitems;
    List<stat1> stat1s;
    TextView adbalance;
    LinearLayout asakybolum2;
    RecyclerView yumlist,gorkezz;
    Boolean tick=false,st=false;
    String TAG="admin"; String type="none";
    String usID,zaID;
    TextView taymer,nomer,seneq,status;
    AppCompatButton otmena;
    EditText summa;
    Boolean bb=false;
    private CountDownTimer countDownTimer;
    String uid,ip,ip2,f1,f2,f3;
    Button addpayment,hasal,ade;
    WebView addweb,go1,go2,go3,go4;
    AppCompatButton prinyat,otkaz;
    String glavnomer="",stat="0";
    BottomSheetDialog paydialog,lasone;
    View payview,lasview;
    NestedScrollView nestedScrollView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpage);
        stat1s=new ArrayList<>();
        Intent intent=getIntent();
        ip2=intent.getStringExtra("ip");
        ip="http://"+intent.getStringExtra("ip")+"/etm/";
        uid=intent.getStringExtra("uid");
        adminitems=new ArrayList<>();
        gorkezz=findViewById(R.id.gorkezz);
        Spinner timelist=findViewById(R.id.timelist);
        Spinner customSpinner = findViewById(R.id.zadanialist);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        nestedScrollView=findViewById(R.id.netss);
        adbalance=findViewById(R.id.adbalance);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Startmess startmess=new Startmess();
        Googlemaps googlemaps=new Googlemaps();
        yandex yandexs=new yandex();
        tmcars tmcars=new tmcars();
        Beletvideo beletvideo=new Beletvideo();
        stat1s.clear();
        ade=findViewById(R.id.ade);
        ade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        seneq=findViewById(R.id.seneq);
        addpayment=findViewById(R.id.addpayment);
        yumlist=findViewById(R.id.yums);
        stat1s.add(new stat1("0","Ýumuş ady","limit","galany","sene"));
        yumlist.setLayoutManager(new LinearLayoutManager(addpage.this));
        yumlist.setAdapter(new statAdapter(addpage.this,stat1s));
        IDS=new ArrayList<>();
        timess=new ArrayList<>();
        Names=new ArrayList<>();
        payview= LayoutInflater.from(addpage.this).
                inflate(R.layout.perewod_,findViewById(R.id.pereID));
        lasview=LayoutInflater.from(addpage.this).inflate(R.layout.tassyklama,findViewById(R.id.lastone));
        lasone=new BottomSheetDialog(addpage.this);
        asakybolum2=lasview.findViewById(R.id.asakybolum2);
        lasone.setContentView(lasview);
        taymer=payview.findViewById(R.id.taymer);
        nomer=payview.findViewById(R.id.mynomer);
        otmena=payview.findViewById(R.id.otmee);
        paydialog=new BottomSheetDialog(addpage.this);
        paydialog.setContentView(payview);
        summa=payview.findViewById(R.id.summa);
        paydialog.setCancelable(false);
        prinyat=lasview.findViewById(R.id.prinyat);
        otkaz=lasview.findViewById(R.id.otkazz);
        go1=lasview.findViewById(R.id.gor1);
        go2=lasview.findViewById(R.id.gor2);
        go3=lasview.findViewById(R.id.gor3);
        gett(go1);
        gett(go2);
        gett(go3);
        go1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        go2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        go3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        addweb=findViewById(R.id.addweb);
        timess.add(new String("Ählisi"));
        timess.add(new String("Şu gün"));
        timess.add(new String("Şu aý"));
        timess.add(new String("Şu ýyl"));
        Names.add(new String("Google maps-da teswir goýmak"));
        Names.add(new String("Yandex maps-da teswir goýmak"));
        Names.add(new String("Start messanger-da topara agza bolmak"));
        Names.add(new String("Belet Video-da agzalan wideo şekile 'like' goýmak"));
        Names.add(new String("Tmcars-da teswir goýmak"));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, Names);
        adapter.setDropDownViewResource(R.layout.custom_spinner_item); // Optional: same style for dropdown
        customSpinner.setAdapter(adapter);
        hasal=payview.findViewById(R.id.hasal);
        ArrayAdapter<String> stringArrayAdapter=new ArrayAdapter<>(this,R.layout.custom_spinner_item,timess);
        stringArrayAdapter.setDropDownViewResource(R.layout.custom_spinner_item);
        timelist.setAdapter(stringArrayAdapter);
        hasal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tick=true;
                taymer.setText("60");
                Calendar calendar=Calendar.getInstance();
                int d=calendar.get(Calendar.DAY_OF_MONTH);
                int m=calendar.get(Calendar.MONTH);
                int y=calendar.get(Calendar.YEAR);
                int h=calendar.get(Calendar.HOUR_OF_DAY);
                int mi=calendar.get(Calendar.MINUTE);
                int s=calendar.get(Calendar.SECOND);
                String md=uid+"$"+String.valueOf(d)+"-"+String.valueOf(m)+"-"+String.valueOf(y)+"-"+String.valueOf(mi)+"-"+String.valueOf(h)+"-"+
                        String.valueOf(s);
                addweb.loadUrl(ip+"md5.php?md="+uid+"&uid="+summa.getText().toString());
                Log.e(TAG, "onClick: "+ip+"md5.php?md="+uid+"&uid="+summa.getText().toString());
                countDownTimer = new CountDownTimer(60000,1100) {
                    @Override
                    public void onTick(long l) {
                        if (tick){
                            int seconds = (int) (l / 1000) % 60;
                            taymer.setText(String.valueOf(seconds));
                            if (seconds % 5==0){
                                getstat(uid);
                            }
                        }
                    }

                    @Override
                    public void onFinish() {
                        tick=false;
                        if (paydialog.isShowing()){
                            paydialog.dismiss();
                        }
                        if (stat.equals("0")){
                            st=true;
                            if (st){

                            }
                        }
                        Toast.makeText(getApplicationContext(),"Wagtyňyz tamamlandy",Toast.LENGTH_SHORT).show();
                    }
                }.start();
            }
        });
        addpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!paydialog.isShowing()){
                    taymer.setText("60");
                    nomer.setText(glavnomer);
                    paydialog.show();
                }
            }
        });
        otmena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  if (tick==true){
                countDownTimer.cancel();
                  }
                tick=false;
                if (paydialog.isShowing()){
                    paydialog.dismiss();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getadmin(uid,type);
                    }
                },1500);
            }
        });
        fragmentManager=null;
        Bundle bundle=new Bundle();
        bundle.putString("zakazid",String.valueOf(uid));
        bundle.putString("ip",ip2);
        bundle.putString("tip",String.valueOf(1));
        googlemaps.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_left).replace(R.id.amallarpole, googlemaps, null)
                .setReorderingAllowed(true).addToBackStack("null").commit();

        customSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e(TAG, "onItemSelected: "+String.valueOf(i) );
                if (i==0 ){
                    fragmentManager=null;
                    Bundle bundle=new Bundle();
                    bundle.putString("zakazid",String.valueOf(uid));
                    bundle.putString("ip",ip2);
                    bundle.putString("tip",String.valueOf(i+1));
                    googlemaps.setArguments(bundle);
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,
                                    R.anim.slide_out_left).replace(R.id.amallarpole, googlemaps, null)
                            .setReorderingAllowed(true).addToBackStack("null").commit();

                }
                if (i==1 ){
                    fragmentManager=null;
                    Bundle bundle=new Bundle();
                    bundle.putString("zakazid",String.valueOf(uid));
                    bundle.putString("ip",ip2);
                    bundle.putString("tip",String.valueOf(i+1));
                    yandexs.setArguments(bundle);
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,
                                    R.anim.slide_out_left).replace(R.id.amallarpole, yandexs, null)
                            .setReorderingAllowed(true).addToBackStack("null").commit();

                }
                if (i==2){
                    fragmentManager=null;

                    Bundle bundle=new Bundle();
                    bundle.putString("zakazid",String.valueOf(uid));
                    bundle.putString("ip",ip2);
                    bundle.putString("tip",String.valueOf(i+1));
                    startmess.setArguments(bundle);
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,
                                    R.anim.slide_out_left).replace(R.id.amallarpole, startmess, null)
                            .setReorderingAllowed(true).addToBackStack("null").commit();

                }
                if (i==4){
                    fragmentManager=null;
                    Bundle bundle=new Bundle();
                    bundle.putString("zakazid",String.valueOf(uid));
                    bundle.putString("ip",ip2);
                    bundle.putString("tip",String.valueOf(i+1));
                    tmcars.setArguments(bundle);
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,
                                    R.anim.slide_out_left).replace(R.id.amallarpole, tmcars, null)
                            .setReorderingAllowed(true).addToBackStack("null").commit();

                }
                if (i==3){
                    fragmentManager=null;
                    fragmentManager = getSupportFragmentManager();
                    Bundle bundle=new Bundle();
                    bundle.putString("tip",String.valueOf(i+1));
                    bundle.putString("zakazid",String.valueOf(uid));
                    bundle.putString("ip",ip2);
                    beletvideo.setArguments(bundle);
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,
                                    R.anim.slide_out_left).replace(R.id.amallarpole, beletvideo, null)
                            .setReorderingAllowed(true).addToBackStack("null").commit();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                getnum();
                balance();
                doredilen(uid);
            }
        }).start();
        Calendar calendar=Calendar.getInstance();
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int m=calendar.get(Calendar.MONTH);
        int y=calendar.get(Calendar.YEAR);
        seneq.setText(day+"-"+(m+1)+"-"+y);
        timelist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                adminitems.clear();;
                gorkezz.setLayoutManager(new LinearLayoutManager(addpage.this));
                gorkezz.setAdapter(new adminAdapter(addpage.this,adminitems));

                if (i==0){
                    type="all";
                    getadmin(uid,type);
                }
                if (i==1){
                    type="day";
                    getadmin(uid,type);
                }
                if (i==2){
                    type="month";
                    getadmin(uid,type);
                }
                if (i==3){
                    type="year";
                    getadmin(uid,type);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(broadcastReceiver,new IntentFilter("gorjek"));
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiverUpdate,new IntentFilter("updateform"));
        prinyat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addweb.loadUrl(ip+"uytget.php?uid="+usID+"&zid="+zaID+"&t=1&o=0");
                Log.e(TAG, ip+"uytget.php?uid="+usID+"&zid="+zaID+"&t=1&o=0" );
                if (lasone.isShowing()){
                    lasone.dismiss();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getadmin(uid,type);
                    }
                },1500);
            }
        });
        otkaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addweb.loadUrl(ip+"uytget.php?uid="+usID+"&zid="+zaID+"&t=0&o=1");
                Log.e(TAG, ip+"uytget.php?uid="+usID+"&zid="+zaID+"&t=0&o=1");
                if (lasone.isShowing()){
                    lasone.dismiss();
                }
            }
        });

    }


    public void getyumus(String uid,String zid){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    Log.e(TAG, "onPostExecute: "+s );
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    if(ja.length()>0){

                      List<String> sanaw;
                      sanaw=new ArrayList<>();
                      sanaw.clear();
                        for (int i=0;i<ja.length();i++) {
                            jo = ja.getJSONObject(i);
                            if (i==0){
                                f1=jo.getString("ff");
                            }
                            if (i==1){
                                f2=jo.getString("ff");
                            }
                            if (i==2){
                                f3=jo.getString("ff");
                            }
                        }
                            if (!lasone.isShowing()){
                                lasone.show();

                                Log.e(TAG, "onPostExecute: "+ip+"ima.php?image="+f1+"|"+
                                        ip+"ima.php?image="+f2+"|"+ip+"ima.php?image="+f3 );

                                go1.loadUrl(ip+"ima.php?image="+f1);
                                go2.loadUrl(ip+"ima.php?image="+f2);
                                go3.loadUrl(ip+"ima.php?image="+f3);
                            }

                    }
                    else
                    {
                        go1.loadUrl(ip+"ima.php?image=11"+f1);
                        go2.loadUrl(ip+"ima.php?image=11"+f2);
                        go3.loadUrl(ip+"ima.php?image=11"+f3);
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
        fromMysql.execute(ip+"dataget.php?uid="+uid+"&zid="+zid);
        Log.e(TAG,ip+"dataget.php?uid="+uid+"&zid="+zid);
    }


    public void getadmin(String uid,String  type){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    Log.e(TAG, "onPostExecute: "+s );
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    if(ja.length()>0){
                        adminitems.clear();;
                        gorkezz.setLayoutManager(new LinearLayoutManager(addpage.this));
                        gorkezz.setAdapter(new adminAdapter(addpage.this,adminitems));

                        for (int i=0;i<ja.length();i++) {
                            jo = ja.getJSONObject(i);
                            String userID,zaID,otm,tass,fio,nZada;
                            nZada=jo.getString("name_zadaca");
                            userID=jo.getString("userid");
                            zaID=jo.getString("zaid");
                            otm= jo.getString("otmen");
                            tass=jo.getString("tass");
                            fio=jo.getString("fio");
                            int mycolor=R.color.white;
                            adminitems.add(new adminitem(zaID,userID,fio,nZada,mycolor));
                            gorkezz.setLayoutManager(new LinearLayoutManager(addpage.this));
                            gorkezz.setAdapter(new adminAdapter(addpage.this,adminitems));
                            if (otm.equals("0") && tass.equals("0")){
                                asakybolum2.setVisibility(View.VISIBLE);
                            } else {
                                asakybolum2.setVisibility(View.INVISIBLE);
                            }
                        }

                    }
                    else
                    {
                        adminitems.clear();
                        gorkezz.setLayoutManager(new LinearLayoutManager(addpage.this));
                        gorkezz.setAdapter(new adminAdapter(addpage.this,adminitems));
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
        fromMysql.execute(ip+"adminall.php?id="+uid+"&type="+type);
        Log.e(TAG, "getadmin: "+ip+"adminall.php?id="+uid+"&type="+type);
    }

    public void balance(){
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
                            String userID,zaID,otm,tass,fio,nZada;
                            adbalance.setText(jo.getString("toleg"));
                        }

                    }
                    else
                    {
                        adbalance.setText("0.0 TMT");
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
        fromMysql.execute(ip+"adbal.php?id="+uid);
    }


    public void getnum(){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    Log.e(TAG, "onPostExecute: "+s );
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    if(ja.length()>0){
                        adminitems.clear();;
                        gorkezz.setLayoutManager(new LinearLayoutManager(addpage.this));
                        gorkezz.setAdapter(new adminAdapter(addpage.this,adminitems));

                        for (int i=0;i<ja.length();i++) {
                            jo = ja.getJSONObject(i);
                            String userID,zaID,otm,tass,fio,nZada;
                            glavnomer=jo.getString("num");
                        }

                    }
                    else
                    {
                    glavnomer="/";
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
        fromMysql.execute(ip+"num.php");
    }

    public void getstat(String mm){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    Log.e(TAG, "onPostExecute: "+s );
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    if(ja.length()>0){
                        adminitems.clear();;
                        gorkezz.setLayoutManager(new LinearLayoutManager(addpage.this));
                        gorkezz.setAdapter(new adminAdapter(addpage.this,adminitems));

                        for (int i=0;i<ja.length();i++) {
                            jo = ja.getJSONObject(i);
                            String userID,zaID,otm,tass,fio,nZada;
                            stat=jo.getString("stat");
                            st=false;
                            if (stat.equals("1")){
                                Toast.makeText(getApplicationContext(),"Töleg hasaba alyndy",Toast.LENGTH_SHORT).show();
                                balance();
                                st=true;
                                if (paydialog.isShowing()){
                                    paydialog.dismiss();
                                    countDownTimer.cancel();
                                }
                            }
                        }

                    }
                    else
                    {
                        stat="0";
                        st=false;
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
        fromMysql.execute(ip+"getstat.php?md="+mm);
        Log.e(TAG, "getstat: "+ip+"getstat.php?md="+mm);
    }


    public void doredilen(String mm){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    Log.e(TAG, "onPostExecute: "+s );
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    if(ja.length()>0){

stat1s.clear();
                        stat1s.add(new stat1("0","Ýumuş ady","limit","galany","sene"));

                        for (int i=0;i<ja.length();i++) {
                            jo = ja.getJSONObject(i);
                            String userID,zaID,otm,tass,fio,nZada;
                    stat1s.add(new stat1("0",jo.getString("ady"),
                            jo.getString("jem"),jo.getString("galan"),
                            jo.getString("wagt")));

                        }

                        yumlist.setLayoutManager(new LinearLayoutManager(addpage.this));
                        yumlist.setAdapter(new statAdapter(addpage.this,stat1s));
                    }
                    else
                    {
                        stat1s.clear();
                        stat1s.add(new stat1("0","Ýumuş ady","limit","galany","sene"));
                        yumlist.setLayoutManager(new LinearLayoutManager(addpage.this));
                        yumlist.setAdapter(new statAdapter(addpage.this,stat1s));
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
        fromMysql.execute(ip+"getlist.php?uid="+mm);

    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(broadcastReceiver,new IntentFilter("gorjek"));
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiverUpdate,new IntentFilter("updateform"));

    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(receiverUpdate);
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(broadcastReceiver);
    }


    public BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        usID=intent.getStringExtra("uID");
        zaID=intent.getStringExtra("zID");
        Log.e(TAG, "onReceive: "+usID+"|"+zaID );
        getyumus(usID,zaID);
        }
    };
public void gett(WebView web){
    WebSettings webSettings =web.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webSettings.setBuiltInZoomControls(true);
    webSettings.setDisplayZoomControls(false); // Hides the default zoom controls
    webSettings.setSupportZoom(true);

    web.setWebViewClient(new WebViewClient());
    web.setWebChromeClient(new WebChromeClient());

}

public BroadcastReceiver receiverUpdate=new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
       String up=intent.getStringExtra("update");
       if (up.equals("1")) {
           Log.e(TAG, "geldi: " );
           nestedScrollView.fullScroll(NestedScrollView.FOCUS_UP);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doredilen(uid);
                    balance();
                }
            },1500);

       }
       }

};

}