package com.bbstudios.reyting;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
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

public class liderFragment extends Fragment {
    String url="",ip="";
    String sutun_Ady="Fakultet\n\n(Kafedra)";
    Switch lidersw,liderradio;
    int user_gozleg=1,data_tip=0,yyl=0,ay=0;
    RadioGroup talypradio;
    TextView tyyl;
    Bundle bundle;
    List<kafitem> kafitems;
    EditText lideradamgozlegmeydan,lidergirizmeydan;
    ImageView lidergozlegknop;
    RecyclerView recyclerView;
    Dialog dialog;
    Button bellemek;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_lider, container, false);
        lidersw=view.findViewById(R.id.lidersw);
        kafitems=new ArrayList<>();
        bundle=getArguments();
        if (bundle!=null){

            ip=bundle.getString("ip");
            url="http://"+ip+"/rey/";
        }

        talypradio=view.findViewById(R.id.talypradio);
        talypradio.setVisibility(View.INVISIBLE);
        tyyl=view.findViewById(R.id.yyl);
        liderradio=view.findViewById(R.id.liderradio);
        Calendar calendar=Calendar.getInstance();
        yyl=calendar.get(Calendar.YEAR);
        tyyl.setText(String.valueOf(yyl));
        recyclerView=view.findViewById(R.id.lidernetije);
        lidergozlegknop=view.findViewById(R.id.lidergozlegknop);
        lideradamgozlegmeydan=view.findViewById(R.id.lideradamgozlegmeydan);
        liderradio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    data_tip=1;
                    ay=calendar.get(Calendar.MONTH);
                    lsanaw(String.valueOf(user_gozleg),String.valueOf(yyl)+"&a="+String.valueOf(ay+1));
                } else {
                    ay=0;
                    data_tip=0;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        DatePickerDialog datePickerDialog =
                                new DatePickerDialog(getActivity());
                        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                yyl=year;
                                tyyl.setText(String.valueOf(yyl));
                                lsanaw(String.valueOf(user_gozleg),String.valueOf(yyl));
                            }
                        });
                        datePickerDialog.show();
                    }
                }
            }
        });
        talypradio.setVisibility(View.INVISIBLE);
        lidersw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    sutun_Ady="Fakultet\n\n(Topar)";
                    talypradio.setVisibility(View.INVISIBLE);
                    user_gozleg=2;
                } else {
                    user_gozleg=1;
                    talypradio.setVisibility(View.INVISIBLE);
                    sutun_Ady="Fakultet\n\n(Kafedra)";
                }
                liderradio.setChecked(false);
                lsanaw(String.valueOf(user_gozleg),String.valueOf(yyl));
            }
        });
        dialog=new Dialog(getActivity());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.yazgy);
        bellemek=dialog.findViewById(R.id.bellemek);
        lidergirizmeydan=dialog.findViewById(R.id.lidergirizmeydan);

        talypradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.liderall){}

                if (checkedId==R.id.lidertopar){}

                if (checkedId==R.id.liderkurs){}
            }
        });


        lsanaw(String.valueOf(user_gozleg),String.valueOf(yyl));
        return view;
    }



    public void lsanaw(String tip,String yyl){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    if(ja.length()>0){
                        kafitems.clear();
                        kafitems.add(new kafitem("0","FAA",sutun_Ady,"Bal"));
                          for (int i=0;i<ja.length();i++)
                        {
                            String gosulma="";
                            jo=ja.getJSONObject(i);
                            if (tip.equals("1")){
                                gosulma=jo.getString("kga");
                            } else {
                                gosulma=jo.getString("okaf");
                            }
                            kafitems.add(new kafitem("0",jo.getString("faa"),
                                    jo.getString("ga")+"\n"+gosulma,jo.getString("gork")

                                    ));
                                }
                             }else{
                        kafitems.clear();
                        kafitems.add(new kafitem("0","FAA",sutun_Ady,"Bal"));
                                   }

                }catch (Exception e){
                    kafitems.clear();
                    kafitems.add(new kafitem("0","FAA",sutun_Ady,"Bal"));
                            }
recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(new kafAdapter(kafitems,getActivity()));

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
        fromMysql.execute(url+"mugyyl2.php?tip="+tip+"&y="+yyl);
        Log.e(TAG, "doldur: "+url+"mugyyl2.php?tip="+tip+"&y="+yyl);


    }

}