package com.example.wifiv3;



public class WifiInformation {
    private long Download;
    private long Upload;
    private long RSSI;
    private long BasisDownload;
    private long BasisUpload;
    private long BasisRSSI;

    public WifiInformation(long download, long upload, long RSSI, long basisDownload, long basisUpload, long basisRSSI) {
        Download = download;
        Upload = upload;
        this.RSSI = RSSI;
        BasisDownload = basisDownload;
        BasisUpload = basisUpload;
        BasisRSSI = basisRSSI;
    }

    public int CheckDownload(){

        int Score = 100;
        // 3mbps anses af mange for at være minimum til både streaming og gaming.
        if(Download < 3){
            Score = -100;
        }
        // 25mbps er hvad Netflix anbefaler for 4k streaming,
        else if(Download < 25){
            Score = -60;
        }
        // 8k streaming kræver 50 mbps
        else if(Download < 50){
            Score = -30;
        }

        // omkring 50 mbps anses af flere til at være tilstrækkeligt for en normal familie.
        else if (Download < 75) {
            Score = -15;
        }
        else if (Download < 75) {
            Score = -5;
        }

        return Score;




    }

    public int DownloadToBasis(int Score){

        // Hvis testen bliver målt højere end basis er der ikke sket et fald og derfor må internettet være perfekt.
        if(Download > BasisDownload){
            return Score;
        }

        return  Score;




    }




}


