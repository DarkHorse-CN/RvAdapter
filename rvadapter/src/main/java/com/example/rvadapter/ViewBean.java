package com.example.rvadapter;

/**
 * @author DarkHorse
 */
public class ViewBean {
    private int mContentId = 0;
    private int mLeftId = 0;
    private int mRightId = 0;

    public ViewBean(int contentId, int leftId, int rightId) {
        mContentId = contentId;
        mLeftId = leftId;
        mRightId = rightId;
    }

    public int getContentId() {
        return mContentId;
    }

    public int getLeftId() {
        return mLeftId;
    }

    public int getRightId() {
        return mRightId;
    }
}
