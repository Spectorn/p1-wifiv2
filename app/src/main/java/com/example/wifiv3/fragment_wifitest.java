package com.example.wifiv3;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.net.ftp.FTPClient;

import androidx.fragment.app.Fragment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class fragment_wifitest extends Fragment {
    FTPClient ftp = new FTPClient();
    Button scanNow,scanStart, english;
    TextView welcomeText, scanText;

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
        /*scanNow = getView().findViewById(R.id.StartTesten);
        scanStart = getView().findViewById(R.id.ScanButton);
        welcomeText = getView().findViewById(R.id.textView2);
        scanText = getView().findViewById(R.id.textView3);

         */


        try {
            SpeedTest();
            WifiScan();
            uploadtest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


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
            int average = total / AverageRSSI.length;


        }






    }

    public void SpeedTest() throws InterruptedException {
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
                    long dt = end - begin;
                    System.out.println("Downloaded it at the speed of " + (67.7/(dt/1000))*8 + " Mb/s");
                }
                catch(IOException e){
                    System.out.println("Du har fucked op");
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Download thread borked");
        }




    }

    public void uploadtest() throws InterruptedException {
        Thread uploadthread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Upload thread has started!!");
                File file = new File(String.valueOf(getContext().getFilesDir()) + "/airtame");
                try {
                    InputStream fs = new BufferedInputStream(new FileInputStream(file));
                    ftp.setBufferSize(10240*10240);
                    System.out.println("Bruh 1");
                    long begin = System.currentTimeMillis();
                    ftp.storeFile("/upload/airtame", fs);
                    long end = System.currentTimeMillis();
                    System.out.println("Bruh 2");
                    long dt = end - begin;
                    fs.close();
                    System.out.println("OMG it has been sent at a speed of " + (67.7/(dt/1000))*8 + " Mb/s. What a chad.");
                } catch (IOException e) {
                    System.out.println("Piss off, low rank");
                    System.out.println(e);
                }
            }

        });
        uploadthread.start();

        try {
            uploadthread.join();
        } catch (InterruptedException e) {
            System.out.println("upload thread borked");
        }
    }





}

