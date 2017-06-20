package com.example.alex.update.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.update.R;
import com.example.alex.update.models.Update;
import com.example.alex.update.ui.UpdateDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alex on 6/20/17.
 */

public class UpdateListAdapter extends RecyclerView.Adapter<UpdateListAdapter.UpdateViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    private ArrayList<Update> mUpdates = new ArrayList<>();
    private Context mContext;

    public UpdateListAdapter(Context context, ArrayList<Update> updates) {
        mContext = context;
        mUpdates = updates;
    }

    @Override
    public UpdateListAdapter.UpdateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_list_item, parent, false);
        UpdateViewHolder viewHolder = new UpdateViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UpdateListAdapter.UpdateViewHolder holder, int position) {
        holder.bindUpdate(mUpdates.get(position));
    }

    @Override
    public int getItemCount() {
        return mUpdates.size();
    }

    public class UpdateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.updateImageView) ImageView mUpdateImageView;
        @Bind(R.id.updateTitleTextView) TextView mTitleTextView;
        @Bind(R.id.updatePublishedAtTextView) TextView mPublishedAtTextView;


        private Context mContext;

        public UpdateViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        public void bindUpdate(Update update) {

            Picasso.with(mContext)
                    .load(update.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mUpdateImageView);


            mTitleTextView.setText(update.getTitle());
            mPublishedAtTextView.setText(update.getPublishedAt());

        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, UpdateDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("updates", Parcels.wrap(mUpdates));
            mContext.startActivity(intent);
        }
    }
}