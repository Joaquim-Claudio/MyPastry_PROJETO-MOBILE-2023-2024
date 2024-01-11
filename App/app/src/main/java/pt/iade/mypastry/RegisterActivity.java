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

import org.w3c.dom.Text;

import java.time.LocalDate;

import pt.iade.mypastry.models.Address;
import pt.iade.mypastry.models.User;

public class RegisterActivity extends AppCompatActivity {
    EditText nameInputText, emailInputText, genderInputText,
            addressStreetInputText, addressPostCodeInputText,
            addressBuildInputText, addressDoorInputText, addressCityInputText,
            birthDateInputText, passInputText, rePassInputText;
    TextView priPolicyTextView, pageNumberTextView;
    ImageView nextButton, previousButton;
    ConstraintLayout firstPage, secondPage, thirdPage;
    Button registerButton;

    User newUser;

    int pageNumber;

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
        genderInputText = (EditText) findViewById(R.id.register_textInput_gender);

        addressStreetInputText = (EditText) findViewById(R.id.register_textInput_address_street);
        addressPostCodeInputText = (EditText) findViewById(R.id.register_textInput_address_postalcode);
        addressBuildInputText = (EditText) findViewById(R.id.register_textInput_address_building);
        addressDoorInputText = (EditText) findViewById(R.id.register_textInput_address_door);
        addressCityInputText = (EditText) findViewById(R.id.register_textInput_address_city);


        birthDateInputText = (EditText) findViewById(R.id.register_textInput_birth_date);
        passInputText = (EditText) findViewById(R.id.register_textInput_password);
        rePassInputText = (EditText) findViewById(R.id.register_textInput_repassword);

        priPolicyTextView = (TextView) findViewById(R.id.register_privacy_policy);

        nextButton = (ImageView) findViewById(R.id.register_button_next);
        previousButton = (ImageView) findViewById(R.id.register_button_previous);
        pageNumberTextView = (TextView) findViewById(R.id.register_page_number_textView);

        firstPage = (ConstraintLayout) findViewById(R.id.register_first_page_constraint);
        secondPage = (ConstraintLayout) findViewById(R.id.register_second_page_constraint);
        thirdPage = (ConstraintLayout) findViewById(R.id.register_third_page_constraint);

        registerButton = (Button) findViewById(R.id.register_button_register);

        pageNumber=1;

        firstPage.setVisibility(View.VISIBLE);
        secondPage.setVisibility(View.GONE);
        thirdPage.setVisibility(View.GONE);
        priPolicyTextView.setVisibility(View.GONE);
        registerButton.setVisibility(View.GONE);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (pageNumber){
                    case 1:
                        firstPage.setVisibility(View.GONE);

                        secondPage.setVisibility(View.VISIBLE);

                        thirdPage.setVisibility(View.GONE);

                        priPolicyTextView.setVisibility(View.GONE);

                        registerButton.setVisibility(View.GONE);

                        previousButton.setVisibility(View.VISIBLE);
                        nextButton.setVisibility(View.VISIBLE);

                        pageNumber++;
                        pageNumberTextView.setText(pageNumber+"/3");
                        break;

                    case 2:
                        firstPage.setVisibility(View.GONE);

                        secondPage.setVisibility(View.GONE);

                        thirdPage.setVisibility(View.VISIBLE);

                        priPolicyTextView.setVisibility(View.VISIBLE);

                        registerButton.setVisibility(View.VISIBLE);

                        previousButton.setVisibility(View.VISIBLE);
                        nextButton.setVisibility(View.GONE);

                        pageNumber++;
                        pageNumberTextView.setText(pageNumber+"/3");
                        break;
                }

            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (pageNumber){
                    case 2:
                        firstPage.setVisibility(View.VISIBLE);

                        secondPage.setVisibility(View.GONE);

                        thirdPage.setVisibility(View.GONE);

                        priPolicyTextView.setVisibility(View.GONE);

                        registerButton.setVisibility(View.GONE);

                        previousButton.setVisibility(View.GONE);
                        nextButton.setVisibility(View.VISIBLE);

                        pageNumber--;
                        pageNumberTextView.setText(pageNumber+"/3");
                        break;

                    case 3:
                        firstPage.setVisibility(View.GONE);

                        secondPage.setVisibility(View.VISIBLE);

                        thirdPage.setVisibility(View.GONE);

                        priPolicyTextView.setVisibility(View.GONE);

                        registerButton.setVisibility(View.GONE);

                        previousButton.setVisibility(View.VISIBLE);
                        nextButton.setVisibility(View.VISIBLE);

                        pageNumber--;
                        pageNumberTextView.setText(pageNumber+"/3");
                        break;
                }
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




                if (!name.equals("") && !email.equals("") && !password.equals("")) {
                    if (!birthDate.equals("") && !gender.equals("")) {

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

        newUser.getAddress().setStreet(addressStreetInputText.getText().toString());
        newUser.getAddress().setPostalCode(addressPostCodeInputText.getText().toString());
        newUser.getAddress().setBuilding(addressBuildInputText.getText().toString());
        newUser.getAddress().setDoor(addressDoorInputText.getText().toString());
        newUser.getAddress().setCity(addressCityInputText.getText().toString());
    }

}