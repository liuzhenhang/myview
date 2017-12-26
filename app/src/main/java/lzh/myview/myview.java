package lzh.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
/**
 * Created by Administrator on 2017/6/7 0007.
 */
public class myview extends View {
    // 颜色表
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    // 饼状图初始绘制角度
    private float mStartAngle = 0;
    // 数据
    private ArrayList<Date > mData = new ArrayList<>();
    // 宽高
    private int mWidth, mHeight;
    // 画笔
    private Paint mPaint = new Paint();
    private  Paint wpaint = new Paint();


    public myview(Context context) {
        super(context);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

    }

    public myview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    public myview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public myview(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        wpaint.setStyle(Paint.Style.STROKE);
        wpaint.setStrokeWidth(30);
        if (null == mData)
            return;
            float currentStartAngle = mStartAngle; // 当前起始角度
            canvas.translate(mWidth / 2, mHeight / 2);  // 将画布坐标原点移动到中心位置
            float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);  // 饼状图半径
            RectF rect = new RectF(-r, -r, r, r);                     // 饼状图绘制区域
            for (int i = 0;i<mData.size();i++){
                Date date = mData.get(i);
                wpaint.setColor(date.getColor());
                canvas.drawArc(rect, currentStartAngle, date.getAngle(), true, wpaint);
                currentStartAngle += date.getAngle();
            }
    }

    public void setDate(ArrayList date){
        this.mData  = date;
        initDate(mData);
        invalidate();   // 刷新
    }
    // 设置起始角度
    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();   // 刷新
    }
    private void initDate(ArrayList<Date> mData) {
        if (null == mData || mData.size() == 0)   // 数据有问题 直接返回
            return;

        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            Date pie = mData.get(i);

            sumValue += pie.getValue();       //计算数值和

            int j = i % mColors.length;       //设置颜色
            pie.setColor(mColors[j]);
        }

        float sumAngle = 0;
        for (int i = 0; i < mData.size(); i++) {
            Date pie = mData.get(i);

            float percentage = pie.getValue() / sumValue;   // 百分比
            float angle = percentage * 360;                 // 对应的角度

            pie.setPercentage(percentage);                  // 记录百分比
            pie.setAngle(angle);                            // 记录角度大小
            sumAngle += angle;
        }

    }
}
