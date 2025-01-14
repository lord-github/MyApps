package com.bbstudios.reyting;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.bbstudios.reyting.kaf.kafAdapter;
import com.bbstudios.reyting.kaf.kafitem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class kafFragment extends Fragment {

List<kafitem> kafitems;
View view;
RecyclerView recyclerView;
Switch radioGroup
        ;
    Bundle bundle;
int y,a;
String url="",ip="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
Calendar calendar=Calendar.getInstance();
bundle=getArguments();
        bundle=getArguments();
        if (bundle!=null){

            ip=bundle.getString("ip");
            url="http://"+ip+"/rey/";
        }
        String url="http://"+ip+"/rey/";
          view=inflater.inflate(R.layout.fragment_kaf, container, false);
        kafitems=new ArrayList<>();
         radioGroup=view.findViewById(R.id.kafradio);
        recyclerView=view.findViewById(R.id.kaflist);
        doldur("kaf.php");
radioGroup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        y=calendar.get(Calendar.YEAR);
        a=calendar.get(Calendar.MONTH);
        if (isChecked==true){

            doldur("kafay.php");
        } else {
            doldur("kaf.php");

        }
    }
});


          return view;
    }



    public void doldur(String l){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    if(ja.length()>0){
                        kafitems.clear();
                        kafitems.add(new kafitem("0","Kafedra ady ","Fakultet","Ball"));
                        for (int i=0;i<ja.length();i++)
                        {
                            jo=ja.getJSONObject(i);
                            kafitems.add(new kafitem("1",jo.getString("kaf"),
                                    jo.getString("fgysga"),jo.getString("baha")));

                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(new kafAdapter(kafitems,getActivity()));
                    }else{
                        kafitems.clear();
                        kafitems.add(new kafitem("0","Kafedra ady ","Fakultet","Ball"));
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(new kafAdapter(kafitems,getActivity()));
                    }

                }catch (Exception e){
                    kafitems.clear();
                    kafitems.add(new kafitem("0","Kafedra ady ","Fakultet","Ball"));
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(new kafAdapter(kafitems,getActivity()));
                    Toast.makeText(getActivity(),"Näsazlyk ýüze çykdy",Toast.LENGTH_SHORT).show();
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
        fromMysql.execute(url+l);
        Log.e(TAG, "doldur: "+url+l );


    }
}