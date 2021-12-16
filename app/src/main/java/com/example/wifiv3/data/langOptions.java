package com.example.wifiv3.data;

import android.content.res.Configuration;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.wifiv3.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class langOptions {
    File file;

    public void longOptions(File files) {
        file = files;
    }
    /////
    ////
    //// WORK IN PROGRESS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    ////
    ////

    /*
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

     */



}
