package com.example.alex.update.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.alex.update.models.Update;
import com.example.alex.update.ui.UpdateDetailFragment;

import java.util.ArrayList;

/**
 * Created by alex on 6/20/17.
 */

public class UpdatePagerAdapter  extends FragmentPagerAdapter {
    private ArrayList<Update> mUpdates;

    public UpdatePagerAdapter(FragmentManager fm, ArrayList<Update> updates) {
        super(fm);
        mUpdates = updates;
    }

    @Override
    public Fragment getItem(int position) {
        return UpdateDetailFragment.newInstance(mUpdates, position);
    }

    @Override
    public int getCount() {
        return mUpdates.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mUpdates.get(position).getTitle();
    }
}
