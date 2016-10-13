package com.duarte.victor.plr.view.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duarte.victor.plr.R;
import com.duarte.victor.plr.model.Plr;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PlrListAdapter extends RecyclerView.Adapter<PlrListAdapter.ViewHolder> {
    List<Plr> items;

    public PlrListAdapter(List<Plr> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plr_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Plr item = items.get(position);
        holder.TxPlrItemText.setText(item.getText());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_plr_item_text)
        TextView TxPlrItemText;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
