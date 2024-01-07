package pt.iade.mypastry.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import pt.iade.mypastry.R;
import pt.iade.mypastry.models.Product;

public class RandRowAdapter extends RecyclerView.Adapter<RandRowAdapter.ViewHolder> {
    ArrayList<Product> randomProducts;
    Context context;
    ItemClickListener clickListener;

    public RandRowAdapter(Context context, ArrayList<Product> randomProducts) {
        this.context = context;
        this.randomProducts = randomProducts;
        clickListener = null;
    }


    @NonNull
    @Override
    public RandRowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_rand_product, parent, false);

        return new ViewHolder(view);
    }

    public void setOnClickListener(ItemClickListener listener){
        clickListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull RandRowAdapter.ViewHolder holder, int position) {
        Product product = randomProducts.get(position);

        holder.imageView.setImageResource(product.getImage());
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText(String.format(Locale.FRANCE, "%.02f€", product.getPrice()));
        holder.deliveryCostTextView.setText(String.format(Locale.FRANCE, "Taxa de entrega: €%.02f",
                (0.1 * product.getPrice())+1));
        holder.timeTextView.setText("10 min");
    }

    @Override
    public int getItemCount() {
        return randomProducts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView nameTextView, priceTextView, deliveryCostTextView, timeTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.rand_imageView);
            nameTextView = itemView.findViewById(R.id.rand_name_texView);
            priceTextView = itemView.findViewById(R.id.rand_price);
            deliveryCostTextView = itemView.findViewById(R.id.rand_delivery_cost);
            timeTextView = itemView.findViewById(R.id.rand_time);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
