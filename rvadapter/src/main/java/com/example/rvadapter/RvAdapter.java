package com.example.rvadapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.rvadapter.bean.MenuBean;
import com.example.rvadapter.interfaces.IItemBind;
import com.example.rvadapter.interfaces.IMulItemBind;
import com.example.rvadapter.interfaces.OnItemClickListener;
import com.example.rvadapter.interfaces.OnItemLongClickListener;

import java.util.List;

/**
 * @author DarkHorse
 */
public class RvAdapter<T> extends RecyclerView.Adapter<ItemView> {

    private RecyclerView mRecyclerView;
    private List<T> mDataList;
    private int[] mClickViewIds;
    private int[] mLongClickViewIds;

    private ItemView mItemView;

    private int mContentLayoutId = 0;
    private MenuBean mLeftMenu;
    private MenuBean mRightMenu;

    private IItemBind mItemBind;
    private IMulItemBind<T> mMulItemBind;
    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;

    private int mItemViewWidth;

    public RvAdapter(List<T> dataList, RecyclerView recyclerView) {
        mDataList = dataList;
        mRecyclerView = recyclerView;
        init();
    }

    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mMulItemBind != null) {
            mContentLayoutId = mMulItemBind.setLayoutId(viewType);
            mLeftMenu = mMulItemBind.addLeftMenu(viewType);
            mRightMenu = mMulItemBind.addRightMenu(viewType);
        }

        int leftMenuLayoutId = 0;
        int rightMenuLayoutId = 0;
        if (mLeftMenu != null) {
            leftMenuLayoutId = mLeftMenu.getLayoutId();
        }
        if (mRightMenu != null) {
            rightMenuLayoutId = mRightMenu.getLayoutId();
        }
        if (leftMenuLayoutId == 0 && rightMenuLayoutId == 0) {
            mItemView = ItemView.newInstance(parent.getContext(), mContentLayoutId, parent);
        } else {
            mItemView = ItemView.newInstance(parent.getContext(), new ViewBean(mContentLayoutId, leftMenuLayoutId, rightMenuLayoutId));
        }
        return mItemView;
    }

    @Override
    public void onBindViewHolder(final ItemView holder, final int position) {
        initContentView(holder);
        initMenu(holder);
        if (mItemBind != null) {
            mItemBind.onViewBind(holder, position);
        } else if (mMulItemBind != null) {
            mMulItemBind.onViewBind(holder, mDataList.get(position), getItemViewType(position));
        }
        if (mItemClickListener != null) {
            holder.setOnClickListener(mClickViewIds, mItemClickListener, holder, position);
        }
        if (mItemLongClickListener != null) {
            holder.setOnLongClickListener(mLongClickViewIds, mItemLongClickListener, holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mMulItemBind != null) {
            return mMulItemBind.setItemViewType(mDataList.get(position), position);
        }
        return super.getItemViewType(position);
    }

    private void init() {
        mRecyclerView.setAdapter(this);

        ViewGroup.LayoutParams recyclerParams = mRecyclerView.getLayoutParams();
        int recyclerPadding = mRecyclerView.getPaddingLeft() + mRecyclerView.getPaddingRight();

        int recyclerMargin = 0;
        if (recyclerParams instanceof ViewGroup.MarginLayoutParams) {
            recyclerMargin = ((ViewGroup.MarginLayoutParams) recyclerParams).leftMargin + ((ViewGroup.MarginLayoutParams) recyclerParams).rightMargin;
        }

        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        mItemViewWidth = getScreenWidth(mRecyclerView.getContext()) - recyclerPadding - recyclerMargin;
        if (layoutManager instanceof GridLayoutManager) {
            mItemViewWidth = mItemViewWidth / ((GridLayoutManager) layoutManager).getSpanCount();
        }
    }

    private void initContentView(ItemView holder) {
        View contentView = holder.getContentView();
        ViewGroup.LayoutParams contentParams = contentView.getLayoutParams();
        contentParams.width = mItemViewWidth;
    }

    private void initMenu(ItemView holder) {
        View leftMenu = holder.getLeftMenu();
        if (leftMenu != null && mLeftMenu != null) {
            ViewGroup.LayoutParams leftMenuParams = leftMenu.getLayoutParams();
            int leftMenuWidth = mItemViewWidth * mLeftMenu.getRatio() / 100;
            leftMenuParams.width = leftMenuWidth;
            mItemView.getSlideLayout().setLeftMenuWidth(leftMenuWidth);
        }
        View rightMenu = holder.getRightMenu();
        if (rightMenu != null && mRightMenu != null) {
            ViewGroup.LayoutParams rightMenuParams = rightMenu.getLayoutParams();
            int rightMenuWidth = mItemViewWidth * mRightMenu.getRatio() / 100;
            rightMenuParams.width = rightMenuWidth;
            mItemView.getSlideLayout().setRightMenuWidth(rightMenuWidth);
        }
    }

    private int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(outMetrics);
        }
        return outMetrics.widthPixels;
    }

    public RvAdapter setItemBind(int contentLayoutId, IItemBind itemBind) {
        mContentLayoutId = contentLayoutId;
        mItemBind = itemBind;
        return this;
    }

    public RvAdapter addLeftMenu(MenuBean menuBean) {
        mLeftMenu = menuBean;
        return this;
    }

    public RvAdapter addRightMenu(MenuBean menuBean) {
        mRightMenu = menuBean;
        return this;
    }

    public RvAdapter setMulItemBind(IMulItemBind<T> mulItemBind) {
        mMulItemBind = mulItemBind;
        return this;
    }

    public RvAdapter addOnItemClickListener(int[] viewIds, OnItemClickListener onItemClickListener) {
        mClickViewIds = viewIds;
        mItemClickListener = onItemClickListener;
        return this;
    }

    public RvAdapter addOnItemLongClickListener(int[] viewIds, OnItemLongClickListener onItemLongClickListener) {
        mLongClickViewIds = viewIds;
        mItemLongClickListener = onItemLongClickListener;
        return this;
    }

}
