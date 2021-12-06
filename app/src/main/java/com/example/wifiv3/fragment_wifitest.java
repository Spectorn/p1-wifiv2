package com.example.wifiv3;



import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

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

    }

    public void WifiScan(){
        WifiManager WFM = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);




        WFM.getScanResults();









    }




}

