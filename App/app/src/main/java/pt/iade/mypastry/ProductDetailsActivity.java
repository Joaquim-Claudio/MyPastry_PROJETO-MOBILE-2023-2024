package pt.iade.mypastry;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import pt.iade.mypastry.models.Product;
import pt.iade.mypastry.models.User;
import pt.iade.mypastry.repositories.ProductRepository;
import pt.iade.mypastry.repositories.UserRepository;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        //  Taking the main objects
        Intent intent = getIntent();
        User user = UserRepository.getUser(intent.getIntExtra("user_id", 0));
        Product product = ProductRepository.getProduct(intent.getIntExtra("product_id", 0));


        //  Getting all the View elements
        TextView productName = (TextView) findViewById(R.id.product_name_textView);
        TextView productDescription = (TextView) findViewById(R.id.product_description_textView);
        TextView productPrice = (TextView) findViewById(R.id.product_price_textView);
        ImageView productImage = (ImageView) findViewById(R.id.product_image);


        //  Setting the value of the the Views
        productName.setText(product.getName());
        productDescription.setText(product.getDescription());
        productPrice.setText(String.format("%.2f", product.getPrice()) + " €");
        productImage.setImageResource(product.getSrcImage());


        //  Getting the quantity button Views
        TextView quantityTextView = (TextView) findViewById(R.id.product_details_quant);
        ConstraintLayout decreaseQuant = (ConstraintLayout) findViewById(R.id.product_details_decrease_quant);
        ConstraintLayout increaseQuant = (ConstraintLayout) findViewById(R.id.product_details_increase_quant);


        TextView subTotalTextView = (TextView) findViewById(R.id.product_details_sub_total_textView);
        Float subTotal = product.getPrice() * parseInt(quantityTextView.getText().toString());
        subTotalTextView.setText(String.format("%.2f", subTotal) + " €");



        //  Setting onClick Listener to increase the product quantity
        increaseQuant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer quantity = parseInt(quantityTextView.getText().toString()) + 1;
                quantityTextView.setText(quantity.toString());

                Float newSubTotal = product.getPrice() * valueOf(quantityTextView.getText().toString());
                subTotalTextView.setText(String.format("%.2f", newSubTotal) + " €");
            }
        });

        // Setting onClick Listener to decrease the product quantity
        decreaseQuant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer quantity = valueOf(quantityTextView.getText().toString());
                if(quantity > 1){
                    quantity--;
                    quantityTextView.setText(quantity.toString());

                    Float newSubTotal = product.getPrice() * valueOf(quantityTextView.getText().toString());
                    subTotalTextView.setText(String.format("%.2f", newSubTotal) + " €");
                }
            }
        });



    }


    public void returnToCallingActivity(View view){
        finish();
    }
}