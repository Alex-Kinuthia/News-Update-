package com.example.alex.update.util;

/**
 * Created by alex on 6/20/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}