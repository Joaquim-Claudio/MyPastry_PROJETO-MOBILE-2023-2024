package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        int points = intent.getIntExtra(WelcomeActivity.EXTRA_POINTS_KEY_2, 0);

        TextView home_textView_points = (TextView) findViewById(R.id.home_textView_points);
        home_textView_points.setText(points + " pontos");
    }

    public void callMenuActiviy(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void callBeveragesListActivity(View view){
        Intent intent = new Intent (this, BeverageListActivity.class);
        startActivity(intent);
    }


    public void callDessertsListActivity(View view){
        Intent intent = new Intent(this, DessertsListActivity.class);
        startActivity(intent);
    }

    public void callConveniencesListActivity(View view){
        Intent intent = new Intent(this, ConveniencesListActivity.class);
        startActivity(intent);
    }
}