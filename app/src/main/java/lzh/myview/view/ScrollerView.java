package lzh.myview.view;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import lzh.myview.R;

/**
 * Created by Administrator on 2017/8/30 0030.
 */

public class ScrollerView extends ViewGroup {
   private Scroller scroller;  // 用于滚动操作的实例
   private int mixTouchSlip; // 判断是否滑动的临界值
    private  float xDown;   // 手指按下时，X 的屏幕坐标
    private float  xMove;    // 移动时，X的屏幕坐标
    private float  xLastMove ; // 上一次 move事件的屏幕坐标
    private int leftBorder;    //界面可滚动的左边界
    private int rightBorder;//    界面可滚动的右边界
    private  int count ;  // 图片的数量
    private  int position ;  // 当前选中的位置
    ViewGroup.LayoutParams  params;
    private Context mContext;
   private OnPayListener listener;
    public ScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        scroller = new Scroller(context);
        mixTouchSlip =ViewConfiguration.get(context).getScaledPagingTouchSlop();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount  = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // 为ScrollerLayout中的每一个子控件测量大小
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }
    @Override
    protected void onLayout(boolean b, int l, int i1, int i2, int i3) {
        if (b) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                // 为ScrollerLayout中的每一个子控件在水平方向上进行布局
                childView.layout(i * childView.getMeasuredWidth(), 0, (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());
            }
            // 初始化左右边界值
            leftBorder = getChildAt(0).getLeft()-100;
            rightBorder = getChildAt(getChildCount() - 1).getRight()+100;
        }
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = ev.getRawX();
                xLastMove = xDown;
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = ev.getRawX();
                float diff = Math.abs(xMove - xDown);
                xLastMove = xMove;
                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
                if (diff > mixTouchSlip) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
             case MotionEvent.ACTION_DOWN:
                return  true;
            case MotionEvent.ACTION_MOVE:
                xMove = event.getRawX();
                int scrolledX = (int) (xLastMove - xMove);
             // getScrollX()
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    return true;
                }
                scrollBy(scrolledX, 0);
                xLastMove = xMove;
                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                position = targetIndex;
                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                // startScroll()方法接收四个参数，第一个参数是滚动开始时X的坐标，第二个参数是滚动开始时Y的坐标，
                // 第三个参数是横向滚动的距离，正值表示向左滚动，第四个参数是纵向滚动的距离，正值表示向上滚动。紧接着调用invalidate()方法来刷新界面。
                scroller.startScroll(getScrollX(), 0, dx, 0);
                invalidate();
                if (listener!=null) listener.onGetPoint(position);
                break;
        }
        return super.onTouchEvent(event);
    }
    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }
    public  void  setCount( int mcount){
        count  = mcount;
        if (params==null) params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        for (int i = 0;i< count;i++){
            ImageView image = new ImageView(mContext);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageResource(R.drawable.meizhi);
            image.setLayoutParams(params);
            addView(image);
        }
        invalidate();
    }
    public interface OnPayListener {
        void onGetPoint(int pas);
    }
    public  void  setListener(OnPayListener mlistener){
        this.listener = mlistener;
    }

}
