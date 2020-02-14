package ScreenAdapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

// 屏幕适配
public class ScreenAdapter_Layout extends LinearLayout {

    public ScreenAdapter_Layout(Context context) {
        super(context);
    }

    public ScreenAdapter_Layout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScreenAdapter_Layout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    static boolean isFlag = true;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isFlag) {
            int count = this.getChildCount();
            float scaleX = UIUtils.getInstance(this.getContext()).getHorizontalScaleValue();
            float scaleY = UIUtils.getInstance(this.getContext()).getVerticalScaleValue();

            for (int i = 0; i < count; i++) {
                View child = this.getChildAt(i);
                // 修改当前控件的尺寸
                LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                layoutParams.width = (int) (layoutParams.width * scaleX);
                layoutParams.height = (int) (layoutParams.height * scaleY);
                layoutParams.rightMargin = (int) (layoutParams.rightMargin * scaleX);
                layoutParams.leftMargin = (int) (layoutParams.leftMargin * scaleX);
                layoutParams.topMargin = (int) (layoutParams.topMargin * scaleY);
                layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * scaleY);
            }

            isFlag = false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
