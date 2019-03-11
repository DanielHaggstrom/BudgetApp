package com.daniel.bugdetapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Transaction> mTransactions; // Cached copy of transactions

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private TransactionViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}
