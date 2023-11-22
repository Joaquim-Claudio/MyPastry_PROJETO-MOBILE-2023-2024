package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import pt.iade.mypastry.models.Cart;
import pt.iade.mypastry.models.User;
import pt.iade.mypastry.repositories.CartRepository;
import pt.iade.mypastry.repositories.UserRepository;

public class EmailConfirmationActivity extends AppCompatActivity {

    String userName;
    String userEmail;
    String confirmKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_confirmation);

        Intent intent = getIntent();
        userName = intent.getStringExtra("user_name");
        userEmail = intent.getStringExtra("user_email");
        confirmKey = intent.getStringExtra("confirm_key");

        TextView emailTextView = (TextView) findViewById(R.id.email_confirm_textView_email);
        emailTextView.setText(userEmail);

    }


    public void confirmEmail(View view) {
        EditText pinInputText = (EditText) findViewById(R.id.email_confirm_textInput_pin);
        String pin = pinInputText.getText().toString();

        if (pin.equals(confirmKey)){
            //  Register new user
            Cart cart = new Cart();
            CartRepository.addCart(cart);
            User user = new User(userName, userEmail, cart.getId());
            UserRepository.addUser(user);

            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.putExtra("user_id", user.getId());
            startActivity(intent);
        }
    }
    public void reSendPin() {
        //TODO: implement the resend function
    }
}