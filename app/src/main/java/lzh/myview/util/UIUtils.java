package lzh.myview.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;


import lzh.myview.MyApplication;

/**
 * 与界面相关的工具类
 */
public class UIUtils {

    public static Context getContext() {
        return MyApplication.getApplication();
    }

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */
    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 获取屏幕测量器(DisplayMetrics)
     *
     * @param activity 引用的上下文
     * @return DisplayMetrics
     */
    public static DisplayMetrics getDM(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        if (null != activity) {
            Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
            defaultDisplay.getMetrics(dm);
        }
        return dm;
    }



    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }

    /**
     * 获取资源
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取文字
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取dimen
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(getContext(), resId);
//        return getResources().getDrawable(resId);
    }

    /**
     * 获取颜色
     */
    public static int getColor(int resId) {
        return ContextCompat.getColor(getContext(), resId);
//        return getResources().getColor(resId);
    }

    /**
     * 获取颜色选择器
     */
    public static ColorStateList getColorStateList(int resId) {
        return ContextCompat.getColorStateList(getContext(), resId);
//        return getResources().getColorStateList(resId);
    }



}


