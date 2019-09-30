package com.alc.uclone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.DisplayCutout;
import android.view.View;
import android.widget.TextView;

import com.alc.uclone.activities.fragments.pastTripsFragment;
import com.alc.uclone.activities.fragments.upcomingTripsFragment;

public class historyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            DisplayCutout displayCutout = getWindow().getDecorView().getRootWindowInsets().getDisplayCutout();        }
        setContentView(R.layout.activity_history);
        assert getSupportActionBar() != null;   //null check
        setTitle("Your Trips");

    }

    public void fragSwitcher(View view){
        Fragment frag;
        if(view==findViewById(R.id.btn_past_trips))
            frag= new pastTripsFragment();
        else
            frag=new upcomingTripsFragment();

        FragmentManager manager =getSupportFragmentManager();
        FragmentTransaction txn=manager.beginTransaction();
        txn.replace(R.id.fragment,frag);
        txn.commit();

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    private  void setStars(int rating){
        TextView tv;
        tv=(TextView)findViewById(R.id.textView);
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
