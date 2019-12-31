package com.amt.indiaiptv.utils.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * 我们一般会在 onCreate()与onDestory() 里面做功能初始化、注销这样一些动作，
 * 比如：推送的注册注销、友盟、eventbus初始化 activity管理堆栈的进栈出栈等等这些事情。
 */

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 是否输出日志信息
     **/
    private boolean isDebug;
    /**
     * 是否允许全屏
     **/
    private boolean isFullScreen = false;
    /**
     * 设置当前activity界面是横屏还是竖屏,true代表竖屏，false代表横屏
     **/
    private boolean isAllowScreenRoate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setBoolean();  // 在initView方法中设置下面使用前设置三个参数的布尔值，是否全屏，是否禁止旋转屏幕
//        // 设置是否全屏（去掉状态栏和标题栏）
//        if (isFullScreen) {
//            setIsFullScreen();
//        }
//        // 设置是否禁止屏幕旋转
//        if (!isAllowScreenRoate) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        } else {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }
        //设置BaseActivity的布局
        setContentView(setLayout());
        //绑定ButterKnife
//        bind = ButterKnife.bind(this);
        // 将当前Activity添加到activity栈中
        ActivityStackManager.addActivity(this);
        // 初始化页面
        initView();
        // 设置数据
        initData();
        // 设置监听
        setListener();
    }

    protected void setListener() {

    }

    // 某个activity界面需求特殊时重写单独设置某个具体值
    public void setBoolean() {
    }

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int setLayout();


    public abstract void initView();

    /**
     * 设置数据
     */
    public abstract void initData();


    /**
     * 跳转Activity
     * skip Another Activity
     *
     * @param activity
     * @param cls
     */
    public static void skipAnotherActivity(Activity activity,
                                           Class<? extends Activity> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     * [是否设置沉浸状态栏]
     *
     * @param statusBar
     */
//    public void setStatusBar(boolean statusBar) {
//        isStatusBar = statusBar;
//    }

    /**
     * [是否设置全屏]
     *
     * @param fullScreen
     */
    public void setFullScreen(boolean fullScreen) {
        isFullScreen = fullScreen;
    }

    /**
     * [是否设置旋转屏幕]
     *
     * @param screenRoate
     */
    public void setScreenRoate(boolean screenRoate) {
        isAllowScreenRoate = screenRoate;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        bind.unbind(); // activity中不用手动解绑ButterKnife
        ActivityStackManager.removeActivity(this); //从栈中移除该activity
    }

    /**
     * [连续两次返回退出]
     */
    private long exitTime;

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if ((System.currentTimeMillis() - exitTime) > 2000) {
//                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
//                exitTime = System.currentTimeMillis();
//            } else {
//                exitApp();
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    /**
     * 退出应用
     * called while exit app.
     */
    public void exitApp() {
        // 从栈中移除所有的activity，并退出
        ActivityStackManager.removeAllActivity();
        System.exit(0);//system exit.
    }

    /**
     * 窗口全屏
     */
    private void setIsFullScreen() {
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

}
