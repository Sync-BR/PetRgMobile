package com.petrg.meuspets.service.upload;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import java.io.File;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadImage {
    public static final int pickImageRequest = 1;
    private Context context;

    public UploadImage(Context context) {
        this.context = context;
    }

    // Método para obter o caminho do arquivo a partir do Uri
    public static String getPathFromUri(Context context, Uri uri) {
        String path = null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            path = cursor.getString(columnIndex);
            cursor.close();
        }
        return path;
    }

    // Abrir galeria
    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        ((Activity) context).startActivityForResult(intent, pickImageRequest);

    }


    public String uploadImage(Uri imageUri) throws Exception {
        String url = "http://186.247.89.58:8080/api/fileController/image";
        String fileName = null;
        String filePath = getPathFromUri(context, imageUri);
        if (filePath != null) {
            File file = new File(filePath);
            fileName = file.getName();
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", file.getName(),
                            RequestBody.create(MediaType.parse("image/*"), file))
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        // Tratar resposta de sucesso
                    } else {
                        System.out.println("falhou");
                        // Tratar resposta de erro
                    }
                }
            });
        } else {
            throw new Exception("Não foi possível obter o caminho do arquivo.");
        }
        return fileName;
    }
}
