package pt.iade.mypastry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pt.iade.mypastry.enums.ProductType;
import pt.iade.mypastry.models.Product;
import pt.iade.mypastry.repositories.ProductRepository;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        for (Product p : ProductRepository.getProducts()) {
            if (p.getType() == ProductType.MENU){
                setListElement(p);
            }
        }

    }

    public void returnToHomeActivity(View view){
        finish();
    }




    private void setListElement(Product product){
        String defaultId = "menu_product_";

        ConstraintLayout menuProduct = (ConstraintLayout) findViewById(getResources().getIdentifier(defaultId + product.getId(), "id", getPackageName()));
        menuProduct.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, ProductDetailsActivity.class);
            intent.putExtra("product_id", product.getId());
            startActivity(intent);
        });


        TextView productName = (TextView) findViewById(getResources().getIdentifier(defaultId+"name_textView_"+product.getId(), "id", getPackageName()));
        TextView productDescription = (TextView) findViewById(getResources().getIdentifier(defaultId+"description_textView_"+product.getId(), "id", getPackageName()));
        TextView productPrice = (TextView) findViewById(getResources().getIdentifier(defaultId+"price_textView_"+product.getId(), "id", getPackageName()));
        ImageView productImage = (ImageView) findViewById(getResources().getIdentifier(defaultId+"imageView_"+product.getId(), "id", getPackageName()));

        productName.setText(product.getName());
        productDescription.setText(product.getDescription());
        productPrice.setText(String.format("%.2f", product.getPrice()) + " €");
        productImage.setImageResource(product.getSrcImage());
    }
}