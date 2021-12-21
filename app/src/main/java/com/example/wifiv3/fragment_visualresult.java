package com.example.wifiv3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class fragment_visualresult extends Fragment {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_results, container, false);
        DownloadText = (TextView) view.findViewById(R.id.DownloadText);
        UploadText = (TextView)  view.findViewById(R.id.UploadText);
        DownloadBrowsing = (TextView)  view.findViewById(R.id.DownloadBrowsing);
        UploadBrowsing = (TextView)  view.findViewById(R.id.UploadBrowsing);
        NetflixText = (TextView)  view.findViewById(R.id.NetflixText);
        StreamingText = (TextView)  view.findViewById(R.id.StreamingText);
        Overall = (TextView)  view.findViewById(R.id.Overall);
        boxOne = (ImageView) view.findViewById(R.id.boxOne);
        boxTwo = (ImageView) view.findViewById(R.id.boxTwo);

        return view;

    }

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("Fragment er i live");
        Fragment fragment = (fragment_result) getFragmentManager().findFragmentByTag("RESULT");
        fragment_result FragRes = (fragment_result) fragment;

        download = FragRes.getR_Download();
        upload = FragRes.getR_Upload();

        Download(download);
        Upload(upload);
        Overall(download, upload);

    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    @SuppressLint("ResourceAsColor")
    public void Download(long download) {

        if(download < 3){

            DownloadBrowsing.setTextColor(android.R.color.holo_red_dark);
            NetflixText.setTextColor(android.R.color.holo_red_dark);

           DownloadBrowsing.setText("Dit Wi-Fi er ikke egnet til browsing i dette punkt");
           NetflixText.setText("Dit Wi-Fi er ikke egent til at streame Netflix 4k i dette punkt");
           boxOne.setBackgroundColor(android.R.color.holo_red_dark);


        }
        else if(download < 3 && download < 25){
            DownloadBrowsing.setTextColor(android.R.color.holo_green_light);
            DownloadBrowsing.setText("Dit Wi-Fi er egnet til browsing i dette punkt");

            NetflixText.setTextColor(android.R.color.holo_red_dark);
            NetflixText.setText("Dit Wi-Fi er ikke egent til at streame Netflix 4k i dette punkt");
            boxOne.setBackgroundColor(android.R.color.holo_orange_light);


        }

        else{

            DownloadBrowsing.setTextColor(android.R.color.holo_green_light);
            NetflixText.setTextColor(android.R.color.holo_green_light);

            NetflixText.setText("Dit Wi-Fi er egent til at streame Netflix 4k i dette punkt");
            DownloadBrowsing.setText("Dit Wi-Fi er egnet til browsing i dette punkt");
            boxOne.setBackgroundColor(android.R.color.holo_green_light);


        }


    }

    @SuppressLint("ResourceAsColor")
    public void Upload(long upload) {


        if(upload < 3){
            UploadBrowsing.setTextColor(android.R.color.holo_red_dark);
            StreamingText.setTextColor(android.R.color.holo_red_dark);

            UploadBrowsing.setText("Dit net er ikke egnet til Emails og browsing i dette punkt");
            StreamingText.setText("Dit net er ikke egent til livestreaming i dette punkt");
            boxTwo.setBackgroundColor(android.R.color.holo_red_dark);

        }

        else if(upload < 3 && upload < 25){
            UploadBrowsing.setTextColor(android.R.color.holo_green_light);
            UploadBrowsing.setText("Dit Wi-Fi er egnet til browsing og Emails i dette punkt");

            StreamingText.setTextColor(android.R.color.holo_red_dark);
            StreamingText.setText("Dit Wi-Fi er ikke egent til at livestreame i dette punkt");
            boxTwo.setBackgroundColor(android.R.color.holo_orange_light);


        }

        else{

            UploadBrowsing.setTextColor(android.R.color.holo_green_light);
            StreamingText.setTextColor(android.R.color.holo_green_light);

            StreamingText.setText("Dit Wi-Fi er egent til at livestreame i dette punkt");
            UploadBrowsing.setText("Dit Wi-Fi er egnet til browsing i dette punkt");
            boxTwo.setBackgroundColor(android.R.color.holo_green_light);


        }


    }

    @SuppressLint("ResourceAsColor")
    public void Overall(long download, long upload){
        if(download > 50 && upload > 25){
            Overall.setText("Dit net fungere perfekt i dette punkt, og er egent til flere enheder");
            Overall.setTextColor(android.R.color.holo_green_light);
        }
        else{
            Overall.setText("Dit net kunne godt forbedres i dette punkt");
        }

    }


}