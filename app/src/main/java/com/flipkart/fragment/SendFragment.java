package com.flipkart.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.flipkart.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SendFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SendFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int CAMERA_REQUEST = 1888;
    EditText emailAddress;
    EditText emailSubject;
    EditText message;
    String picturePath;
    Button Button1;
    private static int RESULT_LOAD_IMG = 1;
    Button btnSend, btnAttachment;
    String attachmentFile;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    int columnIndex;

    private ImageButton imageButton;
    Bitmap photo;
    private ImageView imageView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private OnFragmentInteractionListener mListener;

    public SendFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SendFragment newInstance(String param1, String param2) {
        SendFragment fragment = new SendFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_send, container, false);
         btnSend = (Button) rootView.findViewById(R.id.buttonSend);
         emailAddress = (EditText)  rootView.findViewById(R.id.name);
         emailSubject = (EditText) rootView.findViewById(R.id.mobile);
         message = (EditText) rootView.findViewById(R.id.email);
        imageButton =(ImageButton)rootView.findViewById(R.id.buttonAttachment);
        imageView = (ImageView)rootView.findViewById(R.id.iv_pic);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//
            case R.id.buttonSend:

                String toemailAddress = emailAddress.getText().toString();
                String msubject = emailSubject.getText().toString();
                String mmessage = message.getText().toString();

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


                final Intent emailIntent = new Intent(
                        android.content.Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                        new String[]{"amarwinner.mca@gmail.com"});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        msubject);
                /*if (URI != null) {
                    emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
                }*/
                emailIntent
                        .putExtra(android.content.Intent.EXTRA_TEXT, mmessage +""+msubject +"" +toemailAddress);
                this.startActivity(Intent.createChooser(emailIntent,
                        "Sending email..."));
                break;
            default:
                break;
        }
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

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }

    }
}
