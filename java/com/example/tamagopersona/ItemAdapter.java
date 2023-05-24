package com.example.tamagopersona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private Context context;
    private List<AllItem> AllItemsList;
    private LayoutInflater inflater;

    public ItemAdapter(Context context, List<AllItem> AllItemsList) {
        this.context = context;
        this.AllItemsList = AllItemsList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return AllItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return AllItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.adapter_item, null);

        AllItem currentItem = (AllItem) getItem(position);
        String itemName = currentItem.getName();
        double itemPrice = currentItem.getPrice();
        String imgName = currentItem.getImgName();
        int itemQuantity = currentItem.getQuantity();

        TextView itemNameView = view.findViewById(R.id.item_name);
        itemNameView.setText(itemName);

        TextView itemPriceView = view.findViewById(R.id.item_price);
        itemPriceView.setText(itemPrice + "ћ");

        TextView itemQuantityView = view.findViewById(R.id.item_quantity);
        itemQuantityView.setText("En stock: " + itemQuantity);

        ImageView itemIconView = view.findViewById(R.id.item_icon);
        String resourceName = "icon_" + imgName;
        int resId = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
        itemIconView.setImageResource(resId);

        return view;
    }

}