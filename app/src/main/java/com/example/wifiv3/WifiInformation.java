package com.example.wifiv3;

import java.util.NavigableMap;
import java.util.TreeMap;

public class WifiInformation {
    long Download;
    long Upload;
    long RSSI;
    long BasisDownload;
    long BasisUpload;
    long BasisRSSI;

    public WifiInformation(long download, long upload, long RSSI, long basisDownload, long basisUpload, long basisRSSI) {
        Download = download;
        Upload = upload;
        this.RSSI = RSSI;
        BasisDownload = basisDownload;
        BasisUpload = basisUpload;
        BasisRSSI = basisRSSI;
    }

    public int CheckDownload(){
        System.out.println(Download +BasisDownload+Upload+BasisUpload);

        // Added 185 to counter scoring init
        int Score = 285;
        int Result;

        final NavigableMap<Double, Integer> scoring = new TreeMap<Double, Integer>();
        scoring.put(0.0, Score -= 100);
        scoring.put(3.0, Score -= 80);
        scoring.put(25.0, Score -= 60);
        scoring.put(50.0, Score -= 30);
        scoring.put(800.0, Score -= 15);

        System.out.println("Score efter første test" + Score);
        Result = DownloadToBasis(scoring.get(scoring.floorKey((double) Download)));
        System.out.println("Download Result er:" + Result);

        return Result;
    }

    public int DownloadToBasis(int Score){
        long Diff = BasisDownload - Download;
        // hvis der er et fald på mere end 50% så er det et problem

        if (Diff > BasisDownload/2 ){
            Score -= 50;
        }
        else if (Diff > BasisDownload/3){
            Score -= 30;
        }
        else if (Diff > BasisDownload/4){
            Score -= 15;
        }

        return  Score;
    }

    public int CheckUpload(){
        // Added 250 to counter the subtraction in scoring init
        int Score = 350;
        int Result;
        System.out.println("Upload er LIGE NU " + Score);

        final NavigableMap<Double, Integer> scoring = new TreeMap<Double, Integer>();
        scoring.put(0.0, Score -= 100);
        scoring.put(2.0, Score -= 80);
        scoring.put(5.0, Score -= 40);
        scoring.put(10.0, Score -= 20);
        scoring.put(25.0, Score -= 10);
        System.out.println("Upload er   !!!!!!!!!!!!!!!!!! " + Upload);
        System.out.println("Upload er HER EFTER " + Score);

        Result = UploadToBasis(scoring.get(scoring.floorKey((double) Upload)));
        System.out.println("Upload Result er:" + Result);
        return Result;
    }

    public int UploadToBasis(int Score){
        long Diff = BasisUpload - Download;
        // hvis der er et fald på mere end 50% så er det et problem
        if (Diff > BasisUpload/2 ){
            Score -= 50;
        }
        else if (Diff > BasisUpload/3){
            Score -= 30;
        }
        else if (Diff > BasisUpload/4){
            Score -= 15;
        }
        return  Score;
    }

    public int RankingDownload(int Score){
        int Ranking;

        if( Score >= 75){
            Ranking = 1;
            return Ranking;
        }
        else if (Score >= 50 && Score < 75){
            Ranking = 2;
            return Ranking;
        }
        Ranking = 3;
        return Ranking;
    }

    public int RankingUpload(int Score){
        int Ranking;

        if( Score >= 75){
            Ranking = 1;
            return Ranking;
        }
        else if (Score >= 50 && Score < 75){
            Ranking = 2;
            return Ranking;
        }
        Ranking = 3;
        return Ranking;
}


