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
 * {@link GalaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GalaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalaryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GridView mGridView;

    public static String [] productNameList={"Baby & Kids","BED","Cookware","Home Improvment","Lighting","OutDoor","Sofa","Storage Bin","Woven Diamond White Rug"};
    public static int [] productImages={R.drawable.baby_kids,R.drawable.bed,R.drawable.cookware,R.drawable.home_improvment,R.drawable.lighting,R.drawable.outdoor,R.drawable.sofa,R.drawable.storage_bin,R.drawable.woven_diamond_sprout_white_rug};
    public static String [] productPriceList={"₹ 2000","₹ 12000","₹ 4000","₹ 50000","₹ 1000","₹ 40000","₹ 13000","₹ 3000","₹ 5000"};


    private OnFragmentInteractionListener mListener;

    public GalaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GalaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GalaryFragment newInstance(String param1, String param2) {
        GalaryFragment fragment = new GalaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
