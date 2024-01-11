package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import pt.iade.mypastry.adapters.AdminOrderRowAdapter;
import pt.iade.mypastry.models.Order;
import pt.iade.mypastry.models.OrderProduct;
import pt.iade.mypastry.models.Product;
import pt.iade.mypastry.models.User;

public class AdminHomeActivity extends AppCompatActivity {
    ArrayList<Order> orderList;
    RecyclerView orderListView;
    AdminOrderRowAdapter adminOrderRowAdapter;
    ConstraintLayout refreshButton;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_home);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        setupComponents();
    }

    public void callAdminSettingsActivity(View view){
        Intent intent = new Intent(this, AdminSettingsActivity.class);
        startActivity(intent);
    }

    public void setupComponents() {
        refreshButton = (ConstraintLayout) findViewById(R.id.admin_home_refresh_button);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadOrders();
            }
        });


        orderListView = (RecyclerView) findViewById(R.id.admin_home_order_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(AdminHomeActivity.this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        orderListView.setLayoutManager(layoutManager);

        loadOrders();
    }


    private void loadOrders() {

        Order.GetAll(new Order.GetAllResult() {
            @Override
            public void result(ArrayList<Order> returnedOrders) {
                orderList = returnedOrders;
                adminOrderRowAdapter = new AdminOrderRowAdapter(AdminHomeActivity.this, orderList);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        orderListView.setAdapter(adminOrderRowAdapter);
                        adminOrderRowAdapter.notifyDataSetChanged();
                    }
                });

            }
        });

    }
}