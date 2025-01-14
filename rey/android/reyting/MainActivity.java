package com.bbstudios.reyting;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bbstudios.reyting.poisk.poAdapter;
import com.bbstudios.reyting.poisk.poitem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String url="",ip="durzujukk-001-site1.etempurl.com";
    EditText login,parol;
    Dialog IpDialog;
    EditText ipgiriz,ipgi;
    Button ipal;
    int bas=0;
    ImageView s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.gir);
        ipgi=findViewById(R.id.ipgi);
          url="http://"+ip+"/rey/";
        s1=findViewById(R.id.s1);
        login=findViewById(R.id.login);
        parol=findViewById(R.id.password);
        IpDialog=new Dialog(MainActivity.this);
        IpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        IpDialog.setContentView(R.layout.ipdialog);
        ipgiriz=IpDialog.findViewById(R.id.ipedit);
        ipal=IpDialog.findViewById(R.id.ipbutton);
        ipgiriz.setText(ip);
ipal.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (ipgiriz.length()>5){
            ip=ipgiriz.getText().toString();
              url="http://"+ipgiriz.getText().toString()+"/rey/";
            IpDialog.dismiss();
        }
    }
});
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               bas++;
               if (bas>6){
if (!IpDialog.isShowing()){
    IpDialog.show();
} else {
    IpDialog.dismiss();
}
               }
            }
        });
        ipgi.setText(ip);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login.length()>0 && parol.length()>0){
ip=ipgi.getText().toString();
                    url="http://"+ip+"/rey/";
            lp(login.getText().toString(),parol.getText().toString());
                }

            }
        });
    }

    public void lp(String l,String p ){
        class FromMysql extends AsyncTask<String,Void,String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;
                    if(ja.length()>0){

                        login.setText("");
                        parol.setText("");
                        for (int i=0;i<ja.length();i++)
                        {
                            jo=ja.getJSONObject(i);
//okla(jo.getString("fcl"));
Intent intent=new Intent(MainActivity.this,esas.class);
intent.putExtra("fc",jo.getString("fcl"));
intent.putExtra("ip",ip);
                            startActivity(intent);
                        }


                    }else{

                        login.setText("");
                        parol.setText("");
                        Toast.makeText(getApplicationContext(),"Netije ýok",Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){

                    login.setText("");
                    parol.setText("");
                    Toast.makeText(getApplicationContext(),"Serwer bilen baglanşyk ýok",Toast.LENGTH_LONG).show();
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

        fromMysql.execute(url+"lpas.php?l="+l+"&p="+p);
        Log.e(TAG, "gozleg:"+url+"lpas.php?l="+l+"&p="+p);



    }


}