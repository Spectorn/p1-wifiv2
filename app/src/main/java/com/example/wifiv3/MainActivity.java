package com.example.wifiv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
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
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public void changeLanguage(View view) {
        TextView welcomeText = findViewById(R.id.textView2);
        Locale local = new Locale("en");
        Configuration config = new Configuration();
        config.locale = local;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Changing text according to the language parameter
        welcomeText.setText(getString(R.string.greeting));
        //scanText.setText(getString(R.string.scanGreeting)); WIP


    }
}