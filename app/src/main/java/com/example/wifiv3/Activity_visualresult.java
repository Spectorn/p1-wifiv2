package com.example.wifiv3;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.wifiv3.data.ftpInteraction;

public class Activity_visualresult extends AppCompatActivity {
    TextView DownloadText;
    TextView UploadText;
    TextView UploadBrowsing;
    TextView DownloadBrowsing;
    TextView NetflixText;
    TextView StreamingText;
    TextView Overall;
    ImageView boxOne;
    ImageView boxTwo;
    long download;
    long upload;






    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualresult);


        System.out.println("Fragment er i live");

        DownloadText = (TextView) findViewById(R.id.DownloadText);
        UploadText = (TextView)  findViewById(R.id.UploadText);
        DownloadBrowsing = (TextView)  findViewById(R.id.DownloadBrowsing);
        UploadBrowsing = (TextView)  findViewById(R.id.UploadBrowsing);
        NetflixText = (TextView)  findViewById(R.id.NetflixText);
        StreamingText = (TextView)  findViewById(R.id.StreamingText);
        Overall = (TextView)  findViewById(R.id.Overall);
        boxOne = (ImageView) findViewById(R.id.boxOne);
        boxTwo = (ImageView) findViewById(R.id.boxTwo);

        ftpInteraction ftpCon = new ftpInteraction(getApplicationContext());

        try {
            ftpCon.login();
            ftpCon.downloadTest();
            System.out.println("Download test has been done");
            ftpCon.uploadTest();
            System.out.println("Upload test has been done");

            // CB functions
            download = ftpCon.getDownload();
            upload = ftpCon.getUpload();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    @SuppressLint("ResourceAsColor")
    public void Download(long download) {

        if(isBetween((int) download, 0, 3)){

            DownloadBrowsing.setTextColor(Color.RED);
            NetflixText.setTextColor(Color.RED);
           boxOne.setColorFilter(Color.RED);


        }
        if(isBetween((int) download, 3, 25)){
            DownloadBrowsing.setTextColor(Color.GREEN);
            NetflixText.setTextColor(Color.RED);
            boxOne.setColorFilter(Color.YELLOW);


        }

        else{

            DownloadBrowsing.setTextColor(Color.GREEN);
            NetflixText.setTextColor(Color.GREEN);
            boxOne.setColorFilter(Color.GREEN);


        }


    }

    @SuppressLint("ResourceAsColor")
    public void Upload(long upload) {


        if(isBetween((int) upload, 0, 3)){
            UploadBrowsing.setTextColor(Color.RED);
            StreamingText.setTextColor(Color.RED);
            boxTwo.setColorFilter(Color.RED);
        }

        else if(isBetween((int) upload, 3, 25)){
            UploadBrowsing.setTextColor(Color.GREEN);
            StreamingText.setTextColor(Color.RED);
            boxTwo.setColorFilter(Color.YELLOW);
            System.out.println("Upload could be better");


        }

        else{

            UploadBrowsing.setTextColor(Color.GREEN);
            StreamingText.setTextColor(Color.GREEN);
            boxTwo.setColorFilter(Color.GREEN);
            System.out.println("Upload is higher than 25 mb");


        }


    }


    @SuppressLint("ResourceAsColor")
    public void Overall(long download, long upload){
        if(download > 50 && upload > 25){
            Overall.setText("Dit net er egent til brug i dette punkt");
            Overall.setTextColor(Color.GREEN);
        }
        else{
            Overall.setText("Dit net kunne godt forbedres i dette punkt");
            Overall.setTextColor(Color.RED);
        }

    }

    public void TjekStat(View view) {
        Download(download);
        Upload(upload);
        Overall(download, upload);
    }


}