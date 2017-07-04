package com.flipkart.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flipkart.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductDetailsFragment} interface
 * to handle interaction events.
 * Use the {@link ProductDetailsFragment#} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailsFragment extends Fragment {

    private ImageView mImage;
    private TextView mTvProductName, mTvProductPrice;


    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        mTvProductName = (TextView) view.findViewById(R.id.tv_product_name);
        mTvProductPrice = (TextView) view.findViewById(R.id.tv_product_price);
        mImage = (ImageView) view.findViewById(R.id.imageView);


        String productName = getArguments().getString("ProductName");
        String productPrice = getArguments().getString("ProductPrice");
        int productImage = getArguments().getInt("ProductImage");

        mTvProductName.setText(productName);
        mTvProductPrice.setText(productPrice);
        mImage.setImageResource(productImage);
        Log.d("amar", "onCreateView: " + productName + " ," + productPrice + " ," + productImage);
        return view;
    }


}
