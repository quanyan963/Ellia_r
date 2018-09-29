package com.txtled.ellia_r.aroma;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.txtled.ellia_r.R;
import com.txtled.ellia_r.aroma.mvp.AromaContract;
import com.txtled.ellia_r.aroma.mvp.AromaPresenter;
import com.txtled.ellia_r.base.MvpBaseFragment;

import butterknife.BindView;

import static com.txtled.ellia_r.constant.Constants.PAGE_AROMA;

/**
 * Created by Mr.Quan on 2018/8/30.
 */

public class AromaFragment extends MvpBaseFragment<AromaPresenter> implements AromaContract.View {
    @BindView(R.id.rlv_aroma)
    RecyclerView rlvAroma;

    private AromaAdapter mAromaAdapter;

    private View view;
    private int height;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_aroma, container, false);
        return view;
    }

    @Override
    public void init() {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
//            @Override
//            public boolean canScrollVertically() {
//                // 直接禁止垂直滑动
//                return false;
//            }
//        };

        rlvAroma.setHasFixedSize(true);
        rlvAroma.setLayoutManager(new LinearLayoutManager(getContext()));
        mAromaAdapter = new AromaAdapter(getContext());
        rlvAroma.setNestedScrollingEnabled(false);
        rlvAroma.setAdapter(mAromaAdapter);
        mAromaAdapter.initList();
        //measureHeight();

    }

//    private void measureHeight() {
//        height = mAromaAdapter.getRealHeight();
//        listener.changeData(PAGE_AROMA, height);
//
//    }
}
