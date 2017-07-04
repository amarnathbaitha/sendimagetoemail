package com.flipkart.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.flipkart.R;
import com.flipkart.adapter.ProductListAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GalaryFragment} interface
 * to handle interaction events.
 * Use the {@link GalaryFragment} factory method to
 * create an instance of this fragment.
 */
public class GalaryFragment extends Fragment {

    private GridView mGridView;

    public static String [] productNameList={"Baby & Kids","BED","Cookware","Home Improvment","Lighting","OutDoor","Sofa","Storage Bin","Woven Diamond White Rug"};
    public static int [] productImages={R.drawable.baby_kids,R.drawable.bed,R.drawable.cookware,R.drawable.home_improvment,R.drawable.lighting,R.drawable.outdoor,R.drawable.sofa,R.drawable.storage_bin,R.drawable.woven_diamond_sprout_white_rug};
    public static String [] productPriceList={"₹ 2000","₹ 12000","₹ 4000","₹ 50000","₹ 1000","₹ 40000","₹ 13000","₹ 3000","₹ 5000"};



    public GalaryFragment() {
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
        View mView =  inflater.inflate(R.layout.fragment_galary, container, false);

        mGridView =(GridView)mView.findViewById(R.id.gridView1);

        mGridView.setAdapter(new ProductListAdapter(this, productNameList,productImages,productPriceList));


        return mView;
    }


}
