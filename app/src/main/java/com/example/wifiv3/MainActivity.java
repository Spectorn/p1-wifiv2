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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reads the language from the config file
        getLang(findViewById(R.id.engBtn));

    }

    public void getLang(View view) {
        // Reads from the config.cfg file
        FileInputStream fis = null;

        try {
            fis = openFileInput("config.cfg");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text2;

            while ((text2 = br.readLine()) != null) {
                sb.append(text2).append("\n");
            }
            // This is the language read from the config file...

            if (sb.toString().equals("en\n")) {
                System.out.println("Changing language to english....");
                changeLanguage(findViewById(R.id.engBtn));
            } else {
                System.out.println("Changing language to danish");
                changeLanguage(findViewById(R.id.danishBtn));
            }


        } catch (IOException e) {
            System.out.println("Oh no... couldn't read from the file");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.out.println("Bruh");
                }
            }
        }

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
        TextView scanText = findViewById(R.id.textView);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.imageView3);
        Locale local = new Locale(view.getTag().toString());
        Configuration config = new Configuration();
        config.locale = local;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Changing text according to the language parameter
        welcomeText.setText(getString(R.string.greeting));
        //scanText.setText(getString(R.string.startMeasurement));

        // Writes new lang to config.cfg
        FileOutputStream fos;
        try {
            fos = openFileOutput("config.cfg", MODE_PRIVATE);
            fos.write(view.getTag().toString().getBytes());
            fos.close();
            System.out.println("The language has been written to the file...");
        } catch (IOException e) {
            System.out.println("Bruh the file can not be created... cringe");
        }
    }
}