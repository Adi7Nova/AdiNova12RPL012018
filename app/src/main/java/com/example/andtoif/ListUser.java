package com.example.andtoif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.andtoif.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.User;
import Adapter.UserAdapter;

import static com.example.andtoif.LoginActivity.ID;
import static com.example.andtoif.LoginActivity.ROLE;
import static com.example.andtoif.LoginActivity.SHARED_PREFS;

public class ListUser extends AppCompatActivity {


    private RecyclerView user;
    private UserAdapter usadap;
    private List<User> userList = new ArrayList<>();
    private ProgressDialog mProgress;
    SwipeRefreshLayout swipeLayout;
    private Toolbar toolbar;

    private String id, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("List User");

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        id = sharedPreferences.getString(ID, "");
        role = sharedPreferences.getString(ROLE, "");

        user = findViewById(R.id.recycler_view);
        swipeLayout = findViewById(R.id.swipe_container);


        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code here
                userList.clear();
                userapi();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 1000);
                Toast.makeText(getApplicationContext(), "DATA SIAP", Toast.LENGTH_SHORT).show();// Delay in millis
            }
        });
        setupRecycler();
        userapi();

    }

    private void setupRecycler() {
        usadap = new UserAdapter(this, userList);
        user.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        user.setHasFixedSize(true);
        user.setAdapter(usadap);
    }

    private void userapi() {
        AndroidNetworking.get("http://192.168.1.22/APItugas/show_user.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status = response.optString("STATUS");
                            String message = response.optString("MESSAGE");
                            String sender = response.optString("SENDER");
                            if (status.equalsIgnoreCase("SUCCESS")) {
                                JSONArray hasilList = response.optJSONObject("PAYLOAD").optJSONArray("DATA");

                                if (hasilList == null) return;

                                for (int i = 0; i < hasilList.length(); i++) {
                                    Log.d("p", "TEKAN");
                                    JSONObject hasil = hasilList.getJSONObject(i);
                                    User item = new User();
                                    item.setUsername(hasil.getString("username"));
                                    item.setEmail(hasil.getString("email"));
                                    item.setNoKTP(hasil.getString("noktp"));
                                    item.setNoHP(hasil.getString("nohp"));
                                    userList.add(item);
                                }

                            }
                            usadap.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("", "onError: " + anError.getErrorBody());
                        Log.d("GZS", "onError: " + anError.getLocalizedMessage());
                        Log.d("GZS", "onError: " + anError.getErrorDetail());
                        Log.d("GZS", "onError: " + anError.getResponse());
                        Log.d("GZS  ", "onError: " + anError.getErrorCode());
                        Toast.makeText(getApplicationContext(), "periksa koneksi anda", Toast.LENGTH_SHORT).show();
                    }
                });


    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}