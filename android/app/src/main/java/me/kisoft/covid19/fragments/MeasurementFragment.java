package me.kisoft.covid19.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import me.kisoft.covid19.HealthWatcher.VitalSignsProcess;
import me.kisoft.covid19.R;


public class MeasurementFragment extends Fragment {
    private Button btnStart;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_measurement, container, false);
        btnStart = root.findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), VitalSignsProcess.class);
//                i.putExtra("Usr", user);
                getContext().startActivity(i);

            }
        });
        return root;
    }

}
