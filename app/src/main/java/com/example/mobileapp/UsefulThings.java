package com.example.mobileapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

public class UsefulThings extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public UsefulThings() {
    }

    public static UsefulThings newInstance(String param1, String param2) {
        UsefulThings fragment = new UsefulThings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_useful, container, false);

        ImageView musicPlayer = (ImageView) v.findViewById(R.id.listentomusic);
        ImageView reminder = (ImageView) v.findViewById(R.id.reminder);
        ImageView qrscan = (ImageView) v.findViewById(R.id.qrscan);


        musicPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MusicPlayer.class);
                intent.putExtra("some", "some data");
                startActivity(intent);
            }
        });
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Reminder.class);
                intent.putExtra("some", "some data");
                startActivity(intent);
            }
        });
        qrscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BarcodeScanningActivity.class);
                intent.putExtra("some", "some data");
                startActivity(intent);
            }
        });
        return v;
    }
}