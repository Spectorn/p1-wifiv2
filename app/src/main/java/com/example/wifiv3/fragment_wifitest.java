package com.example.wifiv3;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import org.apache.commons.net.ftp.FTPClient;

import androidx.fragment.app.Fragment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;

public class fragment_wifitest extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){


    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WifiScan();
        SpeedTest();


    }

    public void WifiScan(){
        WifiManager WFM = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo WifiInfo = WFM.getConnectionInfo();

       String BSSID = WifiInfo.getBSSID();
       String SSID = WifiInfo.getSSID();
       int RSSIONE = WifiInfo.getRssi();
       int RSSITWO;
       int RSSITHREE;



       RSSITWO = WifiInfo.getRssi();

       RSSITHREE = WifiInfo.getRssi();

        int[] AverageRSSI = {RSSIONE,RSSITWO,RSSITHREE};




        int total = 0;

        for(int i=0; i<AverageRSSI.length; i++){
            total = total + AverageRSSI[i];
            // System.out.println(total);

            int average = total / AverageRSSI.length;
            /*

            System.out.println(average + "Average");
            System.out.println(RSSIONE + "RSSI One");
            System.out.println(RSSITWO + "RSSI Two");
            System.out.println(RSSITHREE + "RSSI Three");
            System.out.println(BSSID);
            System.out.println(SSID);

             */

        }






    }
    public void SpeedTest(){
        FTPClient ftp = new FTPClient();



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ftp.connect("172.104.152.182",21);
                    ftp.login("p1","comtek21p1b303b");
                    ftp.enterLocalPassiveMode();
                    File file = new File(String.valueOf(getContext().getFilesDir())+"/airtame");
                    OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                    long begin = System.currentTimeMillis();
                    ftp.retrieveFile("/airtame",out);
                    long end = System.currentTimeMillis();
                    long delta = end - begin;
                    System.out.println(delta);
                    System.out.println("Downloaded it at the speed of " + 67.7/(delta/1000) + " MB/s");


                }
                catch(IOException e){
                    System.out.println("Du har fucked op");
                }
            }
        });

        thread.start();




    }





}

