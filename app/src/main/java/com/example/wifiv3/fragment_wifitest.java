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

        WFM.startScan();
        List<ScanResult> result = WFM.getScanResults();
        int[] channelCounter = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        // If 5 GHz: Change network. If 2.4 GHz check if router
        // uses the most populated channel.
        if (WifiInfo.getFrequency() > 5000) {
            System.out.println("Change to another network");
        } else {
            // Appends the channel number to a list. 0th index is for 5 GHz channels
            for (ScanResult network : result) {
                channelCounter[getChannel(network.frequency) + 1] = channelCounter[getChannel(network.frequency) + 1] + 1;
            }
        }

        boolean changeChannel = false;

        // Checks if the current wifi connection is on a wifi channel,
        // which is one of the most used and sets changeChannel to true if so.
        for (int i = 0; i < channelCounter.length; i++) {
            if (getChannel(WifiInfo.getFrequency()) >= channelCounter[i]) {
                changeChannel = true;
            }
        }

        if (changeChannel) {
            System.out.println("Maybe you should change your wifi channel");
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

        TestType = TestNumber;

        try {
            ftpCon.login();
            ftpCon.SpeedTest();
            ftpCon.uploadtest();
            Download = ftpCon.getDownload();
            Upload = ftpCon.getUpload();
            WifiScan();
            SendToActivity(TestNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getChannel(int frequency) {
        final ArrayList<Integer> channelFreq = new ArrayList<>(Arrays.asList(0, 2412, 2417, 2422, 2427, 2432,
                2437, 2442, 2447, 2452, 2457, 2462, 2467, 2472, 2482));
        return channelFreq.indexOf(Integer.valueOf(frequency));
    }
}
