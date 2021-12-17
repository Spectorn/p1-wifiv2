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
    Button button;
    ProgressBar spinner;
    ImageView PictureOne;
    ImageView PictureTwo;
    ImageView PictureThree;
    TextView Explainer;
    int ActivityTestType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifitest);
        button = (Button) findViewById(R.id.ScanButton);
        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.INVISIBLE);
        PictureOne = (ImageView) findViewById(R.id.imageView2);
        PictureTwo = (ImageView) findViewById(R.id.imageView3);
        PictureThree = (ImageView) findViewById(R.id.imageView4);
        Explainer = (TextView) findViewById(R.id.textView);

        ActivityTestType = 1;
    }


    /// Dette er OnClick for Button
    public void ScanStart(View view) {

        // Der bliver skelnet mellem 4 forskellige testtyper. Som bliver brugt til at bestemme hvor tingene skal gemmes
        // Hvis TestType = 1 så er det en Basis værdi måling. alt over TestType 1 bliver set som det samme af activty.
        button.setVisibility(View.GONE);

        if(ActivityTestType == 1){
            BasisTest();
            ActivityTestType ++;
        } else {
            NormalTest(ActivityTestType);
            ActivityTestType ++;
        }
    }

    /// Når brugeren trykker på knappen for først gang bliver denne funktion kaldt
    public void BasisTest(){
        UIremover();

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

    public void NormalTest(int testNumber){
        //Laver instans af fragment_wifitest og kalder dens funktion CallTest.
        UIremover();

        Fragment fragment1 = (fragment_wifitest) getSupportFragmentManager().findFragmentByTag("wifi");
        fragment_wifitest FragWifi = (fragment_wifitest) fragment1;

        switch(testNumber){
            case 2:
                System.out.println("Kører testType 2");
                Explainer.setText("Gå nu hen til et sted hvor du bruger net meget, og tryk scan!");
                break;
            case 3:
                System.out.println("Kører testType 3");
                Explainer.setText("Gå hen til et nyt sted hvor du bruger dit net meget, og tryk scan igen");
                break;
            case 4:
                System.out.println("Kører testType 4");
                Explainer.setText("Gå til et sidste sted hvor du bruger nettet meget, og tryk scan!");
                break;
        }
        FragWifi.CallTest(ActivityTestType);
    }

    // Bliver kaldt af fragment_result og bliver brugt til at gøre layout visible igen
    public void SetLayout(){
        Explainer.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
        button.setText("Scan");
    }

    // denne funktion bliver kaldt af fragment_wifitest og gør at fragment_result bliver sat ind i container
    public void ReplaceFragment(){

        spinner.setVisibility(View.INVISIBLE);
        FragmentTransaction ftThree = getSupportFragmentManager().beginTransaction();

        ftThree.replace(R.id.FragmentContainer, new fragment_result(), "RESULT");
        ftThree.addToBackStack(null);
        ftThree.commit();
    }
    public void UIremover(){
        spinner.setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);
        PictureOne.setVisibility(View.INVISIBLE);
        PictureTwo.setVisibility(View.INVISIBLE);
        PictureThree.setVisibility(View.INVISIBLE);
        Explainer.setVisibility(View.INVISIBLE);
        System.out.println("UI removed");

    }

    // Dette er funktionen hvori alt information bliver delt mellem activities og de 2 fragments
    @Override
    public void WifiData(int TestType, long Download, long Upload, String BSSID, long RSSI) {
        Fragment fragment = (fragment_result) getSupportFragmentManager().findFragmentByTag("RESULT");
        fragment_result FragRes = (fragment_result) fragment;

        FragRes.WifiData(TestType, Download, Upload, BSSID, RSSI);
    }
}

