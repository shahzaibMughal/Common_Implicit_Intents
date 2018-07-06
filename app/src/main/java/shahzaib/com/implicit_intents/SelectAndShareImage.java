package shahzaib.com.implicit_intents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SelectAndShareImage extends AppCompatActivity {
    private static final int REQUEST_CODE_IMAGE_PIC = 100;
    private static final String SAVED_IMAGE_NAME = "image.jpg";
    boolean isImageSelected = false;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_image);
        imageView = findViewById(R.id.imageView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageData) {
        super.onActivityResult(requestCode, resultCode, imageData);
        if (requestCode == REQUEST_CODE_IMAGE_PIC) {
            if(imageData != null)
            {
                Uri imageUri = imageData.getData();
                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    isImageSelected = true;
                    imageView.setImageBitmap(bitmap);


                    //store image into external storage and save the uri
                    File file = new File(getExternalCacheDir(), SAVED_IMAGE_NAME);
                    FileOutputStream fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 30, fos);
                    fos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i("123456","Error occur while reading/writing Image from the storage");
                }
            }
            else
            {
                isImageSelected = false;
                Toast.makeText(this, "Image Not Received", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void showImagePicker(View view) {
        // first check for permission
        if (!isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE)) return;

        Intent chooseImageIntent = new Intent(Intent.ACTION_PICK);
        chooseImageIntent.setType("image/*");

        if (chooseImageIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(Intent.createChooser(chooseImageIntent, "Complete action using"), REQUEST_CODE_IMAGE_PIC);
        } else {
            Toast.makeText(this, "There is no app to perform the action", Toast.LENGTH_SHORT).show();
        }
    }

    public void ShareImage(View view) {
        if(isImageSelected)
        {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            File file = new File(getExternalCacheDir(),SAVED_IMAGE_NAME);

//            Uri uriToImage = Uri.parse(new File(getExternalCacheDir(), SAVED_IMAGE_NAME).getAbsolutePath());
            Uri photoUri = Uri.parse(file.getAbsolutePath());
            sharingIntent.putExtra(Intent.EXTRA_STREAM, photoUri);
            sharingIntent.setType("image/*");
            if (sharingIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(sharingIntent, "Share image using"));
            }
        }
        else
        {
            Toast.makeText(this, "First, Select Image", Toast.LENGTH_SHORT).show();
        }
    }




    public boolean isPermissionGranted(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{permission}, 0);
                Toast.makeText(this, "Permission is not granted", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


}
