package tt.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.widget.ImageView;

// ���Զ���
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
                // λ�ƶ���
                anim = ObjectAnimator.ofFloat(imageView, "translationX", 10,50,20,150);
                anim.setDuration(2000) ;
                anim.start();
                break;
            case KeyEvent.KEYCODE_2:
                // ���Ŷ���
                anim = ObjectAnimator.ofFloat(imageView, "scaleY", 0.1f, 2, 1, 2);
                anim.setDuration(2000) ;
                anim.start();
                break;
            case KeyEvent.KEYCODE_3:
                // ͸��Ч��
                anim = ObjectAnimator.ofFloat(imageView, "alpha", 0, 0.5f, 0, 1,0,1);
                anim.setDuration(2000) ;
                anim.start();
                break;
            case KeyEvent.KEYCODE_4:
                // ��תЧ��
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
                as.setTarget(imageView); // ����������Ӷ���

//              as.playSequentially(oa1, oa2, oa3, oa4);    // ������
                as.playTogether(oa1, oa2, oa3, oa4);        // һ���
                as.start();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
    // ********************************************************************


}
