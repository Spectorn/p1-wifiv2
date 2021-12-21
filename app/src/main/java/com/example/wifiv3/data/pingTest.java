package com.example.wifiv3.data;

import android.util.Log;

import java.io.IOException;

public class pingTest {
    long ping;
    public pingTest() throws IOException, InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    long start = System.currentTimeMillis();
                    Runtime.getRuntime().exec("ping -c 1 172.104.152.182");
                    long end = System.currentTimeMillis();
                    ping = end - start;
                    getPing(ping);
                    System.out.println("The ping is: " + ping + " ms");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
    }

    public long getPing(long time) {
        return time;
    }
}
