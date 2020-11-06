package com.example.cardgames.StartScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.example.cardgames.R;
import com.example.cardgames.StartScreens.SignUp;

import java.util.ArrayList;

/**
 * @author Colton Hazlett
 * Class used for a pop up screen in the sign up page for choosing the icon
 */
public class SignUpImageSelection extends DialogFragment implements View.OnClickListener{

    private ArrayList<ImageView> images;

    /**
     * Creates the method used in the sign up class in getting the image ID
     */
    public interface OnInputListener{
        void sendID(int id);
    }

    public OnInputListener mOnInputListener;

    /**
     * The view that is created on the pop up screen
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sign_up_image_selection, container, false);
        images = new ArrayList<>();
        setImages(view);

        return view;
    }
    /**
     * Sets the images in the view
     */
    public void setImages(View view){
        setImage(R.id.icon1, view);
        setImage(R.id.icon2, view);
        setImage(R.id.icon3, view);
        setImage(R.id.icon4, view);
        setImage(R.id.icon5, view);
        setImage(R.id.icon6, view);
        setImage(R.id.icon7, view);
        setImage(R.id.icon8, view);
        setImage(R.id.icon9, view);

    }
    /**
     * Helping function for setImages() function
     */
    public void setImage(int id, View view){
        ImageView img = view.findViewById(id);
        img.setOnClickListener(this);
        images.add(img);
    }

    /**
     * A listener that relays if an image has been clicked
     */
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.icon1:
                mOnInputListener.sendID(R.drawable.icon1);
                break;
            case R.id.icon2:
                mOnInputListener.sendID(R.drawable.icon4);
                break;
            case R.id.icon3:
                mOnInputListener.sendID(R.drawable.icon5);
                break;
            case R.id.icon4:
                mOnInputListener.sendID(R.drawable.icon6);
                break;
            case R.id.icon5:
                mOnInputListener.sendID(R.drawable.icon2);
                break;
            case R.id.icon6:
                mOnInputListener.sendID(R.drawable.icon3);
                break;
            case R.id.icon7:
                mOnInputListener.sendID(R.drawable.icon7);
                break;
            case R.id.icon8:
                mOnInputListener.sendID(R.drawable.icon8);
                break;
            case R.id.icon9:
                mOnInputListener.sendID(R.drawable.icon9);
                break;
        }
        getDialog().dismiss();
    }
    /**
     * Attaches the activity
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnInputListener = (OnInputListener) getActivity();
        }catch (ClassCastException e){
            Log.e("IMAGE", "onAttach: ClassCastException: " + e.getMessage() );
        }
    }
}

