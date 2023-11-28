package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MobileOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_order);

        int orderNumber = new Random().ints(0, 100).findFirst().getAsInt();
        TextView orderNumberTextView = (TextView) findViewById(R.id.mobile_order_number);
        orderNumberTextView.setText("#"+String.format("%03d", orderNumber));
    }
}