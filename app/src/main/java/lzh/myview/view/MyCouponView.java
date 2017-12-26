package lzh.myview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import lzh.myview.R;

/**
 * Created by LZH on 2017/11/6.
 */

public class MyCouponView extends View {
    /*
   * view的高度
   */
    private int height;
    private int width;
    private Context mcontext ;
    private Paint recPaint;// 矩形画笔
    //半圆画笔
    private Paint semicirclePaint;
    //半圆画笔
    private Paint semicirclePaint2;

    //半圆半径
    private float semicircleRadius = 20;

    //虚线画笔
    private Paint dashLinePaint;
//虚线数量
    private int linenumber = 20;
    // 虚线间距
    private int linegap = 10;

    private int paintColor; // 画笔颜色


    public MyCouponView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;
        getAtt(attrs);
        initPaint();

    }

    private void getAtt(AttributeSet attrs) {
        TypedArray typedArray = mcontext.obtainStyledAttributes(attrs, R.styleable.MyCouponView);
        paintColor =typedArray.getColor(R.styleable.MyCouponView_mcv_paintColor,  Color.parseColor("#FFE6481D"));
    }

    private void initPaint() {
        recPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        recPaint.setStyle(Paint.Style.STROKE);
        recPaint.setColor(paintColor);
        recPaint.setAntiAlias(true);

        semicirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        semicirclePaint.setDither(true);
        semicirclePaint.setColor( Color.parseColor("#ffffff"));
        semicirclePaint.setStyle(Paint.Style.FILL);

        semicirclePaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        semicirclePaint2.setDither(true);
        semicirclePaint2.setColor(paintColor);
        semicirclePaint2.setStyle(Paint.Style.STROKE);


        dashLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dashLinePaint.setDither(true);
        dashLinePaint.setColor( paintColor);
        dashLinePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画圆角矩形(RectF)
        canvas.drawRoundRect(0, 1, width, height-1, 10, 10, recPaint);

        // 画虚线.
        for (int i = 0; i < linenumber; i++) {
            canvas.drawLine(width/3,20*i,width/3,20*i+10,dashLinePaint);
        }
        // 画半圆
        canvas.drawCircle(width/3, 0, semicircleRadius, semicirclePaint);
        canvas.drawCircle(width/3, 0, semicircleRadius, semicirclePaint2);
        canvas.drawCircle(width/3, height, semicircleRadius, semicirclePaint);
        canvas.drawCircle(width/3, height, semicircleRadius, semicirclePaint2);

    }
    public void setColor(String color){
        recPaint.setColor(Color.parseColor("#"+color));
        semicirclePaint2.setColor(Color.parseColor("#"+color));
        dashLinePaint.setColor(Color.parseColor("#"+color));
        invalidate();
    }
}
