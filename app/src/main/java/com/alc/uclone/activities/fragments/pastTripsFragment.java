package com.alc.uclone.activities.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alc.uclone.activities.R;


public class pastTripsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public pastTripsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        //setStars(5);
        super.onResume();
        setStars(2);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_past_trips, container, false);
    }
    private  void setStars(int rating){
        TextView tv;
        tv=(TextView)getView().findViewById(R.id.textView);
        String one = Character.toString((char)9733);
        String zero = Character.toString((char)9734);
        String oneStar=one+""+zero+""+zero+""+zero+""+zero;
        String twoStar=one+""+one+""+zero+""+zero+""+zero;
        String threeStar=one+""+one+""+one+""+zero+""+zero;
        String fourStar=one+""+one+""+one+""+one+""+zero;
        String fiveStar=one+""+one+""+one+""+one+""+one;
        switch (rating)
        {
            case 1:
            {
                tv.setText(oneStar);
                break;
            }
            case 2:
            {
                tv.setText(twoStar);
                break;
            }
            case 3:
            {
                tv.setText(threeStar);
                break;
            }
            case 4:
            {
                tv.setText(fourStar);
                break;
            }
            case 5:
            {
                tv.setText(fiveStar);
                break;
            }
            default:
                tv.setText("&#9734; &#9734; &#9734; &#9734; &#9734;");

        }
    }


}
