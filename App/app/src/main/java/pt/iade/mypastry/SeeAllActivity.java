package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import pt.iade.mypastry.adapters.ProductRowAdapter;
import pt.iade.mypastry.enums.ProductType;
import pt.iade.mypastry.models.Product;
import pt.iade.mypastry.models.User;

public class SeeAllActivity extends AppCompatActivity {
    ArrayList<Product> productsList;
    RecyclerView listView;
    ProductRowAdapter productRowAdapter;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        setupComponents();
    }

    public void returnToHomeActivity(View view){
        finish();
    }

    private void setupComponents() {
        listView = (RecyclerView) findViewById(R.id.see_all_product_list);
        listView.setLayoutManager(new LinearLayoutManager(SeeAllActivity.this));

        Product.GetAll(new Product.GetAllResult() {
            @Override
            public void result(ArrayList<Product> products) {
                productsList = products;

                productRowAdapter = new ProductRowAdapter(SeeAllActivity.this, productsList);
                productRowAdapter.setOnClickListener(new ProductRowAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(SeeAllActivity.this, ProductDetailsActivity.class);
                        intent.putExtra("user", user);
                        intent.putExtra("product", productsList.get(position));

                        startActivity(intent);
                    }
                });

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(productRowAdapter);
                    }
                });
            }
        });
    }
}
