package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EmailConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_confirmation);

        Intent intent = getIntent();
        String email = intent.getStringExtra(RegisterActivity.EXTRA_EMAIL_KEY);

        TextView emailTextView = (TextView) findViewById(R.id.email_confirm_textView_email);
        emailTextView.setText(email);
    }


    public void confirmEmail(View view) {
        EditText pinInputText = (EditText) findViewById(R.id.email_confirm_textInput_pin);
        String pin = pinInputText.getText().toString();

        if (!pin.equals("")){
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }
    }
    public void reSendPin() {
        //TODO: implement the resend function
    }
}