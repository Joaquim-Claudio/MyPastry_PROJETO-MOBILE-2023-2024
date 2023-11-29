package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import pt.iade.mypastry.enums.OrderStatus;
import pt.iade.mypastry.models.Order;

public class MobileOrderActivity extends AppCompatActivity {

    int userId;
    Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_order);

        Intent intent = getIntent();
        userId = intent.getIntExtra("user_id", 0);
        order = (Order) intent.getSerializableExtra("order");

        int orderNumber = new Random().ints(0, 100).findFirst().getAsInt();
        TextView orderNumberTextView = (TextView) findViewById(R.id.mobile_order_number);
        orderNumberTextView.setText("#"+String.format("%03d", orderNumber));

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.mobile_layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (order.getStatus() == OrderStatus.COMPLETED){
                    Intent intent = new Intent(MobileOrderActivity.this, HomeActivity.class);
                    intent.putExtra("user_id", userId);
                    startActivity(intent);
                }

                updateView();

            }
        });
    }

    public void updateView(){
        TextView instructionTextView = (TextView) findViewById(R.id.mobile_instruction_textView);
        ImageView statusImageView = (ImageView) findViewById(R.id.mobile_status_imageView);

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
}