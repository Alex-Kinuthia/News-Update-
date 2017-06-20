package com.example.alex.update.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alex.update.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.getNewsButton) Button mgetNewsButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.savedNewsButton) Button msavedNewsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //added butterknife
        ButterKnife.bind(this);

        //adding a font
        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mAppNameTextView.setTypeface(ostrichFont);
        //added a listener
        mgetNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gathering data from edit text
                String update = mgetNewsButton.getText().toString();
                Log.d(TAG, update);
                Intent intent = new Intent(MainActivity.this, UpdateListActivity.class);
                intent.putExtra("update", update);
                startActivity(intent);
            }
        });
    }
}