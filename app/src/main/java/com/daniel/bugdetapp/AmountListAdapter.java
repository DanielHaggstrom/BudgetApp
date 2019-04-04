package com.daniel.bugdetapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AmountListAdapter extends RecyclerView.Adapter<AmountListAdapter.AmountViewHolder> {

    private LinkedList<String> mAmountList;
    private LayoutInflater mInflater;

    public AmountListAdapter(Context context, LinkedList<String> amountList) {
        mInflater = LayoutInflater.from(context);
        this.mAmountList = amountList;
    }

    void setAmounts(List<Transaction> transactions){
        for (int i = 0; i < transactions.size(); i++) {
            mAmountList.set(i, String.valueOf(transactions.get(i).getQuantity()));
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AmountListAdapter.AmountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.activity_historical_data,
                parent, false);
        return new AmountViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull AmountListAdapter.AmountViewHolder holder, int position) {
        String mCurrent = mAmountList.get(position);
        holder.amountItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mAmountList.size();
    }

    class AmountViewHolder extends RecyclerView.ViewHolder {
        public final TextView amountItemView;
        final AmountListAdapter mAdapter;

        public AmountViewHolder(View itemView, AmountListAdapter adapter) {
            super(itemView);
            amountItemView = itemView.findViewById(R.id.amount);
            this.mAdapter = adapter;
        }
    }
}