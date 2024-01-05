package pt.iade.mypastry;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import pt.iade.mypastry.models.Order;
import pt.iade.mypastry.models.OrderProduct;
import pt.iade.mypastry.models.Product;
import pt.iade.mypastry.models.User;

public class ProductDetailsActivity extends AppCompatActivity {

    ImageView productImage;
    TextView productName, productDescription, productPrice,
            quantityTextView, subTotalTextView;
    ConstraintLayout decreaseQuant, increaseQuant, addButton;


    User user;
    Product product;
    OrderProduct ordProd;
    Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        product = (Product) intent.getSerializableExtra("product");

        setupComponents();
    }


    public void returnToCallingActivity(View view){
        finish();
    }

    private void setupComponents() {
        productName = (TextView) findViewById(R.id.product_details_name_textView);
        productDescription = (TextView) findViewById(R.id.product_details_description_textView);
        productPrice = (TextView) findViewById(R.id.product_details_price_textView);
        productImage = (ImageView) findViewById(R.id.product_details_image);
        quantityTextView = (TextView) findViewById(R.id.product_details_quant);
        decreaseQuant = (ConstraintLayout) findViewById(R.id.product_details_decrease_quant);
        increaseQuant = (ConstraintLayout) findViewById(R.id.product_details_increase_quant);
        subTotalTextView = (TextView) findViewById(R.id.product_details_sub_total_textView);
        addButton = (ConstraintLayout) findViewById(R.id.product_details_add_button);

        //  Setting onClick Listener to increase the product quantity
        increaseQuant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = parseInt(quantityTextView.getText().toString()) + 1;
                quantityTextView.setText(String.format(Locale.FRANCE,"%d", quantity));

                float newSubTotal = product.getPrice() * quantity;
                subTotalTextView.setText(String.format(Locale.ENGLISH, "%.2f", newSubTotal));
            }
        });

        // Setting onClick Listener to decrease the product quantity
        decreaseQuant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = parseInt(quantityTextView.getText().toString());
                if (quantity > 1){
                    quantity--;
                    quantityTextView.setText(String.format(Locale.FRANCE,"%d", quantity));

                    Float newSubTotal = product.getPrice() * quantity;
                    subTotalTextView.setText(String.format(Locale.ENGLISH, "%.2f", newSubTotal));
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ordProd = new OrderProduct();

                //  Firstly checking for existing Pending Order
                Order.GetPending(user.getId(), new Order.GetPendingResult() {
                    @Override
                    public void result(Order pendingOrder) {
                        if (pendingOrder != null){
                            order=pendingOrder;

                            //  Adding a new OrderProduct to an existing order
                            performAddOrderProduct();
                        }
                        else {
                            //  Creating a new Order
                            order = new Order();
                            initializeNewOrder();
                            order.save(new Order.SaveResult() {
                                @Override
                                public void result() {
                                    //  Adding a new OrderProduct to an existing order
                                    performAddOrderProduct();
                                }
                            });
                        }
                    }
                });

            }
        });

        populateViews();
    }

    private void populateViews() {
        productName.setText(product.getName());
        productDescription.setText(product.getDescription());
        productPrice.setText(String.format(Locale.FRANCE, "%.2f €", product.getPrice()));
        //  TODO: update Product model to have an image rsc
        productImage.setImageResource(R.drawable.settings_icon);

        quantityTextView.setText("1");

        Float subTotal = product.getPrice() * parseInt(quantityTextView.getText().toString());
        subTotalTextView.setText(String.format(Locale.ENGLISH, "%.2f", subTotal));
    }

    private void commitViews() {
        ordProd.setProductId(product.getId());
        ordProd.setOrderId(order.getId());
        ordProd.setQuantity(parseInt(quantityTextView.getText().toString()));
        ordProd.setSubTotal(Float.parseFloat(subTotalTextView.getText().toString()));
    }

    private void initializeNewOrder() {
        order.setUserId(user.getId());
    }

    private void performAddOrderProduct() {
        commitViews();
        ordProd.saveProdToOrder(order.getId(), new OrderProduct.SaveProdResult() {
            @Override
            public void result() {
                Intent intent = new Intent(ProductDetailsActivity.this, OrderActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("order", order);
                intent.putExtra("ordprod", ordProd);
                intent.putExtra("product", product);

                startActivity(intent);
            }
        });
    }
}