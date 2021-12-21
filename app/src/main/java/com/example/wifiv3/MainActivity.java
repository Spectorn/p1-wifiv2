package com.example.wifiv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import com.example.wifiv3.data.langOptions;

public class MainActivity extends AppCompatActivity {
    // test
    langOptions language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dependency injection
        language = new langOptions(getApplicationContext());

        try {
            language.changeLanguage(findViewById(R.id.danishBtn), language.getLang(), 1);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Before testing this method to check if wifi is turned on
    public void StartTest(View view) {
        FragmentManager fm = getSupportFragmentManager();
        WifiManager WFM = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        DialogFragment PopUpWindow = new PopUpWindow();

        Intent intent = new Intent(this, activity_wifitest.class);

        // Checks if wifi is turned on
        if (WFM.isWifiEnabled() == true) {
            startActivity(intent);
        } else {
            PopUpWindow.show(getSupportFragmentManager(), "PopUp");
        }
    }

    // Language buttons calls this method on click event
    public void changeLanguage(View v) throws IOException{
        language.changeLanguage(v, v.getTag().toString(), 1);
        language.setLang(v.getTag().toString());
    }
}
