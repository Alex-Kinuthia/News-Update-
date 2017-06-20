package com.example.alex.update.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex.update.Constants;
import com.example.alex.update.R;
import com.example.alex.update.models.Update;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UpdateDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.updateImageView) ImageView mImageLabel;
    @Bind(R.id.updateAuthorTextView) TextView mAuthorLabel;
    @Bind(R.id.updateTitleTextView) TextView mTitleLabel;
    @Bind(R.id.updateDescriptionTextView) TextView mDescriptionLabel;
    @Bind(R.id.urlTextView) TextView mUrlLabel;
    @Bind(R.id.updatePublishedAtTextView) TextView mPublishedAtLabel;
    @Bind(R.id.saveNewsButton) TextView mSaveNewsButton;

    private Update mUpdate;
    private ArrayList<Update> mUpdates;
    private int mPosition;

    public static UpdateDetailFragment newInstance(ArrayList<Update> updates, Integer position) {
        UpdateDetailFragment updateDetailFragment = new UpdateDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable("update", Parcels.wrap(updates));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);
        updateDetailFragment.setArguments(args);
        return updateDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUpdate = Parcels.unwrap(getArguments().getParcelable("update"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mUpdate.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        mAuthorLabel.setText("Author: " + mUpdate.getAuthor());
        mUrlLabel.setOnClickListener(this);
        mTitleLabel.setText("Title " + mUpdate.getTitle());
        mPublishedAtLabel.setText("PublishedAt: " + mUpdate.getPublishedAt());
        mDescriptionLabel.setText("Description: " + mUpdate.getDescription());


        mSaveNewsButton.setOnClickListener(this);

        return view;
    }



    @Override
    public void onClick(View v) {

        if (v == mUrlLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mUpdate.getUrl()));
            startActivity(webIntent);
        }

        if (v == mSaveNewsButton) {
            DatabaseReference updateRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_UPDATES);
            updateRef.push().setValue(mUpdate);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

}