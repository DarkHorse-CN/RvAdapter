package com.example.rvadapter.interfaces;

import com.example.rvadapter.ItemView;
import com.example.rvadapter.bean.MenuBean;

/**
 * @author DarkHorse
 *         ItemView多元布局实现接口
 */

public interface IMulItemBind<T> {
    int setItemViewType(T bean, int position);

    int setLayoutId(int type);

    void onViewBind(ItemView view, T bean, int type);

    MenuBean addLeftMenu(int type);

    MenuBean addRightMenu(int type);
}
