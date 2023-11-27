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

import pt.iade.mypastry.models.Cart;
import pt.iade.mypastry.models.CartProduct;
import pt.iade.mypastry.models.Product;
import pt.iade.mypastry.models.User;
import pt.iade.mypastry.repositories.ProductRepository;
import pt.iade.mypastry.repositories.UserRepository;

public class CartActivity extends AppCompatActivity {
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();
        userId = intent.getIntExtra("user_id", 0);
        User user = UserRepository.getUser(userId);

        Cart cart = user.getCart();

        for (CartProduct p : cart.getCartProducts()){
            setCartProduct(cart, p);
        }


        TextView total = (TextView) findViewById(R.id.cart_total_textView);
        total.setText(String.format("%.2f", cart.getTotal()) + " €");


        Button checkOutButton = (Button) findViewById(R.id.cart_check_out_button);
        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cart.getCartProducts().size() == 0){

                }else{
                    Intent intent = new Intent(CartActivity.this, CheckOutActivity.class);
                    intent.putExtra("user_id", userId);
                    startActivity(intent);
                }
            }
        });
    }

    public void callHomeActivity(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("user_id", userId);
        startActivity(intent);
    }




    private void setCartProduct(Cart cart, CartProduct cartProduct){
        String defaultId = "cart_product_";
        Product product = ProductRepository.getProduct(cartProduct.getProductId());

        TextView cartProductName = (TextView) findViewById(getResources().getIdentifier(defaultId+"name_textView_"+cartProduct.getId(), "id", getPackageName()));
        TextView cartProductDescription = (TextView) findViewById(getResources().getIdentifier(defaultId+"description_textView_"+cartProduct.getId(), "id", getPackageName()));
        TextView cartProductSubTotal = (TextView) findViewById(getResources().getIdentifier(defaultId+"sub_total_textView_"+cartProduct.getId(), "id", getPackageName()));
        TextView cartProductQuantity = (TextView) findViewById(getResources().getIdentifier(defaultId+"quant_"+cartProduct.getId(), "id", getPackageName()));
        ImageView cartProductImage = (ImageView) findViewById(getResources().getIdentifier(defaultId+"imageView_"+cartProduct.getId(), "id", getPackageName()));

        cartProductName.setText(product.getName());
        cartProductDescription.setText(product.getDescription());
        cartProductSubTotal.setText(String.format("%.2f", cartProduct.getSubTotal()) + " €");
        cartProductQuantity.setText(cartProduct.getQuantity().toString());
        cartProductImage.setImageResource(product.getSrcImage());

        TextView quantityTextView = (TextView) findViewById(getResources().getIdentifier(defaultId+"quant_"+cartProduct.getId(), "id", getPackageName()));
        ConstraintLayout decreaseQuant = (ConstraintLayout) findViewById(getResources().getIdentifier(defaultId+"decrease_quant_"+cartProduct.getId(), "id", getPackageName()));
        ConstraintLayout increaseQuant = (ConstraintLayout) findViewById(getResources().getIdentifier(defaultId+"increase_quant_"+cartProduct.getId(), "id", getPackageName()));



        //  Setting onClick Listener to increase the product quantity
        increaseQuant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer quantity = parseInt(quantityTextView.getText().toString()) + 1;
                quantityTextView.setText(quantity.toString());
                cartProduct.setQuantity(quantity);

                Float newSubTotal = product.getPrice() * parseInt(quantityTextView.getText().toString());
                cartProductSubTotal.setText(String.format("%.2f", newSubTotal) + " €");

                TextView total = (TextView) findViewById(R.id.cart_total_textView);
                total.setText(String.format("%.2f", cart.getTotal()) + " €");
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
                    cartProduct.setQuantity(quantity);

                    Float newSubTotal = product.getPrice() * parseInt(quantityTextView.getText().toString());
                    cartProductSubTotal.setText(String.format("%.2f", newSubTotal) + " €");

                    TextView total = (TextView) findViewById(R.id.cart_total_textView);
                    total.setText(String.format("%.2f", cart.getTotal()) + " €");
                }
            }
        });
    }
}