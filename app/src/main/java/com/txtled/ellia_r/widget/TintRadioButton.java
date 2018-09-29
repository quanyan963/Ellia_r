package com.txtled.ellia_r.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import com.txtled.ellia_r.R;


public class TintRadioButton extends AppCompatRadioButton implements CompoundButton.
        OnCheckedChangeListener {
    private Drawable wrappedDrawable;
    private Drawable drawableTop;
    private Drawable drawableLeft;
    private boolean isTop;

    public TintRadioButton(Context context) {
        super(context);
        init();
    }

    public TintRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TintRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnCheckedChangeListener(this);
        Drawable[] compoundDrawables = getCompoundDrawables();
        drawableTop = compoundDrawables[1];
        drawableLeft = compoundDrawables[0];
        isTop = drawableTop != null;
        if (isTop) {
            //这里判断下表示设置的上边图片
            wrappedDrawable = DrawableCompat.wrap(drawableTop);
            drawableTop = wrappedDrawable;
            //设置默认着色
            DrawableCompat.setTint(wrappedDrawable, getResources().getColor(R.color.dark_grey));
            setCompoundDrawables(null, drawableTop, null, null);
        } else {
//            wrappedDrawable = DrawableCompat.wrap(drawableLeft);
//            drawableLeft = wrappedDrawable;
//            //设置默认着色
//            DrawableCompat.setTint(wrappedDrawable, getResources().getColor(R.color.dark_grey));
//            setCompoundDrawables(drawableLeft, null, null, null);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (isTop) {
            if (b) {
                //是否选择
                DrawableCompat.setTint(wrappedDrawable, getResources().getColor(R.color.colorSelect));
            } else {
                DrawableCompat.setTint(wrappedDrawable, getResources().getColor(R.color.dark_grey));
            }

            setCompoundDrawables(null, drawableTop, null, null);
        } else {
//            setCompoundDrawables(drawableLeft, null, null, null);
        }
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top,
                                                        Drawable right, Drawable bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        if (top != null) {
            //这里只要改后面两个参数就好了，一个宽一个是高
//            top.setBounds(0, 0, 180, 180);
        }
        setCompoundDrawables(left, top, right, bottom);
    }
}