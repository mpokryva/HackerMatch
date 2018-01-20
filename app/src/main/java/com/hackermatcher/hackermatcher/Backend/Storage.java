package com.example.firebasetutorial;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Storage {

    FirebaseStorage mStorage;
    StorageReference storageReference;

    public Storage(){
        mStorage = FirebaseStorage.getInstance();
        storageReference = mStorage.getReference();
    }




    public FirebaseStorage getmStorage(){return mStorage;}
    public StorageReference getStorageReference(){return storageReference;}
}
