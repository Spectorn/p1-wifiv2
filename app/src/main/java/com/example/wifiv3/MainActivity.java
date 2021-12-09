package com.example.wifiv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.StartTesten);


    }


    public void StartTest(View view) {
        FragmentManager fm = getSupportFragmentManager();
        WifiManager WFM = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        DialogFragment PopUpWindow = new PopUpWindow();

        Intent intent = new Intent(this, activity_wifitest.class);

        if (WFM.isWifiEnabled() == true) {
            startActivity(intent);
        } else {
            PopUpWindow.show(getSupportFragmentManager(), "PopUp");
        }


    }
}