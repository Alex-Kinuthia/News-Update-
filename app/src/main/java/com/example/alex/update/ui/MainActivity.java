package com.example.alex.update.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alex.update.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.getNewsButton) Button mgetNewsButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.savedNewsButton) Button msavedNewsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mAppNameTextView.setTypeface(ostrichFont);

        mgetNewsButton.setOnClickListener(this);
        msavedNewsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == mgetNewsButton) {
            Intent intent = new Intent(MainActivity.this, UpdateListActivity.class);
            startActivity(intent);
        }

        if (v == msavedNewsButton) {
            Intent intent = new Intent(MainActivity.this, SavedUpdateListActivity.class);
            startActivity(intent);
        }

    }

}