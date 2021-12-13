package com.example.wifiv3.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

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
                    long Download = (long) ((67.7 / (dt / 1000)) * 8);

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
                    //ftp.setBufferSize(10240 * 10240);
                    System.out.println("Milestone 1");
                    long begin = System.currentTimeMillis();
                    ftp.storeFile("/upload/airtame", fs);
                    long end = System.currentTimeMillis();
                    System.out.println("Milestone 2");
                    long dt = end - begin;
                    fs.close();
                    System.out.println("OMG it has been sent at a speed of " + (67.7 / (dt / 1000)) * 8 + " Mb/s. What a chad.");
                    long Upload = (long) ((67.7 / (dt / 1000)) * 8);

                    // Needs a CB f
                    // unction
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
    ////////
    ///////
    /////// This function is taken from https://github.com/ashishvshenoy/iperf-java/blob/master/iperf/src/iperf/Iperfer.java
    ///////
    ///////
    public void iperf() {
        String hostName = "172.104.152.182";
        int portNumber = 5201;
        int time = 5;

        try (
                Socket tcpSocket = new Socket(hostName, portNumber);
                PrintWriter out =
                        new PrintWriter(tcpSocket.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(tcpSocket.getInputStream()));
        ) {
            long totalTime = (long) (time*Math.pow(10,9));
            long startTime = System.nanoTime();
            boolean toFinish = false;
            long totalNumberOfBytes = 0;
            while(!toFinish){
                byte[] dataChunk = new byte[1000];
                totalNumberOfBytes+=(long)1000;
                Arrays.fill(dataChunk, (byte)0);
                out.println(dataChunk);
                in.readLine();
                toFinish = (System.nanoTime() - startTime >= totalTime);
            }
            int sentInKB = (int) (totalNumberOfBytes/1024);
            long rate = (totalNumberOfBytes/(long)Math.pow(2,20 ))/time;
            System.out.print("sent="+sentInKB+"KB rate="+rate+"Mbps");
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }


}
