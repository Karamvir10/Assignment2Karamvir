package com.example.map524assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private RecycleViewClickListener listener;
    ArrayList<History.Operation> transactions;
    Context context;
    public HistoryAdapter(ArrayList<History.Operation> transactions,
                          Context context, RecycleViewClickListener listener){
        this.transactions = transactions;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.productName.setText(String.valueOf(transactions.get(position).name
                + "      Price: " + transactions.get(position).price
                + "      QTY: " + transactions.get(position).quantity));
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView productName;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.history_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }
    public interface RecycleViewClickListener{
        void onClick(View view, int position);
    }
}
