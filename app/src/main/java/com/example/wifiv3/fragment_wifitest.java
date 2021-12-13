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

import org.apache.commons.net.ftp.FTPClient;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

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

public class fragment_wifitest extends Fragment {

    FTPClient ftp = new FTPClient();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_test, parent, false);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        File file = new File(getContext().getFilesDir()+"/airtame");
        ftpInteraction ftpCon = new ftpInteraction(file);
        try {
            ftpCon.login();
            ftpCon.SpeedTest();
            ftpCon.uploadtest();
        } catch (InterruptedException e) {
            System.out.println("Bruh");
        }



        }



    public void WifiScan(){
        WifiManager WFM = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo WifiInfo = WFM.getConnectionInfo();

        String BSSID = WifiInfo.getBSSID();
        String SSID = WifiInfo.getSSID();
        int RSSI = WifiInfo.getRssi();


        }
}
