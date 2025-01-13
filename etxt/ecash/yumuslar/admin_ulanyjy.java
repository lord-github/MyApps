package com.bbstudios.ecash.yumuslar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bbstudios.ecash.Bas_sahypa;
import com.bbstudios.ecash.R;
import com.bbstudios.ecash.addpage;
import com.bbstudios.ecash.optional.ipAdapter;
import com.bbstudios.ecash.optional.yekeip;
import com.bbstudios.ecash.ulanakt;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class admin_ulanyjy extends Fragment {
    View view,bottomSheet1,wywodView;
    EditText newpass,reppas,summbalance,wywodbalans;
    TextView lastip,changing,myname,mybalance,reytingID,sredwywod;
    ImageView whoweare;
    WebView web22,wywodweb;
    BottomSheetDialog bottomSheetDialog1,wywodDialog;
    Integer scet=0;
    String url,fio,uid;
    Button changePass,cykarp,cykarmeni;
    String TAG="adula";
    List<yekeip> yekeips;
    private MediaPlayer mediaPlayer;
    TextView zadanbala;
    RecyclerView iplist;
    public admin_ulanyjy() {
        // Required empty public constructor
    }

    public static admin_ulanyjy newInstance(String param1, String param2) {
        admin_ulanyjy fragment = new admin_ulanyjy();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_admin_ulanyjy, container, false);
    Bundle bundle=getArguments();
    if (bundle!=null)
    {
    uid=bundle.getString("uid");
    fio=bundle.getString("fio");
    url=bundle.getString("ip");
        Log.e("TAG", "onCreateView: "+uid+" - fio - "+fio+"/"+url );
    } else {
        Log.e("TAG", "NOARG: " );
    }

        zadanbala=view.findViewById(R.id.zadanbala);
    iplist=view.findViewById(R.id.iplist);
        mybalance=view.findViewById(R.id.mybalance);
        mybalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Siziň hasabyňyz",Toast.LENGTH_LONG).show();
            }
        });
        reytingID=view.findViewById(R.id.reytingID);
        yekeips=new ArrayList<>();
        lastip=view.findViewById(R.id.lastip);
        myname=view.findViewById(R.id.myname);
        myname.setText(fio);
        zadanbala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Barlagdaky ýumuşlaryňyzyň tölegi",Toast.LENGTH_LONG).show();
            }
        });
        lastip.setText(getLocalIpAddress());
        whoweare=view.findViewById(R.id.whoweare);
        changing=view.findViewById(R.id.changeme);
        bottomSheet1= LayoutInflater.from(getActivity()).
                inflate(R.layout.change_username,view.findViewById(R.id.bsc2));
        wywodView=LayoutInflater.from(getActivity()).inflate(R.layout.wywod,view.findViewById(R.id.pull));
        bottomSheetDialog1=new BottomSheetDialog(getActivity());
        wywodDialog=new BottomSheetDialog(getActivity());
        wywodDialog.setContentView(wywodView);
        cykarmeni=view.findViewById(R.id.cykarmeni);
        summbalance=wywodView.findViewById(R.id.hasabym);
        wywodbalans=wywodView.findViewById(R.id.cykarjakpul);
        cykarp=wywodView.findViewById(R.id.transfermoney);
        wywodweb=wywodView.findViewById(R.id.wywodweb);
        sredwywod=view.findViewById(R.id.sredwywod);
        sredwywod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Hasapdan çykarmaly pulyňyz",Toast.LENGTH_LONG).show();
            }
        });
        cykarp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Float s1,s2;
                s1=Float.valueOf(summbalance.getText().toString());
                s2=Float.valueOf(wywodbalans.getText().toString());
                if (s1>50){
                    if (s1>=s2 ){
                        if (s2>30 && s2<=50) {
                            wywodweb.loadUrl("http://" + url + "/etm/wyrequest.php?uid=" + uid + "&ser=" + (s2-s2*0.02));
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    wywodweb.loadUrl("http://" + url + "/etm/updatebalance.php?uid=" + uid + "&ser=" + s2);
                                }
                            }, 1500);

                        if (wywodDialog.isShowing()){
                            wywodDialog.dismiss();
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getIPadd(uid);
                                getWorkBalance(uid);
                                getMyBalance(uid);
                                getMyReyt(uid);
                                getwb(uid);
                            }
                        },1500);
                    } else {
                            Toast.makeText(getActivity(),"Siz goýlan limitdan geçdiňiz",Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getActivity(),"Siziň balansyňyz azdyr",Toast.LENGTH_SHORT).show();
                }
            }
        });
        bottomSheetDialog1.setContentView(bottomSheet1);
        changePass=bottomSheet1.findViewById(R.id.passc);
        newpass=bottomSheet1.findViewById(R.id.changename);
        reppas=bottomSheet1.findViewById(R.id.chanpass);
        web22=bottomSheet1.findViewById(R.id.web22);
        changing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bottomSheetDialog1.isShowing()){
                    bottomSheetDialog1.show();
                }
            }
        });
        whoweare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scet++;
                Log.e("TAG", "onClick: "+scet );
                if (scet==10){

                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    try {
                        mediaPlayer = new MediaPlayer();
                        Toast.makeText(getActivity(),"From bbstudios \uD83C\uDFA4",Toast.LENGTH_LONG).show();
                        Integer hays;
                        Random random = new Random();
                        hays=random.nextInt(4)+1;
                        String fn=String.valueOf(hays)+".mp3";
                        mediaPlayer.setDataSource(getContext().getAssets().openFd(fn).getFileDescriptor(),
                                getContext().getAssets().openFd(fn).getStartOffset(),
                                getContext().getAssets().openFd(fn).getLength());
                        Log.e("TAG", "Lenthg: "+String.valueOf(getContext().getAssets().openFd(fn).getLength()) );
                        mediaPlayer.prepare(); // Prepare MediaPlayer
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Error loading audio file!", Toast.LENGTH_SHORT).show();
                    }
                    if (!mediaPlayer.isPlaying()) {
                        mediaPlayer.start();

                    } else {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    }

                    scet=0;
                }
            }
        });
         scet=0;
         Thread thread=new Thread(new Runnable() {
             @Override
             public void run() {
                 getIPadd(uid);
                 getWorkBalance(uid);
                 getMyBalance(uid);
                 getMyReyt(uid);
                 getwb(uid);
             }
         });
         thread.start();
        cykarmeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b1,b2;
                b1=(mybalance.getText().toString().trim().substring(0,mybalance.getText().toString().lastIndexOf(" ")));

                b1=b1.replace("00","0");
                summbalance.setText(String.valueOf(Float.valueOf(b1)));
                wywodbalans.setText("0.0");

                if (!wywodDialog.isShowing() && Float.valueOf(b1)>0){
                    wywodDialog.show();
                } else {
                    Toast.makeText(getActivity(),"Hasabyňyzda serişde ýok",Toast.LENGTH_LONG).show();
                }
            }
        });
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (newpass.getText().length()>6 && reppas.getText().length()>6){
                   if (newpass.getText().toString().trim().equals(reppas.getText().toString().trim())){
                       web22.loadUrl("http://"+url+"/etm/uppass.php?uid="+uid+"&pass="+reppas.getText().toString().trim());
                       new Handler().postDelayed(new Runnable() {
                           @Override
                           public void run() {
                               if (bottomSheetDialog1.isShowing()){
                                   bottomSheetDialog1.dismiss();
                               }
                               Toast.makeText(getActivity(),"Kabul edildi",Toast.LENGTH_SHORT).show();
                           }
                       },1500);
                   } else {
                       Toast.makeText(getActivity(),"Açar sözleri gabat gelmeýär",Toast.LENGTH_SHORT).show();
                   }
               } else {
                   Toast.makeText(getActivity(),"Açar sözi 7simwoldan uly bolmaly",Toast.LENGTH_SHORT).show();
               }
            }
        });
        return  view;
    }
    public static String getLocalIpAddress() {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface networkInterface : interfaces) {
                List<InetAddress> addresses = Collections.list(networkInterface.getInetAddresses());
                for (InetAddress address : addresses) {
                    if (!address.isLoopbackAddress() && address instanceof java.net.Inet4Address) {
                        return address.getHostAddress(); // Возвращает локальный IP-адрес
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Anyklap bolmady";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    public void getIPadd(String address){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    Log.e(TAG, "onPostExecute: "+s );
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    yekeips.clear();;
                    if(ja.length()>0){

                        for (int i=0;i<ja.length();i++) {
                            jo = ja.getJSONObject(i);
                            String tip=jo.getString("ips");
                            yekeips.add(new yekeip(tip));
                        }
                        iplist.setLayoutManager(new LinearLayoutManager(getActivity()));
                        iplist.setAdapter(new ipAdapter(getActivity(),yekeips));
                    }

                    else
                    {
                        yekeips.clear();;
                        iplist.setLayoutManager(new LinearLayoutManager(getActivity()));
                        iplist.setAdapter(new ipAdapter(getActivity(),yekeips));
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
        fromMysql.execute("http://"+url+"/etm/lastips.php?uid="+address);
        Log.e(TAG, "http://"+url+"/etm/lastips.php?uid="+address);
    }
    public void getWorkBalance(String uid){
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
                            String bal=jo.getString("toleg");
                            zadanbala.setText(bal+" TMT");
                        }
                    }

                    else
                    {
                        zadanbala.setText("0.0 TMT");
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
        fromMysql.execute("http://"+url+"/etm/balance.php?id="+uid);
        Log.e(TAG, "getWorkBalance: "+"http://"+url+"/etm/balance.php?id="+uid );
    }
    public void getMyBalance(String uid){
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
                            String bal=jo.getString("toleg");
                            mybalance.setText(bal+" TMT");
                        }
                    }

                    else
                    {
                        mybalance.setText("0.0 TMT");
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
        fromMysql.execute("http://"+url+"/etm/mybal.php?id="+uid);
        Log.e(TAG, "getWorkBalance: "+"http://"+url+"/etm/mybal.php?id="+uid);
    }
    public void getMyReyt(String uid){
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
                            String bal=jo.getString("rey");
                            reytingID.setText(bal);
                        }
                    }

                    else
                    {
                        reytingID.setText("0");
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
        fromMysql.execute("http://"+url+"/etm/reyting.php?id="+uid);
        Log.e(TAG, "getMyRey: "+"http://"+url+"/etm/reyting.php?id="+uid);
    }

    public void getwb(String uid){
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
                            String bal=jo.getString("rey");
                            sredwywod.setText(bal);
                        }
                    }

                    else
                    {
                        sredwywod.setText("0");
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
        fromMysql.execute("http://"+url+"/etm/wb.php?id="+uid);
        Log.e(TAG, "http://"+url+"/etm/wb.php?id="+uid );
    }

}