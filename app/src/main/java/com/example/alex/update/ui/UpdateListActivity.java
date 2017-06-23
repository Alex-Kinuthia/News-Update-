package com.example.alex.update.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.alex.update.Constants;
import com.example.alex.update.R;
import com.example.alex.update.adapters.UpdateListAdapter;
import com.example.alex.update.models.Update;
import com.example.alex.update.services.UpdateService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UpdateListActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentTitle;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private UpdateListAdapter mAdapter;
    public ArrayList<Update> mUpdates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String source = intent.getStringExtra("source");

        getUpdates(source);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
//
//        if (mRecentAddress != null) {
//            getRestaurants(mRecentAddress);
//        }
    }

    private void getUpdates(String title) {
        final UpdateService updateService = new UpdateService();

        updateService.findUpdates(title, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mUpdates = updateService.processResults(response);

                UpdateListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new UpdateListAdapter(getApplicationContext(), mUpdates);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(UpdateListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}











