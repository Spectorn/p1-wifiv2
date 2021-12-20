package com.example.wifiv3;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wifiv3.data.ftpInteraction;
import com.example.wifiv3.data.pingTest;
import com.example.wifiv3.data.wifiScanning;


import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class fragment_wifitest extends Fragment implements DataSender {

    public fragment_wifitest(){

    }

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
        File file = new File(String.valueOf(getContext().getFilesDir()) +"/10mb.txt");
        ftpInteraction ftpCon = new ftpInteraction(file);
        wifiScanning wifiScan = new wifiScanning(getContext().getApplicationContext());

        try {
            pingTest ping = new pingTest();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        try {
            ftpCon.login();
            ftpCon.SpeedTest();
            ftpCon.uploadtest();
            Download = ftpCon.getDownload();
            Upload = ftpCon.getUpload();
            wifiScan.WifiScan();
            //WifiScan();
            SendToActivity(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // det igennem denne funktion at den indsamlede data bliver sendt til
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

    // Det funktionen activity kalder når denne fragment skal lave testen
    public void CallTest(int TestNumber){
        File file = new File(String.valueOf(getContext().getFilesDir()) +"/testPDF");
        ftpInteraction ftpCon = new ftpInteraction(file);
        wifiScanning wifiScan = new wifiScanning(getContext().getApplicationContext());

        TestType = TestNumber;

        try {
            ftpCon.login();
            ftpCon.SpeedTest();
            ftpCon.uploadtest();
            Download = ftpCon.getDownload();
            Upload = ftpCon.getUpload();
            wifiScan.WifiScan();
            SendToActivity(TestNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
