package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.Locale;

import pt.iade.mypastry.enums.OrderStatus;
import pt.iade.mypastry.enums.OrderType;
import pt.iade.mypastry.models.Order;
import pt.iade.mypastry.models.User;

public class CheckOutActivity extends AppCompatActivity {
    TextView subTotalTextView, totalTextView, deliveryCostTextView;
    EditText deliveryAddressTextView;
    Button paymentButton;
    ConstraintLayout layout;
    RadioButton mobileRadioButton, deliveryRadioButton;
    User user;
    Order order;
    String deliveryAddress;
    double deliveryCost = 0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        layout = (ConstraintLayout) findViewById(R.id.checkout_layout);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        order = (Order) intent.getSerializableExtra("order");

        setupComponents();
    }

    private void setupComponents() {
        subTotalTextView = (TextView) findViewById(R.id.checkout_subtotal_textView);
        totalTextView = (TextView) findViewById(R.id.checkout_total_textView);
        deliveryCostTextView = (TextView) findViewById(R.id.checkout_delivery_cost_textView);
        deliveryAddressTextView = (EditText) findViewById(R.id.checkout_delivery_address_textView);
        deliveryRadioButton = (RadioButton) findViewById(R.id.checkout_delivery_radio_button);
        mobileRadioButton = (RadioButton) findViewById(R.id.checkout_mobile_radio_button);
        paymentButton = (Button) findViewById(R.id.checkout_pay_button);

        mobileRadioButton.setChecked(true);
        deliveryAddressTextView.setVisibility(View.GONE);

        deliveryRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliveryCost = (0.1 * order.getTotal())+1;
                deliveryAddressTextView.setVisibility(View.VISIBLE);
                deliveryCostTextView.setText(String.format(Locale.ENGLISH, "%.02f €", deliveryCost));
            }
        });

        mobileRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliveryCost = 0f;
                deliveryAddressTextView.setVisibility(View.GONE);
                deliveryCostTextView.setText(String.format(Locale.ENGLISH, "%.02f €", deliveryCost));
            }
        });

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (deliveryRadioButton.isChecked()) {
                    order.setType(OrderType.DELIVERY);
                    throwPopUpWindow();
                }

                if(mobileRadioButton.isChecked()){
                    order.setType(OrderType.MOBILE);
                    throwPopUpWindow();
                }


            }
        });

        populateViews();
    }

    private void populateViews() {
        subTotalTextView.setText(String.format(Locale.ENGLISH, "%.2f €", order.getTotal()));
        deliveryCostTextView.setText(String.format(Locale.ENGLISH, "%.02f €", deliveryCost));
        totalTextView.setText(String.format(Locale.ENGLISH, "%.2f €", order.getTotal()));
    }

    private void commitValues() {
        user.addPoints((int) Math.floor(order.getTotal()));

        order.setStatus(OrderStatus.PREPARING);
        order.setDate(LocalDate.now());
        order.setTotal(order.getTotal() + deliveryCost);
    }

    public void throwPopUpWindow(){

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
        valueTextView.setText(String.format(Locale.FRANCE, "%.2f €", order.getTotal() + deliveryCost));

        Button cancelButton = (Button) popUpView.findViewById(R.id.payment_popup_cancel_button);
        Button confirmButton = (Button) popUpView.findViewById(R.id.payment_popup_confirm_button);

        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popUpWindow.dismiss();
                return false;
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.dismiss();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitValues();

                user.save();
                order.save(new Order.SaveResult() {
                    @Override
                    public void result() {
                        Intent intent = new Intent(CheckOutActivity.this, MobileOrderActivity.class);
                        intent.putExtra("user", user);
                        intent.putExtra("order", order);

                        startActivity(intent);
                    }
                });

            }
        });


    }

    public void returnToCallingActivity(View view){
        finish();
    }
}