package com.example.wifiv3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import javax.xml.transform.Result;

public class activity_wifitest extends AppCompatActivity implements DataSender{
    private Button button;
    ProgressBar spinner;
    ImageView PictureOne;
    ImageView PictureTwo;
    ImageView PictureThree;
    TextView Explainer;
    int ActiveTestType;



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

        ActiveTestType = 1;


    }


    /// Dette er OnClick for Button
    public void ScanStart(View view) {

        // Der bliver skelnet mellem 4 forskellige testtyper. Som bliver brugt til at bestemme hvor tingene skal gemmes
        // Hvis TestType = 1 så er det en Basis værdi måling. alt over TestType 1 bliver set som det samme af activty.


        if(ActiveTestType == 1){
            BasisTest();
            ActiveTestType ++;
        }
        else {
            NormalTest();
            ActiveTestType ++;
        }

        }






    /// Når brugeren trykker på knappen for først gang bliver denne funktion kaldt
    public void BasisTest(){
        // fjerner alt tekst og billeder
        spinner.setVisibility(View.VISIBLE);
        button.setVisibility(View.GONE);
        PictureOne.setVisibility(View.GONE);
        PictureTwo.setVisibility(View.GONE);
        PictureThree.setVisibility(View.GONE);
        Explainer.setVisibility(View.GONE);


        // Start fragment_result
        FragmentTransaction ftTwo = getSupportFragmentManager().beginTransaction();
        ftTwo.add(R.id.FragmentContainer, new fragment_result(), "RESULT");
        ftTwo.addToBackStack(null);
        ftTwo.commit();

        // Start fragment_wifitest og sæt dens View i container istedet for fragment_result
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.FragmentContainer, new fragment_wifitest(), "wifi");
        ft.commit();

    }
    // når brugeren trykker på knappen alle andre gange bliver denne funktion kaldt

    public void NormalTest(){
        //Laver instans af fragment_wifitest og kalder dens funktion CallTest.
        Fragment fragment1 = (fragment_wifitest) getSupportFragmentManager().findFragmentByTag("wifi");
        fragment_wifitest FragWifi = (fragment_wifitest) fragment1;
        FragWifi.CallTest(ActiveTestType);
    }

    // Bliver kaldt af fragment_result og bliver brugt til at gøre layout visible igen
    public void SetLayout(){
        Explainer.setVisibility(View.VISIBLE);
        Explainer.setText("Gå nu hen til et sted hvor du bruger net meget, og tryk scan!");
        button.setVisibility(View.VISIBLE);
        button.setText("Scan");

    }


    // denne funktion bliver kaldt af fragment_wifitest og gør at fragment_result bliver sat ind i container
    public void ReplaceFragment(){

        spinner.setVisibility(View.GONE);
        FragmentTransaction ftThree = getSupportFragmentManager().beginTransaction();

        ftThree.replace(R.id.FragmentContainer, new fragment_result(), "RESULT");
        ftThree.addToBackStack(null);
        ftThree.commit();



    }


    // Dette er funktionen hvori alt information bliver delt mellem activities og de 2 fragments
    @Override
    public void WifiData(int TestType, long Download, long Upload, String BSSID, long RSSI) {
        System.out.println("Download er: "+Download+"Upload er: "+Upload+"BSSID er: "+BSSID+"RSSI er: "+RSSI);

        Fragment fragment = (fragment_result) getSupportFragmentManager().findFragmentByTag("RESULT");
        fragment_result FragRes = (fragment_result) fragment;

        FragRes.WifiData(TestType, Download, Upload, BSSID, RSSI);








    }



}

