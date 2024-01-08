package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

import pt.iade.mypastry.models.Address;
import pt.iade.mypastry.models.User;

public class AccountActivity extends AppCompatActivity {
    TextView nameTextView, genderTextView, bdateTextView;
    EditText emailTextView, addressTextView;
    ConstraintLayout updatePassButton, logOutButton, saveButton;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        setupComponents();
    }

    public void returnToHomeActivity(View view){
        finish();
    }

    private void setupComponents(){
        nameTextView = (TextView) findViewById(R.id.account_user_name_textView);
        emailTextView = (EditText) findViewById(R.id.account_user_email_editView);
        bdateTextView = (TextView) findViewById(R.id.account_user_bdate_textView);
        genderTextView = (TextView) findViewById(R.id.account_user_gender_textView);
        addressTextView = (EditText) findViewById(R.id.account_user_address_editView);
        updatePassButton = (ConstraintLayout) findViewById(R.id.account_update_password_button);
        logOutButton = (ConstraintLayout) findViewById(R.id.account_log_out_button);
        saveButton = (ConstraintLayout) findViewById(R.id.account_save_button);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitViews();

                user.save();
                finish();
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.save();

                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                AccountActivity.this.finish();
            }
        });

        populateViews();
    }

    private void populateViews(){
        nameTextView.setText(user.getName());
        emailTextView.setText(user.getEmail());
        bdateTextView.setText(user.getBirthDate().toString());
        genderTextView.setText(user.getGender());
        addressTextView.setText(String.format(Locale.FRANCE,
                "%s %s %s, %s", user.getAddress().getStreet(), user.getAddress().getBuilding(),
                user.getAddress().getDoor(), user.getAddress().getCity()));
    }

    private void commitViews() {
        user.setEmail(emailTextView.getText().toString());
        user.setAddress(new Address(user.getAddress().getId(), addressTextView.getText().toString(), "", "", "", ""));
    }
}