package com.example.rvadapter.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

import java.util.concurrent.ScheduledThreadPoolExecutor;


/**
 * @author DarkHorse
 */
public class SlideLayout extends HorizontalScrollView {

    private int mLeftMenuWidth = 0;
    private int mRightMenuWidth = 0;
    private ScheduledThreadPoolExecutor mScheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(3);

    public SlideLayout(Context context) {
        this(context, null);
    }

    public SlideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOverScrollMode(View.OVER_SCROLL_NEVER);
        setHorizontalScrollBarEnabled(false);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        scrollTo(mLeftMenuWidth, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                float scrollX = getScrollX();
                if (scrollX < mLeftMenuWidth / 2) {
                    mScheduledThreadPoolExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            smoothScrollTo(0, 0);
                        }
                    });
                } else if (scrollX - mLeftMenuWidth > mRightMenuWidth / 2) {
                    mScheduledThreadPoolExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            smoothScrollTo(mRightMenuWidth + mLeftMenuWidth, 0);
                        }
                    });
                } else {
                    mScheduledThreadPoolExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            smoothScrollTo(mLeftMenuWidth, 0);
                        }
                    });
                }
                break;
            default:
        }
        return super.onTouchEvent(ev);
    }

    public void setLeftMenuWidth(int leftMenuWidth) {
        mLeftMenuWidth = leftMenuWidth;
    }

    public void setRightMenuWidth(int rightMenuWidth) {
        mRightMenuWidth = rightMenuWidth;
    }
}
