package com.txtled.ellia_r.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.txtled.ellia_r.R;
import com.txtled.ellia_r.widget.CustomTextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr.Quan on 2018/8/31.
 */

public class TimerAdapter extends RecyclerView.Adapter<TimerAdapter.TimerViewHolder> {

    private Context mContext;
    private List<String> mTitleList;
    private OnCheckedListener mListener;
    private OnImgListener mImgListener;
    private int selectPosition = -1;

    public TimerAdapter(Context mContext, OnCheckedListener mListener, OnImgListener mImgListener) {
        this.mContext = mContext;
        this.mListener = mListener;
        this.mImgListener = mImgListener;
    }

    public void initList() {
        mTitleList = Arrays.asList(mContext.getResources().getStringArray(R.array.timer_time));
        //notifyDataSetChanged();
    }

//    public void removeAll(){
//        mTitleList = null;
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public TimerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, null);
        return new TimerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TimerViewHolder holder, final int position) {
        RelativeLayout.LayoutParams lp =
                (RelativeLayout.LayoutParams) holder.layoutItem.getLayoutParams();
        lp.setMargins(mContext.getResources().getDimensionPixelSize(R.dimen.dp_16_x),
                mContext.getResources().getDimensionPixelSize(R.dimen.dp_16_y),
                mContext.getResources().getDimensionPixelSize(R.dimen.dp_16_x), 0);
        holder.layoutItem.setLayoutParams(lp);
        holder.tvLeft.setText(mTitleList.get(position));
        holder.tvLeftSecond.setText(R.string.minutes);
        holder.ivItemRight.setImageResource(R.mipmap.ic_launcher);
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPosition = position;
                mListener.onClick(position);
                notifyDataSetChanged();
            }
        });
        holder.ivItemRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPosition = -1;
                mImgListener.onImgClick(position);
                notifyDataSetChanged();


            }
        });
        if (selectPosition == -1){
            holder.layoutItem.setVisibility(View.VISIBLE);
            holder.layoutItem.setBackgroundResource(R.drawable.item_bg_unselected);
            holder.ivItemRight.setClickable(false);
        }else if (selectPosition == position){
            holder.layoutItem.setVisibility(View.VISIBLE);
            holder.layoutItem.setBackgroundResource(R.drawable.item_bg_selected);
            holder.ivItemRight.setClickable(true);
        }else {
            holder.layoutItem.setVisibility(View.GONE);
            holder.layoutItem.setBackgroundResource(R.drawable.item_bg_unselected);
        }
    }

    public interface OnCheckedListener {
        void onClick(int position);
    }

    public interface OnImgListener {
        void onImgClick(int position);
    }



    @Override
    public int getItemCount() {
        return mTitleList == null ? 0:mTitleList.size();
    }

    public class TimerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_left)
        CustomTextView tvLeft;
        @BindView(R.id.iv_item_right)
        ImageView ivItemRight;
        @BindView(R.id.layout_item)
        RelativeLayout layoutItem;
        @BindView(R.id.tv_left_second)
        CustomTextView tvLeftSecond;

        public TimerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
