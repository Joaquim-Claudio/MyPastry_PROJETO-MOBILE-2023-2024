package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import pt.iade.mypastry.models.Order;
import pt.iade.mypastry.models.User;
import pt.iade.mypastry.repositories.UserRepository;

public class EmailConfirmationActivity extends AppCompatActivity {

    User newUser;
    String confirmKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_confirmation);

        Intent intent = getIntent();
        newUser = (User) intent.getSerializableExtra("new_user");
        confirmKey = intent.getStringExtra("confirm_key");

        TextView emailTextView = (TextView) findViewById(R.id.email_confirm_textView_email);
        emailTextView.setText(newUser.getEmail());

    }


    public void confirmEmail(View view) {
        EditText pinInputText = (EditText) findViewById(R.id.email_confirm_textInput_pin);
        String pin = pinInputText.getText().toString();

        if (pin.equals(confirmKey)){
            //  Register new user
            UserRepository.addUser(newUser);

            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.putExtra("user_id", newUser.getId());
            startActivity(intent);
        }
    }
    public void reSendPin() {
        //TODO: implement the resend function
    }
}