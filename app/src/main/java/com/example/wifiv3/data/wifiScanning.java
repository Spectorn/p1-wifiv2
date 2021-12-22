package com.example.wifiv3.data;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class wifiScanning extends View {
    public wifiScanning(Context ctx) {
        super(ctx);
    }

    public boolean WifiScan(){
        WifiManager WFM = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo WifiInfo = WFM.getConnectionInfo();

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

        // Checks if the current wifi connection is on a wifi channel,
        // which is one of the most used and sets changeChannel to true if so.
        for (int i = 0; i < channelCounter.length; i++) {
            if (getChannel(WifiInfo.getFrequency()) - 1 > channelCounter[i] && channelCounter[i] != 0) {
                System.out.println("Maybe you should change your wifi channel");
                return true;
            }
        }
        return false;
    }

    public int getChannel(int frequency) {
        final ArrayList<Integer> channelFreq = new ArrayList<>(Arrays.asList(0, 2412, 2417, 2422, 2427, 2432,
                2437, 2442, 2447, 2452, 2457, 2462, 2467, 2472, 2482));
        return channelFreq.indexOf(Integer.valueOf(frequency));
    }

    public long getRssi (){
        WifiManager WFM = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo WifiInfo = WFM.getConnectionInfo();

        long RSSI;
       RSSI = WifiInfo.getRssi();
       return RSSI;
    }

}
