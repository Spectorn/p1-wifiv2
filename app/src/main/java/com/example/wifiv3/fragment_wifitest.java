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

import com.example.wifiv3.data.ftpInteraction;


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
import java.io.*;

public class fragment_wifitest extends Fragment implements DataSender {

    public fragment_wifitest(){

    }

    long Download;
    long Upload;
    long RSSI;
    String BSSID;
    int TestType;
    // Fix this shit...




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_test, parent, false);

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File file = new File(String.valueOf(getContext().getFilesDir()) +"/airtame");
        ftpInteraction ftpCon = new ftpInteraction(file);

        try {
            ftpCon.login();
            ftpCon.SpeedTest();
            ftpCon.uploadtest();
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







    // det igennem denne funktion at den indsamlede data bliver sendt til
    public void SendToActivity(int Testnumber){
        activity_wifitest activity = (activity_wifitest) getActivity();
        activity.ReplaceFragment();
        TestType = Testnumber;

        // sender til activity
            activity.WifiData(TestType, Download, Upload, BSSID, RSSI);







    }


    @Override
    public void WifiData(int TestType, long Download, long Upload, String BSSID, long RSSI) {


    }

    // Det funktionen activity kalder når denne fragment skal lave testen
    public void CallTest(int TestNumber){
        File file = new File(String.valueOf(getContext().getFilesDir()) +"/airtame");
        ftpInteraction ftpCon = new ftpInteraction(file);

        TestType = TestNumber;

        try {
            ftpCon.login();
            ftpCon.SpeedTest();
            ftpCon.uploadtest();
            WifiScan();
            SendToActivity(TestNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
