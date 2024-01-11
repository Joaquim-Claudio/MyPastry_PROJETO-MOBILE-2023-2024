package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import pt.iade.mypastry.adapters.HistRowAdapter;
import pt.iade.mypastry.models.Order;
import pt.iade.mypastry.models.User;

public class HistoricActivity extends AppCompatActivity {
    ConstraintLayout backButton;

    ArrayList<Order> orderList;
    RecyclerView listView;
    HistRowAdapter histRowAdapter;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        setupComponents();
    }


    private void setupComponents() {
        backButton = (ConstraintLayout) findViewById(R.id.hist_back_button);

        listView = (RecyclerView) findViewById(R.id.historic_order_list);
        listView.setLayoutManager(new LinearLayoutManager(HistoricActivity.this));

        orderList = user.getOrders();

        histRowAdapter = new HistRowAdapter(HistoricActivity.this, orderList);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(histRowAdapter);
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}