package com.txtled.ellia_r.light;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.txtled.ellia_r.R;
import com.txtled.ellia_r.bean.ColorList;
import com.txtled.ellia_r.utils.Utils;
import com.txtled.ellia_r.widget.RoundTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr.Quan on 2018/9/6.
 */

public class LightAdapter extends RecyclerView.Adapter<LightAdapter.LightViewHolder> {


    private List<ColorList> mColorList;
    private Context mContext;
    private int mCheckPosition = -1;
    private int height;
    private boolean first;
    private OnGetMeasure mListener;

    public LightAdapter(Context mContext) {
        this.mContext = mContext;
        if (mContext instanceof OnGetMeasure){
            mListener = (OnGetMeasure) mContext;
        }
    }

    public void initList(List<ColorList> mColorList) {
        this.mColorList = mColorList;
    }

    @NonNull
    @Override
    public LightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_light, null);
        return new LightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LightViewHolder holder, final int position) {

        holder.tvLightColor.setRoundBackground(mColorList.get(mColorList.size() - position - 1).getColor());
        if (mCheckPosition == position) {
            holder.tvLightColor.setText(R.string.selected);
        } else {
            holder.tvLightColor.setText("");
        }
        holder.tvLightColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCheckPosition = position;
                notifyDataSetChanged();
            }
        });
        if (!first){
            holder.itemView.measure(0,0);
            height = holder.itemView.getMeasuredHeight();
            first = true;
            mListener.getMeasure(height * mColorList.size());
        }
    }

    public interface OnGetMeasure{
        void getMeasure(int height);
    }

    @Override
    public int getItemCount() {
        return mColorList == null ? 0 : mColorList.size();
    }

    public void insertColor(ColorList colorList) {
        mColorList.add(colorList);
        first = false;
        notifyItemInserted(0);
    }

    public class LightViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_light_color)
        RoundTextView tvLightColor;

        public LightViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
