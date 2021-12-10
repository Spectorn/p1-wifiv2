package com.example.wifiv3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class activity_wifitest extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifitest);
        button = (Button) findViewById(R.id.ScanButton);
    }



    public void ScanStart(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FragmentContainer, new fragment_wifitest());

        ft.replace(R.id.FragmentContainer, new fragment_wifitest());

        ft.commit();

    }
}
