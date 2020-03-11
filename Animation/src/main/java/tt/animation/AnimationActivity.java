package tt.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

// 补间动画
public class AnimationActivity extends Activity {

    private ImageView imageView;
    private ListView lv;
    private String[] names = {"aa", "bb", "cc"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        lv = findViewById(R.id.lv);
        ListAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        lv.setAdapter(adapter);
    }

    // ********************************************************************
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Animation anim;
        switch (keyCode) {
            case KeyEvent.KEYCODE_1:
                // anim = new TranslateAnimation(320, 0, 0, 0);
                anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1,
                        Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0,
                        Animation.ABSOLUTE, 0);
                anim.setDuration(3000);
                imageView.startAnimation(anim);
                break;

            case KeyEvent.KEYCODE_2:
                // anim = new ScaleAnimation(2, 1, 1, 1);
                anim = new ScaleAnimation(2, 1, 2, 1, Animation.RELATIVE_TO_SELF,
                        0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setDuration(3000);
                imageView.startAnimation(anim);
                break;

            case KeyEvent.KEYCODE_3:
                anim = new RotateAnimation(90, 0, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setDuration(3000);
                imageView.startAnimation(anim);
                break;

            case KeyEvent.KEYCODE_4:
                anim = new AlphaAnimation(0, 1);
                anim.setDuration(3000);
                imageView.startAnimation(anim);
                break;

            case KeyEvent.KEYCODE_5:
                anim = AnimationUtils.loadAnimation(this, R.anim.mytran);
                imageView.startAnimation(anim);
                break;

            case KeyEvent.KEYCODE_6:
                AnimationSet set = new AnimationSet(true);
                set.setDuration(3000);
                set.addAnimation(new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 0.5f, Animation.ABSOLUTE, 0,
                        Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0));
                set.addAnimation(new AlphaAnimation(0, 1));
                set.setInterpolator(new AnticipateInterpolator());// 先后退一点，再向前
                LayoutAnimationController controller = new LayoutAnimationController(set);
                lv.setLayoutAnimation(controller);
                lv.startLayoutAnimation();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
    // ********************************************************************

}
