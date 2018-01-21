package com.hackermatcher.hackermatcher.Backend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hackermatcher.hackermatcher.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Miki on 1/20/2018.
 */

public class HackerMatchActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = database.getReference();
    DatabaseReference hackersRef = rootRef.child("Hackers");
    private final boolean TESTING = true;
    private Button interestsButton;
    private Button skillsButton;
    private Hacker user;
    private List<Hacker> hackers;
    private boolean shouldExit;
    private final int NUM_HACKERS = 40;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TESTING) {
            pushTestData(NUM_HACKERS);
        }
        hackersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Hacker> hackers = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    Hacker hacker = child.getValue(Hacker.class);
                    hackers.add(hacker);
                }
                HackerMatchActivity.this.hackers = hackers;
                HackerMatchActivity.this.user = hackers.get(0); // FOR NOW.
                hackers.remove(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        resetContentView();
    }

    private void resetContentView() {
        setContentView(R.layout.activity_hacker_match_activity);
        interestsButton = findViewById(R.id.interests_button);
        skillsButton = findViewById(R.id.skills_button);
        interestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<Hacker, Double> hackerMap = user.rankMatches(null, hackers, false);
                CircleView circleView  = new CircleView(HackerMatchActivity.this, hackerMap,
                        new Callback<Hacker>() {
                            @Override
                            public void onEvent(Hacker hacker) {
                                startProfileActivity(hacker);
                            }
                        });
                HackerMatchActivity.this.setContentView(circleView);
                HackerMatchActivity.this.shouldExit = false;
            }
        });
        skillsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<Hacker, Double> hackerMap = user.skillMatches(null, hackers, false);
                CircleView circleView  = new CircleView(HackerMatchActivity.this, hackerMap,
                        new Callback<Hacker>() {
                            @Override
                            public void onEvent(Hacker hacker) {
                                startProfileActivity(hacker);
                            }
                        });
                HackerMatchActivity.this.setContentView(circleView);
                HackerMatchActivity.this.shouldExit = false;
            }
        });
    }

    private void startProfileActivity(Hacker hacker) {
        Intent intent = new Intent(this, HackerProfileActivity.class);
        intent.putExtra("hacker", hacker);
        startActivity(intent);
    }

    private void pushTestData(int numUsers) {
        hackersRef.removeValue();
        String[] names = new String[]{"Jenny", "Tayo", "Henry", "Miki", "John", "Steven", "Alex", "Vicky", "Nikki", "Ben", "Benny"};
        String[] schools = new String[]{"Stony Brook", "Binghamton", "Texas A&M", "CMU", "Columbia", "Oneonta"};
        String[] interests = new String[]{"ML", "Artificial Intelligence", "Data", "Mobile", "Web", "IoT", "Robotics"};
        String[] skills = new String[]{"Neural Nets", "AI", "Big Data", "Android", "React", "IoT", "Arduino"};
        for (int i = 0; i < numUsers; i++) {
            String[] randInterests = randArray(interests);
            List<Interest> interestList = new ArrayList<>();

            Random rand = new Random();
            for (int j = 0; j < randInterests.length; j++) {
                interestList.add(new Interest(randInterests[j],
                        rand.nextInt(Interest.MAX_INTEREST_LEVEL + 1)));
            }
            String[] randSkills = randArray(skills);
            List<Skill> skillList = new ArrayList<>();
            for (int j = 0; j < randSkills.length; j++) {
                skillList.add(new Skill(randSkills[j], Skill.MAX_SKILL_LEVEL + 1));
            }
            Hacker hacker = new Hacker(names[rand.nextInt(names.length)], interestList, schools[rand.nextInt(schools.length)], skillList);
            hackersRef.push().setValue(hacker);
        }
    }

    private String[] randArray(String[] arr) {
        Random rand = new Random();
        int numToPick = rand.nextInt(arr.length);
        numToPick = Math.max(numToPick, 1);
        String[] ret = new String[numToPick];
        for (int i = 0; i < numToPick; i++) {
            ret[i] = arr[rand.nextInt(arr.length)];
        }
        return ret;
    }

    @Override
    public void onBackPressed() {
        if (shouldExit) {
            super.onBackPressed();
        } else {
            resetContentView();
            shouldExit = true;
        }
    }
}
