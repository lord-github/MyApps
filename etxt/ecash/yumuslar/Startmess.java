package com.bbstudios.ecash.yumuslar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bbstudios.ecash.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Startmess extends Fragment {
EditText ady,sany,dusun,kanal;
Button hasabal;
WebView ugrat;
View view;
String zakazid,ipaddress,tip,userbalance="0.0";


    public Startmess() {
        // Required empty public constructor
    }


    public static Startmess newInstance(String param1, String param2) {
        Startmess fragment = new Startmess();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_startmess, container, false);;
        Bundle arguments = getArguments();
        if (arguments!=null){
            zakazid=arguments.getString("zakazid");
            ipaddress=arguments.getString("ip");
            tip=arguments.getString("tip");
        }
        ady=view.findViewById(R.id.startady);
        kanal=view.findViewById(R.id.startkanal);
        sany=view.findViewById(R.id.startsany);
        dusun=view.findViewById(R.id.startdusun);
        hasabal=view.findViewById(R.id.startbut);
        ugrat=view.findViewById(R.id.startweb);
        balance();
        hasabal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ady.getText().length()>0 && kanal.getText().length()>0 && sany.getText().length()>0 &&dusun.getText().length()>0 ){
                    if (Float.valueOf(userbalance)>(0.05*Float.valueOf(sany.getText().toString()))){
                        ugrat.loadUrl("http://"+ipaddress+"/etm/insert.php?name="+
                                ady.getText().toString()+"&zakazid="+zakazid+"&jemi="+sany.getText().toString()+
                                "&tipID="+tip+"&bir_baha="+"0.05"+"&jyns="+"0"+"&dusun="+dusun+"&tesgor="+"0"+
                                "&simsan=120&dili="+"TKM");
                        Log.e("start", "http://"+ipaddress+"/etm/insert.php?name="+
                                ady.getText().toString()+"&zakazid="+zakazid+"&jemi="+sany.getText().toString()+
                                "&tipID="+tip+"&bir_baha="+"0.05"+"&jyns="+"0"+"&dusun="+dusun+"&tesgor="+"0"+
                                "&simsan=120&dili="+"TKM" );
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                obnovi();
                            }
                        },1500);
                    }   else {
                        Toast.makeText(getActivity(),"Siziň balansyňyz ýeterlikli däl",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(),"Meýdanlary dolduryň",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    public void balance(){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    Log.e("TAG", "MyBalance: "+s );
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    if(ja.length()>0){

                        for (int i=0;i<ja.length();i++) {
                            jo = ja.getJSONObject(i);
                            String userID,zaID,otm,tass,fio,nZada;
                            userbalance=jo.getString("toleg");
                        }

                    }
                    else
                    {
                        userbalance="0.0";
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
        fromMysql.execute("http://"+ipaddress+"/etm/adbal.php?id="+zakazid);
        Log.e("google", "balance: http://"+ipaddress+"/etm/adbal.php?id="+zakazid);
    }



    private void obnovi() {
        Intent intent=new Intent("updateform");
        intent.putExtra("update","1");
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

}