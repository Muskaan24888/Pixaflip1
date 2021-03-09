package com.example.pixaflip.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pixaflip.DisplayCovidActivity;
import com.example.pixaflip.DisplayPdfActivity;
import com.example.pixaflip.MainActivity;
import com.example.pixaflip.R;
import com.example.pixaflip.VideoActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button playVideo=root.findViewById(R.id.playVideo);
        Button showPdf=root.findViewById(R.id.showPdf);
        Button ShowReport=root.findViewById(R.id.ShowReport);

        playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //play video in landscape mode.
                Intent it = new Intent(MainActivity.context,VideoActivity.class);
                startActivity(it);
                //Toast.makeText(MainActivity.context,"Play video available in Assets folder",Toast.LENGTH_LONG).show();
            }
        });

        showPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.context, DisplayPdfActivity.class);
                startActivity(intent);
            }
        });

        ShowReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.context, DisplayCovidActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }
}
