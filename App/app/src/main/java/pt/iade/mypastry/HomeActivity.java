package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        userId = intent.getIntExtra("user_id", 0);
        /*

        TextView home_textView_points = (TextView) findViewById(R.id.home_textView_points);
        home_textView_points.setText(UserRepository.getUser(userId).getPoints() + " pontos");

        ProductRepository.populate();

         */
    }

    public void callMenuActiviy(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("user_id", userId);
        startActivity(intent);
    }

    public void callBeveragesListActivity(View view){
        Intent intent = new Intent (this, BeverageListActivity.class);
        intent.putExtra("user_id", userId);
        startActivity(intent);
    }


    public void callDessertsListActivity(View view){
        Intent intent = new Intent(this, DessertsListActivity.class);
        intent.putExtra("user_id", userId);
        startActivity(intent);
    }

    public void callConveniencesListActivity(View view){
        Intent intent = new Intent(this, ConveniencesListActivity.class);
        intent.putExtra("user_id", userId);
        startActivity(intent);
    }

    public void callDelicaciesListActivity(View view){
        Intent intent = new Intent(this, DelicaciesListActivity.class);
        intent.putExtra("user_id", userId);
        startActivity(intent);
    }

    public void callOrderActivity(View view){
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("user_id", userId);
        startActivity(intent);
    }

    public void callAccountActivity(View view){
        Intent intent = new Intent(this, AccountActivity.class);
        intent.putExtra("user_id", userId);
        startActivity(intent);
    }
}