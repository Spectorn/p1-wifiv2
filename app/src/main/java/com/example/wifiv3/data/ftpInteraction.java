package com.example.wifiv3.data;

import android.content.Context;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;

public class ftpInteraction extends View {
    FTPClient ftp = new FTPClient();
    long Download;
    long Upload;
    File file;

    // UI layer injects an application context into the constructor
    public ftpInteraction(Context ctx) {
        super(ctx);
        file = new File(getContext().getFilesDir() + "/10mb.txt");
    }

    public void login() {
        Thread loginThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Throws IOException. Replace "<HOSTNAME>" with IP-address
                    ftp.connect("172.104.152.182", 21);
                    // Throws IOException. Replace "<USERNAME>" and "<PASSWORD>" with a valid account
                    ftp.login("p1", "comtek21p1b303b");
                    // Opens data port for FTP file transfers
                    ftp.enterLocalPassiveMode();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        });
        loginThread.start();
        try {
            loginThread.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }


    public void downloadTest() throws InterruptedException {
        Thread downloadThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OutputStream out = new BufferedOutputStream(new FileOutputStream(file));

                    // Starts speedtest
                    long begin = System.currentTimeMillis();
                    // Throws IOException
                    ftp.retrieveFile("/10mb.txt", out);
                    long end = System.currentTimeMillis();
                    // Throws IOException
                    out.close();

                    // Calculates time it takes to complete
                    long dt = end - begin;

                    // Converts download speed to Mb/s
                    Download = (long) ((10.48 / (dt / 1000)) * 8);
                    System.out.println("The download speed is: " + Download + " Mb/s");
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        });
        downloadThread.start();
        try {
            // Throws InterruptedException
            downloadThread.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void uploadTest() {
        Thread uploadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Throws FileNotFoundException (Goes under IOException)
                    InputStream fs = new BufferedInputStream(new FileInputStream(file));

                    // Starts upload test
                    long begin = System.currentTimeMillis();
                    // Throws IOException
                    ftp.storeFile("/upload/10mb.txt", fs);
                    long end = System.currentTimeMillis();
                    // Throws IOException
                    fs.close();

                    // Calculates time it takes to complete
                    long dt = end - begin;

                    // Converts to upload speed to Mb/s
                    Upload = (long) ((10.48 / (dt / 1000)) * 8);
                    System.out.println("The upload speed is: " + Upload + " Mb/s");

                    // Closes FTP connection
                    ftp.disconnect();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        });
        uploadThread.start();

        try {
            // Throws InterruptedException
            uploadThread.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
        // Callback function
        public long getDownload() {
             return Download;
        }

        // Callback function
        public long getUpload() {
            return Upload;
        }
    }