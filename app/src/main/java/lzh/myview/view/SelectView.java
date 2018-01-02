package lzh.myview.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import lzh.myview.R;
import lzh.myview.util.UIUtils;

/**
 * Created by XHH on 2017/12/27.
 */

public class SelectView extends ViewGroup {
    ViewGroup.LayoutParams  params;
    private Context mContext;
    private Scroller scroller;  // 用于滚动操作的实例
    private ArrayList<String> mlist ;
    private OnPayListener listener;
    private  float yDown;   // 手指按下时，Y 的屏幕坐标
    private float  yMove;    // 移动时，Y的屏幕坐标
    private float yLastMove ; // 上一次 move事件的屏幕坐标
    int select =0;
    List<TextView> tlist = new ArrayList<>();

    int t ;
    public SelectView(Context context) {
        super(context);
    }

    public SelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        scroller = new Scroller(context);
    }
    public SelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
            for (int i = 0; i < mlist.size(); i++) {
                    TextView childView = (TextView) getChildAt(i);
                    tlist.add(childView);
                // 为ScrollerLayout中的每一个子控件在水平方向上进行布局
                childView.layout(getWidth()/2-childView.getMeasuredWidth()/2, getHeight()/2+i *2* childView.getMeasuredHeight(), getWidth()/2+childView.getMeasuredWidth()/2, getHeight()/2+(i+1) *2* childView.getMeasuredHeight());
            }

        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int scrolledY = 0;
        TextView childView = (TextView) getChildAt(0);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                if (scroller != null && !scroller.isFinished()) {
//                    scroller.abortAnimation();
//                }
                yDown = event.getRawY();
                yMove=yDown;
                yLastMove = yMove;
                return  true;
            case MotionEvent.ACTION_MOVE:
                yMove =event.getRawY();
                 scrolledY = (int) (yLastMove - yMove);
                 //下滑边界
                if ( scrolledY<0 &&getScrollY()<0) {
//                    Log.d("=================","TTTTTTTTTTTTTTTT"+getScrollY());
                   return super.onTouchEvent(event);
                   // 上滑边界
                } else if (   scrolledY>0  &&getScrollY()>(mlist.size()-1)*childView.getHeight()) {
//                    Log.d("=================","BBBBBBBBBBBBBBBBB"+getScrollY());
                  return super.onTouchEvent(event);
                }

            if (getScrollY()<(mlist.size()-1)*childView.getHeight()) {
             if (getScrollY()>=childView.getHeight()/2) t = (getScrollY())/(childView.getHeight())+1;
            if (scrolledY<0&&getScrollY()<=childView.getHeight()/2) t=0;
//                Log.d("=================","SSSSSSSSSSSSSS"+getScrollY());
//                Log.d("=================","SSSSSSSSSSSSSS"+t);
            }
                for (int i =0;i<mlist.size();i++){
                    if (i==t){
                        tlist.get(i).setTextColor(Color.parseColor("#333333"));
                        select =i;
                    }else {
                        tlist.get(i).setTextColor(Color.parseColor("#dddddd"));
                    }
                }
                yLastMove = yMove;
                scrollBy(0, scrolledY);
//                  scroller.startScroll(0, getScrollY(), 0, scrolledY,50);
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:

                if (listener!=null) listener.onGetPoint(select);
                return true;
        }
        return super.onTouchEvent(event);
    }

//    @Override
//    public void computeScroll() {
//        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
//        if (scroller.computeScrollOffset()) {
//            scrollTo(scroller.getCurrX(), scroller.getCurrY());
//            postInvalidate();
//        }
//    }
    public  void  setCount(ArrayList<String> list){
        mlist  = list;
        if (params==null) params = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        for (int i = 0;i< mlist.size();i++){
            TextView textView = new TextView(mContext);
            textView.setText(list.get(i));
            if (i==0){textView.setTextColor(Color.parseColor("#333333"));}
            else {
                textView.setTextColor(Color.parseColor("#dddddd"));
            }
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,UIUtils.getDimens(R.dimen.size_30px));
            textView.setLayoutParams(params);
            addView(textView);
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
