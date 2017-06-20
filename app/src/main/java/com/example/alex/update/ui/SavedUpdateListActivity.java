package com.example.alex.update.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alex.update.Constants;
import com.example.alex.update.R;
import com.example.alex.update.adapters.FirebaseUpdateViewHolder;
import com.example.alex.update.models.Update;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedUpdateListActivity extends AppCompatActivity {

    private DatabaseReference mUpdateReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);

        mUpdateReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_UPDATES);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Update, FirebaseUpdateViewHolder>
                (Update.class, R.layout.update_list_item, FirebaseUpdateViewHolder.class,
                        mUpdateReference) {

            @Override
            protected void populateViewHolder(FirebaseUpdateViewHolder viewHolder,
                                              Update model, int position) {
                viewHolder.bindUpdate(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
