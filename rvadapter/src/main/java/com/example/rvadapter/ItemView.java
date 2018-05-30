package com.example.rvadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rvadapter.interfaces.OnItemClickListener;
import com.example.rvadapter.interfaces.OnItemLongClickListener;
import com.example.rvadapter.widgets.SlideLayout;

/**
 * @author DarkHorse
 */
public class ItemView extends RecyclerView.ViewHolder {
    private SlideLayout mSlideLayout;
    private View mContentView;
    private View mLeftMenu;
    private View mRightMenu;

    private ItemView(View contentView) {
        super(contentView);
        mContentView = contentView;
    }

    private ItemView(SlideLayout slideLayout, View contentView, View leftMenu, View rightMenu) {
        super(slideLayout);
        mSlideLayout = slideLayout;
        mContentView = contentView;
        mLeftMenu = leftMenu;
        mRightMenu = rightMenu;
    }

    static ItemView newInstance(Context context, int contentId, ViewGroup parent) {
        View contentView = LayoutInflater.from(context).inflate(contentId, parent, false);
        return new ItemView(contentView);
    }

    static ItemView newInstance(Context context, ViewBean viewBean) {
        SlideLayout slideLayout = new SlideLayout(context);

        LinearLayout linearLayout = new LinearLayout(context);
        slideLayout.addView(linearLayout);

        View leftMenu = null;
        View rightMenu = null;

        if (viewBean.getLeftId() != 0) {
            leftMenu = LayoutInflater.from(context).inflate(viewBean.getLeftId(), linearLayout, false);
            linearLayout.addView(leftMenu);
        }

        View contentView = LayoutInflater.from(context).inflate(viewBean.getContentId(), linearLayout, false);
        linearLayout.addView(contentView);

        if (viewBean.getRightId() != 0) {
            rightMenu = LayoutInflater.from(context).inflate(viewBean.getRightId(), linearLayout, false);
            linearLayout.addView(rightMenu);
        }
        return new ItemView(slideLayout, contentView, leftMenu, rightMenu);
    }

    public SlideLayout getSlideLayout() {
        return mSlideLayout;
    }

    public View getContentView() {
        return mContentView;
    }

    public View getLeftMenu() {
        return mLeftMenu;
    }

    public View getRightMenu() {
        return mRightMenu;
    }

    public void setText(int viewId, String str) {
        TextView textView = mContentView.findViewById(viewId);
        textView.setText(str);
    }

    public void setOnClickListener(int[] viewIds, final OnItemClickListener itemClickListener, final ItemView holder, final int position) {
        for (int id : viewIds) {
            View view = getViewById(id);
            if (view != null) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onItemClick(holder, v.getId(), position);
                    }
                });
            }
        }
    }

    public void setOnLongClickListener(int[] viewIds, final OnItemLongClickListener onItemLongClickListener, final ItemView holder, final int position) {
        for (int id : viewIds) {
            View view = getViewById(id);
            if (view != null) {
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onItemLongClickListener.onItemLongClick(holder,v.getId(),position);
                        return false;
                    }
                });
            }
        }
    }

    public View getViewById(int id) {
        if (mSlideLayout == null) {
            return mContentView.findViewById(id);
        }
        return mSlideLayout.findViewById(id);
    }
}
