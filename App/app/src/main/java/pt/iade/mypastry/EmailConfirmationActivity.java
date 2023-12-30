package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kotlinx.coroutines.internal.ThreadSafeHeap;
import pt.iade.mypastry.models.Order;
import pt.iade.mypastry.models.User;
import pt.iade.mypastry.models.results.ConfirmKeyResponse;
import pt.iade.mypastry.utilities.KeyRequest;

public class EmailConfirmationActivity extends AppCompatActivity {
    TextView emailTextView;
    EditText pinInputText;
    Button confirmButton;
    Button resendButton;
    User newUser;
    private String confirmKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_confirmation);

        Intent intent = getIntent();
        newUser = (User) intent.getSerializableExtra("new_user");

        setupComponents();
        getConfirmationKey();
    }


    private void setupComponents(){
        emailTextView = (TextView) findViewById(R.id.email_confirm_textView_email);
        pinInputText = (EditText) findViewById(R.id.email_confirm_textInput_pin);
        confirmButton = (Button) findViewById(R.id.email_confirm_button_confirm);
        resendButton = (Button) findViewById(R.id.email_confirm_button_resend);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pin = pinInputText.getText().toString();

                if (!pin.equals(confirmKey)){
                    //  TODO: implement confirmation error

                } else {
                    //  Register new user
                    newUser.register(new User.RegisterResult() {
                        @Override
                        public void result() {
                            Intent intent = new Intent(EmailConfirmationActivity.this, WelcomeActivity.class);
                            intent.putExtra("user", newUser);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        resendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: implement the resend function
            }
        });

        populateViews();
    }

    private void populateViews() {
        emailTextView.setText(newUser.getEmail());
    }

    private void getConfirmationKey() {
        KeyRequest.GetKey(newUser.getEmail(), new KeyRequest.KeyRequestResult() {
            @Override
            public void result(ConfirmKeyResponse response) {
                confirmKey = response.getKey();
            }
        });
    }
}