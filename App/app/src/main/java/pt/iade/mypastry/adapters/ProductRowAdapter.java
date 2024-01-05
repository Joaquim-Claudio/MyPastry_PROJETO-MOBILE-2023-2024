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

public class ProductRowAdapter extends RecyclerView.Adapter<ProductRowAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Product> items;
    private ItemClickListener clickListener;

    public ProductRowAdapter(Context context, ArrayList<Product> items){
        this.context = context;
        this.items = items;
        clickListener = null;
    }


    @NonNull
    @Override
    public ProductRowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_product, parent, false);

        return new ViewHolder(view);
    }

    public void setOnClickListener(ItemClickListener listener){
        clickListener = listener;
    }


    @Override
    public void onBindViewHolder(@NonNull ProductRowAdapter.ViewHolder holder, int position) {
        Product item = items.get(position);

        holder.imageView.setImageResource(R.drawable.settings_icon);
        holder.nameTextView.setText(item.getName());
        holder.descriptionTextView.setText(item.getDescription());
        holder.timeTextView.setText("10 min - Estimado");
        holder.priceTextView.setText(String.format(Locale.FRANCE, "%.2f €", item.getPrice()));

        if(position == items.size()-1){
            holder.listDivider.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView nameTextView, descriptionTextView, timeTextView, priceTextView;
        View listDivider;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.product_imageView);
            nameTextView = itemView.findViewById(R.id.product_name_textView);
            descriptionTextView = itemView.findViewById(R.id.product_description_textView);
            timeTextView = itemView.findViewById(R.id.product_time_textView);
            priceTextView = itemView.findViewById(R.id.product_price_textView);
            listDivider = itemView.findViewById(R.id.list_divider);

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
