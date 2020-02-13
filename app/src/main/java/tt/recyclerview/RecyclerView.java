package tt.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView extends ViewGroup {

    Adapter adapter;
    Recycler recycler;
    List<View> viewList;    // 当前显示的view
    int touchSlop;          // 最小滑动距离

    boolean needRelayout;   // 初始化
    int scrollY;            // y偏移量
    int firstRow;           // 第一可见行，占内容的第几行
    int rowCount;           // 行数

    int width;              // RecyclerView的宽度
    int height;             // RecyclerView的高度
    int[] heights;          // item高度数组


    public RecyclerView(Context context) {
        this(context, null);
    }

    public RecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        ViewConfiguration configuration = ViewConfiguration.get(context);
        this.touchSlop = configuration.getScaledTouchSlop(); // 最小滑动距离
        this.viewList = new ArrayList<>();
        this.needRelayout = true;
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        if(adapter != null) {
            recycler = new Recycler(adapter.getViewTypeCount());
            scrollY = 0;
            firstRow = 0;
            needRelayout = true;
            requestLayout(); //1.onMeasure 2.onLayout
        }
    }


    // 1----------------------------------------------------------------------------
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int h;
        if(adapter != null) {
            this.rowCount = adapter.getCount();
            heights = new int[rowCount];
            for (int i = 0; i < heights.length; i++) {
                heights[i] = adapter.getHeight(i);
            }
        }
        //数据高度
        int tmpH = sumArray(heights, 0, heights.length);
        h = Math.min(heightSize, tmpH);  // 实际可见内容的高度

        // 设置RecycleView的最终高度
        // 在onMeasure方法中最终调用 setMeasuredDimension 方法来确定控件的大小
        setMeasuredDimension(widthSize, h);
    }

    // 获取array数组中从firstIndex 到 firstIndex + count 之间的数据之和
    private int sumArray(int array[], int firstIndex, int count) {
        int sum = 0;
        count += firstIndex;
        for (int i = firstIndex; i < count; i++) {
            sum += array[i];
        }
        return sum;
    }

    // 初始化, 重点
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(needRelayout || changed) {
            needRelayout = false;
            viewList.clear();
            removeAllViews();
            if(adapter != null) { // 摆放子控件
                width = r - l;
                height = b - t;
                int top = 0, bottom;
                for (int i = 0; i < rowCount && top < height; i++) {
                    bottom = top + heights[i];
                    View view = makeAndStep(i, 0, top, width, bottom); // 生成一个view, 重点
                    viewList.add(view);
                    top = bottom; //循环摆放
                }
            }
        }
    }

    private View makeAndStep(int row, int left, int top, int right, int bottom) {
        // 实例化一个有宽度  高度的View
        View view = obtainView(row, right - left, bottom - top);
        // 设置位置
        view.layout(left, top, right, bottom);
        return view;
    }

    private View obtainView(int row, int width, int height) {
        int itemType = adapter.getItemViewType(row); // 根据这个类型 返回相应View
        View recyclerView = recycler.get(itemType);
        View view;
        if(recyclerView == null) { // 取不到
            view = adapter.onCreateViewHolder(row, recyclerView, this);
            if(view == null) {
                throw new RuntimeException("onCreateViewHolder 必须填充布局");
            }
        }else {
            view = adapter.onBinderViewHolder(row, recyclerView, this);
        }

        view.setTag(R.id.tag_type_view, itemType);
        int widthSize =  MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        int heightSize =  MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        view.measure(widthSize, heightSize);
        addView(view, 0);
        return view;
    }
    // 1----------------------------------------------------------------------------


    // 2----------------------------------------------------------------------------
    int currentY;   // 当前滑动的y值

    // 拦截 滑动事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentY = (int) event.getRawY(); //以屏幕左上角为坐标原点(不管当前activity有没有标题栏)
//                currentY = (int) event.getY(); //以当前控件左上角为坐标原点
                break;
            case MotionEvent.ACTION_MOVE:
                int y2 = Math.abs(currentY - (int) event.getRawY());
                if(y2 > touchSlop) {
                    intercept = true;
                }
                break;
        }
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int y2 = (int) event.getRawY();  // 移动的距离，y方向
                int diffY = currentY - y2;       // diffY > 0   往上划
                currentY = y2;                   // 不加影响会变慢   important!!!!!
                scrollBy(0, diffY);              // 画布移动，并不影响子控件的位置
        }
        return super.onTouchEvent(event);
    }
    // 2----------------------------------------------------------------------------


    @Override
    public void scrollBy(int x, int y) {
        //scrollY表示第一个可见item的左上顶点 距离屏幕左上顶点的距离,手指滑动距离
        scrollY += y;
        scrollY = scrollBounds(scrollY);

        if(scrollY > 0) { // 上滑
            //1.上滑移除
            while (scrollY > heights[firstRow]) { // item完全移出去了
                removeView(viewList.remove(0));
                scrollY -= heights[firstRow];
                firstRow++;
            }
            //2.上滑加载
            while( getFillHeight() < height) {
                int addLast = firstRow + viewList.size();
                View view = obtainView(addLast, width, heights[addLast]);
                viewList.add(viewList.size(), view);
            }
        }else if(scrollY < 0) { // 下滑
            while (scrollY < 0) {
                // 下滑加载
                int firstAddRow = firstRow - 1;
                View view = obtainView(firstAddRow, width, heights[firstAddRow]);
                viewList.add(0, view);

                firstRow--;
                scrollY += heights[firstRow];
            }
            //3.下滑移除
            while(sumArray(heights, firstRow, viewList.size()) - scrollY -
                    heights[firstRow + viewList.size() - 1] >= height) {
                removeView(viewList.remove(viewList.size() - 1));
            }
        }

        repositionViews(); // 重新对子控件进行layout
    }


    // 边界极限条件
    private int scrollBounds(int scrollY) {
        //上滑
        if(scrollY > 0) {
            scrollY = Math.min(scrollY, sumArray(heights, firstRow, heights.length - firstRow) - height);
        }else { //下滑
            //极限值取0 非极限值取scrollY
            scrollY = Math.max(scrollY, -sumArray(heights, 0, firstRow));
        }
        return scrollY;
    }


    //数据的高度 - scrollY
    private int getFillHeight() {
        return sumArray(heights, firstRow, viewList.size()) - scrollY;
    }

    private void repositionViews() {
        int top, bottom, i;
        top = - scrollY;
        i = firstRow;
        for (View view : viewList) {
            bottom = top + heights[i++];
            view.layout(0, top, width, bottom);
            top = bottom;
        }
    }


    @Override
    public void removeView(View view) {
        super.removeView(view);
        int key = (int) view.getTag(R.id.tag_type_view);
        recycler.put(view, key);
    }

}
