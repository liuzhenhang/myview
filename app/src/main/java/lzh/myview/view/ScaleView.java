package lzh.myview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import lzh.myview.R;

/**
 * Created by LZH on 2017-12-21.
 */

public class ScaleView extends View {
    private Context mcontext;
    private int mMax; //最大刻度
    private int mMin; // 最小刻度
    private int mScaleMargin; //刻度间距
    private int mScaleHeight; //刻度线的高度
    private int iScaleHeight; //整刻度线高度
    private int mCountScale; //滑动的总刻度
    private int mWith; // 屏幕宽度
    private int paintLinewidth;
    private int allWidth; //总宽度
    private int mHeight; //高度
    private int textSize;
    private Scroller mScroller; // 滑动类
    private int mLastX; // 滑动的坐标
    private int direction; // 用于判断滑动方向
    private int mMidScale; //中间刻度
    private Paint paint; // 画笔
    protected OnScrollListener mScrollListener;

    public interface OnScrollListener {
        void onScaleScroll(int scale);
    }
    public ScaleView(Context context) {
        super(context);
    }

    public ScaleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;
        getAtt(attrs);
        init();
    }
    /**
     * 初始化
     */
    private void init() {
        mScroller = new Scroller(mcontext);
        allWidth = (mMax - mMin) * mScaleMargin;
        mHeight = mScaleHeight * 8;
        iScaleHeight = mScaleHeight * 2;
        // 设置layoutParams
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(allWidth, mHeight);
        this.setLayoutParams(lp);
        // 画笔
        paint = new Paint();
        paint.setColor(Color.parseColor("#dddddd"));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(paintLinewidth);
        // 是否使用图像抖动处理
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        // 文字居中
        paint.setTextAlign(Paint.Align.CENTER);

    }
    private void getAtt(AttributeSet attrs) {
        TypedArray typedArray = mcontext.obtainStyledAttributes(attrs, R.styleable.ScaleView);
        mMin = typedArray.getInt(R.styleable.ScaleView_scale_view_min, 0);
        mMax = typedArray.getInt(R.styleable.ScaleView_scale_view_max, 200000);
        mScaleMargin = (int) typedArray.getDimension(R.styleable.ScaleView_scale_view_margin, 15);
        mScaleHeight = (int) typedArray.getDimension(R.styleable.ScaleView_scale_view_height, 20);
        textSize = (int) typedArray.getDimension(R.styleable.ScaleView_scale_view_text_size,20);
        paintLinewidth = (int) typedArray.getDimension(R.styleable.ScaleView_scale_view_line_size,2);
        typedArray.recycle();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height= View.MeasureSpec.makeMeasureSpec(mHeight, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, height);
        mWith = getMeasuredWidth();
        direction = mWith / mScaleMargin / 2 + mMin;
        mMidScale = mWith / mScaleMargin / 2 + mMin;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onDrawScale(canvas);
        onDrawDirection(canvas);
        onDrawLine(canvas);
    }

    // 确定中心位置
    private void onDrawDirection(Canvas canvas) {
        //每一屏幕刻度的个数/2
        int countScale = mWith / mScaleMargin / 2;
        //根据滑动的距离，计算指针的位置（指针始终位于屏幕中间）
        int finalX = mScroller.getFinalX();
        //滑动的刻度
        int tmpCountScale = (int) Math.rint((double) finalX / (double) mScaleMargin); //四舍五入取整
        //总刻度
        mCountScale = tmpCountScale + countScale + mMin;
        if (mScrollListener != null) { //回调方法
            mScrollListener.onScaleScroll(mCountScale); // 获取当前的刻度
        }
    }

    //画刻度和数字
    private void onDrawScale(Canvas canvas) {
        paint.setColor(Color.parseColor("#dddddd"));
        paint.setTextSize(textSize);
        for (int i = 0, k = mMin; i <200; i++) {
            if (i % 10 == 0) { //整值
                paint.setStrokeWidth(paintLinewidth);
                canvas.drawLine(i * mScaleMargin, mHeight, i * mScaleMargin, mHeight - iScaleHeight, paint);
                //整值文字
                paint.setStrokeWidth(0);
                canvas.drawText(String.valueOf((k+"")), i * mScaleMargin, mHeight - iScaleHeight -10, paint);
                k += 10000;
            } else if (i % 5 == 0){
                paint.setStrokeWidth(paintLinewidth);
                canvas.drawLine(i * mScaleMargin, mHeight, i * mScaleMargin, mHeight - iScaleHeight+10, paint);
            } else {
                paint.setStrokeWidth(paintLinewidth);
                canvas.drawLine(i * mScaleMargin, mHeight, i * mScaleMargin, mHeight - mScaleHeight, paint);
            }
        }
    }

    // 画底部线
    private void onDrawLine(Canvas canvas) {
        paint.setColor(Color.parseColor("#e77a48"));
        paint.setStrokeWidth(paintLinewidth);
        canvas.drawLine(-mWith, mHeight, allWidth+mWith, mHeight, paint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mScroller != null && !mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mLastX = x;
                return true;
            case MotionEvent.ACTION_MOVE:
                int dataX = mLastX - x;
                if (mCountScale - direction < 0) { //向右边滑动
                    if (mCountScale <= mMin && dataX <= 0) //禁止继续向右滑动
                        return super.onTouchEvent(event);
                } else if (mCountScale - direction > 0) { //向左边滑动
                    if (mCountScale >= mMax && dataX >= 0) //禁止继续向左滑动
                        return super.onTouchEvent(event);
                }
                ScrollBy(dataX, 0);
                mLastX = x;
                postInvalidate();
                direction = mCountScale;
                return true;
            case MotionEvent.ACTION_UP:
                if (mCountScale < mMin) mCountScale = mMin;
                if (mCountScale > mMax) mCountScale = mMax;
                int finalX = (mCountScale - mMidScale) * mScaleMargin;
                mScroller.setFinalX(finalX); //纠正指针位置
                postInvalidate();
                return true;
        }
        return super.onTouchEvent(event);
    }

    // 重新绘制
    public  void  setchange(){
        invalidate();
    }


    /**
     * 使用Scroller时需重写
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        // 判断Scroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // 通过重绘来不断调用computeScroll
            invalidate();
        }
    }

    public void ScrollBy(int dx, int dy) {
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
    }
    /**
     * 设置回调监听
     */
    public void setOnScrollListener(OnScrollListener listener) {
        this.mScrollListener = listener;
    }
}