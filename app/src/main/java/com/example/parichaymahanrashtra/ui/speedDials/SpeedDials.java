package com.example.parichaymahanrashtra.ui.speedDials;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parichaymahanrashtra.R;

public class SpeedDials extends Fragment {

    private SpeedDialsViewModel mViewModel;

    public static SpeedDials newInstance() {
        return new SpeedDials();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_speed_dials, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SpeedDialsViewModel.class);
        // TODO: Use the ViewModel
    }

}