package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

import pt.iade.mypastry.enums.OrderStatus;
import pt.iade.mypastry.enums.OrderType;
import pt.iade.mypastry.models.Order;
import pt.iade.mypastry.models.User;

public class OrderStatusActivity extends AppCompatActivity {

    TextView orderTypeTextView, orderNumberTextView,  instructionTextView;
    ImageView orderTypeImageView, statusImageView;
    ConstraintLayout layout;

    User user;
    Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        order = (Order) intent.getSerializableExtra("order");

        setupComponents();
    }



    private void setupComponents() {
        orderTypeTextView = (TextView) findViewById(R.id.order_type_textView);
        orderTypeImageView = (ImageView) findViewById(R.id.order_type_imageView);

        orderNumberTextView = (TextView) findViewById(R.id.admin_order_id_textView);
        layout = (ConstraintLayout) findViewById(R.id.mobile_layout);
        instructionTextView = (TextView) findViewById(R.id.mobile_instruction_textView);
        statusImageView = (ImageView) findViewById(R.id.mobile_status_imageView);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (order.getStatus() == OrderStatus.COMPLETED){

                    order.save(new Order.SaveResult() {
                        @Override
                        public void result() {

                            Intent intent = new Intent(OrderStatusActivity.this, HomeActivity.class);
                            intent.putExtra("user", user);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                            startActivity(intent);
                            OrderStatusActivity.this.finish();
                        }
                    });

                } else {
                    updateView();

                }


            }
        });

        populateViews();
    }

    public void updateView(){
        if (order.getStatus() == OrderStatus.PREPARING){
            order.setStatus(OrderStatus.DELIVERING);
            instructionTextView.setText("O seu pedido está pronto. Apresente-se ao balcão.");
            statusImageView.setImageResource(R.drawable.mobile_order_ready);
        }
        else if (order.getStatus() == OrderStatus.DELIVERING){
            order.setStatus(OrderStatus.COMPLETED);
            instructionTextView.setVisibility(View.GONE);
            statusImageView.setImageResource(R.drawable.mobile_comp_order);
        }
    }


    private void populateViews() {
        int orderNumber = new Random().ints(0, 100).findFirst().getAsInt();

        orderNumberTextView.setText(String.format(Locale.FRANCE,"#%03d", orderNumber));

        if (order.getType() == OrderType.MOBILE){
            orderTypeImageView.setImageResource(R.drawable.fast_delivery_icon);
            orderTypeTextView.setText("Pedido Mobile");
        } else {
            orderTypeImageView.setImageResource(R.drawable.location_icon);
            orderTypeTextView.setText("Pedido Delivery");
        }
    }
}