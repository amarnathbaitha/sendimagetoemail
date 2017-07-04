package com.flipkart.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.flipkart.R;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CameraFragment} interface
 * to handle interaction events.
 * Use the {@link CameraFragment#} factory method to
 * create an instance of this fragment.
 */
public class CameraFragment extends Fragment {

    private static final int CAMERA_REQUEST = 1888;
    private ImageView mImageView;
    private File f;


    public CameraFragment() {
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
        View mView = inflater.inflate(R.layout.fragment_camera, container, false);
        mImageView = (ImageView) mView.findViewById(R.id.imageView1);

        Button photoButton = (Button) mView.findViewById(R.id.button);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    f = createImageFile();
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                    Uri photoURI = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", f);
                    cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }
        });

        return mView;
    }

    public File getAlbumDir() {
        File storageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "BAC/"
        );

        // Create directories if needed
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        return storageDir;
    }

    private File createImageFile() throws IOException {
        // Create an image file name

        String imageFileName = getAlbumDir().toString() + "/image.jpg";
        File image = new File(imageFileName);
        return image;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            if (data == null) return;
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(photo);

            int imageURI = R.drawable.bed;


            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("image/*");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"amarnathbaitha@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "first picture");
            i.putExtra(Intent.EXTRA_TEXT, "body of email");
//            Uri uri = Uri.fromFile(f);

            Uri uri = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", f);

            Log.d("amar", "uri: " + uri);

            i.putExtra(Intent.EXTRA_STREAM, uri);

//            i.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+imageURI));
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);


            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
