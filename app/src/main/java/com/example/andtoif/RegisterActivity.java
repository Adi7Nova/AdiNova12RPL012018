package com.example.andtoif;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.andtoif.R;

import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    Intent intent;
    HashMap<String, String> hashMap = new HashMap<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Assign Id'S
        Button register = findViewById(R.id.btnregister);
        TextView login = findViewById(R.id.readyaccount);
        final EditText txt_username = (EditText) findViewById(R.id.txtusername1);
        final EditText txt_password = (EditText) findViewById(R.id.txtpass1);
        final EditText nohp = findViewById(R.id.txtnomerhp);
        final EditText alamat = findViewById(R.id.txtalamat);
        final EditText email = findViewById(R.id.txtemail);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidNetworking.post("http://192.168.1.11/APItugas/register.php")
                        .addBodyParameter("username", txt_username.getText().toString())
                        .addBodyParameter("nohp", nohp.getText().toString())
                        .addBodyParameter("email", email.getText().toString())
                        .addBodyParameter("alamat", alamat.getText().toString())
                        .addBodyParameter("password", txt_password.getText().toString())
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                String username = txt_username.getText().toString();
                                String password = txt_password.getText().toString();
                                String alamatt = alamat.getText().toString();
                                String emaill = email.getText().toString();
                                String nohpp = nohp.getText().toString();

                                if ((username.isEmpty()) || (password.isEmpty()) || (alamatt.isEmpty()) || (emaill.isEmpty() || (nohpp.isEmpty()))) {
                                    Toast.makeText(RegisterActivity.this, "semuanya harus di isi", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(RegisterActivity.this, "success", Toast.LENGTH_SHORT).show();
                                    Intent in = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(in);
                                }
                            }

                            @Override
                            public void onError(ANError error) {
                                Toast.makeText(RegisterActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(in);
            }
        });


    }


}
