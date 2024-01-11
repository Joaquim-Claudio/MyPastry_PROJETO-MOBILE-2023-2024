package pt.iade.mypastry;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import pt.iade.mypastry.adapters.OrdProdRowAdapter;
import pt.iade.mypastry.enums.OrderStatus;
import pt.iade.mypastry.models.Order;
import pt.iade.mypastry.models.OrderProduct;
import pt.iade.mypastry.models.Product;
import pt.iade.mypastry.models.User;

public class OrderActivity extends AppCompatActivity {
    TextView emptyTextView, totalTextView;
    Button checkOutButton, keepButton;
    ArrayList<OrderProduct> ordProdsList;
    ArrayList<Product> productsList;
    RecyclerView listView;
    OrdProdRowAdapter ordProdRowAdapter;

    Order order;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        setupComponents();
    }


    public void callHomeActivity(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }


    private void setupComponents() {
        emptyTextView = (TextView) findViewById(R.id.order_empty_textView);
        totalTextView = (TextView) findViewById(R.id.order_total_textView);
        checkOutButton = (Button) findViewById(R.id.order_check_out_button);
        keepButton = (Button) findViewById(R.id.order_keep_button);

        listView = (RecyclerView) findViewById(R.id.order_listView);
        listView.setLayoutManager(new LinearLayoutManager(OrderActivity.this));

        //  Firstly checking for an existing Pending Order
        Order.GetPending(user.getId(), new Order.GetPendingResult() {
            @Override
            public void result(Order pendingOrder) {
                //  If a Pending Order is found
                if (pendingOrder != null) {

                    order = pendingOrder;
                    setFullyListView();

                    ordProdsList = order.getOrdProds();

                    ordProdRowAdapter = new OrdProdRowAdapter(OrderActivity.this, ordProdsList);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listView.setAdapter(ordProdRowAdapter);
                        }
                    });

                    //  Takes all the OrdProds that belong to this Order
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setEmptyListView();
                        }
                    });
                }
            }
        });


        keepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, HomeActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ordProdsList != null){

                    Intent intent = new Intent(OrderActivity.this, CheckOutActivity.class);
                    intent.putExtra("user", user);
                    intent.putExtra("order", order);
                    startActivity(intent);
                }
            }
        });

    }


    private void setFullyListView() {
        emptyTextView.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        totalTextView.setText(String.format(Locale.ENGLISH, "%.02f", order.getTotal()));
    }
    private void setEmptyListView() {
        listView.setVisibility(View.GONE);
        emptyTextView.setVisibility(View.VISIBLE);
        totalTextView.setText("0.00");
    }

    private float calculateTotal() {
        float total = 0f;
        for (OrderProduct ordProd : order.getOrdProds()){
            total += ordProd.getSubTotal();
        }
        return total;
    }
}