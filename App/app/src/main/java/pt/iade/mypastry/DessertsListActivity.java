package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pt.iade.mypastry.adapters.ProductRowAdapter;
import pt.iade.mypastry.enums.ProductType;
import pt.iade.mypastry.models.Product;
import pt.iade.mypastry.models.User;

public class DessertsListActivity extends AppCompatActivity {
    ArrayList<Product> productsList;
    RecyclerView listView;
    ProductRowAdapter productRowAdapter;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desserts_list);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        setupComponents();
    }
    public void returnToHomeActivity(View view){
        finish();
    }

    private void setupComponents() {
        Product.GetAllByType(ProductType.DESSERT, new Product.GetByTypeResult() {
            @Override
            public void result(ArrayList<Product> products) {
                productsList = products;

                productRowAdapter = new ProductRowAdapter(DessertsListActivity.this, productsList);
                productRowAdapter.setOnClickListener(new ProductRowAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(DessertsListActivity.this, ProductDetailsActivity.class);
                        intent.putExtra("user", user);
                        intent.putExtra("product", productsList.get(position));

                        startActivity(intent);
                    }
                });

                listView = (RecyclerView) findViewById(R.id.dessert_product_list);
                listView.setLayoutManager(new LinearLayoutManager(DessertsListActivity.this));
                listView.setAdapter(productRowAdapter);
            }
        });
    }
}
