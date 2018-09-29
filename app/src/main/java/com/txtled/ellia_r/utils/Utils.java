package com.txtled.ellia_r.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.media.AudioManager;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.txtled.ellia_r.R;
import com.txtled.ellia_r.application.MyApplication;
import com.txtled.ellia_r.widget.CustomTextView;
import com.txtled.ellia_r.widget.ItemLayout;
import com.txtled.ellia_r.widget.listener.MusicPlayerListener;
import com.txtled.ellia_r.widget.listener.ViewClickListener;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by KomoriWu
 * on 2017/9/18.
 */

public class Utils {
    public static final boolean isLog = true;

    public static String getColorStr(int color) {
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        String r1 = Utils.getBothColor(r);
        String g1 = Utils.getBothColor(g);
        String b1 = Utils.getBothColor(b);
        return r1 + g1 + b1;
    }

    public static String getBothColor(int str) {
        if (str < 16) {
            return "0" + Integer.toHexString(str);
        } else {
            return Integer.toHexString(str);
        }
    }

    public static DisplayImageOptions getImageOptions() {
        return getImageOptions(R.mipmap.ic_launcher_round, 0);
    }

    public static DisplayImageOptions getImageOptions(int defaultIconId) {
        return getImageOptions(defaultIconId, 0);
    }

    public static DisplayImageOptions getImageOptions(int defaultIconId, int cornerRadiusPixels) {
        return new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels))
                .showImageOnLoading(defaultIconId)
                .showImageOnFail(defaultIconId)
                .showImageForEmptyUri(defaultIconId)
                .cacheInMemory(true)
                .cacheOnDisc()
                .build();
    }

    public static void displayImage(Context context, String uri, ImageView imageView) {
        MyApplication.getImageLoader(context).displayImage(uri, imageView, getImageOptions());
    }

    public static void displayImage(Context context, String uri, ImageView imageView,
                                    DisplayImageOptions displayImageOptions) {
        MyApplication.getImageLoader(context).displayImage(uri, imageView, displayImageOptions);
    }

//    public static void showPopup(View view, final Activity activity, int position, String[] data,
//                                 int titleId, final PopWindowListener popWindowListener) {
//        View popView = LayoutInflater.from(activity).inflate(R.layout.pop_picker, null);
//        final PopupWindow mPopWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        MontserratButton btnTitle = (MontserratButton) popView.findViewById(R.id.btn_title);
//        btnTitle.setText(titleId);
//        mPopWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        MontserratButton btnSave = (MontserratButton) popView.findViewById(R.id.btn_save);
//        final LoopView loopView = (LoopView) popView.findViewById(R.id.loop_view);
//        loopView.setDataList(Arrays.asList(data));
//        loopView.setInitPosition(position);
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                popWindowListener.onSave(loopView.getSelectedItem());
//                mPopWindow.dismiss();
//            }
//        });
//
//        // 当点击取消的时候，pw消失
//        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                backgroundAlpha(activity, 1.0f);
//            }
//        });
//        backgroundAlpha(activity, 0.8f);
//        mPopWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
//    }

    public static void showBottomDialog(Activity activity, final MusicPlayerListener listener) {
        final BottomSheetDialog dialog = new BottomSheetDialog(activity);
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.pop_player, null);

        //dialogView.setBackgroundResource(R.color.transparent);
        ImageView ivRewind = (ImageView) dialogView.findViewById(R.id.iv_rewind);
        ImageView ivForward = (ImageView) dialogView.findViewById(R.id.iv_forward);
        ImageView ivPlay = (ImageView) dialogView.findViewById(R.id.iv_play);
        ImageView ivClose = (ImageView) dialogView.findViewById(R.id.iv_close);
        ItemLayout ilSeekBar = (ItemLayout) dialogView.findViewById(R.id.il_volume);
        ivRewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRewindClick();
            }
        });
        ivForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onForwardClick();
            }
        });
        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPlayClick();
            }
        });
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ilSeekBar.setOnSeekBarListener(new ItemLayout.OnSeekBarListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                listener.onVolumeClick(seekBar.getProgress());
            }

            @Override
            public void onProgressChange(SeekBar seekBar) {

            }
        });

        dialog.setContentView(dialogView);
        // 除默认白背景
        ViewGroup parentTwo = (ViewGroup) dialogView.getParent();
        parentTwo.setBackgroundResource(android.R.color.transparent);
        dialog.show();
    }

    /**
     * 设置添加屏幕的背景透明度
     */
    public static void backgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow()
                .addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        activity.getWindow().setAttributes(lp);
    }

    public static String getShowTime(int timerCount) {
        int min = timerCount / 60 < 0 ? 0 : timerCount / 60;
        String minute = min < 10 ? "0" + min : min + "";
        int s = timerCount % 60 < 0 ? 0 : timerCount % 60;
        String second = s < 10 ? "0" + s : s + "";
        return minute + ":" + second;
    }

    public static String[] musicMedias = {MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DURATION, MediaStore.Audio.Albums.ALBUM_ID};

    public static boolean isLocationEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(
                Context.LOCATION_SERVICE);
        boolean networkProvider = locationManager.isProviderEnabled(LocationManager.
                NETWORK_PROVIDER);
        boolean gpsProvider = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (networkProvider || gpsProvider) return true;
        return false;
    }

