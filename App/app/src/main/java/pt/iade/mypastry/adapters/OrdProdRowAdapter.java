package pt.iade.mypastry.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import pt.iade.mypastry.R;
import pt.iade.mypastry.models.OrderProduct;
import pt.iade.mypastry.models.Product;

public class OrdProdRowAdapter extends RecyclerView.Adapter<OrdProdRowAdapter.ViewHolder> {

    Context context;
    ArrayList<OrderProduct> orderProducts;
    ArrayList<Product> products;

    public OrdProdRowAdapter(Context context, ArrayList<OrderProduct> orderProducts, ArrayList<Product> products) {
        this.context = context;
        this.orderProducts = orderProducts;
        this.products = products;
    }


    @NonNull
    @Override
    public OrdProdRowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_order_product, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdProdRowAdapter.ViewHolder holder, int position) {
        OrderProduct ordProd = orderProducts.get(position);
        Product product = products.get(position);

        holder.nameTextView.setText(product.getName());
        holder.descriptionTextView.setText(product.getDescription());
        holder.imageView.setImageResource(product.getImage());
        holder.subTotalTextView.setText(String.format(Locale.ENGLISH, "%.02f", ordProd.getSubTotal()));
        holder.quantityTextView.setText(String.format(Locale.FRANCE, "%d", ordProd.getQuantity()));

        //TODO: Set up click listener for all the buttons
        setUpButtons(holder, position);
    }

    @Override
    public int getItemCount() {
        return orderProducts.size();
    }

    private void setUpButtons(@NonNull OrdProdRowAdapter.ViewHolder holder, int position) {
        OrderProduct ordProd = orderProducts.get(position);
        Product product = products.get(position);

        //  Sets up click listener to remove a product from the order
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderProduct.DeleteProdFromOrder(ordProd.getId(), ordProd.getOrderId(), new OrderProduct.DeleteProdResult() {
                    @Override
                    public void result() {
                        notifyItemRemoved(position);
                    }
                });

                orderProducts.remove(position);
            }
        });


        //  Sets up click listener to increase quantity of product
        holder.increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int quantity = Integer.parseInt(holder.quantityTextView.getText().toString()) + 1;
                ordProd.setQuantity(quantity);

                float newSubTotal = product.getPrice() * quantity;
                ordProd.setSubTotal(newSubTotal);

                ordProd.saveProdToOrder(ordProd.getOrderId(), new OrderProduct.SaveProdResult() {
                    @Override
                    public void result() {

                        notifyItemChanged(position);
                    }
                });
            }
        });

        //  Sets up click listener to decrease quantity of product
        holder.decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int quantity = Integer.parseInt(holder.quantityTextView.getText().toString());

                if (quantity > 1) {
                    quantity--;
                    ordProd.setQuantity(quantity);

                    float newSubTotal = product.getPrice() * quantity;
                    ordProd.setSubTotal(newSubTotal);

                    ordProd.saveProdToOrder(ordProd.getOrderId(), new OrderProduct.SaveProdResult() {
                        @Override
                        public void result() {

                            notifyItemChanged(position);
                        }
                    });
                }
            }
        });

    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView, descriptionTextView, subTotalTextView, quantityTextView;
        ImageView imageView;
        ConstraintLayout deleteButton, decreaseButton, increaseButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.order_product_name_textView);
            descriptionTextView = itemView.findViewById(R.id.order_product_description_textView);
            subTotalTextView = itemView.findViewById(R.id.order_product_sub_total_textView);
            quantityTextView = itemView.findViewById(R.id.order_product_quant_textView);
            imageView = itemView.findViewById(R.id.order_product_imageView);
            deleteButton = itemView.findViewById(R.id.order_product_delete_button);
            decreaseButton = itemView.findViewById(R.id.order_product_decrease_quant);
            increaseButton = itemView.findViewById(R.id.order_product_increase_quant);
        }
    }
}
