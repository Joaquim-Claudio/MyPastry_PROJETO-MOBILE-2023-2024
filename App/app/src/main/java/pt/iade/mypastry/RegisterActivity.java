package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;

import pt.iade.mypastry.models.User;

public class RegisterActivity extends AppCompatActivity {
    EditText nameInputText, emailInputText, passInputText,
            birthDateInputText, genderInputText, addressInputText;
    TextView priPolicyTextView;
    ImageView nextButton, previousButton;
    ConstraintLayout firstPage, secondPage;
    Button registerButton;

    User newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setupComponents();
    }

    public void callSignInActivity(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    private void setupComponents(){
        nameInputText = (EditText) findViewById(R.id.register_textInput_name);
        emailInputText = (EditText) findViewById(R.id.register_textInput_email);
        passInputText = (EditText) findViewById(R.id.register_textInput_password);
        birthDateInputText = (EditText) findViewById(R.id.register_textInput_birth_date);
        genderInputText = (EditText) findViewById(R.id.register_textInput_gender);
        addressInputText = (EditText) findViewById(R.id.register_textInput_address);
        priPolicyTextView = (TextView) findViewById(R.id.register_privacy_policy);
        nextButton = (ImageView) findViewById(R.id.register_button_next);
        previousButton = (ImageView) findViewById(R.id.register_button_previous);
        firstPage = (ConstraintLayout) findViewById(R.id.register_first_page_constraint);
        secondPage = (ConstraintLayout) findViewById(R.id.register_second_page_constraint);
        registerButton = (Button) findViewById(R.id.register_button_register);

        firstPage.setVisibility(View.VISIBLE);
        secondPage.setVisibility(View.GONE);
        priPolicyTextView.setVisibility(View.GONE);
        registerButton.setVisibility(View.GONE);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout firstPage = (ConstraintLayout) findViewById(R.id.register_first_page_constraint);
                firstPage.setVisibility(View.GONE);
                ConstraintLayout secondPage = (ConstraintLayout) findViewById(R.id.register_second_page_constraint);
                priPolicyTextView.setVisibility(View.VISIBLE);
                registerButton.setVisibility(View.VISIBLE);
                secondPage.setVisibility(View.VISIBLE);
                previousButton.setVisibility(View.VISIBLE);
                TextView pageNumber = (TextView) findViewById(R.id.register_page_number_textView);
                pageNumber.setText("2/2");
                nextButton.setVisibility(View.GONE);
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout secondPage = (ConstraintLayout) findViewById(R.id.register_second_page_constraint);
                secondPage.setVisibility(View.GONE);
                ConstraintLayout firstPage = (ConstraintLayout) findViewById(R.id.register_first_page_constraint);
                priPolicyTextView.setVisibility(View.GONE);
                registerButton.setVisibility(View.GONE);
                firstPage.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);
                TextView pageNumber = (TextView) findViewById(R.id.register_page_number_textView);
                pageNumber.setText("1/2");
                previousButton.setVisibility(View.GONE);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = nameInputText.getText().toString();
                String email = emailInputText.getText().toString();
                String password = passInputText.getText().toString();
                String birthDate = birthDateInputText.getText().toString();
                String gender = genderInputText.getText().toString();
                String address = addressInputText.getText().toString();

                if (!name.equals("") && !email.equals("") && !password.equals("")) {
                    if (!birthDate.equals("") && !gender.equals("") && !address.equals("")) {

                        newUser = new User();
                        commitViews();

                        Intent intent = new Intent(RegisterActivity.this, EmailConfirmationActivity.class);

                        intent.putExtra("new_user", newUser);

                        startActivity(intent);
                    }
                }
            }
        });

    }

    private void commitViews(){
        newUser.setName(nameInputText.getText().toString());
        newUser.setEmail(emailInputText.getText().toString());
        newUser.setPassword(passInputText.getText().toString());
        newUser.setBirthDate(LocalDate.now());
        newUser.setGender(genderInputText.getText().toString());
    }
}