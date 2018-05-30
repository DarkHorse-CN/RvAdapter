package com.example.darkhorse.rvadapter;

/**
 * Created by DarkHorse on 2017/12/26.
 */

public class Bean {
    private String title;
    private boolean isCared;

    public Bean(String title, boolean isCared) {
        this.title = title;
        this.isCared = isCared;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCared() {
        return isCared;
    }

    public void setCared(boolean cared) {
        isCared = cared;
    }
}
