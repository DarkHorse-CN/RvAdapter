package com.example.darkhorse.rvadapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rvadapter.ItemView;
import com.example.rvadapter.RvAdapter;
import com.example.rvadapter.bean.MenuBean;
import com.example.rvadapter.interfaces.IMulItemBind;
import com.example.rvadapter.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class Sample2 extends AppCompatActivity {

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
                .setMulItemBind(new IMulItemBind<Bean>() {
                    @Override
                    public int setItemViewType(Bean bean, int position) {
                        if (position % 2 == 0) {
                            return 2;
                        }
                        return 1;
                    }

                    @Override
                    public int setLayoutId(int type) {
                        return R.layout.item_recycler_demo;
                    }

                    @Override
                    public void onViewBind(ItemView view, Bean bean, int type) {
                        if (type == 1) {
                            if (bean.isCared()) {
                                view.getViewById(R.id.tv_1).setBackgroundColor(Color.parseColor("#123456"));
                            } else {
                                view.getViewById(R.id.tv_1).setBackgroundColor(Color.parseColor("#654321"));
                            }
                        }
                        ((TextView) view.getViewById(R.id.tv_text)).setText(bean.getTitle());
                    }

                    @Override
                    public MenuBean addLeftMenu(int type) {
                        if (type == 1) {
                            return new MenuBean(R.layout.menu_left, 20);
                        }
                        return null;
                    }

                    @Override
                    public MenuBean addRightMenu(int type) {
                        if (type == 2) {
                            return new MenuBean(R.layout.menu_right, 40);
                        }
                        return null;
                    }
                })
                .addOnItemClickListener(viewIds, new OnItemClickListener() {
                    @Override
                    public void onItemClick(ItemView holder,int viewId, int position) {
                        switch (viewId) {
                            case R.id.tv_text:
                                Toast.makeText(Sample2.this, mDataList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
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
                });
    }
}
