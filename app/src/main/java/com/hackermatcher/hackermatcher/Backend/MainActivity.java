package com.hackermatcher.hackermatcher.Backend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.hackermatcher.hackermatcher.R;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Database mDatabase;
    private Storage mStorage;
    
    private Button uploadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mDatabase = new Database();
        mStorage = new Storage();

        mStorage.uploadImage();

        Hacker newHacker = new Hacker("Jenny", "Stony Brook");
        Hacker newHacker2 = new Hacker("Kenny", "HARVARD");
        Hackathon pennApps = new Hackathon("1", "PennApps");
        Hackathon yHacks = new Hackathon("2", "YHacks");
        List<Hacker> hackers = new ArrayList<Hacker>();
        hackers.add(newHacker);
        hackers.add(newHacker2);
        HackerTeam team1 = new HackerTeam("1", "Team Rockets",hackers, "1", 4);
        mDatabase.addHacker(newHacker);
        mDatabase.addHacker(newHacker2);
        mDatabase.addHackathon(pennApps);
        mDatabase.addHackathon(yHacks);
        mDatabase.addTeam(team1);
        
        uploadImage = (Button)findViewById(R.id.uploadImg);
        uploadImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =  new Intent(view.getContext(), UploadProfilePicture.class);
                startActivityForResult(intent, 0);
            }
        });

    }

}
