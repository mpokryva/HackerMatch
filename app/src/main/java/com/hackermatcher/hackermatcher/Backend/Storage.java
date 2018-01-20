package com.hackermatcher.hackermatcher.Backend;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.URLUtil;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class Storage {

    private final String TAG = "debugStorage";

    private FirebaseStorage mStorage;

    public Storage(){
        mStorage = FirebaseStorage.getInstance();
        // Create a storage reference from our app

    }

    public void uploadImage(){
        StorageReference storageRef = mStorage.getReference();

        // Create a reference to "mountains.jpg"
        StorageReference mountainsRef = storageRef.child("mountain.jpg");

        // Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = storageRef.child("images/mountain.jpg");


        Uri file = Uri.fromFile(new File("C:/Users/zheng/Desktop/FireBaseTutorial/images/rivers.jpg"));
        URLUtil.isValidUrl(String.valueOf(file));

        StorageReference riversRef = storageRef.child("images");
        UploadTask uploadTask = riversRef.putFile(file);

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Log.d(TAG, "uploadtask success");
            }
        });
    }
}
