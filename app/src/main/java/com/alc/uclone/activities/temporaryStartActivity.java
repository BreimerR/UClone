package com.alc.uclone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class temporaryStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporary_start);
    }
    public void activitySwitcher(View v){
        switch (v.getId()){
            case R.id.buttonHistory:
                Intent intent= new Intent(this,historyActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonMaps:
                Intent intent2= new Intent(this,MapsActivity.class);
                startActivity(intent2);
                break;
        }

    }
}
