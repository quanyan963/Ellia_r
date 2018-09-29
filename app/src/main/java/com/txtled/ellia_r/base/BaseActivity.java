package com.txtled.ellia_r.base;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.txtled.ellia_r.R;
import com.txtled.ellia_r.application.MyApplication;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {
    public static final String TAG = BaseActivity.class.getSimpleName();
    public TextView tvTitle;
    public boolean isBack = true;
    private long mExitTime;
    private MyApplication mApplication;
    public Toolbar toolbar;
    public Snackbar snackbar;

    public abstract void init();

    public abstract int getLayout();

    //private OnMenuItemClick mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);    //设置全屏
        setContentView(getLayout());
        ButterKnife.bind(this);
        mApplication = MyApplication.getInstance();
        addActivity();
        onCreateView();
        init();
    }

    public void onCreateView() {

    }


    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            //tvTitle = (TextView) findViewById(R.id.tv_title);
            //toolbar.inflateMenu(R.menu.main_menu);
            setSupportActionBar(toolbar);

            //setTitle("");

            //toolbar.setOnMenuItemClickListener(onMenuItemClick);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isBack){
                        onBackPressed();
                    }else {

                    }

                }
            });
        }

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void setNavigationIcon(boolean isBack) {
        this.isBack = isBack;
        if (isBack) {
            //toolbar.setNavigationIcon(R.mipmap.ic_homebtn);
        } else {

            //toolbar.setNavigationIcon(R.mipmap.ic_state_disconnect);
        }

    }

//    public Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
//        @Override
//        public boolean onMenuItemClick(MenuItem menuItem) {
//            mListener.OnMenuClicked(menuItem.getItemId());
//            return true;
//        }
//    };
//
//    public interface OnMenuItemClick {
//        void OnMenuClicked(int itemId);
//    }
//
//    public void setOnClickListener(OnMenuItemClick listener) {
//        this.mListener = listener;
//    }

    public boolean onExitActivity(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, R.string.exit_program_hint,
                        Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                removeAllActivity();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    public void addActivity() {
        mApplication.addActivity(this);
    }


    public void removeAllActivity() {
        mApplication.removeAllActivity();
    }

    public void showSnackBar(View view, int str) {
        if (snackbar == null) {
            snackbar = Snackbar.make(view, str, Snackbar.LENGTH_INDEFINITE);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.dark_grey));
        }
        snackbar.show();
    }

    public void hideSnackBar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    @Subscribe
    public void onEventMainThread(String serviceEvent) {
//        Utils.Logger(TAG,"Position",serviceEvent.getPosition()+"");
//        Utils.Logger(TAG,"Progress",serviceEvent.getProgress()+"");
    }
//
//    @Subscribe
//    public void onEventFlameThread(FlameEvent flameEvent) {
//
//    }
}
