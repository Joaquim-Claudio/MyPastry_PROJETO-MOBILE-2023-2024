package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import pt.iade.mypastry.enums.OrderStatus;
import pt.iade.mypastry.enums.OrderType;
import pt.iade.mypastry.models.Order;
import pt.iade.mypastry.models.User;
import pt.iade.mypastry.repositories.OrderRepository;
import pt.iade.mypastry.repositories.UserRepository;

public class CheckOutActivity extends AppCompatActivity {

    ConstraintLayout layout;
    int userId;
    Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        layout = (ConstraintLayout) findViewById(R.id.checkout_layout);

        Intent intent = getIntent();
        userId = intent.getIntExtra("user_id", 0);
        int orderId = intent.getIntExtra("order_id", 0);
        order = OrderRepository.getOrder(orderId);



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
                    createPopUpWindow();
                }


            }
        });
    }

    public void createPopUpWindow(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.popup_payment, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;

        PopupWindow popUpWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popUpWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
            }
        });

        TextView valueTextView = (TextView) popUpView.findViewById(R.id.payment_popup_value_textView);
        valueTextView.setText(String.format("%.2f", order.getTotal()) + " €");

        Button cancelButton = (Button) popUpView.findViewById(R.id.payment_popup_cancel_button);
        Button confirmButton = (Button) popUpView.findViewById(R.id.payment_popup_confirm_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.dismiss();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRepository.getUser(userId).addPoints((int) Math.floor(order.getTotal()));
                order.setStatus(OrderStatus.PREPARING);
                Intent intent = new Intent(CheckOutActivity.this, MobileOrderActivity.class);
                intent.putExtra("user_id", userId);
                intent.putExtra("order", order);
                startActivity(intent);
            }
        });

        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popUpWindow.dismiss();
                return false;
            }
        });
    }

    public void returnToCallingActivity(View view){
        finish();
    }
}