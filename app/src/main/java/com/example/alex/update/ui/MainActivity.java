package com.example.alex.update.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alex.update.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.getNewsButton) Button mgetNewsButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.getPoliticsButton) Button mPoliticsButton;
    @Bind(R.id.getEntertainmentButton) Button mEntertainmentButton;
    @Bind(R.id.getBusinessButton) Button mBusinessButton;
    @Bind(R.id.getTecnologyButton) Button mTecnologyButton;
    @Bind(R.id.savedNewsButton) Button msavedNewsButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4, floatingActionButton5, floatingActionButton6;

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
        mgetNewsButton.setOnClickListener(this);
        msavedNewsButton.setOnClickListener(this);
        mPoliticsButton.setOnClickListener(this);
        mEntertainmentButton.setOnClickListener(this);
        mBusinessButton.setOnClickListener(this);
        mTecnologyButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {

                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


            @Override
            public void onClick(View v) {
                //gathering data from edit text
                if(v == mgetNewsButton) {
                    String update = "talksport";
                    Log.d(TAG, update);
                    Intent intent = new Intent(MainActivity.this, UpdateListActivity.class);
                    intent.putExtra("source", update);
                    startActivity(intent);
                }

                if(v == mPoliticsButton) {
                    String update = "breitbart-news";
                    Log.d(TAG, update);
                    Intent intent = new Intent(MainActivity.this, UpdateListActivity.class);
                    intent.putExtra("source", update);
                    startActivity(intent);
                }

                if(v == mEntertainmentButton) {
                    String update = "entertainment-weekly";
                    Log.d(TAG, update);
                    Intent intent = new Intent(MainActivity.this, UpdateListActivity.class);
                    intent.putExtra("source", update);
                    startActivity(intent);
                }

                if(v == mBusinessButton) {
                    String update = "business-insider";
                    Log.d(TAG, update);
                    Intent intent = new Intent(MainActivity.this, UpdateListActivity.class);
                    intent.putExtra("source", update);
                    startActivity(intent);
                }

                if(v == mTecnologyButton) {
                    String update = "engadget";
                    Log.d(TAG, update);
                    Intent intent = new Intent(MainActivity.this, UpdateListActivity.class);
                    intent.putExtra("source", update);
                    startActivity(intent);
                }



                if (v == msavedNewsButton) {
                    Intent intent = new Intent(MainActivity.this, SavedUpdateListActivity.class);
                    startActivity(intent);
                }

            }
        }
