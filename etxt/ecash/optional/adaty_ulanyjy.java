package com.bbstudios.ecash.optional;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bbstudios.ecash.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class adaty_ulanyjy extends Fragment {
View view;
List<statItem> statItems;
RecyclerView recstat;
String TAG="adaty_ulanyjy";
String uid="",url="";
TextView yyer,prinyaty,zakaz,bar;
    public adaty_ulanyjy() {
    }
    public static adaty_ulanyjy newInstance(String param1, String param2) {
        adaty_ulanyjy fragment = new adaty_ulanyjy();
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
        view =  inflater.inflate(R.layout.fragment_adaty_ulanyjy, container, false);
Bundle bundle=getArguments();
if (bundle!=null){
    uid=bundle.getString("uid");
    url=bundle.getString("ip");
}
        Log.e("TAG", "URL: "+url+" UID : "+uid);
statItems=new ArrayList<>();
recstat=view.findViewById(R.id.yumustable);
yyer=view.findViewById(R.id.yeryet);
prinyaty=view.findViewById(R.id.prinyaty);
zakaz=view.findViewById(R.id.zakaz);
        bar=view.findViewById(R.id.bar);
new Thread(new Runnable() {
    @Override
    public void run() {
        getmylist(uid);
        getAll(uid);
        gettassyklanan(uid);
        getotmen(uid);
        getbarlag(uid);
    }
}).start();

        return view;
    }

    public void getmylist(String uid){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    Log.e(TAG, "onPostExecute: "+s );
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    statItems.clear();
                    if(ja.length()>0){
                        for (int i=0;i<ja.length();i++) {
                            jo = ja.getJSONObject(i);
                            String name,cislo,tass,otm;
                            name=jo.getString("name");
                            cislo=jo.getString("g")+"-"+jo.getString("a")+"-"+jo.getString("y");
                            tass=jo.getString("tassyklanan");
                            otm=jo.getString("otmena");
                            int mycolor = 0;
                            if (tass.equals("1")){
                                mycolor=R.color.yasyl;
                            }
                            if (otm.equals("1")){
                                mycolor=R.color.otkazuser;
                            }
                            if (otm.equals("0") && tass.equals("0")){
                                mycolor=R.color.sary;
                            }
                            statItems.add(new statItem(name,cislo,mycolor));


                        }
                        recstat.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recstat.setAdapter(new sAdapter(getActivity(),statItems));

                    }

                    else
                    {
                        statItems.clear();
                        recstat.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recstat.setAdapter(new sAdapter(getActivity(),statItems));
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
        fromMysql.execute("http://"+url+"/etm/getmylist.php?id="+uid);
        Log.e(TAG, "getmylist: "+"http://"+url+"/etm/getmylist.php?id="+uid );
    }

    public void getAll(String uid){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    Log.e(TAG, "onPostExecute: "+s );
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    statItems.clear();
                    if(ja.length()>0){
                        for (int i=0;i<ja.length();i++) {
                            jo = ja.getJSONObject(i);
                            String name,cislo,tass,otm;
                            name=jo.getString("sany");
                            yyer.setText(getActivity().getResources().getString(R.string.yeryet)+" "+name);

                        }

                    }

                    else
                    {
                        yyer.setText(getActivity().getResources().getString(R.string.yeryet)+" 0");

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
        fromMysql.execute("http://"+url+"/etm/getall.php?id="+uid);
        Log.e(TAG, "getAll: "+"http://"+url+"/etm/getall.php?id="+uid );
    }

    public void gettassyklanan(String uid){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    Log.e(TAG, "onPostExecute: "+s );
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    statItems.clear();
                    if(ja.length()>0){
                        for (int i=0;i<ja.length();i++) {
                            jo = ja.getJSONObject(i);
                            String name,cislo,tass,otm;
                            name=jo.getString("sany");
                            prinyaty.setText(getActivity().getResources().getString(R.string.zakazpri)+" "+name);

                        }

                    }

                    else
                    {
                        prinyaty.setText(getActivity().getResources().getString(R.string.zakazpri)+" 0");

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
        fromMysql.execute("http://"+url+"/etm/gettassyklanan.php?id="+uid);
        Log.e(TAG, "gettassyklanan: "+"http://"+url+"/etm/gettassyklanan.php?id="+uid );
    }

    public void getotmen(String uid){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    Log.e(TAG, "onPostExecute: "+s );
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    statItems.clear();
                    if(ja.length()>0){
                        for (int i=0;i<ja.length();i++) {
                            jo = ja.getJSONObject(i);
                            String name,cislo,tass,otm;
                            name=jo.getString("sany");
                            zakaz.setText(getActivity().getResources().getString(R.string.zakaz)+" "+name);

                        }

                    }

                    else
                    {
                        zakaz.setText(getActivity().getResources().getString(R.string.zakaz)+" 0");

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
        fromMysql.execute("http://"+url+"/etm/getotmen.php?id="+uid);
        Log.e(TAG, "gettassyklanan: "+"http://"+url+"/etm/getotmen.php?id="+uid );
    }

    public void getbarlag(String uid){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    Log.e(TAG, "onPostExecute: "+s );
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    statItems.clear();
                    if(ja.length()>0){
                        for (int i=0;i<ja.length();i++) {
                            jo = ja.getJSONObject(i);
                            String name,cislo,tass,otm;
                            name=jo.getString("sany");
                            bar.setText(getActivity().getResources().getString(R.string.bar)+" "+name);

                        }

                    }

                    else
                    {
                        bar.setText(getActivity().getResources().getString(R.string.bar)+" 0");

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
        fromMysql.execute("http://"+url+"/etm/getbarlag.php?id="+uid);
        Log.e(TAG, "getbarlag: "+"http://"+url+"/etm/getbarlag.php?id="+uid );
    }




}