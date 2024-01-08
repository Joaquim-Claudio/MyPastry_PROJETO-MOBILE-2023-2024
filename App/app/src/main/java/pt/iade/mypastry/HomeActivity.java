package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import pt.iade.mypastry.adapters.RandRowAdapter;
import pt.iade.mypastry.enums.ProductType;
import pt.iade.mypastry.models.Product;
import pt.iade.mypastry.models.User;

public class HomeActivity extends AppCompatActivity {

    TextView home_textView_points;
    ConstraintLayout primaryButton, delicacyButton, beverageButton,
            dessertButton, convenienceButton, seeAllButton;
    ConstraintLayout homeButton;
    ArrayList<Product> randProdList;
    RecyclerView randListView;
    RandRowAdapter randRowAdapter;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        setupComponents();
    }


    public void callHistoricActivity(View view) {
        Intent intent = new Intent(this, HistoricActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void callOrderActivity(View view){
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void callAccountActivity(View view){
        Intent intent = new Intent(this, AccountActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void setupComponents() {
        home_textView_points = (TextView) findViewById(R.id.home_textView_points);
        primaryButton = (ConstraintLayout) findViewById(R.id.home_primary_button);
        delicacyButton = (ConstraintLayout) findViewById(R.id.home_delicacy_button);
        beverageButton = (ConstraintLayout) findViewById(R.id.home_bever_button);
        dessertButton = (ConstraintLayout) findViewById(R.id.home_dessert_button);
        convenienceButton = (ConstraintLayout) findViewById(R.id.home_convinience_button);
        seeAllButton = (ConstraintLayout) findViewById(R.id.home_see_all_button);

        homeButton = (ConstraintLayout) findViewById(R.id.home_button);

        randListView = (RecyclerView) findViewById(R.id.random_product_list);
        randListView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));

        randProdList = new ArrayList<Product>();

        Product.GetAllByType(ProductType.PRIMARY, new Product.GetByTypeResult() {
            @Override
            public void result(ArrayList<Product> products) {
                int startIndex = new Random().nextInt(products.size());
                int nextIndex = new Random().nextInt(products.size());
                int lastIndex =new Random().nextInt(products.size());

                randProdList.add(products.get(startIndex));
                randProdList.add(products.get(nextIndex));
                randProdList.add(products.get(lastIndex));

                randRowAdapter = new RandRowAdapter(HomeActivity.this, randProdList);
                randRowAdapter.setOnClickListener(new RandRowAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(HomeActivity.this, ProductDetailsActivity.class);
                        intent.putExtra("user", user);
                        intent.putExtra("product", randProdList.get(position));

                        startActivity(intent);
                    }
                });

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        randListView.setAdapter(randRowAdapter);
                    }
                });

            }
        });

        primaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PrimaryListActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        delicacyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DelicaciesListActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        beverageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BeverageListActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        dessertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DessertsListActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        convenienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ConveniencesListActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        seeAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SeeAllActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        populateViews();
    }

    public void populateViews() {
        home_textView_points.setText(String.format(Locale.FRANCE, "%d pontos", user.getPoints()));
    }
}