//    public static String getWeekString(String weeks, Context context) {
//        int[] week = new int[7];
//        String repeatText = "";
//        String[] menu = context.getResources().getStringArray(R.array.week_repeat_list);
//        if (weeks.equals(Constants.ALL_WEEK)) {
//            return context.getString(R.string.everyday);
//        } else if (weeks.equals(Constants.NONE_WEEK)) {
//            return context.getString(R.string.none);
//        } else if (weeks.equals(Constants.WORK_DAY)) {
//            return context.getString(R.string.workday);
//        } else if (weeks.equals(Constants.WEEKEND_DAY)) {
//            return context.getString(R.string.weekend);
//        } else {
//            for (int i = 0; i < weeks.length(); i++) {
//                week[i] = Integer.parseInt(String.valueOf(weeks.charAt(i)));
//                if (week[i] == 1) {
//                    repeatText += " " + menu[i];
//                }
//            }
//            if (repeatText.length() <= 4) {
//                repeatText = context.getString(R.string.every) + repeatText;
//            }
//            return repeatText;
//        }
//    }

    public static String bytesToAscii(byte[] bytes, int offset, int dateLen) {
        if ((bytes == null) || (bytes.length == 0) || (offset < 0) || (dateLen <= 0)) {
            return null;
        }
        if ((offset >= bytes.length) || (bytes.length - offset < dateLen)) {
            return null;
        }

        String asciiStr = null;
        byte[] data = new byte[dateLen];
        System.arraycopy(bytes, offset, data, 0, dateLen);
        try {
            asciiStr = new String(data, "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
        }
        return asciiStr;
    }

    static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String asciiToString(String value) {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }

    public static String formatData(int data) {
        return data < 10 ? "0" + data : data + "";
    }

    public static String formatData(String data) {
        int s = Integer.parseInt(data);
        return formatData(s);
    }

    public static String formatHex(int data) {
        String s = ((Integer) data).toHexString(data);
        return data < 16 ? "0" + s : s + "";
    }

    public static String getSubTime(String time) {
        return time.substring(0, 2);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    public static void setVolume(int volume) {
        AudioManager audioManager = (AudioManager) MyApplication.getInstance().getSystemService(
                Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, volume, AudioManager.
                FLAG_SHOW_UI);
    }

    public static int format12Hour(int meridian, int hour) {
        int formatHour;
        if (meridian == 0) {
            if (hour == 11) {
                formatHour = 0;
            } else {
                formatHour = hour + 1;
            }
        } else {
            if (hour == 11) {
                formatHour = hour + 1;
            } else {
                formatHour = hour + 13;
            }
        }
        return formatHour;
    }

    public static float x = 0;
    public static float y = 0;

    public static boolean changeViewColor(View v, MotionEvent event, Context context, ViewClickListener listener) {
        Drawable drawable = ((ImageView) v).getDrawable().mutate();
        Drawable.ConstantState state = drawable.getConstantState();
        Drawable drawable1 = DrawableCompat.wrap(state == null ? drawable : state.newDrawable()).mutate();
        drawable1.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            DrawableCompat.setTint(drawable1, ContextCompat.getColor(context, R.color.dark_grey));
            ((ImageView) v).setImageDrawable(drawable1);
            x = event.getX();
            y = event.getY();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            DrawableCompat.setTint(drawable1, ContextCompat.getColor(context, R.color.white));
            ((ImageView) v).setImageDrawable(drawable1);
            if (Math.abs(event.getX() - x) < (v.getWidth() * 2 / 3) &&
                    Math.abs(event.getY() - y) < (v.getHeight() * 2 / 3)) {
                listener.getViewId(v.getId());
            }
            return false;
        } else {
            DrawableCompat.setTint(drawable1, ContextCompat.getColor(context, R.color.white));
            ((ImageView) v).setImageDrawable(drawable1);
            return false;
        }
    }

    public static int getSoundValue(int value, float everyValue) {
        if (value % everyValue > everyValue / 2f) {
            return (int) (value / everyValue + 1);
        }
        return (int) (value / everyValue);
    }

    public static void Logger(String TAG, String type, String value) {
        if (!isLog)
            return;
        Log.i(TAG, type + ":------" + value);
    }

    //将xml中color值转成String类型取出
    public static String changeColor(Context context,int id){
        StringBuffer stringBuffer = new StringBuffer();
        int color = context.getResources().getColor(id);
        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);


        stringBuffer.append(Integer.toHexString(red));
        stringBuffer.append(Integer.toHexString(green));
        stringBuffer.append(Integer.toHexString(blue));
        return stringBuffer.toString();
    }

    //判断当前View 是否处于touch中
    public static boolean inRangeOfView(View view, MotionEvent ev) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        if (ev.getX() < x || ev.getX() > (x + view.getWidth()) || ev.getY() < y || ev.getY() > (y
                + view.getHeight())) {
            return false;
        }
        return true;
    }
}
