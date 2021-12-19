package com.example.wifiv3.data;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.view.View;
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
        FileWriter myWriter = new FileWriter(getContext().getFilesDir() + "/bruh.txt");
        myWriter.write(lang);
        myWriter.close();
        System.out.println("wew læds!!!!!!!!!!!!");
    }

    public String getLang() throws IOException{
        File bruh = new File(getContext().getFilesDir() + "/bruh.txt");
        Scanner myReader = new Scanner(bruh);

        while (myReader.hasNextLine()) {
            System.out.println(myReader.nextLine());
        }
        System.out.println("OMG THE CONTENT OF THE FILE HAS BEEN WRITTEN AND READ TO!!!!!!!!!");
        return "wew";
    }

    public void updateText() {

    }


    public void changeLanguage(View view) throws IOException{
        TextView welcomeText = view.getRootView().findViewById(R.id.textView2);
        //TextView scanText = getRootView().findViewById(R.id.textView);

        // Ensuring the text phrases gets pulled from the right strings.xml
        Locale local = new Locale(view.getTag().toString());
        Configuration config = new Configuration();
        config.locale = local;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Changing text according to the language parameter
        welcomeText.setText(getContext().getString(R.string.greeting));
        //scanText.setText(getContext().getString(R.string.scanGreeting));

        setLang(view.getTag().toString());
    }
}
