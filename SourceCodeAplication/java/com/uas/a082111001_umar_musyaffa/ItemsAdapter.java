package com.uas.a082111001_umar_musyaffa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemsAdapter extends BaseAdapter {

    private final Context context;
    private final List<Items> itemsList;

    public ItemsAdapter(Context context, List<Items> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
            holder = new ViewHolder();
            holder.textViewName = convertView.findViewById(R.id.textViewName);
            holder.textViewPrice = convertView.findViewById(R.id.textViewPrice);
            holder.imageViewCPU = convertView.findViewById(R.id.imageViewCPU);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        Items items = itemsList.get(position);
        holder.textViewName.setText(items.getNamaBarang());
        holder.textViewPrice.setText(items.getHargaBarang());

        // Switch case for CPU name
        switch (items.getCpuName().toLowerCase()) {
            case "intel":
                holder.imageViewCPU.setImageResource(R.drawable.intel_logo);
                break;
            case "amd":
                holder.imageViewCPU.setImageResource(R.drawable.amd_logo);
                break;
            // Add more cases as needed
            default:
                holder.imageViewCPU.setImageResource(R.drawable.default_logo);
                break;
        }

        // Set an onClickListener
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ItemDetailActivity.class);
            intent.putExtra("gambarBarang", items.getGambarBarang()); // Correct instance method call
            intent.putExtra("namaBarang", items.getNamaBarang());
            intent.putExtra("hargaBarang", items.getHargaBarang());
            intent.putExtra("deskripsiBarang", items.getDeskripsiBarang());
            intent.putExtra("cpuName", items.getCpuName());
            context.startActivity(intent);
        });

        return convertView;
    }

    static class ViewHolder {
        TextView textViewName;
        TextView textViewPrice;
        ImageView imageViewCPU;
    }
}
