package com.example.rvadapter.interfaces;

import android.view.View;

import com.example.rvadapter.ItemView;

/**
 * @author DarkHorse
 *         ItemView点击事件接口
 */
public interface OnItemClickListener<T> {
    void onItemClick(ItemView itemView, int viewId, int position);
}