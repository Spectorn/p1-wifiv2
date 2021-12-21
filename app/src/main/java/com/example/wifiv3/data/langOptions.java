package com.example.wifiv3.data;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.wifiv3.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Scanner;

public class langOptions extends View{
    public langOptions(Context context) {
        super(context);
    }

    public void setLang(String lang) throws  IOException{
        FileWriter myWriter = new FileWriter(getContext().getFilesDir() + "/config.cfg");
        myWriter.write(lang);
        myWriter.close();
        getLang();
    }

    // Reads the language
    public String getLang() throws IOException{
        File file= new File(getContext().getFilesDir() + "/config.cfg");

        // Reads the lines in the text file
        Scanner myReader = new Scanner(file);
        String lang = null;

        while (myReader.hasNextLine()) {
            lang = myReader.nextLine();
        }

        return lang;
    }

    public void changeLanguage(View view, String lang, int activityNum) throws IOException{
        // Ensuring the text phrases gets pulled from the right strings.xml
        Locale local = new Locale(lang);
        Configuration config = new Configuration();
        config.locale = local;

        // Updating the strings.xml file to get pulled from
        getResources().updateConfiguration(config, getContext().getResources().getDisplayMetrics());

        // Updates text depending on which activity is calling.
        // 1: MainActivity
        // 2: activity_wifitest
        if (activityNum == 1) {
            TextView welcomeText = view.getRootView().findViewById(R.id.textView2);
            welcomeText.setText(getResources().getString(R.string.greeting));
        } else if (activityNum == 2) {
            TextView scanText = view.getRootView().findViewById(R.id.textView);
            Button startScan = view.getRootView().findViewById(R.id.ScanButton);
            scanText.setText(getContext().getString(R.string.scanGreeting));
            startScan.setText(getResources().getString(R.string.scanButton));
        }

        setLang(lang);
    }
}
