package com.example.wifiv3.data;

import android.util.Log;

import java.io.IOException;

public class pingTest {
    public pingTest() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        long start = System.currentTimeMillis();
        Process proc = runtime.exec("ping -c 1 172.104.152.182");
        long end = System.currentTimeMillis();
        long dt = end-start;

        System.out.println("The ping is: " + dt + " ms");
    }
}
