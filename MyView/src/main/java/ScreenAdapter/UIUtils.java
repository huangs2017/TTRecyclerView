package ScreenAdapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class UIUtils {

    private static UIUtils utils;

    public static UIUtils getInstance(Context context) {
        if (utils == null) {
            utils = new UIUtils(context);
        }
        return utils;
    }


    //参照宽高
    public final float STANDARD_WIDTH = 720;
    public final float STANDARD_HEIGHT = 1232;
    //设备实际宽高
    public float displayWidth;
    public float displayHeight;


    private UIUtils(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //加载当前界面信息
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        if (displayWidth == 0.0f || displayHeight == 0.0f) {
            int systemBarHeight = getSystemBarHeight(context, 48);
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                displayWidth = displayMetrics.heightPixels;
                displayHeight = displayMetrics.widthPixels - systemBarHeight;
            } else {
                displayWidth = displayMetrics.widthPixels;
                displayHeight = displayMetrics.heightPixels - systemBarHeight;
            }
        }
    }

    /**
     * 取得系数
     *
     * 假定设备  720 * 1232
     * 实际设备  800 * 600
     */
    public float getHorizontalScaleValue() {
        return displayWidth / STANDARD_WIDTH;
    }

    public float getVerticalScaleValue() {
        return displayHeight / STANDARD_HEIGHT;
    }


    //获取状态框高度
    public int getSystemBarHeight(Context context, int defValue) {
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object r = clazz.newInstance();
            Field field = clazz.getField("system_bar_height");
            int x = (int) field.get(r);
            return context.getResources().getDimensionPixelOffset(x);
        } catch (Exception e) {
            return defValue;
        }
    }

}
