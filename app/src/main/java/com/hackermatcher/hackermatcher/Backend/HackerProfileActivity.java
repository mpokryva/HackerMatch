package com.hackermatcher.hackermatcher.Backend;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.hackermatcher.hackermatcher.R;

import java.io.InputStream;
import java.util.List;


public class HackerProfileActivity extends AppCompatActivity {

    private TextView hackerName;
    private TextView hackerUni;
    private TextView hackerHacks;
    private TextView hackerInterests;
    private TextView hackerSkills;

    private ImageView pfp;
    private Button changePFP;
    private String pfpUri;

    private ImageView reviews;

    protected void onCreate(Bundle savedInstanceStates) {
        super.onCreate(savedInstanceStates);
        setContentView(R.layout.activity_hacker_profile);

        hackerName = findViewById(R.id.name);
        hackerUni = findViewById(R.id.university);
        hackerInterests = findViewById(R.id.interest);
        hackerSkills = findViewById(R.id.skills);
        pfp = findViewById(R.id.pfp);
        Hacker hacker = (Hacker) getIntent().getSerializableExtra("hacker");

        List<Interest> interests = hacker.getInterests();
        List<Skill> skills = hacker.getSkills();
        String interestArr = "Interests:"  + "\n";
        for (int i = 0; i < interests.size(); i++) {
            interestArr += interests.get(i).getInterest() + ", ";
        }
        interestArr = interestArr.substring(0, interestArr.length() - 2);
        String skillsArr = "Skills:" + "\n";
        for (int i = 0; i < skills.size(); i++) {
            skillsArr += skills.get(i).getSkill() + ", ";
        }
        skillsArr = skillsArr.substring(0, skillsArr.length() - 2);

        hackerName.setText(hacker.getName());
        hackerUni.setText(hacker.getUniversity());
        hackerInterests.setText(interestArr);
        hackerSkills.setText(skillsArr);
    }

}