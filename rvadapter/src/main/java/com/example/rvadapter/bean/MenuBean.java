package com.example.rvadapter.bean;

/**
 * @author DarkHorse
 */

public class MenuBean {
    private int layoutId;
    private int ratio;

    public MenuBean(int layoutId, int ratio) {
        this.layoutId = layoutId;
        this.ratio = ratio;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public int getRatio() {
        return ratio;
    }
}
