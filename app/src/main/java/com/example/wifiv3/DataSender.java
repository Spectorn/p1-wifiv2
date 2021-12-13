package com.example.wifiv3;

public interface DataSender {
    void WifiData(int TestType, long Download, long Upload, String BSSID, long RSSI);


}
