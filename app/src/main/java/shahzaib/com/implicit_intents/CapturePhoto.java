package shahzaib.com.implicit_intents;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

public class CapturePhoto extends AppCompatActivity {

    private int IMAGE_CAPTURE_REQUEST_CODE = 5;
    private static final String SAVED_IMAGE_NAME = "capturedImage.jpg";

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_photo);
        imageView = findViewById(R.id.imageView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageData) {
        super.onActivityResult(requestCode, resultCode, imageData);
        if (requestCode == IMAGE_CAPTURE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (imageData != null) {

                Bundle extras = imageData.getExtras();
                Bitmap imageThumbnail = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageThumbnail);

            } else {
                Toast.makeText(this, "Image Not Received", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void capturePhotoFromCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, IMAGE_CAPTURE_REQUEST_CODE);
        } else {
            Toast.makeText(this, "No activity can perform this action", Toast.LENGTH_SHORT).show();
        }
    }
}
