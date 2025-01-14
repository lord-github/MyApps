package com.bbstudios.reyting;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bbstudios.reyting.poisk.poAdapter;
import com.bbstudios.reyting.poisk.poitem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ballFragment extends Fragment {
    View view;
String [] sanawt,sanawM;
List<String> islertalyp,islerMug,talypbaha,Mugbaha;
List<poitem> poitems;
String log_id;
    String url="http://10.100.0.37/rey/",bal="0",userid="0",Top_or_Kaf_id="0",fa_id="0",kk="0",ei="",fa_doly;
    RadioGroup Radgrup,Radgrup2;
    Dialog dialog;
   String ip="";
    Bundle bundle;
    WebView bbal;
    int  gun1 = 0, ay = 0, yyl = 0;
    EditText adamgozlegmeydan; ImageView gozlegknop;
    TextView issene,berbal;
    int u_tip = 1;
    Spinner spinner;
    RecyclerView recyclerView;
    Button button;
    ArrayAdapter<String> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_ball, container, false);
          spinner = (Spinner) view.findViewById(R.id.yeryetis);
        int aa=34;
        char b = (char) aa;
        bundle=getArguments();
        if (bundle!=null){
           log_id=bundle.getString("fc");
            ip=bundle.getString("ip");
            url="http://"+ip+"/rey/";
        }
islertalyp=new ArrayList<>();
islerMug=new ArrayList<>();
talypbaha=new ArrayList<>();
Mugbaha=new ArrayList<>();
poitems=new ArrayList<>();
        berbal=view.findViewById(R.id.berbal);
        issene=view.findViewById(R.id.issene);
        issene.setText("");
        dialog=new Dialog(getActivity());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.poisknetije);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
recyclerView=dialog.findViewById(R.id.recnet);
        bbal=view.findViewById(R.id.bbal);
        issene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    DatePickerDialog datePickerDialog =
                            new DatePickerDialog(getActivity());
                    datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            gun1 = dayOfMonth;
                            ay = month;
                            String a, g;
                            yyl = year;
                            if (dayOfMonth < 10) {
                                g = '0' + String.valueOf(dayOfMonth);
                            } else {
                                g = String.valueOf(dayOfMonth);
                            }
                            if (ay + 1 < 10) {
                                a = '0' + String.valueOf(month + 1);
                            } else {
                                a = String.valueOf(month + 1);
                            }
                            issene.setText(g+"."+a+"."+String.valueOf(year));
                        }
                    });
                    datePickerDialog.show();
                } else {
                    Toast.makeText(getActivity(),"Android wersiýa kiçi",Toast.LENGTH_LONG).show();
                }

            }
        });
        gozlegknop=view.findViewById(R.id.gozlegknop);
        adamgozlegmeydan=view.findViewById(R.id.adamgozlegmeydan);
        Radgrup=view.findViewById(R.id.Radgrup);
        Radgrup2=view.findViewById(R.id.Radgrup2);
        Radgrup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.ngf){
                    fa_id="1";
                }
                if (checkedId==R.id.gf){
                    fa_id="2";
                }
                if (checkedId==R.id.eidf){
                    fa_id="3";
                }
                if (checkedId==R.id.htef){
                    fa_id="4";
                }
                if (checkedId==R.id.ydf){
                    fa_id="5";
                }
                if (checkedId==R.id.stkf){
                    fa_id="6";
                }
                RadioButton radioButton=(RadioButton) group.findViewById(checkedId);
                fa_doly=radioButton.getText().toString();
            }
        });
        Radgrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                bal="0";
                userid="0";
                Top_or_Kaf_id="0";
                adamgozlegmeydan.setText("");
                ei="";
                kk="";
                if (checkedId==R.id.balmug){
                u_tip=1;

                    istip("1",islerMug,Mugbaha,sanawM);

                }
                if (checkedId==R.id.baltal){
                    u_tip=2;
                    istip("2",islertalyp,talypbaha,sanawt);
                }

            }
        });
