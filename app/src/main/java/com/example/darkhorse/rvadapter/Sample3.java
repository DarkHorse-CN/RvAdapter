package com.example.darkhorse.rvadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.widget.Toast;

import com.example.rvadapter.ItemView;
import com.example.rvadapter.RvAdapter;
import com.example.rvadapter.interfaces.IItemBind;
import com.example.rvadapter.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class Sample3 extends AppCompatActivity {

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
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        int[] viewIds = new int[]{R.id.tv_text};
        mDataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Bean bean = new Bean("我的好友 " + i, false);
            mDataList.add(bean);
        }

        mAdapter = new RvAdapter<>(mDataList, mRecyclerView)
                .setItemBind(R.layout.item_recycler_demo, new IItemBind() {
                    @Override
                    public void onViewBind(ItemView view, int position) {
                        view.setText(R.id.tv_text, mDataList.get(position).getTitle());
                    }
                })
                .addOnItemClickListener(viewIds, new OnItemClickListener() {
                    @Override
                    public void onItemClick(ItemView holder, int viewId, int position) {
                        switch (viewId) {
                            case R.id.tv_text:
                                Toast.makeText(Sample3.this, mDataList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                                break;
                            default:
                        }
                    }
                });
    }
}
