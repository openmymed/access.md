package me.kisoft.covid19.fragments;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import me.kisoft.covid19.HealthWatcher.StartVitalSigns;
import me.kisoft.covid19.MainActivity;
import me.kisoft.covid19.R;
import me.kisoft.covid19.utils.NotificationUtility;

import static me.kisoft.covid19.AppWraper.CHANNEL_1_ID;


public class NotificationsFragment extends Fragment {
    Button b ;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        b=root.findViewById(R.id.btnNotify);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), StartVitalSigns.class);
                getContext().startActivity(i);
            }
        });
        return root;
    }

}
