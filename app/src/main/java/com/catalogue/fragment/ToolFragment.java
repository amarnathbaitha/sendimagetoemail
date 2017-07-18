package com.catalogue.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.catalogue.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ToolFragment} interface
 * to handle interaction events.
 * Use the {@link ToolFragment#} factory method to
 * create an instance of this fragment.
 */
public class ToolFragment extends Fragment {

    public ToolFragment() {
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
        View view = inflater.inflate(R.layout.fragment_tool, container, false);


        ImageView button = (ImageView) view.findViewById(R.id.location);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.co.in/maps/place/Mysore+Zoo/@12.3023573,76.6631185,17z/data=!3m1!4b1!4m5!3m4!1s0x3baf7023040e7795:0xea57334ccb6cbfcb!8m2!3d12.3023573!4d76.6653072"));
                startActivity(intent);

            }
        });
        return view;
    }

}
