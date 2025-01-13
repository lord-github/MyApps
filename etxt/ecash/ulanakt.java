package com.bbstudios.ecash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.bbstudios.ecash.optional.adaty_ulanyjy;
import com.bbstudios.ecash.yumuslar.admin_ulanyjy;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class ulanakt extends AppCompatActivity {
    FragmentManager fragmentManager;
    AppCompatButton close;
    WebView statweb;
    String url,uid,fio;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ulanakt);
        bottomNavigationView=findViewById(R.id.asakmenu2);
        adaty_ulanyjy ada1=new adaty_ulanyjy();
        admin_ulanyjy ad1=new admin_ulanyjy();
        fragmentManager=null;
        Intent intent=getIntent();
        url=intent.getStringExtra("ip");
        fio=intent.getStringExtra("fio");
        uid= intent.getStringExtra("uid");
        Log.e("TAG", "onCreate: url="+url+" fio="+fio+" uid="+uid );
        statweb=findViewById(R.id.statweb);
        statweb.loadUrl("http://"+url+"/etm/ipadd.php?uid="+uid+"&ip="+getLocalIpAddress());
        close=findViewById(R.id.closebutton);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        fragmentManager=null;
        Bundle bundle=new Bundle();
        bundle.putString("uid",uid);
        bundle.putString("ip",url);
        bundle.putString("fio",fio);
        ad1.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_left).replace(R.id.suyereulanyjy, ad1, null)
                .setReorderingAllowed(true).addToBackStack("null").commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.firstpagge){
                    fragmentManager=null;
                    fragmentManager = getSupportFragmentManager();
                    Bundle bundle=new Bundle();
                    bundle.putString("uid",uid);
                    bundle.putString("ip",url);
                    bundle.putString("fio",fio);
                    ada1.setArguments(bundle);
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,
                                    R.anim.slide_out_left).replace(R.id.suyereulanyjy, ada1, null)
                            .setReorderingAllowed(true).addToBackStack("null").commit();
                    return true;
                }

                if (item.getItemId()==R.id.secondpagge){
                    fragmentManager=null;
                    Bundle bundle=new Bundle();
                    bundle.putString("uid",uid);
                    bundle.putString("ip",url);
                    bundle.putString("fio",fio);
                    ad1.setArguments(bundle);
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,
                                    R.anim.slide_out_left).replace(R.id.suyereulanyjy, ad1, null)
                            .setReorderingAllowed(true).addToBackStack("null").commit();
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Çykmak düwmesine basyň", Toast.LENGTH_SHORT).show();
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

}