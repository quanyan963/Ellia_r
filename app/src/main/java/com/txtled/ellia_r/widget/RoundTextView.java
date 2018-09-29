package com.txtled.ellia_r.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.txtled.ellia_r.R;

/**
 * Created by Mr.Quan on 2018/9/14.
 */

public class RoundTextView extends android.support.v7.widget.AppCompatTextView {
    public RoundTextView(Context context) {
        super(context);
        init(context,null,0);
    }

    public RoundTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public RoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);

    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundTextView, defStyleAttr, 0);

        if (attributes != null) {

            int rtvBorderWidth = attributes.getDimensionPixelSize(R.styleable.RoundTextView_rtvBorderWidth, 0);
            int rtvBorderColor = attributes.getColor(R.styleable.RoundTextView_rtvBorderColor, Color.BLACK);
            float rtvRadius = attributes.getDimension(R.styleable.RoundTextView_rtvRadius, 0);
            int rtvBgColor = attributes.getColor(R.styleable.RoundTextView_rtvBgColor, Color.WHITE);
            attributes.recycle();

            GradientDrawable gd = new GradientDrawable();//创建drawable
            gd.setColor(rtvBgColor);
            gd.setCornerRadius(rtvRadius);
            if (rtvBorderWidth > 0) {
                gd.setStroke(rtvBorderWidth, rtvBorderColor);
            }

            this.setBackground(gd);
        }
    }
    public void setRoundBackground(@ColorInt int color) {
        GradientDrawable myGrad = (GradientDrawable) getBackground();
        if (myGrad != null)
            myGrad.setColor(color);
    }
}
