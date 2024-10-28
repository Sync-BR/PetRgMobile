package com.petrg.meuspets.service.upload;


import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

public class UploadImage {
public static final int pickImageRequest = 1;
private final Activity activity;

    public UploadImage(Activity activity) {
        this.activity = activity;
    }
    //Open gallery
    public void openGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        activity.startActivityForResult(intent, pickImageRequest);

    }
}
