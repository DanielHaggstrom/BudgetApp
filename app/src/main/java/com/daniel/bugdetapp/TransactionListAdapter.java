package com.daniel.bugdetapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder> {
    private final LayoutInflater mInflater;
    private List<Transaction> mTransactions; // Cached copy of transactions

    TransactionListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new TransactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        if (mTransactions != null) {
            Transaction current = mTransactions.get(position);
            holder.quantityItemView.setText(Float.toString(current.getQuantity()));
            holder.timeItemView.setText(current.getTimestamp());
        } else {
            // Covers the case of data not being ready yet.
        }
    }

    void setTransactions(List<Transaction> transactions){
        mTransactions = transactions;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mTransactions has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mTransactions != null)
            return mTransactions.size();
        else return 0;
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder {
        private final TextView quantityItemView;
        private final TextView timeItemView;

        private TransactionViewHolder(View itemView) {
            super(itemView);
            quantityItemView = itemView.findViewById(R.id.textViewQuantity);
            timeItemView = itemView.findViewById(R.id.textViewTime);
        }
    }
}
