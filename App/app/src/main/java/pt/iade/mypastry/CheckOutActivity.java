package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import pt.iade.mypastry.enums.OrderType;
import pt.iade.mypastry.models.Order;

public class CheckOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        Intent intent = getIntent();
        Order order = (Order) intent.getSerializableExtra("order");

        TextView subTotalTextView = (TextView) findViewById(R.id.checkout_subtotal_textView);
        subTotalTextView.setText(String.format("%.2f", order.getTotal()) + " €");

        TextView totalTextView = (TextView) findViewById(R.id.checkout_total_textView);
        totalTextView.setText(String.format("%.2f", order.getTotal()) + " €");

        Button paymentButton = (Button) findViewById(R.id.checkout_pay_button);
        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton deliveryRadioButton = (RadioButton) findViewById(R.id.checkout_delivery_radio_button);
                RadioButton mobileRadioButton = (RadioButton) findViewById(R.id.checkout_mobile_radio_button);

                if (deliveryRadioButton.isChecked()) {
                    order.setType(OrderType.DELIVERY);
                }

                if(mobileRadioButton.isChecked()){
                    order.setType(OrderType.MOBILE);
                    Intent intent = new Intent(CheckOutActivity.this, MobileOrderActivity.class);
                    intent.putExtra("order", order);
                    startActivity(intent);
                }


            }
        });
    }

    public void returnToCallingActivity(View view){
        finish();
    }
}