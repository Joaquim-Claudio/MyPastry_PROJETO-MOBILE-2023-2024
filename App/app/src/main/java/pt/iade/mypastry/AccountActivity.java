package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import pt.iade.mypastry.models.User;
import pt.iade.mypastry.repositories.UserRepository;

public class AccountActivity extends AppCompatActivity {

    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Intent intent = getIntent();
        userId = intent.getIntExtra("user_id", 0);
        User user = UserRepository.getUser(userId);

        TextView nameTextView = (TextView) findViewById(R.id.account_user_name_textView);
        nameTextView.setText(user.getName());

        TextView emailTextView = (TextView) findViewById(R.id.account_user_email_textView);
        emailTextView.setText(user.getEmail());

        TextView bdateTextView = (TextView) findViewById(R.id.account_user_bdate_textView);
        bdateTextView.setText(user.getBirthDate());

        TextView genderTextView = (TextView) findViewById(R.id.account_user_gender_textView);
        genderTextView.setText(user.getGender());

        TextView addressTextView = (TextView) findViewById(R.id.account_user_address_textView);
        addressTextView.setText(user.getAddress());

        ConstraintLayout logOutButton = (ConstraintLayout) findViewById(R.id.account_log_out_button);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId=0;
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                AccountActivity.this.finish();
            }
        });
    }

    public void returnToHomeActivity(View view){
        finish();
    }
}