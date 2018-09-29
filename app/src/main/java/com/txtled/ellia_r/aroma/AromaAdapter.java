package com.txtled.ellia_r.aroma;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
 * Created by Mr.Quan on 2018/9/4.
 */

public class AromaAdapter extends RecyclerView.Adapter<AromaAdapter.AromaViewHolder> {

    private List<String> mAromaPower;
    private List<String> mAromaML;
    private Context mContext;
    private int height;

    public AromaAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void initList() {
        mAromaPower = Arrays.asList(mContext.getResources().getStringArray(R.array.aroma_power));
        mAromaML = Arrays.asList(mContext.getResources().getStringArray(R.array.aroma_ml));
    }

    @NonNull
    @Override
    public AromaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_aroma, null);
        return new AromaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AromaViewHolder holder, int position) {

        holder.tvBottomT.setText(mAromaPower.get(position));
        holder.tvBottomL.setText(mAromaML.get(position));
        holder.tvBottomR.setText(R.string.milliliters);
        holder.ivItemRightB.setImageResource(R.mipmap.ic_launcher);
//        holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(0,
//                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0,
//                View.MeasureSpec.UNSPECIFIED));
//        int itemHeight = holder.itemView.getMeasuredHeight();
//        height = itemHeight * mAromaPower.size();
    }

//    public int getRealHeight(){
//        return height;
//    }

    @Override
    public int getItemCount() {
        return mAromaPower == null ? 0 : mAromaPower.size();
    }

    public class AromaViewHolder extends RecyclerView.ViewHolder {
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
        public AromaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
