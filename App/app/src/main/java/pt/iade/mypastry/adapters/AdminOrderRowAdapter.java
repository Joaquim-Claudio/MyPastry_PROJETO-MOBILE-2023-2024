package pt.iade.mypastry.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

import pt.iade.mypastry.R;
import pt.iade.mypastry.enums.OrderStatus;
import pt.iade.mypastry.enums.OrderType;
import pt.iade.mypastry.models.Order;
import pt.iade.mypastry.models.OrderProduct;
import pt.iade.mypastry.models.Product;

public class AdminOrderRowAdapter extends RecyclerView.Adapter<AdminOrderRowAdapter.ViewHolder> {
    Context context;
    ArrayList<Order> orderList;

    public AdminOrderRowAdapter(Context context, ArrayList<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }


    @NonNull
    @Override
    public AdminOrderRowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.column_admin_order, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminOrderRowAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Order order = orderList.get(position);
        ArrayList<OrderProduct> ordProds = new ArrayList<OrderProduct>();
        ArrayList<Product> products = new ArrayList<Product>();

        for (OrderProduct orderProduct : order.getOrdProds()){
            ordProds.add(orderProduct);
            products.add(orderProduct.getProduct());
        }


        if (order.getType() == OrderType.DELIVERY) {
            holder.typeImageView.setImageResource(R.drawable.location_icon);
            holder.typeTextView.setText("Pedido Delivery");
            holder.idTextView.setText(String.format(Locale.FRANCE, "PD-%04d", order.getId()));
        } else {
            holder.typeImageView.setImageResource(R.drawable.fast_delivery_icon);
            holder.typeTextView.setText("Pedido Mobile");
            holder.idTextView.setText(String.format(Locale.FRANCE, "PM-%04d", order.getId()));
        }

        holder.prodTextView1.setText(String.format(Locale.FRANCE, "%s:     x%d", products.get(0).getName(), ordProds.get(0).getQuantity()));
        holder.prodTextView2.setText(String.format(Locale.FRANCE, "%s:     x%d", products.get(1).getName(), ordProds.get(1).getQuantity()));
        holder.prodTextView3.setText(String.format(Locale.FRANCE, "%s:     x%d", products.get(2).getName(), ordProds.get(2).getQuantity()));
        holder.prodTextView4.setText(String.format(Locale.FRANCE, "%s:     x%d", products.get(3).getName(), ordProds.get(3).getQuantity()));
        holder.updateButton.setText("PRONTO");


        holder.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (order.getStatus() == OrderStatus.PREPARING){
                    order.setStatus(OrderStatus.DELIVERING);

                    order.save(new Order.SaveResult() {
                        @Override
                        public void result() {
                            notifyItemChanged(position);
                        }
                    });
                }

                else if (order.getStatus() == OrderStatus.DELIVERING){
                    order.setStatus(OrderStatus.COMPLETED);
                    order.save(new Order.SaveResult() {
                        @Override
                        public void result() {
                            orderList.remove(position);
                            notifyItemRemoved(position);
                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView typeImageView;
        TextView typeTextView, idTextView, prodTextView1,
                prodTextView2, prodTextView3, prodTextView4;
        Button updateButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.typeImageView = itemView.findViewById(R.id.admin_order_type_imageView);
            this.typeTextView = itemView.findViewById(R.id.admin_order_type_textView);
            this.idTextView = itemView.findViewById(R.id.admin_order_id_textView);
            this.prodTextView1 = itemView.findViewById(R.id.admin_order_prod_1);
            this.prodTextView2 = itemView.findViewById(R.id.admin_order_prod_2);
            this.prodTextView3 = itemView.findViewById(R.id.admin_order_prod_3);
            this.prodTextView4 = itemView.findViewById(R.id.admin_order_prod_4);
            this.updateButton = itemView.findViewById(R.id.admin_order_update_button);
        }
    }
}
