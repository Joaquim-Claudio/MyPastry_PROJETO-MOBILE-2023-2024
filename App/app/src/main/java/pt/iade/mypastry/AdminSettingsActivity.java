package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_settings);

        ConstraintLayout logOutButton = (ConstraintLayout) findViewById(R.id.admin_settings_log_out_button);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminSettingsActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                AdminSettingsActivity.this.finish();
            }
        });
    }

    public void returnToAdminHomeActivity(View view){
        finish();
    }

    public void callAdminOrdersListActivity(View view){
        Intent intent = new Intent(this, AdminOrdersListActivity.class);
        startActivity(intent);
    }
}