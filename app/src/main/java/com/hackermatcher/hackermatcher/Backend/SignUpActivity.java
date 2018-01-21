package com.hackermatcher.hackermatcher.Backend;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.google.firebase.auth.FirebaseAuth;


import com.hackermatcher.hackermatcher.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    CheckBox machine_learning, vr, iot, mobile_dev, hardware, web_dev, data_science, blockchain, health;
    private EditText signup_email, signup_password,
            first_name, last_name, school, username;
    private EditText input_machine_learning, input_vr, input_iot, input_mobile_dev,
            input_hardware, input_web_dev, input_data_science, input_block_chain,
            input_health;
    private Button signup_button;
    List<Interest> interests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        signup_button = (Button) findViewById(R.id.btn_create);
        signup_email = (EditText) findViewById(R.id.input_email);
        signup_password = (EditText) findViewById(R.id.input_password);
        first_name = (EditText) findViewById(R.id.input_first_name);
        last_name = (EditText) findViewById(R.id.input_last_name);
        school = (EditText) findViewById(R.id.input_school);
        username = (EditText) findViewById(R.id.input_username);


        // Assign xml checkboxes to CheckBox objects
        machine_learning = (CheckBox) findViewById(R.id.checkBox);
        vr = (CheckBox) findViewById(R.id.checkBox2);
        iot = (CheckBox) findViewById(R.id.checkBox3);
        mobile_dev = (CheckBox) findViewById(R.id.checkBox4);
        hardware = (CheckBox) findViewById(R.id.checkBox5);
        web_dev = (CheckBox) findViewById(R.id.checkBox6);
        data_science = (CheckBox) findViewById(R.id.checkBox7);
        blockchain = (CheckBox) findViewById(R.id.checkBox8);
        health = (CheckBox) findViewById(R.id.checkBox9);

        input_machine_learning = (EditText) findViewById(R.id.input_machine_learning);
        input_vr = (EditText) findViewById(R.id.input_vr);
        input_iot = (EditText) findViewById(R.id.input_iot);
        input_mobile_dev = (EditText) findViewById(R.id.input_mobile_dev);
        input_hardware = (EditText) findViewById(R.id.input_hardware);
        input_web_dev = (EditText) findViewById(R.id.input_web_dev);
        input_data_science = (EditText) findViewById(R.id.input_data_science);
        input_block_chain = (EditText) findViewById(R.id.input_block_chain);
        input_health = (EditText) findViewById(R.id.input_health);


        final LinearLayout lin_layount = (LinearLayout) this.findViewById(R.id.linLayout);
        final LinearLayout lin_layount2 = (LinearLayout) this.findViewById(R.id.linLayout2);
        final LinearLayout lin_layount3 = (LinearLayout) this.findViewById(R.id.linLayout3);
        final LinearLayout lin_layount4 = (LinearLayout) this.findViewById(R.id.linLayout4);
        final LinearLayout lin_layount5 = (LinearLayout) this.findViewById(R.id.linLayout5);
        final LinearLayout lin_layount6 = (LinearLayout) this.findViewById(R.id.linLayout6);
        final LinearLayout lin_layount7 = (LinearLayout) this.findViewById(R.id.linLayout7);
        final LinearLayout lin_layount8 = (LinearLayout) this.findViewById(R.id.linLayout8);
        final LinearLayout lin_layount9 = (LinearLayout) this.findViewById(R.id.linLayout9);

        // functions update the sign up screen based on checked boxes
        machine_learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (machine_learning.isChecked()) {
                    lin_layount.setVisibility(LinearLayout.VISIBLE);
                } else {
                    lin_layount.setVisibility(LinearLayout.GONE);
                }
            }
        });
        vr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vr.isChecked()) {
                    lin_layount2.setVisibility(LinearLayout.VISIBLE);
                } else {
                    lin_layount2.setVisibility(LinearLayout.GONE);
                }
            }
        });
        iot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iot.isChecked()) {
                    lin_layount3.setVisibility(LinearLayout.VISIBLE);
                } else {
                    lin_layount3.setVisibility(LinearLayout.GONE);
                }
            }
        });
        mobile_dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobile_dev.isChecked()) {
                    lin_layount4.setVisibility(LinearLayout.VISIBLE);
                } else {
                    lin_layount4.setVisibility(LinearLayout.GONE);
                }
            }
        });
        hardware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hardware.isChecked()) {
                    lin_layount5.setVisibility(LinearLayout.VISIBLE);
                } else {
                    lin_layount5.setVisibility(LinearLayout.GONE);
                }
            }
        });
        web_dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (web_dev.isChecked()) {
                    lin_layount6.setVisibility(LinearLayout.VISIBLE);
                } else {
                    lin_layount6.setVisibility(LinearLayout.GONE);
                }
            }
        });
        data_science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data_science.isChecked()) {
                    lin_layount7.setVisibility(LinearLayout.VISIBLE);
                } else {
                    lin_layount7.setVisibility(LinearLayout.GONE);
                }
            }
        });
        blockchain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (blockchain.isChecked()) {
                    lin_layount8.setVisibility(LinearLayout.VISIBLE);
                } else {
                    lin_layount8.setVisibility(LinearLayout.GONE);
                }
            }
        });
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (health.isChecked()) {
                    lin_layount9.setVisibility(LinearLayout.VISIBLE);
                } else {
                    lin_layount9.setVisibility(LinearLayout.GONE);
                }
            }
        });


        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, HackerMatchActivity.class);
                startActivity(intent);
            }
        });

    }


    public void setInterests() {
        if (!input_machine_learning.getText().toString().equals("")) {
            Interest interest = new Interest("ML", Integer.parseInt(input_machine_learning.getText().toString()));
            interests.add(interest);
            Log.e("CHECK", interest.toString());
        }
        if (!input_vr.getText().toString().equals("")) {
            Interest interest = new Interest("VR", Integer.parseInt(input_vr.getText().toString()));
            interests.add(interest);
            Log.e("CHECK", interest.toString());
        }
        if (!input_iot.getText().toString().equals("")) {
            Interest interest = new Interest("IOT", Integer.parseInt(input_iot.getText().toString()));
            interests.add(interest);
        }
        if (!input_mobile_dev.getText().toString().equals("")) {
            Interest interest = new Interest("Mobile Dev", Integer.parseInt(input_mobile_dev.getText().toString()));
            interests.add(interest);
        }
        if (!input_hardware.getText().toString().equals("")) {
            Interest interest = new Interest("Hardware", Integer.parseInt(input_hardware.getText().toString()));
            interests.add(interest);
        }
        if (!input_web_dev.getText().toString().equals("")) {
            Interest interest = new Interest("Web Dev", Integer.parseInt(input_web_dev.getText().toString()));
            interests.add(interest);
        }
        if (!input_data_science.getText().toString().equals("")) {
            Interest interest = new Interest("Data Science", Integer.parseInt(input_data_science.getText().toString()));
            interests.add(interest);
        }
        if (!input_block_chain.getText().toString().equals("")) {
            Interest interest = new Interest("Block Chain", Integer.parseInt(input_block_chain.getText().toString()));
            interests.add(interest);
        }
        if (!input_health.getText().toString().equals("")) {
            Interest interest = new Interest("Health", Integer.parseInt(input_health.getText().toString()));
            interests.add(interest);
        }

    }
}