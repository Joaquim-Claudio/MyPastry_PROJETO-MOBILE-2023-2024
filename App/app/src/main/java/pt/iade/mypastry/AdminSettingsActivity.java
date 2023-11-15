package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_settings);
    }

    public void returnToAdminHomeActivity(View view){
        Intent intent = new Intent(this, AdminHomeActivity.class);
        startActivity(intent);
    }

    public void callAdminOrdersListActivity(View view){
        Intent intent = new Intent(this, AdminOrdersListActivity.class);
        startActivity(intent);
    }
}