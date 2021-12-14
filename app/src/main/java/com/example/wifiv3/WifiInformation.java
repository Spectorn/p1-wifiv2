package com.example.wifiv3;



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

        int Score = 100;
        int Result;
        // 3mbps anses af mange for at være minimum til både streaming og gaming.
        if(Download < 3){
            Score = Score -100;
        }
        // 25mbps er hvad Netflix anbefaler for 4k streaming,
        else if(Download < 25){
            Score = Score -60;
        }
        // 8k streaming kræver 50 mbps
        else if(Download < 50){
            Score = Score -30;
        }

        // omkring 50 mbps anses af flere til at være tilstrækkeligt for en normal familie.
        else if (Download < 75) {
            Score = Score -15;
        }
        else if (Download < 75) {
            Score = Score -5;
        }
        System.out.println("Score efter første test" + Score);
        Result = DownloadToBasis(Score);
        System.out.println("Download Result er:" + Result);

        return Result;




    }

    public int DownloadToBasis(int Score){
        long Diff = BasisDownload - Download;
        // hvis der er et fald på mere end 50% så er det et problem
        if (Diff > BasisDownload/2 ){
            Score = Score -50;
        }
        else if (Diff > BasisDownload/3){
            Score = Score -30;
        }
        else if (Diff > BasisDownload/4){
            Score = Score -15;
        }
        // Hvis testen bliver målt højere end basis er der ikke sket et fald og derfor må internettet være perfekt.
        else {
            Score = Score +1;
        }

        return  Score;

    }


    public int CheckUpload(){
        int Score = 100;
        int Result;

        /// Upload behøves kun at være en 1/10 af download til det meste brug
        if(Upload < 2){
            Score = Score -80;
        }

        else if (Upload < 5 && Upload > 2) {
            Score = Score -40;
        }
        else if (Upload < 10 && Upload > 5){
            Score = Score -20;
        }
        else if (Upload < 25 && Upload > 10){
            Score = Score -10;
        }

        Result = UploadToBasis(Score);
        System.out.println("Upload Result er:" + Result);
        return Result;
    }

    public int UploadToBasis(int Score){
        long Diff = BasisUpload - Download;
        // hvis der er et fald på mere end 50% så er det et problem
        if (Diff > BasisUpload/2 ){
            Score = Score -50;
        }
        else if (Diff > BasisUpload/3){
            Score = Score -30;
        }
        else if (Diff > BasisUpload/4){
            Score = Score -15;
        }
        // Hvis testen bliver målt højere end basis er der ikke sket et fald og derfor må internettet være perfekt.
        else {
            Score = Score +1;
        }
        return  Score;




    }






}


