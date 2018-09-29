package com.txtled.ellia_r.widget.listener;

/**
 * Created by Mr.Quan on 2018/9/13.
 */

public interface MusicPlayerListener {
    void onRewindClick();
    void onForwardClick();
    void onPlayClick();
    void onVolumeClick(int progress);
}
