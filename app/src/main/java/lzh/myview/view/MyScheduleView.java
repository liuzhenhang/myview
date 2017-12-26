package lzh.myview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import lzh.myview.R;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * Created by Administrator on 2017/8/2 0002.
 */

public class MyScheduleView extends View {
    private  Context mcontext;
    //进度个数
    private int ScheduleCount = 3;
    // 圆的颜色
    private int circleColor;
    // 线的颜色
    private int lineColor = Color.BLACK;
   // 当前进度
    private  int progress = 1;

    /* 测量文本宽高的矩形 */
    private Rect mTextRect;

    /* 测量title文本宽高的矩形 */
    private Rect tTextRect;

    //用来内容文本的矩形
    private Rect cTextRect;


   private  String  texttitle [] ={"天使投资人","至尊VIP","黑金VIP","联合创始人"};
    // 标签text 画笔
    private Paint ttPaint;

    /* 文本画笔 */
    private Paint mTextPaint;
    // 三角形画笔
    private Paint tipPaint;
  //  文本大小
    private float textsize ;
    //  标签文本大小
    private float ttextsize ;


 // 矩形画笔
    private Paint RECpaint;
  // 矩形
    private RectF recRect= new RectF();
    /**
     * 画三角形的path
     */
    private Path path = new Path();
    /**
     * 三角形的高
     */
    private int triangleHeight;
    /*
     * view的高度
     */
    private int height;
    private int width;
    // 圆的半径
    private float circlewidth= 10;
    //线的高度
    private float linehight = 10;
    /* 线画笔 */
    private Paint mLinePaint;
    /* 圆画笔 */
    private Paint CirclePaint;
    /**
     * 第一个圆开始绘制的圆心坐标
     */
    private float startX;
    private float startY;

    private String  textcont[] ={"累计年化投资额达10万，邀约3人以上，即晋级为天使投资人","累计年化投资额达30万，邀约3人以上，即晋级为至尊VIP","累计年化投资额达60万，邀约3人以上，即晋级为黑金VIP","累计年化投资额达90万，邀约3人以上，即晋级为联合创始人","通关。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。"};

