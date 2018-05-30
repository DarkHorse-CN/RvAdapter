package com.example.rvadapter.interfaces;

import com.example.rvadapter.ItemView;

/**
 * @author DarkHorse
 *         ItemView布局实现接口
 */
public interface IItemBind {
    void onViewBind(ItemView view, int position);
}
