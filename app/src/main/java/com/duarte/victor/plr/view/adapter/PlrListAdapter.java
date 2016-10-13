package com.duarte.victor.plr.view.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
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
    private List<Plr> items;
    private Context context;

    public PlrListAdapter(Context context, List<Plr> items) {
        this.context = context;
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
        holder.TxtPlrItemText.setText(item.getText());

        holder.txtPlrItemDate.setText(
                item.getCreated() != null ?
                        DateFormat.format("dd/MM/yyyy \'às\' hh:mm", item.getCreated()) :
                        context.getResources().getString(R.string.create_plr_now));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_plr_item_text)
        TextView TxtPlrItemText;

        @BindView(R.id.txt_plr_item_date)
        TextView txtPlrItemDate;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
