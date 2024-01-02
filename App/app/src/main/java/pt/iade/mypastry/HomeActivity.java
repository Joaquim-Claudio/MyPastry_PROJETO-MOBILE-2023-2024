package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import pt.iade.mypastry.models.User;

public class HomeActivity extends AppCompatActivity {

    TextView home_textView_points;
    ConstraintLayout primaryButton, delicacyButton, beverageButton,
            dessertButton, convivienceButton, seeAllButton;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        setupComponents();
    }


    public void callOrderActivity(View view){
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void callAccountActivity(View view){
        Intent intent = new Intent(this, AccountActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void setupComponents() {
        home_textView_points = (TextView) findViewById(R.id.home_textView_points);
        primaryButton = (ConstraintLayout) findViewById(R.id.home_primary_button);
        delicacyButton = (ConstraintLayout) findViewById(R.id.home_delicacy_button);
        beverageButton = (ConstraintLayout) findViewById(R.id.home_bever_button);
        dessertButton = (ConstraintLayout) findViewById(R.id.home_dessert_button);
        convivienceButton = (ConstraintLayout) findViewById(R.id.home_convinience_button);
        seeAllButton = (ConstraintLayout) findViewById(R.id.home_see_all_button);

        primaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PrimaryListActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        delicacyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DelicaciesListActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        beverageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BeverageListActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        dessertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DessertsListActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        convivienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ConveniencesListActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        seeAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SeeAllActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        populateViews();
    }

    public void populateViews() {
        home_textView_points.setText("89 pontos");
    }
}