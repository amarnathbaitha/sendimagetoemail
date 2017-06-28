package com.flipkart.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flipkart.MainActivity;
import com.flipkart.R;
import com.flipkart.fragment.GalaryFragment;

/**
 * Created by santosh on 24/6/17.
 */

public class ProductListAdapter extends BaseAdapter {

    String [] productName;
    String [] productPrice;
    Fragment context;
    int [] imageId;
    private static LayoutInflater inflater=null;

    public ProductListAdapter(Fragment fragment, String[] productNameList, int[] productImages, String[] productPriceList) {

        productName=productNameList;
        context=fragment;
        imageId=productImages;
        productPrice = productPriceList;
        inflater = (LayoutInflater)context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return productName.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tvProductName;
        ImageView img;
        TextView tvProductPrice;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.item_list, null);
        holder.tvProductName=(TextView) rowView.findViewById(R.id.tv_product_name);
        holder.tvProductPrice=(TextView) rowView.findViewById(R.id.tv_product_price);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

        holder.tvProductName.setText(productName[position]);
        holder.img.setImageResource(imageId[position]);
        holder.tvProductPrice.setText(productPrice[position]);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context.getActivity(), "You Clicked "+productName[position], Toast.LENGTH_LONG).show();
            }
        });

        return rowView;
    }

}