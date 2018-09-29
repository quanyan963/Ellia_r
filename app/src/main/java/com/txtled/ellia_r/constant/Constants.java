package com.txtled.ellia_r.constant;

import android.os.Environment;

/**
 * Created by KomoriWu
 * on 2017-08-15.
 */


public class Constants {
    public static final int PERMISSION_REQUEST_CODE = 100;
    public static final int STATUS_BAR_TRANSLUCENT = 0;
    public static final String MUSIC_NUM = "music_num";
    public static final String RB_ID = "rb_id";
    public static final String RB_POSITION = "rb_position";

    public static final String TIMER_COUNT= "timer_count";

    public static final String AROMA_NAME = "aroma_name";
    public static final String SOUND_NAME = "sound_name";
    public static final String LIGHT_NAME = "light_name";
    public static final String TIMER_NAME = "timer_name";

    public static final String CONN_FLAGS = "conn_flags";

    public static final String IMG_FILE_PATH= Environment.getExternalStorageDirectory()+
            "/Ellia/" + System.currentTimeMillis() + ".jpg";

    public static final int PAGE_SOUND = 1;
    public static final int PAGE_AROMA = 0;
    public static final int PAGE_LIGHT = 2;
}
