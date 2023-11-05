package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    public final static String EXTRA_EMAIL_KEY = "pt.iade.mypastry.EMAIL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void register(View view){
        EditText nameInputText = (EditText) findViewById(R.id.register_textInput_name);
        String name = nameInputText.getText().toString();

        EditText emailInputText = (EditText) findViewById(R.id.register_textInput_email);
        String email = emailInputText.getText().toString();

        EditText passInputText = (EditText) findViewById(R.id.register_textInput_password);
        String password = passInputText.getText().toString();


        if (!name.equals("") && !email.equals("")&& !password.equals("")){
            Intent intent = new Intent(this, EmailConfirmationActivity.class);
            intent.putExtra(EXTRA_EMAIL_KEY, email);
            startActivity(intent);
        }
    }

    public void callSignInActivity(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}