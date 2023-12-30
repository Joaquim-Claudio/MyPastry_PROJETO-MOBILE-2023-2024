package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pt.iade.mypastry.models.User;

public class SignInActivity extends AppCompatActivity {

    EditText emailInputText;
    EditText passInputText;
    Button signInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setupComponents();
    }


    public void callRegisterActivity(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void setupComponents() {
        emailInputText = (EditText) findViewById(R.id.sign_in_textInput_email);
        passInputText = (EditText) findViewById(R.id.sign_in_textInput_password);
        signInButton = (Button) findViewById(R.id.sign_in_button_login);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInputText.getText().toString();
                String password = passInputText.getText().toString();

                if (email.equals("adminLogin") && password.equals("adminPassword")){
                    Intent intent = new Intent(SignInActivity.this, AdminHomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    SignInActivity.this.finish();

                } else if (!email.equals("") && !password.equals("")) {

                    User.Athenticate(email, password, new User.AuthenticateResult() {
                        @Override
                        public void result(User authenticatedUser) {
                            if(authenticatedUser == null){
                                //TODO: implement authentication error

                            } else {
                                Intent intent = new Intent(SignInActivity.this, WelcomeActivity.class);
                                intent.putExtra("user", authenticatedUser);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });
    }

}