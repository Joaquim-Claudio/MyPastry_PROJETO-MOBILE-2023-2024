package pt.iade.mypastry.adapters;

import static pt.iade.mypastry.R.color.text_color_orange;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import pt.iade.mypastry.R;
import pt.iade.mypastry.enums.OrderStatus;
import pt.iade.mypastry.models.Order;

public class HistRowAdapter extends RecyclerView.Adapter<HistRowAdapter.ViewHolder> {

    Context context;
    ArrayList<Order> orders;

    public HistRowAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public HistRowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_order_historic, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull HistRowAdapter.ViewHolder holder, int position) {
        Order order = orders.get(position);

        holder.idTextView.setText(String.format(Locale.FRANCE, "%05d", order.getId()));
        holder.dateTextView.setText(order.getDate().toString());
        holder.statusTextView.setText(order.getStatus().toString());
        holder.costTextView.setText(String.format(Locale.FRANCE, "%.02f€", order.getTotal()));

        if (order.getStatus() == OrderStatus.PENDING){
            holder.statusTextView.setTextColor(text_color_orange);
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView idTextView, dateTextView, statusTextView, costTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.hist_id_textView);
            dateTextView = itemView.findViewById(R.id.hist_date_textView);
            statusTextView = itemView.findViewById(R.id.hist_status_textView);
            costTextView = itemView.findViewById(R.id.hist_cost_textView);
        }
    }
}
