package tt.recyclerview;

import android.view.View;
import java.util.Stack;

// 回收池
public class Recycler {
    private Stack<View>[] views;

    public Recycler(int typeNumber) {
        views = new Stack[typeNumber]; //实例化一个栈数组
        for (int i = 0; i < typeNumber; i++) {
            views[i] = new Stack<>();
        }
    }

    public void put(View view, int type) {
        views[type].push(view);
    }

    // 取到对应类型的View
    public View get(int type) {
        try {
            return views[type].pop();
        } catch (Exception e) {
            return null;
        }
    }
}
