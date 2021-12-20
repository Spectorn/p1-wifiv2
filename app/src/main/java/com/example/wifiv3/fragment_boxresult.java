
package com.example.wifiv3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class fragment_boxresult extends Fragment implements View.OnClickListener {

    Button buttonOne;
    Button buttonTwo;
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
        background = (ImageView) view.getRootView().findViewById(R.id.imageView5);
        Text = (TextView) view.findViewById(R.id.textView3);
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);


        return view;

    }

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Fragment virker");


        Fragment fragment = (fragment_result) getFragmentManager().findFragmentByTag("RESULT");
        fragment_result FragRes = (fragment_result) fragment;


        Rank = FragRes.getterRanking();
        System.out.println(Rank);
        SetScene(Rank);


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
                //background.setBackgroundColor(android.R.color.holo_green_light);
                break;
            case 2:
                System.out.println("kører case 2");
                //background.setBackgroundColor(android.R.color.holo_orange_light);
                break;
            case 3:
                System.out.println("kører case 3");
                //background.setBackgroundColor(android.R.color.holo_orange_light);
                break;


        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:


                Fragment fragment = (fragment_result) getFragmentManager().findFragmentByTag("RESULT");
                fragment_result FragRes = (fragment_result) fragment;

                FragRes.Destroy();

                FragmentTransaction ftFour = getFragmentManager().beginTransaction();
                ftFour.replace(R.id.FragmentContainer, new fragment_visualresult(), "Visual");
                ftFour.addToBackStack(null);
                ftFour.commit();

                break;
            case R.id.button2:

                //action
                break;
        }

    }

}