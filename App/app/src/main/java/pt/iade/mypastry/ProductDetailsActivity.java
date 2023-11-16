package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pt.iade.mypastry.models.Product;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra(MenuActivity.EXTRA_PRODUCT_KEY);

        //ImageView productImage = (ImageView) findViewById(R.id.product_image);
        //productImage.setImageDrawable(product.getSrcImage());

        TextView productName = (TextView) findViewById(R.id.product_name_textView);
        productName.setText(product.getName());

        TextView productDescription = (TextView) findViewById(R.id.product_description_textView);
        productDescription.setText(product.getDescription());

        TextView productPrice = (TextView) findViewById(R.id.product_price_textView);
        productPrice.setText(product.getPrice().toString());


    }

    public void returnToMenuActivity(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}