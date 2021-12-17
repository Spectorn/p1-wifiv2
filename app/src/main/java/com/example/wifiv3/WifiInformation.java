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

    public int CheckDownload() {

        // Added 185 to counter scoring init
        int Score = 385;
        int Result;

        final NavigableMap<Double, Integer> scoring = new TreeMap<Double, Integer>();
        scoring.put(0.0, Score -= 100);
        scoring.put(3.0, Score -= 80);
        scoring.put(25.0, Score -= 60);
        scoring.put(50.0, Score -= 30);
        scoring.put(800.0, Score -= 15);

        Result = DownloadToBasis(scoring.get(scoring.floorKey((double) Download)));
        Result = RankingDownload(Result);

        return Result;
    }

    public int DownloadToBasis(int Score) {
        long Diff = BasisDownload - Download;
        // hvis der er et fald på mere end 50% så er det et problem

        if (Diff > BasisDownload / 2) {
            Score -= 50;
        } else if (Diff > BasisDownload / 3) {
            Score -= 30;
        } else if (Diff > BasisDownload / 4) {
            Score -= 15;
        }

        return Score;
    }

    public int CheckUpload() {
        // Added 250 to counter the subtraction in scoring init
        int Score = 350;
        int Result;

        final NavigableMap<Double, Integer> scoring = new TreeMap<Double, Integer>();
        scoring.put(0.0, Score -= 100);
        scoring.put(2.0, Score -= 80);
        scoring.put(5.0, Score -= 40);
        scoring.put(10.0, Score -= 20);
        scoring.put(25.0, Score -= 10);

        Result = UploadToBasis(scoring.get(scoring.floorKey((double) Upload)));
        Result = RankingUpload(Result);
        return Result;
    }

    public int UploadToBasis(int Score) {
        long Diff = BasisUpload - Download;
        // hvis der er et fald på mere end 50% så er det et problem
        if (Diff > BasisUpload / 2) {
            Score -= 50;
        } else if (Diff > BasisUpload / 3) {
            Score -= 30;
        } else if (Diff > BasisUpload / 4) {
            Score -= 15;
        }
        return Score;
    }

    public int RankingDownload(int Score) {
        int Ranking;

        if (Score >= 75) {
            Ranking = 1;
            return Ranking;
        } else if (Score >= 50 && Score < 75) {
            Ranking = 2;
            return Ranking;
        }
        Ranking = 3;
        return Ranking;
    }

    public int RankingUpload(int Score) {
        int Ranking = 0;


        if (Score >= 75) {
            Ranking = 1;
            return Ranking;

        } else if (Score >= 50 && Score < 75) {

            return Ranking;
        }
        Ranking = 3;
        return Ranking;

    }
    // det hele handler om at koge det ned til at tal
    public int FinalRank (int Upload, int Download){
        int sum = Upload + Download;
        int FinalRank;

        // kun hvis begge test får fuld score får man en grøn skærm
        if (sum == 2){
            FinalRank = 1;
        }
        else if (sum == 3 || sum == 4){
            FinalRank = 2;
        }
        else{
            FinalRank = 3;
        }
        return FinalRank;



    }

}


