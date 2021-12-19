
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

public class fragment_boxresult extends Fragment {

    Button buttonOne;
    Button buttonTwo;
    ImageView background;
    int Rank;
    TextView Text;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_boxresult, container, false);
        buttonOne = (Button) view.findViewById(R.id.button);
        buttonTwo = (Button) view.findViewById(R.id.button2);
        background = (ImageView) view.findViewById(R.id.imageView5);
        Text = (TextView) view.findViewById(R.id.textView3);


        return view;

    }

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment fragment = (fragment_result) getFragmentManager().findFragmentByTag("RESULT");
        fragment_result FragRes = (fragment_result) fragment;

        Rank = FragRes.getterRanking();
        System.out.println(Rank);
        SetScene(Rank);



    }


    @SuppressLint("ResourceAsColor")
    public void SetScene (int Ranking){
        System.out.println("Sætter scene");

        switch (Ranking){
            case 1:
                System.out.println("boxresult case 1");
                background.setBackgroundColor(android.R.color.holo_green_light);
                break;
            case 2:
                System.out.println("kører case 2");


        }
    }

}