button=view.findViewById(R.id.hal);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ei=ei.replace(" ","_");
        if (!userid.equals("0")){
            if (ei.length()>2){
                if (gun1>0 && ay>-1 && yyl>0 ){
            bbal.loadUrl(url+"gos.php?bal="+bal+"&kk="+kk+"&fa="+fa_doly
                            +"&g="+String.valueOf(gun1)+"&a="+String.valueOf(ay+1)+
                            "&y="+String.valueOf(yyl)+
                 "&id="+userid+"&tip="+String.valueOf(u_tip)+
                    "&ei="+ei

                    );
                    Log.e(TAG, "onClick: "+url+"gos.php?bal="+bal+"&kk="+kk+"&fa="+fa_doly
                            +"&g="+String.valueOf(gun1)+"&a="+String.valueOf(ay+1)+
                            "&y="+String.valueOf(yyl)+
                            "&id="+userid+"&tip="+String.valueOf(u_tip)+
                            "&ei="+ei


                    );
                bal="0";
                userid="0";
                Top_or_Kaf_id="0";
                adamgozlegmeydan.setText("");
                ei="";
                kk="";
                gun1=0;
                yyl=0;
                ay=-1;
                berbal.setText("");
 issene.setText("");

        } else{
                    Toast.makeText(getActivity(),"Senäni saýlaň!",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
});
            gozlegknop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
if (adamgozlegmeydan.length()>3){
    if (!fa_id.equals("0")){
        if (Integer.valueOf(log_id)>6){
    gozleg(String.valueOf(u_tip),fa_id,adamgozlegmeydan.getText().toString());
}

        if (Integer.valueOf(log_id)<7){
            if (fa_id.equals(log_id)){
                gozleg(String.valueOf(u_tip),fa_id,adamgozlegmeydan.getText().toString());
            }else {
                gozleg(String.valueOf(u_tip),fa_id,adamgozlegmeydan.getText().toString());
//             Toast.makeText(getContext(),"Bu fakultet üçin gözleg  gadagan",Toast.LENGTH_LONG).show();
            }
        }
    }
}
                }
            });
        LocalBroadcastManager.getInstance(getContext()).registerReceiver
                (broadcastReceiver, new IntentFilter("custom-message5"));

        return view;
    }
    public void istip(String tip,List list,List baha,String[] sim){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    if(ja.length()>0){
                        list.clear();
                        baha.clear();
                        Log.e(TAG, "onPostExecute:"+String.valueOf(ja.length()) );
                            for (int i=0;i<ja.length();i++)
                        {
                            jo=ja.getJSONObject(i);
                    list.add(jo.getString("ady"));
                    baha.add(jo.getString("baha"));
                          }

                        adapter=new ArrayAdapter<String>(getActivity(),R.layout.simple_spinner_item,list);
                        adapter.setDropDownViewResource( R.layout.spinner_item);

                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                              berbal.setText("Berilýän ball - "+baha.get(position).toString());
                              bal=baha.get(position).toString();
                                Log.e(TAG, "onItemSelected: "+bal );
                              ei=list.get(position).toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
bal="0";
                            }
                        });
                           }else{
                        list.clear();
                        baha.clear();
                        Log.e(TAG, "onPostExecute: 0" );
                           }

                }catch (Exception e){
                    list.clear();
                    baha.clear();

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
        fromMysql.execute(url+"isler.php?t="+tip);
        Log.e(TAG, "istip: "+url+"isler.php?t="+tip );


    }
    public void gozleg(String tip,String fakultet,String ady){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    if(ja.length()>0){
poitems.clear();
                        for (int i=0;i<ja.length();i++)
                        {
                            jo=ja.getJSONObject(i);
                            poitems.add(new poitem(jo.getString("mid"),
                                    jo.getString("kk"),
                                    jo.getString("faa")
                                    ));


                        }
recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(new poAdapter(poitems,getActivity()));
                        dialog.show();

                    }else{
                        poitems.clear();
                        Toast.makeText(getActivity(),"Netije ýok",Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    poitems.clear();
//                    Toast.makeText(getActivity(),"Serwer bilen baglanşyk ýok",Toast.LENGTH_LONG).show();
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

        fromMysql.execute(url+"gozleg.php?tip="+tip+"&fa="+fakultet+"&n="+ady);
        Log.e(TAG, "gozleg: "+url+"gozleg.php?tip="+tip+"&fa="+fakultet+"&n="+ady );



    }
    public BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getStringExtra("tip").equals("1")){
        userid=intent.getStringExtra("id");
        kk=intent.getStringExtra("kk");
            Log.e(TAG, "onReceive: "+userid +" - "+kk);

        if (dialog.isShowing()){
            dialog.dismiss();
        }}
            else {
                Toast.makeText(getActivity(),intent.getStringExtra("tip"),Toast.LENGTH_LONG).show();
            }
        }
    };
}