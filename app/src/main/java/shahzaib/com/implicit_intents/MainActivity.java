package shahzaib.com.implicit_intents;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @SuppressLint("MissingPermission")
    public void placePhoneCall(View view) {

        if (!isPermissionGranted(Manifest.permission.CALL_PHONE)) return;

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:03056302013"));
        if (callIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(callIntent);
        } else {
            Toast.makeText(this, "ResolverActivity is not available", Toast.LENGTH_SHORT).show();
        }

    }

    public void sendSms(View view) {
        startActivity(new Intent(this, SendSms.class));
    }

    public void sendEmail(View view) {
        startActivity(new Intent(this, SendEmail.class));
    }

    public void launchWebsite(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
        if (browserIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(browserIntent);
        }
    }

    public void openPlayStore(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=com.shahzaib.glla"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void openMap(View view) {
        String latitude = "32.438736";
        String longitude = "74.115763";

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:" + latitude + "," + longitude + "?z=12")); // z = zoom level, min = 1 & max = 23
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No application can perform this action", Toast.LENGTH_SHORT).show();
        }
    }

    public void selectAndShareImage(View view) {
        startActivity(new Intent(this, SelectAndShareImage.class));
    }


    public void capturePhoto(View view) {
        startActivity(new Intent(this,CapturePhoto.class));
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


    public void selectContact(View view) {
        startActivity(new Intent(this,SelectContact.class));
    }
}
