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

import com.google.android.material.snackbar.Snackbar;

import pt.iade.mypastry.models.Order;
import pt.iade.mypastry.models.OrderProduct;
import pt.iade.mypastry.models.Product;
import pt.iade.mypastry.models.User;

public class OrderActivity extends AppCompatActivity {
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        userId = intent.getIntExtra("user_id", 0);
        /*
        User user = UserRepository.getUser(userId);

        Order order = OrderRepository.getUserPendingOrder(user.getId());

        if(order == null){

        }   else{
                for (OrderProduct p : order.getOrderProducts()){
                    setCartProduct(order, p);
                }


                TextView total = (TextView) findViewById(R.id.order_total_textView);
                total.setText(String.format("%.2f", order.getTotal()) + " €");


                Button checkOutButton = (Button) findViewById(R.id.order_check_out_button);
                checkOutButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (order.getOrderProducts().size() == 0){

                        }else{
                            Intent intent = new Intent(OrderActivity.this, CheckOutActivity.class);
                            intent.putExtra("user_id", userId);
                            intent.putExtra("order_id", order.getId());
                            startActivity(intent);
                        }
                    }
                });

            }

         */

    }

    public void callHomeActivity(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("user_id", userId);
        startActivity(intent);
    }




    private void setCartProduct(Order order, OrderProduct orderProduct){
        String defaultId = "order_product_";
        /*
        Product product = ProductRepository.getProduct(orderProduct.getProductId());

        TextView orderProductName = (TextView) findViewById(getResources().getIdentifier(defaultId+"name_textView_"+ orderProduct.getId(), "id", getPackageName()));
        TextView orderProductDescription = (TextView) findViewById(getResources().getIdentifier(defaultId+"description_textView_"+ orderProduct.getId(), "id", getPackageName()));
        TextView orderProductSubTotal = (TextView) findViewById(getResources().getIdentifier(defaultId+"sub_total_textView_"+ orderProduct.getId(), "id", getPackageName()));
        TextView orderProductQuantity = (TextView) findViewById(getResources().getIdentifier(defaultId+"quant_"+ orderProduct.getId(), "id", getPackageName()));
        ImageView orderProductImage = (ImageView) findViewById(getResources().getIdentifier(defaultId+"imageView_"+ orderProduct.getId(), "id", getPackageName()));

        orderProductName.setText(product.getName());
        orderProductDescription.setText(product.getDescription());
        orderProductSubTotal.setText(String.format("%.2f", orderProduct.getSubTotal()) + " €");
        orderProductQuantity.setText(orderProduct.getQuantity().toString());
        orderProductImage.setImageResource(product.getSrcImage());

        TextView quantityTextView = (TextView) findViewById(getResources().getIdentifier(defaultId+"quant_"+ orderProduct.getId(), "id", getPackageName()));
        ConstraintLayout decreaseQuant = (ConstraintLayout) findViewById(getResources().getIdentifier(defaultId+"decrease_quant_"+ orderProduct.getId(), "id", getPackageName()));
        ConstraintLayout increaseQuant = (ConstraintLayout) findViewById(getResources().getIdentifier(defaultId+"increase_quant_"+ orderProduct.getId(), "id", getPackageName()));



        //  Setting onClick Listener to increase the product quantity
        increaseQuant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer quantity = parseInt(quantityTextView.getText().toString()) + 1;
                quantityTextView.setText(quantity.toString());
                orderProduct.setQuantity(quantity);

                Float newSubTotal = product.getPrice() * parseInt(quantityTextView.getText().toString());
                orderProductSubTotal.setText(String.format("%.2f", newSubTotal) + " €");

                TextView total = (TextView) findViewById(R.id.order_total_textView);
                total.setText(String.format("%.2f", order.getTotal()) + " €");
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
                    orderProduct.setQuantity(quantity);

                    Float newSubTotal = product.getPrice() * parseInt(quantityTextView.getText().toString());
                    orderProductSubTotal.setText(String.format("%.2f", newSubTotal) + " €");

                    TextView total = (TextView) findViewById(R.id.order_total_textView);
                    total.setText(String.format("%.2f", order.getTotal()) + " €");
                }
            }
        });

        ConstraintLayout deleteProduct = (ConstraintLayout) findViewById(getResources().getIdentifier(defaultId+"delete_button_"+ orderProduct.getId(), "id", getPackageName()));
        deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isRemoved = order.removeOrderProduct(orderProduct.getId());


               if (isRemoved){
                   ConstraintLayout coordinatorLayout = (ConstraintLayout) findViewById(R.id.order_layout);
                   Snackbar snackbar =  Snackbar.make(coordinatorLayout, "Produto removido com sucesso!", Snackbar.LENGTH_LONG);
                   snackbar.show();
               } else{
                   ConstraintLayout coordinatorLayout = (ConstraintLayout) findViewById(R.id.order_layout);
                   Snackbar snackbar =  Snackbar.make(coordinatorLayout, "Impossível remover o produto!", Snackbar.LENGTH_LONG);
                   snackbar.show();
               }
            }
        });
        */
    }


}