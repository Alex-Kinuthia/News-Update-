package com.example.alex.update.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.update.Constants;
import com.example.alex.update.R;
import com.example.alex.update.models.Update;
import com.example.alex.update.ui.UpdateDetailActivity;
import com.example.alex.update.util.ItemTouchHelperViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by alex on 6/20/17.
 */

public class FirebaseUpdateViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    public ImageView mUpdateImageView;

    public FirebaseUpdateViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindUpdate(Update update) {
        mUpdateImageView = (ImageView) mView.findViewById(R.id.updateImageView);
        TextView authorTextView = (TextView) mView.findViewById(R.id.updateAuthorTextView);
        TextView titleTextView = (TextView) mView.findViewById(R.id.updateTitleTextView);
        TextView publishedAtTextView = (TextView) mView.findViewById(R.id.updatePublishedAtTextView);
        TextView descriptionTextView = (TextView) mView.findViewById(R.id.updateDescriptionTextView);

        Picasso.with(mContext)
                .load(update.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mUpdateImageView);

        authorTextView.setText(update.getAuthor());
        titleTextView.setText(update.getTitle());
        publishedAtTextView.setText(update.getPublishedAt());
        descriptionTextView.setText(update.getTitle());
    }

    @Override
    public void onItemSelected() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_on);
        set.setTarget(itemView);
        set.start();
    }

    @Override
    public void onItemClear() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_off);
        set.setTarget(itemView);
        set.start();
    }

}