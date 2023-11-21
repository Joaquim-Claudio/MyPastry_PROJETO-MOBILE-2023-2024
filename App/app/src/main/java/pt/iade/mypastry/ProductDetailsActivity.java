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


        TextView quantityTextView = (TextView) findViewById(R.id.product_details_quant);
        ConstraintLayout decreaseQuant = (ConstraintLayout) findViewById(R.id.product_details_decrease_quant);
        ConstraintLayout increaseQuant = (ConstraintLayout) findViewById(R.id.product_details_increase_quant);


        Button addButton = (Button) findViewById(R.id.product_details_add_button);
        Float subTotal = product.getPrice() * valueOf(quantityTextView.getText().toString());
        addButton.setText("Adicionar ao carrinho - " + String.format("%.2f", subTotal) + " €");

        increaseQuant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer quantity = valueOf(quantityTextView.getText().toString()) + 1;
                quantityTextView.setText(quantity.toString());

                Float newSubTotal = product.getPrice() * valueOf(quantityTextView.getText().toString());
                addButton.setText("Adicionar ao carrinho - " + String.format("%.2f", newSubTotal) + " €");
            }
        });

        decreaseQuant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer quantity = valueOf(quantityTextView.getText().toString());
                if(quantity > 1){
                    quantity--;
                    quantityTextView.setText(quantity.toString());

                    Float newSubTotal = product.getPrice() * valueOf(quantityTextView.getText().toString());
                    addButton.setText("Adicionar ao carrinho - " + String.format("%.2f", newSubTotal) + " €");
                }
            }
        });



    }


    public void returnToCallingActivity(View view){
        finish();
    }
}