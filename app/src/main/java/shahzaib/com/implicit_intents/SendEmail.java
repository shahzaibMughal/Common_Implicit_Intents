package shahzaib.com.implicit_intents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SendEmail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);
    }

    public void sendMail(View view) {
        EditText messageET = findViewById(R.id.messageET);
        EditText emailET = findViewById(R.id.emailET);
        if(emailET.getText().toString().length()>0 && messageET.getText().toString().length() > 0)
        {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { emailET.getText().toString() });
            intent.putExtra(Intent.EXTRA_SUBJECT, "no-subject");
            intent.putExtra(Intent.EXTRA_TEXT, messageET.getText().toString());
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(intent, "Choose an application"));
            }


//            Intent smsIntent = new Intent(Intent.ACTION_SEND);
//            smsIntent.setData(Uri.parse("mail:"));
//            smsIntent.setType("text/plain");
//            smsIntent.putExtra("sms_body",messageET.getText().toString());
////            smsIntent.putExtra("address","PHONE NUMBER");
//
        }
        else
        {
            Toast.makeText(this, "Email Address OR Message is missing", Toast.LENGTH_SHORT).show();
        }
    }
}
