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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bbstudios.ecash.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class yandex extends Fragment {

    String zakazid="1";
    Spinner gornus,dili;
    View view;
    String tip="1",nameof,zadaniyasany,jynsy,dusun,birinin_bahasy,tesgor,simsan,saylanandili,ipaddress;
    EditText Editsimsan,Editname,yumusany,editregion,upperpay,Editdusungoogle;
    Button sendgooglemap;
    WebView insertmaps;
    String userbalance="0.0";
    public yandex() {
        // Required empty public constructor
    }


    public static yandex newInstance(String param1, String param2) {
        yandex fragment = new yandex();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_yandex, container, false);;
        Bundle arguments = getArguments();
        if (arguments!=null){
            zakazid=arguments.getString("zakazid");
            ipaddress=arguments.getString("ip");
            tip=arguments.getString("tip");
        }
        dili=view.findViewById(R.id.tesdiliyan);
        gornus=view.findViewById(R.id.tematikayan);
        Editname=view.findViewById(R.id.nameOfyan);
        Editsimsan=view.findViewById(R.id.sanysimyan);
        yumusany=view.findViewById(R.id.yumusanyyan);
        String[] diller = {"Türkmen dili", "Rus dili", "Iňlis dili", "Pars dili","Türk dili"};
        String[] tem = {"Umumy", "Mahabatlandyrylan"};
        jynsy="0";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),
                R.layout.custom_spinner_item, tem);

        adapter.setDropDownViewResource(R.layout.custom_spinner_item);
        gornus.setAdapter(adapter);
        saylanandili="Türkmen dili";
        tesgor="Umumy";
        Editdusungoogle=view.findViewById(R.id.Editdusungoogleyan);
        sendgooglemap=view.findViewById(R.id.sendgooglemapyan);
        gornus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tesgor= (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        balance();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(view.getContext(), R.layout.custom_spinner_item, diller);
        adapter.setDropDownViewResource(R.layout.custom_spinner_item); // Optional: same style for dropdown
        dili.setAdapter(adapter2);
        dili.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                saylanandili= (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        insertmaps=view.findViewById(R.id.insertmapsyan);

        sendgooglemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Float.valueOf(userbalance)>=(0.2*Float.valueOf(yumusany.getText().toString()))){
                    nameof=Editname.getText().toString().trim();
                    birinin_bahasy="0.2";
                    zadaniyasany=yumusany.getText().toString().trim();
                    dusun=Editdusungoogle.getText().toString().trim();
                    simsan=Editsimsan.getText().toString().trim();
                    insertmaps.loadUrl("http://"+ipaddress+"/etm/insert.php?name="+nameof+"&zakazid="+zakazid+"&jemi="+zadaniyasany+
                            "&tipID="+tip+"&bir_baha="+"0.2"+"&jyns="+jynsy+"&dusun="+dusun+"&tesgor="+tesgor+
                            "&simsan="+simsan+"&dili="+saylanandili
                    );
                    Log.e("yandex", "http://"+ipaddress+"/etm/insert.php?name="+nameof+"&zakazid="+zakazid+"&jemi="+zadaniyasany+
                            "&tipID="+tip+"&bir_baha="+birinin_bahasy+"&jyns="+jynsy+"&dusun="+dusun+"&tesgor="+tesgor+
                            "&simsan="+simsan+"&dili="+saylanandili);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            obnovi();
                        }
                    },1500);
                } else {
                    Toast.makeText(getActivity(),"Siziň balansyňyz ýeterlikli däl",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return  view;
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