package com.chengying.root.suggess;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chengying.root.zhongcha.R;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 16-1-9.
 */
public class SuggessAdapter extends BaseAdapter {
    List<Map<String, String>> data;
    Context context;

    public SuggessAdapter(Context context, List<Map<String, String>> data) {
        this.context = context;
        this.data = data;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return data.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_near_item, null);
        }
        TextView tvShopName;
        TextView tvGoodsName;
        TextView tvPrice;
        TextView tvUnit;
        TextView tvDistance;
        tvShopName = (TextView) convertView.findViewById(R.id.shop_name);
        tvGoodsName = (TextView) convertView.findViewById(R.id.goods_name);
        tvPrice = (TextView) convertView.findViewById(R.id.price);
        tvUnit = (TextView) convertView.findViewById(R.id.unit);
        tvDistance = (TextView) convertView.findViewById(R.id.distance);

        tvShopName.setText(data.get(position).get("shopName"));
        tvGoodsName.setText(data.get(position).get("goodsName"));
        tvPrice.setText(data.get(position).get("price"));
        tvUnit.setText(data.get(position).get("unit"));
        tvDistance.setText(data.get(position).get("distance"));
        return convertView;
    }
}
