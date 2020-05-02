package me.kisoft.covid19.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.paperdb.Paper;
import me.kisoft.covid19.LoginActivity;
import me.kisoft.covid19.ProfileActivity;
import me.kisoft.covid19.R;
import me.kisoft.covid19.SecurityCodeActivity;
import me.kisoft.covid19.SettingsActivity;
import me.kisoft.covid19.utils.Keys;


public class MenuFragment extends Fragment {
    private ListView menuListView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        menuListView = root.findViewById(R.id.menu_list_view);

        String[] dataList = new String[]{getString(R.string.profile), getString(R.string.security_code_title), getString(R.string.title_settings), getString(R.string.logout)};
        int[] drawableArray = new int[]{R.drawable.ic_account_circle_black_24dp, R.drawable.ic_vpn_key_black_24dp, R.drawable.ic_settings_black_24dp, R.drawable.ic_exit_to_app_black_24dp};
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String, String>>();
        for(int i=0;i<dataList.length;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("ListTitle",dataList[i]);
            hm.put("ListImages",Integer.toString(drawableArray[i]));
            aList.add(hm);
        }
        String[] from = {
                "ListImages","ListTitle"
        };
        int[] to = {
                R.id.img_menu_item,R.id.tv_menu_item_title
        };
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),aList,R.layout.menu_item_layout,from,to);
        menuListView.setAdapter(simpleAdapter);

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                        intent = new Intent(getContext(), SettingsActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
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
