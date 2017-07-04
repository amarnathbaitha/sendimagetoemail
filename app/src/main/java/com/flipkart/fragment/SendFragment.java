package com.flipkart.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.flipkart.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SendFragment} interface
 * to handle interaction events.
 * Use the {@link SendFragment} factory method to
 * create an instance of this fragment.
 */
public class SendFragment extends Fragment implements View.OnClickListener {

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 99;
    private EditText emailAddress, emailSubject, message;

    private Button btnSend;

    private Bitmap photo;
    private ImageView imageView,imageButton;

    private String toemailAddress, msubject, mmessage;


    public SendFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_send, container, false);
        btnSend = (Button) rootView.findViewById(R.id.buttonSend);
        emailAddress = (EditText) rootView.findViewById(R.id.name);
        emailSubject = (EditText) rootView.findViewById(R.id.mobile);
        message = (EditText) rootView.findViewById(R.id.email);
        imageButton = (ImageView) rootView.findViewById(R.id.buttonAttachment);
        imageView = (ImageView) rootView.findViewById(R.id.iv_pic);
        btnSend.setOnClickListener(this);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        return rootView;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//
            case R.id.buttonSend:

                toemailAddress = emailAddress.getText().toString();
                msubject = emailSubject.getText().toString();
                mmessage = message.getText().toString();

                if (toemailAddress.matches("")) {
                    Toast.makeText(getActivity(), "You did not enter a username", Toast.LENGTH_SHORT).show();
                    return;
                } else if (msubject.matches("")) {
                    Toast.makeText(getActivity(), "Please mention phone", Toast.LENGTH_SHORT).show();
                    return;
                } else if (mmessage.matches("")) {
                    Toast.makeText(getActivity(), "Message is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                checkPermission();

                break;
            default:
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
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
            if (bitmap != null)
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
                new String[]{"amarwinner.mca@gmail.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Regarding replacement of furniture");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, mmessage + "" + msubject + "" + toemailAddress);
        Uri myUri = Uri.parse("file://" + path);
        emailIntent.setType("image/png");
        emailIntent.putExtra(Intent.EXTRA_STREAM, myUri);
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }
}
