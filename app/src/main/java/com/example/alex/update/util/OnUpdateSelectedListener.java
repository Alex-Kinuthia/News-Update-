package com.example.alex.update.util;

/**
 * Created by alex on 6/22/17.
 */

import com.example.alex.update.models.Update;

import java.util.ArrayList;

public interface OnUpdateSelectedListener {
    public void onUpdateSelected(Integer position, ArrayList<Update> updates);

}