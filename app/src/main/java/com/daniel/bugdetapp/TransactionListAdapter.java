package com.daniel.bugdetapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder> {
    private final LayoutInflater mInflater;
    private List<Transaction> mTransactions; // Cached copy of transactions

    TransactionListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new TransactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        if (mTransactions != null) {
            Transaction current = mTransactions.get(position);
            holder.quantityItemView.setText(current.getQuantity().toString() + " €");
            holder.timeItemView.setText(current.getTimestamp());
            if (position == 0){
                holder.timeItemView.setVisibility(View.VISIBLE);
                holder.timeCardView.setVisibility(View.VISIBLE);
            }
            else {
                Transaction previous = mTransactions.get(position -1);
                if (previous.getTimestamp().equals(current.getTimestamp())){
                    holder.timeItemView.setVisibility(View.INVISIBLE);
                    holder.timeCardView.setVisibility(View.INVISIBLE);
                }
                else {
                    holder.timeItemView.setVisibility(View.VISIBLE);
                    holder.timeCardView.setVisibility(View.VISIBLE);
                }
            }
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
        private final CardView timeCardView;

        private TransactionViewHolder(View itemView) {
            super(itemView);
            quantityItemView = itemView.findViewById(R.id.textViewQuantity);
            timeItemView = itemView.findViewById(R.id.textViewTime);
            timeCardView = itemView.findViewById(R.id.card_view_time);
        }
    }
}
