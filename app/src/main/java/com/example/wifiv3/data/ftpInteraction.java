package com.example.wifiv3.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.net.ftp.FTPClient;


public class ftpInteraction {
    FTPClient ftp = new FTPClient();
    File file;

    public ftpInteraction(File files) {
        file = files;
    }


    public void login() {
        Thread loginThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Loggin in");
                    ftp.connect("172.104.152.182", 21);
                    ftp.login("p1", "comtek21p1b303b");
                    ftp.enterLocalPassiveMode();
                } catch (IOException e) {
                    System.out.println("Login failed...");
                }
            }
        });
        loginThread.start();
        try {
            loginThread.join();
        } catch (InterruptedException e) {
            System.out.println("Download thread borked");
        }
    }


    public void SpeedTest() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //File file = new File(String.valueOf(getContext().getFilesDir()) + "/airtame");
                    OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                    System.out.println("Started download test...");
                    long begin = System.currentTimeMillis();
                    ftp.retrieveFile("/airtame", out);
                    long end = System.currentTimeMillis();
                    long dt = end - begin;
                    System.out.println("Downloaded it at the speed of " + (67.7 / (dt / 1000)) * 8 + " Mb/s");

                    // Needs a CB function
                } catch (IOException e) {
                    System.out.println("Du har fucked op");
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Download thread borked");
        }
    }

    public void uploadtest() throws InterruptedException {
        Thread uploadthread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Upload thread has started!!");
                //File file = new File(String.valueOf(getContext().getFilesDir()) + "/airtame");
                try {
                    InputStream fs = new BufferedInputStream(new FileInputStream(file));
                    ftp.setBufferSize(10240 * 10240);
                    System.out.println("Milestone 1");
                    long begin = System.currentTimeMillis();
                    ftp.storeFile("/upload/airtame", fs);
                    long end = System.currentTimeMillis();
                    System.out.println("Milestone 2");
                    long dt = end - begin;
                    fs.close();
                    System.out.println("OMG it has been sent at a speed of " + (67.7 / (dt / 1000)) * 8 + " Mb/s. What a chad.");

                    // Needs a CB function
                } catch (IOException e) {
                    System.out.println("Error");
                    System.out.println(e);
                }
            }

        });
        uploadthread.start();


        try {
            uploadthread.join();
        } catch (InterruptedException e) {
            System.out.println("upload thread borked");
        }


    }


}
