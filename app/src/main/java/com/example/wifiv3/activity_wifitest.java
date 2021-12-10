package com.example.wifiv3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class activity_wifitest extends AppCompatActivity {
    private Button button;
    ProgressBar spinner;
    ImageView PictureOne;
    ImageView PictureTwo;
    ImageView PictureThree;
    TextView Explainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifitest);
        button = (Button) findViewById(R.id.ScanButton);
        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        PictureOne = (ImageView) findViewById(R.id.imageView2);
        PictureTwo = (ImageView) findViewById(R.id.imageView3);
        PictureThree = (ImageView) findViewById(R.id.imageView4);
        Explainer = (TextView) findViewById(R.id.textView);
    }



    public void ScanStart(View view) {
        spinner.setVisibility(View.VISIBLE);
        button.setVisibility(View.GONE);
        PictureOne.setVisibility(View.GONE);
        PictureTwo.setVisibility(View.GONE);
        PictureThree.setVisibility(View.GONE);
        Explainer.setVisibility(View.GONE);





        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FragmentContainer, new fragment_wifitest());

        ft.replace(R.id.FragmentContainer, new fragment_wifitest());

        ft.commit();

    }








}

