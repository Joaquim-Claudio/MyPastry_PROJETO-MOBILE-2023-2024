package pt.iade.mypastry;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pt.iade.mypastry.enums.ProductType;
import pt.iade.mypastry.models.Product;
import pt.iade.mypastry.models.User;

public class BeverageListActivity extends AppCompatActivity {

    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverages_list);

        setupComponents();
    }

    public void returnToHomeActivity(View view){
        finish();
    }

    private void setupComponents() {

    }
}