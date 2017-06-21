package com.example.alex.update.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alex.update.R;
import com.example.alex.update.adapters.UpdatePagerAdapter;
import com.example.alex.update.models.Update;

import org.parceler.Parcels;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UpdateDetailActivity extends AppCompatActivity {

    @Bind(R.id.viewPager) ViewPager mViewPager;
    private UpdatePagerAdapter adapterViewPager;
    ArrayList<Update> mUpdates = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_detail);
        ButterKnife.bind(this);

        mUpdates = Parcels.unwrap(getIntent().getParcelableExtra("updates"));

        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new UpdatePagerAdapter(getSupportFragmentManager(), mUpdates);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}