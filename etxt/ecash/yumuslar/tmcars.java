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


public class tmcars extends Fragment {
View view;
    String zakazid="1",ipaddress,saylanandili,tesgor;
    Spinner dili,gor;
    EditText tmcarsAdy,tmcarssany,tmdusun,tmsimsan;
    Button tmupload;
    WebView inserttmcars;
    String userbalance="0.0";
    public tmcars() {

    }

    public static tmcars newInstance(String param1, String param2) {
        tmcars fragment = new tmcars();
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
        view = inflater.inflate(R.layout.fragment_tmcars, container, false);
        Bundle arguments = getArguments();
        if (arguments!=null){
            zakazid=arguments.getString("zakazid");
            ipaddress=arguments.getString("ip");
        }
        tmcarsAdy=view.findViewById(R.id.tmady);
        tmcarssany=view.findViewById(R.id.tmsan);
        tmdusun=view.findViewById(R.id.tmdusun);
        tmsimsan=view.findViewById(R.id.tmsim);
        dili=view.findViewById(R.id.tmdili);
        gor=view.findViewById(R.id.tmtematika);
        tmsimsan.setText("0");
        tmdusun.setText("");
        balance();
        tmcarssany.setText("5");
        tmcarsAdy.setText("");
        tmupload=view.findViewById(R.id.tmupp);
        String[] diller = {"Türkmen dili", "Rus dili", "Iňlis dili", "Pars dili","Türk dili"};
        String[] tem = {"Umumy", "Mahabatlandyrylan"};
        String jynsy="0";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),
                R.layout.custom_spinner_item, tem);
        adapter.setDropDownViewResource(R.layout.custom_spinner_item);
        gor.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(view.getContext(), R.layout.custom_spinner_item, diller);
        adapter.setDropDownViewResource(R.layout.custom_spinner_item);
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
        gor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tesgor= (String) adapterView.getItemAtPosition(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        inserttmcars=view.findViewById(R.id.tmokla);
        tmupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Float.valueOf(userbalance)>=(0.1*Float.valueOf(tmcarssany.getText().toString())))
                {

                    inserttmcars.loadUrl("http://" + ipaddress + "/etm/insert.php?name=" +
                            tmcarsAdy.getText().toString().trim()
                            + "&zakazid=" + zakazid + "&jemi=" + tmcarssany.getText().toString().trim() +
                            "&tipID=" + "5" + "&bir_baha=" + "0.1" + "&jyns=" + jynsy + "&dusun=" +
                            tmdusun.getText().toString().trim() + "&tesgor=" + tesgor +
                            "&simsan=" + tmsimsan.getText().toString().trim() + "&dili=" + saylanandili
                    );
                    Log.e("tmcars", "http://" + ipaddress + "/etm/insert.php?name=" +
                            tmcarsAdy.getText().toString().trim()
                            + "&zakazid=" + zakazid + "&jemi=" + tmcarssany.getText().toString().trim() +
                            "&tipID=" + "5" + "&bir_baha=" + "0.1" + "&jyns=" + jynsy + "&dusun=" +
                            tmdusun.getText().toString().trim() + "&tesgor=" + tesgor +
                            "&simsan=" + tmsimsan.getText().toString().trim() + "&dili=" + saylanandili );
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            obnovi();
                        }
                    }, 1500);
                } else{
                    Toast.makeText(getActivity(),"Siziň balansyňyz ýeterlikli däl",Toast.LENGTH_SHORT).show();

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