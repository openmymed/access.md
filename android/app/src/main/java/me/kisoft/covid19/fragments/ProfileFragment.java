package me.kisoft.covid19.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import io.paperdb.Paper;
import me.kisoft.covid19.LoginActivity;
import me.kisoft.covid19.R;
import me.kisoft.covid19.utils.Keys;


public class ProfileFragment extends Fragment {

    private Button btnLogout;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        btnLogout = root.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paper.book().delete(Keys.PHONE_KEY);
                Paper.book().delete(Keys.PASSWORD_KEY);
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return root;
    }
}
