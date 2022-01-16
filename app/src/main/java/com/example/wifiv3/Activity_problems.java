package com.example.wifiv3;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.wifiv3.data.pingTest;
import com.example.wifiv3.data.wifiScanning;

import java.io.IOException;

public class Activity_problems extends AppCompatActivity {

    EditText ChannelMultiline;
    EditText RssiMultiline;
    EditText LatencyMultiline;


    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems);
        ChannelMultiline = (EditText) findViewById(R.id.ChannelMulti);
        RssiMultiline = (EditText) findViewById(R.id.RssiMulti);
        LatencyMultiline = (EditText) findViewById(R.id.LatencyMulti);
        InformationGather();
    }


    public void InformationGather(){
        RSSI();
        Channel();
        try {
            Latency();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    public void RSSI(){
        wifiScanning wifiScan = new wifiScanning(getApplicationContext());
        int RSSI = (int) wifiScan.getRssi();

        if(isBetween(RSSI, -40, -1)){
            RssiMultiline.setText("Din signalstyrke er god");
        }
        else if(isBetween(RSSI, -67, -40)){
            RssiMultiline.setText("Din signal styrke er fin, ingen ændringer behøves");
        }
        else if(isBetween(RSSI, -75, -67)){
            RssiMultiline.setText("Din signalstyrke er acceptabel, men kan forbedres");
        }
        else if(isBetween(RSSI, -80, -75)){
            RssiMultiline.setText("Din signalstyrke er forholdsvis lav, og du kan opleve udfald");
        }
        else{
            RssiMultiline.setText("Din signalstyrke er rigtig dårlig og dette kan forudsage problemer for dit Wi-fi");
        }

    }

    public void Latency() throws IOException, InterruptedException {
        long ping;
        pingTest Latency = new pingTest();

        ping = Latency.pingTest();

        if(ping < 50) {
            LatencyMultiline.setText("Der er meget lidt forsinkelse");
        }
        else if(isBetween((int) ping,50, 100)){
            LatencyMultiline.setText("Du har en normal forsinkelse");
        }
        else if(isBetween((int) ping,100, 150)){
            LatencyMultiline.setText("Du har en udsædvanlig høj ping og kan muligvis opleve problemer");
        }
        else{
            LatencyMultiline.setText("Der er en utrolig høj forsinkelse og dette skaber problemer");
        }



    }
    public void Channel(){
        wifiScanning wifiScan = new wifiScanning(getApplicationContext());
        boolean ChangeChannel = wifiScan.WifiScan();
        if (ChangeChannel = true){
            ChannelMultiline.setText("Der er mange enheder på samme Wi-fi kanal. overvej at skifte kanal");
        }
        else{
            ChannelMultiline.setText("Du befinder dig på¨en okay Wi-Fi kanal");
        }


    }


}