    public MyScheduleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;
        getAtt(attrs);
        initPaint();
    }
    private void getAtt(AttributeSet attrs) {
        TypedArray typedArray = mcontext.obtainStyledAttributes(attrs, R.styleable.MyScheduleView);
        ScheduleCount = typedArray.getInt(R.styleable.MyScheduleView_ScheduleCount, ScheduleCount);
        circleColor = typedArray.getColor(R.styleable.MyScheduleView_scircleColor,  Color.parseColor("#df0781"));
        lineColor =typedArray.getColor(R.styleable.MyScheduleView_lineColor, lineColor);
        circlewidth = typedArray.getDimension(R.styleable.MyScheduleView_circleWidth,dp2px(10));
        linehight = typedArray.getDimension(R.styleable.MyScheduleView_linehight,linehight);
        textsize = typedArray.getDimension(R.styleable.MyScheduleView_msvTextsize,textsize);
        ttextsize = typedArray.getDimension(R.styleable.MyScheduleView_msvTTextsize,ttextsize);
        typedArray.recycle();
        mTextRect = new Rect();
        tTextRect = new Rect();
        cTextRect = new Rect();
    }
    /**
     * 初始化画笔
     */
    private void initPaint() {
        mLinePaint = new Paint();
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setColor(circleColor);
        mLinePaint.setAntiAlias(true);

        ttPaint = new Paint();
        ttPaint.setStyle(Paint.Style.FILL);
        ttPaint.setColor(Color.BLACK);
        ttPaint.setTextSize(ttextsize);
        ttPaint.setAntiAlias(true);

        CirclePaint = new Paint();
        CirclePaint.setStyle(Paint.Style.FILL);
        CirclePaint.setColor(circleColor);
        CirclePaint.setAntiAlias(true);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(textsize);
        mTextPaint.setAntiAlias(true);

        RECpaint = new Paint();
        RECpaint.setStyle(Paint.Style.FILL);
        RECpaint.setColor(Color.BLUE);
        CirclePaint.setAntiAlias(true);

        tipPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        tipPaint.setColor(Color.BLUE);
        tipPaint.setAntiAlias(true);
        tipPaint.setStrokeCap(Paint.Cap.ROUND);
        tipPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 设置画笔
     * @param style       画笔风格
     * @param color       画笔颜色
     * @return
     */
    private Paint getPaint( Paint.Style style, int color) {
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setStyle(style);
        paint.setColor(color);
        paint.setAntiAlias(true);
        return paint;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        startX = w / ScheduleCount / 2;
        startY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(canvas);
        drawCircle(canvas);
        drawTriangle(canvas);
        drawText(canvas);
        drawTitle(canvas);
    }

    private void drawTitle(Canvas canvas) {
        for (int i = 0; i < ScheduleCount; i++) {
            if (i<=progress-1){
                ttPaint.setColor(circleColor);
            }else {
                ttPaint.setColor(Color.BLACK);;
            }
          //  canvas.drawCircle(startX + i * 2 * startX, startY, circlewidth, CirclePaint);
            ttPaint.getTextBounds(texttitle[i], 0, texttitle[i].length(), tTextRect);
            float TextWidth = tTextRect.width();
            float TextHeight = tTextRect.height();
            canvas.drawText(texttitle[i],startX + i * 2 * startX-TextWidth/2, (float) (getHeight()/ 2 - TextHeight), ttPaint);
        }
    }

    /**
     * 画实心圆
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        for (int i = 0; i < ScheduleCount; i++) {
          if (i<=progress-1){
              CirclePaint.setColor(circleColor);
          }else {
              CirclePaint.setColor(Color.BLACK);;
          }
            canvas.drawCircle(startX + i * 2 * startX, startY, circlewidth, CirclePaint);
        }
    }
    // 画线
    private void drawLine(Canvas canvas) {
        for (int i = 0; i < ScheduleCount-1; i++) {
            if (i<=progress-2){
                mLinePaint.setColor(lineColor);
            }else {
                mLinePaint.setColor(Color.BLACK);;
            }
            canvas.drawLine(startX + i * 2 * startX,height/2,startX + (i+1) * 2 * startX,height/2,mLinePaint);
        }
    }
  // 绘制内容文本
    private void drawText(Canvas canvas) {
        mTextPaint.getTextBounds(textcont[progress], 0, textcont[progress].length(), mTextRect);
        float TextWidth = mTextRect.width();
        float TextHeight = mTextRect.height();
        cTextRect.set((int)(startX - (2 * circlewidth)),(int)((height / 2) + 2 * circlewidth),(int)(startX+TextWidth ), (int)((height / 2) + (5 * circlewidth)));
        // 绘制矩形
      canvas.drawRect(cTextRect, RECpaint);

        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        // 获取baseLine
        int baseline = cTextRect.top + (cTextRect.bottom - cTextRect.top - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(textcont[progress],cTextRect.centerX(),baseline, mTextPaint);

    }


    /**
     * 绘制三角形
     *
     * @param canvas
     */
    private void drawTriangle(Canvas canvas) {
        for (int i = 0; i < ScheduleCount; i++) {
            if (i == progress-1){
                path.moveTo(startX + i * 2 * startX, (float) (height / 2+1.5*circlewidth));
                path.lineTo((float) (startX + i * 2 * startX-0.5*circlewidth),  height / 2+2*circlewidth);
                path.lineTo((float) (startX + i * 2 * startX+0.5*circlewidth), height / 2+2*circlewidth);
                canvas.drawPath(path, tipPaint);
                path.reset();
            }
        }
    }

    public void setProgress(int mprogress) {
        progress = mprogress;
        invalidate();
    }


    /**
     * dp 2 px
     *
     * @param dpVal
     */
    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }
}
