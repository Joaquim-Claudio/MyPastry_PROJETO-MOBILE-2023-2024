package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pt.iade.mypastry.models.Product;
import pt.iade.mypastry.repositories.ProductRepository;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        Product product = ProductRepository.getProduct(intent.getIntExtra("product_id", 0));

        TextView productName = (TextView) findViewById(R.id.product_name_textView);
        TextView productDescription = (TextView) findViewById(R.id.product_description_textView);
        TextView productPrice = (TextView) findViewById(R.id.product_price_textView);
        ImageView productImage = (ImageView) findViewById(R.id.product_image);

        productName.setText(product.getName());
        productDescription.setText(product.getDescription());
        productPrice.setText(String.format("%.2f", product.getPrice()) + " €");
        productImage.setImageResource(product.getSrcImage());

    }

    public void returnToMenuActivity(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}