package shahzaib.com.implicit_intents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SendSms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);


    }

    public void sendSms(View view) {
        EditText messageET = findViewById(R.id.messageET);

        if(messageET.getText().toString().length() > 0)
        {
            Intent smsIntent = new Intent(Intent.ACTION_SEND);
            smsIntent.setData(Uri.parse("sms:"));
            smsIntent.setType("text/plain");
            smsIntent.putExtra("sms_body",messageET.getText().toString());
//            smsIntent.putExtra("address","PHONE NUMBER");
            if(smsIntent.resolveActivity(getPackageManager()) != null)
            {
                Intent chooser =Intent.createChooser(smsIntent,"Choose an application");
                startActivity(chooser);
            }
        }
        else
        {
            Toast.makeText(this, "Message is missing", Toast.LENGTH_SHORT).show();
        }


    }
}
