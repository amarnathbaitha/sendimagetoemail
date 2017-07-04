package com.flipkart.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.flipkart.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShareFragment} interface
 * to handle interaction events.
 * Use the {@link ShareFragment#} factory method to
 * create an instance of this fragment.
 */
public class SlideShowFragment extends Fragment {

    int mFlipping = 0 ; // Initially flipping is off
    Button mButton ; // Reference to button available in the layout to start and stop the flipper


    public SlideShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_slide_show, container, false);


        /** Click event handler for button */
        OnClickListener listener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                ViewFlipper flipper = (ViewFlipper) view.findViewById(R.id.flipper1);

                if(mFlipping==0){
                    /** Start Flipping */
                    flipper.startFlipping();
                    mFlipping=1;
                    mButton.setText(R.string.str_btn_stop);
                }
                else{
                    /** Stop Flipping */
                    flipper.stopFlipping();
                    mFlipping=0;
                    mButton.setText(R.string.str_btn_start);
                }
            }
        };

        /** Getting a reference to the button available in the resource */
        mButton = (Button) view.findViewById(R.id.btn);

        /** Setting click event listner for the button */
        mButton.setOnClickListener(listener);

        return view;
    }






}
