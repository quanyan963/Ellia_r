package com.txtled.ellia_r.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Map;

public class SlideViewPager extends ViewPager {

    private Map<Integer, Integer> map = new HashMap<>(3);
    private int currentPage;

    private boolean isCanScroll = true;

    private int height;

    public SlideViewPager(Context context) {
        super(context);
    }

    public SlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIsScanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // TODO Auto-generated method stub
        if (isCanScroll) {
            return super.onTouchEvent(arg0);
        } else {
            return false;
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (map.get(i) == null || map.get(i) < h)
                map.put(i,h);
//            if (h > height)
//                height = h;
        }
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        if(map.size()>currentPage){
            height=map.get(currentPage);
        }
        heightMeasureSpec=MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 在切换tab的时候，重置ViewPager的高度
     * @param current
     */
    public void resetHeight(int current){
        this.currentPage=current;
        MarginLayoutParams params= (MarginLayoutParams) getLayoutParams();
        if(map.size()>currentPage){
            if(params==null){
                params=new MarginLayoutParams(LayoutParams.MATCH_PARENT,map.get(current));
            }else {
                params.height=map.get(current);
            }
            setLayoutParams(params);
        }

    }

    /**
     * 获取、存储每一个tab的高度，在需要的时候显示存储的高度
     * @param current  tab的position
     * @param integer   当前tab的高度
     */
    public void addHeight(int current,Integer integer){
        map.put(current,integer);
        resetHeight(current);

    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        // TODO Auto-generated method stub
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        // TODO Auto-generated method stub
        super.setCurrentItem(item);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        // TODO Auto-generated method stub
        if (isCanScroll) {
            return super.onInterceptTouchEvent(arg0);
        } else {
            return false;
        }

    }
}