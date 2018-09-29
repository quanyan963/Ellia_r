package com.txtled.ellia_r.sound;

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
 * Created by Mr.Quan on 2018/9/6.
 */

public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.SoundViewHolder> {

    private List<String> mTitleList;
    private List<String> mSongList;
    private Context mContext;
    private int height;

    private PlayerStatue mListener;

    public SoundAdapter(Context mContext) {
        this.mContext = mContext;
        if (mContext instanceof PlayerStatue){
            mListener = (PlayerStatue) mContext;
        }
    }

    public void initList() {
        mTitleList = Arrays.asList(mContext.getResources().getStringArray(R.array.sound_title));
        mSongList = Arrays.asList(mContext.getResources().getStringArray(R.array.sound_song));
    }

    @NonNull
    @Override
    public SoundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_aroma, null);
        return new SoundViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SoundViewHolder holder, final int position) {
        holder.tvBottomT.setText(mTitleList.get(position));
        holder.tvBottomL.setText(mSongList.get(position));
        holder.ivItemRightB.setImageResource(R.mipmap.tab_sounds_selected);
        holder.layoutItem.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:
                        mListener.onPlayerClick(position);
                        break;
                }
                return false;
            }
        });
        holder.ivItemRightB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onPlayButtonClick();
            }
        });
    }

    public interface PlayerStatue{
        void onPlayerClick(int position);
        void onPlayButtonClick();
    }

    @Override
    public int getItemCount() {
        return mTitleList == null ? 0 : mTitleList.size();
    }

    public class SoundViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_bottom_l)
        CustomTextView tvBottomL;
        @BindView(R.id.tv_bottom_r)
        CustomTextView tvBottomR;
        @BindView(R.id.tv_bottom_t)
        CustomTextView tvBottomT;
        @BindView(R.id.iv_item_right_b)
        ImageView ivItemRightB;
        @BindView(R.id.layout_item)
        RelativeLayout layoutItem;
        public SoundViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mListener = null;
    }
}
