package com.hackermatcher.hackermatcher.Backend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TESTING) {
            pushTestData();
        }
        hackersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Hacker> hackers = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    Hacker hacker = child.getValue(Hacker.class);
                    hackers.add(hacker);
                }
                Hacker user = hackers.get(0);
                hackers.remove(0);
                HashMap<Hacker, Double> hackerMap = user.rankMatches(null, hackers, true);
                CircleView circleView  = new CircleView(HackerMatchActivity.this, hackerMap);
                setContentView(circleView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void pushTestData() {
        hackersRef.removeValue();
        String[] names = new String[]{"Jenny", "Tayo", "Henry", "Miki"};
        String[] schools = new String[]{"Stony Brook", "Stony Brook", "Stony Brook", "Stony Brook"};
        String[] interests = new String[]{"ML", "Artificial Intelligence", "Data", "Mobile", "Web", "IoT", "Robotics"};
        String[] skills = new String[]{"Neural Nets", "AI", "Big Data", "Android", "React", "IoT", "Arduino"};
        for (int i = 0; i < names.length; i++) {
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
            Hacker hacker = new Hacker(names[i], interestList, schools[i], skillList);
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
}
