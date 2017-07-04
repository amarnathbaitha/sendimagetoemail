package com.flipkart.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
public class CameraFragment extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 99;
    private static final int CAMERA_REQUEST = 1888;
    private Button send;
    private ImageView image;
    private Bitmap photo;


    public CameraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        send = (Button) view.findViewById(R.id.emailsendbutton);
        image = (ImageView) view.findViewById(R.id.imageView1);

        disableEnableEmailButton(0.5f, false);

        ImageView camera = (ImageView) view.findViewById(R.id.iv_camera);
        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                checkPermission();
            }
        });

        return view;
    }

    private void disableEnableEmailButton(float alpha, boolean enabled) {
        send.setAlpha(alpha);
        send.setEnabled(enabled);
        send.setClickable(enabled);
        send.setFocusable(enabled);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            disableEnableEmailButton(1, true);

            photo = (Bitmap) data.getExtras().get("data");
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(photo, 400, 600, false);

            image.setImageBitmap(resizedBitmap);
        }

    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }
        saveBitmap(photo);
    }


    public void saveBitmap(Bitmap bitmap) {
        String filePath = Environment.getExternalStorageDirectory()
                + File.separator + "Pictures/screenshot.png";
        File imagePath = new File(filePath);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            sendMail(filePath);
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    public void sendMail(String path) {
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                new String[]{"manjitkaurkhehra@gmail.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                "Truiton Test Mail");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                "This is an autogenerated mail from Truiton's InAppMail app");
        emailIntent.setType("image/png");
        Uri myUri = Uri.parse("file://" + path);
        emailIntent.putExtra(Intent.EXTRA_STREAM, myUri);
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }



}
