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
import android.widget.ProgressBar;

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
import java.util.concurrent.TimeUnit;

public class fragment_wifitest extends Fragment implements DataSender {

    public fragment_wifitest(){

    }
    FTPClient ftp = new FTPClient();
    long Download;
    long Upload;
    long RSSI;
    String BSSID;
    int TestType;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_test, parent, false);

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            SpeedTest();
            uploadtest();
            WifiScan();
            SendToActivity(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        }





    public void WifiScan(){
        WifiManager WFM = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo WifiInfo = WFM.getConnectionInfo();

        BSSID = WifiInfo.getBSSID();
        String SSID = WifiInfo.getSSID();
        RSSI = WifiInfo.getRssi();


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
                    System.out.println("Started download test...");
                    long begin = System.currentTimeMillis();
                    ftp.retrieveFile("/airtame",out);
                    long end = System.currentTimeMillis();
                    long dt = end - begin;
                    System.out.println("Downloaded it at the speed of " + (67.7/(dt/1000))*8 + " Mb/s");
                    Download = (long) (67.7/(dt/1000))*8;
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
                    // ftp.setBufferSize(10240*10240);
                    System.out.println("Milestone 1");
                    long begin = System.currentTimeMillis();
                    ftp.storeFile("/upload/airtame", fs);
                    long end = System.currentTimeMillis();
                    System.out.println("Milestone 2");
                    long dt = end - begin;
                    fs.close();
                    System.out.println("OMG it has been sent at a speed of " + (67.7/(dt/1000))*8 + " Mb/s. What a chad.");
                    Upload = (long) ((67.7/(dt/1000))*8);

                } catch (IOException e) {
                    System.out.println("Error");
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

    public void SendToActivity(int Testnumber){
        activity_wifitest activity = (activity_wifitest) getActivity();
        activity.ReplaceFragment();
        TestType = Testnumber;




        if (activity != null) {
            activity.WifiData(TestType, Download, Upload, BSSID, RSSI);
        }
        else{
            System.out.println("Fejl i fragment");
        }





    }


    @Override
    public void WifiData(int TestType, long Download, long Upload, String BSSID, long RSSI) {


    }


    public void CallTest(int TestNumber){

        TestType = TestNumber;

        try {
            SpeedTest();
            uploadtest();
            WifiScan();
            SendToActivity(TestNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
