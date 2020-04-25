package me.kisoft.covid19.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.paperdb.Paper;
import me.kisoft.covid19.LoginActivity;
import me.kisoft.covid19.ProfileActivity;
import me.kisoft.covid19.R;
import me.kisoft.covid19.SecurityCodeActivity;
import me.kisoft.covid19.utils.Keys;


public class SettingsFragment extends Fragment {
    private ListView profileListView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        profileListView = root.findViewById(R.id.settings_listview);

        List<String> dataList = new ArrayList<>(Arrays.asList(getString(R.string.profile), getString(R.string.security_code_title), getString(R.string.logout)));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,dataList);
        profileListView.setAdapter(adapter);

        profileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(getContext(), ProfileActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getContext(), SecurityCodeActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Paper.book().delete(Keys.PHONE_KEY);
                        Paper.book().delete(Keys.PASSWORD_KEY);
                        Paper.book().delete(Keys.CURRENT_USER_KEY);
                        intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        break;
                    default:
                        break;
                }
            }
        });
        return root;
    }
}
