package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import pt.iade.mypastry.models.User;

public class AccountActivity extends AppCompatActivity {
    TextView nameTextView;
    TextView emailTextView;
    TextView bdateTextView;
    TextView genderTextView;
    TextView addressTextView;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        setupComponents();

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user_id");

    }

    public void returnToHomeActivity(View view){
        finish();
    }

    private void setupComponents(){
        nameTextView = (TextView) findViewById(R.id.account_user_name_textView);
        emailTextView = (TextView) findViewById(R.id.account_user_email_textView);
        bdateTextView = (TextView) findViewById(R.id.account_user_bdate_textView);
        genderTextView = (TextView) findViewById(R.id.account_user_gender_textView);
        addressTextView = (TextView) findViewById(R.id.account_user_address_textView);

        populateViews();
    }

    private void populateViews(){
        nameTextView.setText(user.getName());
        emailTextView.setText(user.getEmail());
        bdateTextView.setText(user.getBirthDate().toString());
        genderTextView.setText(user.getGender());
    }
}