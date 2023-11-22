package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import pt.iade.mypastry.models.User;
import pt.iade.mypastry.repositories.UserRepository;

public class SignInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void signIn (View view){
        EditText emailInputText = (EditText) findViewById(R.id.sign_in_textInput_email);
        String email = emailInputText.getText().toString();

        EditText passInputText = (EditText) findViewById(R.id.sign_in_textInput_password);
        String password = passInputText.getText().toString();

        if (email.equals("adminLogin") && password.equals("adminPassword")){
            Intent intent = new Intent(this, AdminHomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            SignInActivity.this.finish();
        }
    /*
        else if (!email.equals("") && !password.equals("")){
            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.putExtra(EXTRA_POINTS_KEY, 89);
            startActivity(intent);
        }

     */
        else{
            for (User user : UserRepository.getUsers()) {
                if (user.getEmail().equals(email)) {
                    Intent intent = new Intent(this, WelcomeActivity.class);
                    intent.putExtra("user_id", user.getId());
                    startActivity(intent);
                }
            }
        }
    }

    public void callRegisterActivity(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }



}