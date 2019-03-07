package com.example.luisgfoliveira1.lojinhob;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Splash extends AppCompatActivity {

    ProgressBar pb;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread tela = new Thread(){
            @Override
            public void run(){
                try{
                    super.run();
                    sleep(2000);//2segundos
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent tlogin = new Intent(Splash.this,Login.class);
                    startActivity(tlogin);
                    finish();
                }
            }
        };
        tela.start();


    }
}
