package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import pt.iade.mypastry.models.User;

public class HomeActivity extends AppCompatActivity {

    TextView home_textView_points;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        setupComponents();
    }

    public void callMenuActiviy(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void callBeveragesListActivity(View view){
        Intent intent = new Intent (this, BeverageListActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }


    public void callDessertsListActivity(View view){
        Intent intent = new Intent(this, DessertsListActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void callConveniencesListActivity(View view){
        Intent intent = new Intent(this, ConveniencesListActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void callDelicaciesListActivity(View view){
        Intent intent = new Intent(this, DelicaciesListActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
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
        populateViews();
    }

    public void populateViews() {
        home_textView_points.setText("89 pontos");
    }
}