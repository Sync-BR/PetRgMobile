package com.petrg.meuspets.model;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

public class UploadModel {
    public static final int pickImageRequest = 1;
    private final Activity activity;
    private Uri imageUri;
    public UploadModel(Activity activity) {
        this.activity = activity;
    }
    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, pickImageRequest);
    }
    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
