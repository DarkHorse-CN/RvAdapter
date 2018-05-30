package com.example.darkhorse.rvadapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.widget.Toast;


import com.example.rvadapter.ItemView;
import com.example.rvadapter.RvAdapter;
import com.example.rvadapter.bean.MenuBean;
import com.example.rvadapter.interfaces.IItemBind;
import com.example.rvadapter.interfaces.OnItemClickListener;
import com.example.rvadapter.interfaces.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

public class Sample1 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RvAdapter mAdapter;
    private List<Bean> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        findViewById();
        init();
    }

    private void findViewById() {
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
    }

    private void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        int[] viewIds = new int[]{R.id.tv_text, R.id.tv_1, R.id.tv_2, R.id.tv_3};
        mDataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Bean bean = new Bean("我的好友 " + i, false);
            mDataList.add(bean);
        }

        mAdapter = new RvAdapter<>(mDataList, mRecyclerView)
                .setItemBind(R.layout.item_recycler_demo, new IItemBind() {
                    @Override
                    public void onViewBind(ItemView view, int position) {
                        if (mDataList.get(position).isCared()) {
                            view.getViewById(R.id.tv_1).setBackgroundColor(Color.parseColor("#123456"));
                        } else {
                            view.getViewById(R.id.tv_1).setBackgroundColor(Color.parseColor("#654321"));
                        }
                        view.setText(R.id.tv_text, mDataList.get(position).getTitle());
                    }
                })
                .addLeftMenu(new MenuBean(R.layout.menu_left, 20))
                .addRightMenu(new MenuBean(R.layout.menu_right, 40))
                .addOnItemClickListener(viewIds, new OnItemClickListener() {
                    @Override
                    public void onItemClick(ItemView holder, int viewId, int position) {
                        switch (viewId) {
                            case R.id.tv_text:
                                Toast.makeText(Sample1.this, mDataList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.tv_1:
                                Bean bean = mDataList.get(position);
                                if (bean.isCared()) {
                                    mDataList.get(position).setCared(false);
                                } else {
                                    mDataList.get(position).setCared(true);
                                }
                                mAdapter.notifyItemChanged(position);
                                break;
                            case R.id.tv_2:
                                mDataList.add(0, mDataList.remove(position));
                                mAdapter.notifyDataSetChanged();
                                break;
                            case R.id.tv_3:
                                mDataList.remove(position);
                                mAdapter.notifyDataSetChanged();
                                break;
                            default:
                        }
                    }
                }).addOnItemLongClickListener(new int[]{R.id.tv_text}, new OnItemLongClickListener() {
                    @Override
                    public void onItemLongClick(ItemView holder, int viewId, int position) {
                        Toast.makeText(Sample1.this, "长按:" + mDataList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
