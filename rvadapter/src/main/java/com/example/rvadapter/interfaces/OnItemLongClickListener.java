package com.example.rvadapter.interfaces;

import com.example.rvadapter.ItemView;

/**
 * @author DarkHorse
 *         ItemView长按点击事件接口
 */
public interface OnItemLongClickListener {
    void onItemLongClick(ItemView itemView, int viewId, int position);
}
