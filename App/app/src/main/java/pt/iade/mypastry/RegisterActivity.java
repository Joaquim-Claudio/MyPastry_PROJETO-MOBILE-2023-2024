package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import pt.iade.mypastry.models.User;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ConstraintLayout firstPage = (ConstraintLayout) findViewById(R.id.register_first_page_constraint);
        firstPage.setVisibility(View.VISIBLE);
        ConstraintLayout secondPage = (ConstraintLayout) findViewById(R.id.register_second_page_constraint);
        secondPage.setVisibility(View.GONE);

        ImageView nextButton = (ImageView) findViewById(R.id.register_button_next);
        ImageView previousButton = (ImageView) findViewById(R.id.register_button_previous);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout firstPage = (ConstraintLayout) findViewById(R.id.register_first_page_constraint);
                firstPage.setVisibility(View.GONE);
                ConstraintLayout secondPage = (ConstraintLayout) findViewById(R.id.register_second_page_constraint);
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
                firstPage.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);
                TextView pageNumber = (TextView) findViewById(R.id.register_page_number_textView);
                pageNumber.setText("1/2");
                previousButton.setVisibility(View.GONE);
            }
        });
    }



    public void register(View view){
        EditText nameInputText = (EditText) findViewById(R.id.register_textInput_name);
        String name = nameInputText.getText().toString();

        EditText emailInputText = (EditText) findViewById(R.id.register_textInput_email);
        String email = emailInputText.getText().toString();

        EditText passInputText = (EditText) findViewById(R.id.register_textInput_password);
        String password = passInputText.getText().toString();

        EditText birthDateInputText = (EditText) findViewById(R.id.register_textInput_birth_date);
        String birthDate = birthDateInputText.getText().toString();

        EditText genderInputText = (EditText) findViewById(R.id.register_textInput_gender);
        String gender = genderInputText.getText().toString();

        EditText addressInputText = (EditText) findViewById(R.id.register_textInput_address);
        String address = addressInputText.getText().toString();


        if (!name.equals("") && !email.equals("")&& !password.equals("")){
            if(!birthDate.equals("") && !gender.equals("")&& !address.equals("")){
                Intent intent = new Intent(this, EmailConfirmationActivity.class);
                String confirm_key = "000000";
                User newUser = new User(name, email, password, birthDate,gender, address);
                intent.putExtra("new_user", newUser);
                intent.putExtra("confirm_key", confirm_key);
                startActivity(intent);
            }else{
                ConstraintLayout coordinatorLayout = (ConstraintLayout) findViewById(R.id.register_layout);
                Snackbar snackbar =  Snackbar.make(coordinatorLayout, "Existem campos não preenchidos na 2ª etapa!", Snackbar.LENGTH_LONG);
                snackbar.show();
            }

        }else{
            ConstraintLayout coordinatorLayout = (ConstraintLayout) findViewById(R.id.register_layout);
            Snackbar snackbar =  Snackbar.make(coordinatorLayout, "Existem campos não preenchidos na 1ª etapa!", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    public void callSignInActivity(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}