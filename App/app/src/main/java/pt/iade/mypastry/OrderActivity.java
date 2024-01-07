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

    float total = 0f;

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

        populateView();

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
                    commitViews();

                    Intent intent = new Intent(OrderActivity.this, CheckOutActivity.class);
                    intent.putExtra("user", user);
                    intent.putExtra("order", order);
                    startActivity(intent);
                }
            }
        });

        //  Firstly checking for an existing Pending Order
        Order.GetPending(user.getId(), new Order.GetPendingResult() {
            @Override
            public void result(Order pendingOrder) {
                //  If a Pending Order is found
                if (pendingOrder != null){

                    setFullyListView();

                    order=pendingOrder;

                    //  Takes all the OrdProds that belong to this Order
                    order.getOrdProducts(new Order.GetOrdProdResult() {
                        @Override
                        public void result(ArrayList<OrderProduct> ordProds) {

                            if (ordProds.size() > 0){
                                ordProdsList = ordProds;

                                ArrayList<Integer> proIdList = getProductIds();

                                //  Takes all the products associated to the OrdProds previously taken
                                Product.GetAllById(proIdList, new Product.GetAllByIdResult() {
                                    @Override
                                    public void result(ArrayList<Product> products) {
                                        productsList= products;

                                        //  Creates the list view adapter
                                        ordProdRowAdapter = new OrdProdRowAdapter(OrderActivity.this, ordProdsList, productsList);

                                        //  Set the list view adapter after running all previous code
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                listView.setAdapter(ordProdRowAdapter);
                                                calculateTotal();
                                                totalTextView.setText(String.format(Locale.ENGLISH, "%.02f", total));
                                            }
                                        });

                                    }
                                });
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
                }
                else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setEmptyListView();
                        }
                    });
                }
            }
        });

    }

    private void populateView() {
        totalTextView.setText(String.format(Locale.ENGLISH, "%.02f", total));
    }


    private void initializeNewOrder() {
        order.setUserId(user.getId());
    }

    private void setFullyListView() {
        emptyTextView.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }
    private void setEmptyListView() {
        listView.setVisibility(View.GONE);
        emptyTextView.setVisibility(View.VISIBLE);
    }

    private ArrayList<Integer> getProductIds() {
        ArrayList<Integer> productIds = new ArrayList<Integer>();

        for (OrderProduct p : ordProdsList){
            productIds.add(p.getProductId());
        }

        return productIds;
    }

    private void commitViews() {
        calculateTotal();
        order.setTotal(total);
    }

    private void calculateTotal() {
        total = 0;
        for (OrderProduct p : ordProdsList){
            total += p.getSubTotal();
        }
        order.setTotal(total);
    }

}