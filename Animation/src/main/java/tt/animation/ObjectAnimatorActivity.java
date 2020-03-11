package tt.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.widget.ImageView;

// 属性动画
public class ObjectAnimatorActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
    }

    // ********************************************************************
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        ObjectAnimator anim;
        switch (keyCode) {
            case KeyEvent.KEYCODE_1:
                // 位移动画
                anim = ObjectAnimator.ofFloat(imageView, "translationX", 10,50,20,150);
                anim.setDuration(2000) ;
                anim.start();
                break;
            case KeyEvent.KEYCODE_2:
                // 缩放动画
                anim = ObjectAnimator.ofFloat(imageView, "scaleY", 0.1f, 2, 1, 2);
                anim.setDuration(2000) ;
                anim.start();
                break;
            case KeyEvent.KEYCODE_3:
                // 透明效果
                anim = ObjectAnimator.ofFloat(imageView, "alpha", 0, 0.5f, 0, 1,0,1);
                anim.setDuration(2000) ;
                anim.start();
                break;
            case KeyEvent.KEYCODE_4:
                // 旋转效果
                anim = ObjectAnimator.ofFloat(imageView, "rotation", 0, 180, 90, 360);
//              anim = ObjectAnimator.ofFloat(imageView, "rotationY", 0, 180, 90, 360);
                anim.setDuration(2000) ;
                anim.start();
                break;
            case KeyEvent.KEYCODE_5:
                AnimatorSet as = new AnimatorSet();
                ObjectAnimator oa1 = ObjectAnimator.ofFloat(imageView, "translationX", 10, 50, 20, 100);
                ObjectAnimator oa2 = ObjectAnimator.ofFloat(imageView, "scaleY", 0.1f, 2, 1, 2);
                ObjectAnimator oa3 = ObjectAnimator.ofFloat(imageView, "alpha", 0, 0.5f, 0, 1);
                ObjectAnimator oa4 = ObjectAnimator.ofFloat(imageView, "rotationY", 0, 180, 90, 360);
                as.setDuration(2000) ;
                as.setTarget(imageView); // 往集合中添加动画

//              as.playSequentially(oa1, oa2, oa3, oa4);    // 挨个飞
                as.playTogether(oa1, oa2, oa3, oa4);        // 一起飞
                as.start();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
    // ********************************************************************


}
