
package com.example.wifiv3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class fragment_boxresult extends Fragment implements View.OnClickListener {

    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;
    ImageView background;
    int Rank;
    TextView Text;
    int TestNumber;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_boxresult, container, false);
        buttonOne = (Button) view.findViewById(R.id.button);
        buttonTwo = (Button) view.findViewById(R.id.button2);
        buttonThree  = (Button) view.findViewById(R.id.button3);
        background = (ImageView) view.getRootView().findViewById(R.id.imageView5);
        Text = (TextView) view.findViewById(R.id.WiFiStatus);
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);


        return view;

    }

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Fragment virker");
        Fragment fragment = (fragment_result) getFragmentManager().findFragmentByTag("RESULT");
        fragment_result FragRes = (fragment_result) fragment;


        Rank = FragRes.GetRanking();
        System.out.println(Rank);


    }

    public void Initiator() {
        System.out.println("Fragment virker");
        System.out.println(TestNumber);
    }


    @SuppressLint("ResourceAsColor")
    public void SetScene(int Ranking) {
        System.out.println("Sætter scene");

        switch (Ranking) {
            case 1:
                System.out.println("boxresult case 1");
                background.setColorFilter(getContext().getResources().getColor(R.color.SucesGreen));
                Text.setText("Dit Wi-Fi virker godt i dette punkt");
                break;
            case 2:
                System.out.println("kører case 2");
                background.setColorFilter(getContext().getResources().getColor(R.color.DecentYellow));
                Text.setText("Dit Wi-fi virker nogenlunde i dette punkt");
                break;
            case 3:
                System.out.println("kører case 3");
                background.setColorFilter(getContext().getResources().getColor(R.color.FailureRed));
                Text.setText("Dit Wi-Fi virker dårligt i dette punkt");
                break;


        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:

                Intent intent = new Intent(getActivity(), Activity_visualresult.class);
                startActivity(intent);


                break;
            case R.id.button2:
                Intent intent2 = new Intent(getActivity(), Activity_problems.class);
                startActivity(intent2);

                break;
            case R.id.button3:
                SetScene(Rank);
                break;


        }

    }

}