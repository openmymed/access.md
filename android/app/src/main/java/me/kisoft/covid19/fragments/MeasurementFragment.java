package me.kisoft.covid19.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import me.kisoft.covid19.VitalSignsProcess;
import me.kisoft.covid19.R;


public class MeasurementFragment extends Fragment {
    private Button btnStart;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_measurement, container, false);
        //hide Action bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        btnStart = root.findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), VitalSignsProcess.class);
                getContext().startActivity(i);

            }
        });
        return root;
    }

}
