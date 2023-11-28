package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import pt.iade.mypastry.models.User;

public class RegisterActivity extends AppCompatActivity {

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

        //TODO: EditText birthDateInputText = (EditText) findViewById(R.id.register_textInput_birthDate);
        String birthDate = "";

        //TODO: EditText genderInputText = (EditText) findViewById(R.id.register_textInput_gender);
        String gender = "";

        //TODO: EditText addressInputText = (EditText) findViewById(R.id.register_textInput_address);
        String address = "";


        if (!name.equals("") && !email.equals("")&& !password.equals("")){
            Intent intent = new Intent(this, EmailConfirmationActivity.class);
            String confirm_key = "000000";
            User newUser = new User(name, email, password, birthDate,gender, address);
            intent.putExtra("new_user", newUser);
            intent.putExtra("confirm_key", confirm_key);
            startActivity(intent);
        }
    }

    public void callSignInActivity(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}