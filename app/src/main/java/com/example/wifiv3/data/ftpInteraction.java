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
    long Download;
    long Upload;
    boolean success = true;
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

                    while (!success) {
                        success = ftp.login("p1", "comtek21p1b303b");
                    }

                    ftp.enterLocalPassiveMode();
                    ftp.setBufferSize(1024*1024);
                    System.out.println("Buffer size it: " + ftp.getBufferSize());
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
                    ftp.retrieveFile("/10mb.txt", out);
                    long end = System.currentTimeMillis();
                    long dt = end - begin;
                    System.out.println("Downloaded it at the speed of " + (67.7 / (dt / 1000)) * 8 + " Mb/s");
                    dt = 1000;
                    Download = (long) ((10 / (dt / 1000)) * 8);

                    // Needs a CB function
                } catch (IOException e) {
                    System.out.println("Der er et problem");
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
                try {

                    InputStream fs = new BufferedInputStream(new FileInputStream(file));
                    System.out.println("Milestone 1");
                    long begin = System.currentTimeMillis();
                    ftp.storeFile("/upload/10mb.txt", fs);
                    long end = System.currentTimeMillis();
                    System.out.println("Milestone 2");
                    long dt = end - begin;
                    fs.close();
                    System.out.println("OMG it has been sent at a speed of " + (67.7 / (dt / 1000)) * 8 + " Mb/s. What a chad.");
                    dt = 1000;
                    Upload = (long) ((10 / (dt / 1000)) * 8);

                    // Needs a CB f
                    // unction
                    ftp.disconnect();
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

        public long getDownload() {
            return Download;
        }

        public long getUpload() {
            return Upload;
        }
    }


