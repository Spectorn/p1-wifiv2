package com.example.wifiv3;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class fragment_problems extends Fragment {

    EditText ChannelMultiline;
    EditText RssiMultiline;
    EditText LatencyMultiline;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_problems, container, false);
        ChannelMultiline = (EditText) view.findViewById(R.id.ChannelMulti);
        RssiMultiline = (EditText) view.findViewById(R.id.RssiMulti);
        LatencyMultiline = (EditText) view.findViewById(R.id.LatencyMulti);


        return view;

    }

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    public void RSSI(){

    }


}


