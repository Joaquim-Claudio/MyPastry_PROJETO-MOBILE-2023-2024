package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pt.iade.mypastry.models.Product;

public class MenuActivity extends AppCompatActivity {
    public final static String EXTRA_PRODUCT_KEY = "pt.iade.mypastry.PRODUCT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void returnToHomeActivity(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void callProductDetailsActivity(View view){
        Intent intent = new Intent(this, ProductDetailsActivity.class);

        ImageView imageView = (ImageView) findViewById(R.id.menu_product_imageView1);
        Drawable productImage = imageView.getDrawable();

        TextView name_textView = (TextView) findViewById(R.id.menu_product_name_textView1);
        String productName = name_textView.getText().toString();

        TextView description_textView = (TextView) findViewById(R.id.menu_product_description_textView1);
        String productDescription = description_textView.getText().toString();

        TextView price_textView = (TextView) findViewById(R.id.menu_product_price_textView1);
        Float productPrice = Float.valueOf(price_textView.getText().toString());

        Product product = new Product();
        product.setName(productName);
        //product.setSrcImage(productImage);
        product.setDescription(productDescription);
        product.setPrice(productPrice);


        intent.putExtra(EXTRA_PRODUCT_KEY, product);
        startActivity(intent);
    }
}