package com.example.andtoif;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.example.andtoif.R;

public class Dashboard extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String USERNAME = "username";
    public static final String ROLE = "roleuser";
    public static final String NOKTP = "noktp";
    public static final String NOHP = "nohp";
    public static final String EMAIL = "email";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView text = findViewById(R.id.textViewUser);
        TextView user = findViewById(R.id.username);
        TextView ktp = findViewById(R.id.noktp);
        TextView hp = findViewById(R.id.nohp);
        TextView mail = findViewById(R.id.email);
        Button logout = findViewById(R.id.logout);
        CardView listuser = findViewById(R.id.card1);
        CardView listsepeda = findViewById(R.id.card2);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String username = sharedPreferences.getString(USERNAME, "");
        String noktp = sharedPreferences.getString(NOKTP, "");
        String nohp = sharedPreferences.getString(NOHP, "");
        String email = sharedPreferences.getString(EMAIL, "");
        String roleuser = sharedPreferences.getString(ROLE, "");

        user.setText(username);
        ktp.setText(noktp);
        hp.setText(nohp);
        mail.setText(email);
        text.setText("Customer");

        if (roleuser.equalsIgnoreCase("Admin")) {
            System.out.println("respon " + roleuser);
            text.setText("Admin");
            listuser.setVisibility(View.VISIBLE);
            listuser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Dashboard.this, ListUser.class);
                    startActivity(intent);
                }
            });
        }
        listsepeda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Dashboard.this, ListToko.class);
                startActivity(in);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Dashboard.this)
                        .setMessage("Anda yakin ingin keluar ?")
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                {
                                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.clear();
                                    editor.commit();
                                    Intent in = new Intent(Dashboard.this, LoginActivity.class);
                                    startActivity(in);
                                    finish();
                                }
                            }

                        }).show();
            }
        });


    }


}
