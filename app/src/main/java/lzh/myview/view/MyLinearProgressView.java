package lzh.myview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.VectorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import lzh.myview.R;

import static android.R.attr.factor;
import static android.R.attr.startX;

/**
 * Created by Administrator on 2017/8/24 0024.
 */

public class MyLinearProgressView extends View{
    private Context mcontext ;
    private Paint  paint ; // 初始画线笔
    private Paint mPaint; // 进度画线笔

    private Paint recPaint;// 矩形画笔
    private Paint recPaint1;// 矩形画笔
    private Paint recPaint2;// 矩形画笔


    private float paintWidth ; // 画笔宽度
    private float bgPaintSize ; // 画笔宽度
    private  int    paintColor; // 画笔颜色
    private  float textSize ; // 百分比text的大小
    private Paint tPaint; // 不带矩形的Text画笔
    private Paint ttPaint; // 带矩形的Text画笔


    /* 渐变颜色数组 */
    private int[] mColorArray;
    /* 渐变起始颜色 */
    private int mStartColor;
    /* 渐变终止颜色 */
    private int mEndColor;
    // 当前进度
    private  float progress = 1;
    /*
   * view的高度
   */
    private int height;
    private int width;
    /* 测量文本宽高的矩形 */
    private Rect tTextRect;

    /* 文本的矩形 */
    private RectF tRect;
    private Rect pRect;

   private ImageView imageView;

        private float pading = 100f; // 左右边距
    // 线的X起始左标
    private float startX;
    private float endX;

    private  int type ; // 那种风格
    /**
     * 画三角形的path
     */
    private Path path = new Path();
    private float lenth;

    public MyLinearProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;
        getAtt(attrs);
        initPaint();
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    private void getAtt(AttributeSet attrs) {
        TypedArray typedArray = mcontext.obtainStyledAttributes(attrs, R.styleable.MyLinearProgressView);
        paintColor = typedArray.getColor(R.styleable.MyLinearProgressView_lp_paintColor,  Color.parseColor("#df0781"));
        paintWidth = typedArray.getDimension(R.styleable.MyLinearProgressView_lp_paintWidth,dp2px(10));
        bgPaintSize = typedArray.getDimension(R.styleable.MyLinearProgressView_lp_bgPaintSize,dp2px(10));
        textSize = typedArray.getDimension(R.styleable.MyLinearProgressView_lp_textSize,30f);
        type = typedArray.getInt(R.styleable.MyLinearProgressView_lp_type,1);
        mStartColor = typedArray.getColor(R.styleable.MyLinearProgressView_lp_startColor, Color.parseColor("#3FC199"));
        mEndColor = typedArray.getColor(R.styleable.MyLinearProgressView_lp_endColor, Color.parseColor("#000000"));
        mColorArray = new int[]{mStartColor, mEndColor};
        typedArray.recycle();
        tTextRect = new Rect();
        tRect  = new RectF();
        pRect = new Rect();
    }
    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor( Color.parseColor("#ffffff"));
        paint.setStrokeWidth(bgPaintSize/5);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        //mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(paintWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);

        tPaint = new Paint();
        tPaint.setStyle(Paint.Style.FILL);
        tPaint.setColor(Color.RED);
        tPaint.setStrokeWidth(paintWidth);
        tPaint.setTextSize(textSize);
        tPaint.setAntiAlias(true);

        ttPaint = new Paint();
        ttPaint.setStyle(Paint.Style.FILL);
        ttPaint.setColor(Color.WHITE);
        ttPaint.setStrokeWidth(paintWidth);
        ttPaint.setTextSize(textSize);
        ttPaint.setAntiAlias(true);

        recPaint = new Paint();
        recPaint.setStyle(Paint.Style.FILL);
        recPaint.setColor(Color.parseColor("#0c8cf5"));

        recPaint.setStrokeWidth(3);
        recPaint.setAntiAlias(true);
        recPaint1 = new Paint();
        recPaint1.setStyle(Paint.Style.STROKE);
        recPaint1.setColor(Color.parseColor("#ffffff"));
        recPaint1.setStrokeWidth(1);
        recPaint1.setAntiAlias(true);
        recPaint2 = new Paint();
        recPaint2.setStyle(Paint.Style.FILL);
        recPaint2.setColor(Color.parseColor("#ffffff"));
        recPaint2.setAntiAlias(true);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
//        startX = paintWidth+pading;
//        endX = width -pading;
        String text =(int) progress+"%";
        ttPaint.getTextBounds(text, 0, text.length(), tTextRect);
        float TextWidth = tTextRect.width();
        startX = paintWidth+TextWidth+10;
        endX = width -TextWidth-10;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置渐变渲染
        LinearGradient linearGradient = new LinearGradient(0, getHeight(), getWidth() , getHeight(), mColorArray, null, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        // 根据 progress进度 ，获得X坐标
         lenth = (endX*progress/100)+startX-(progress/100*(startX));
        // 底部背景线
       canvas.drawLine(startX,height/2+2,endX,height/2+2,paint);
        // 画 进度线
       drawLine(canvas);
        if (type==1){
            // 不带矩形的进度
            drawText(canvas);
        }else if (type==2){
            // 画带三角形的矩形进度条
           drawRecText(canvas);

        }
    }


    private void drawRecText(Canvas canvas) {
        String text =(int) progress+"%";
        ttPaint.getTextBounds(text, 0, text.length(), tTextRect);
        float TextHeight = tTextRect.height();
        float TextWidth = tTextRect.width();

        tRect.set((float) (lenth-TextWidth/2-8f),(float)(height/2-2*TextHeight),(float)(lenth+TextWidth/2+8f),(float)(height/2-0.6*TextHeight));
//        pRect.set((int) (lenth-TextWidth/2-8f),(int)(height/2-2*TextHeight),(int)(lenth+TextWidth/2+8f),(int)(height/2-0.2*TextHeight));


        // 画矩形
//        canvas.drawRect(tRect,recPaint);
//        canvas.drawRoundRect(tRect,6,6,recPaint);
        canvas.drawRoundRect(tRect,6,6,recPaint1);
//        Bitmap bitmap = BitmapFactory.decodeResource(mcontext.getResources(),R.mipmap.con_img_float);
//        // 指定图片绘制区域(左上角的四分之一)
//        Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//// 绘制图片
//        canvas.drawBitmap(bitmap, src, pRect, null);
//        //资源文件(drawable/mipmap/raw)中获取
        Paint.FontMetricsInt fontMetrics = ttPaint.getFontMetricsInt();
        // 获取baseLine
        int baseline = (int) (tRect.top + (tRect.bottom - tRect.top - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top);
        ttPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text,tRect.centerX(),baseline, ttPaint);

        // 画三角形
        path.moveTo(lenth, (float) (height/2-0.3*TextHeight));
        path.lineTo((float) (lenth+0.13*TextWidth), (float) (height / 2-0.6*TextHeight));
        path.lineTo((float) (lenth-0.13*TextWidth), (float) (height / 2-0.6*TextHeight));
        path.close();
        canvas.drawPath(path, recPaint1);
        path.reset();
        canvas.drawLine((float) (lenth+0.10*TextWidth),(float) (height / 2-0.6*TextHeight),(float) (lenth-0.10*TextWidth),(float) (height / 2-0.6*TextHeight),recPaint);
    }

    private void drawText(Canvas canvas) {

        String text =(int) progress+"%";
        tPaint.getTextBounds(text, 0, text.length(), tTextRect);
        float TextHeight = tTextRect.height();
        canvas.drawText(text,lenth+6f,height/2+TextHeight/2, tPaint);

    }

    private void drawLine(Canvas canvas) {
        canvas.drawLine(startX,height/2,lenth,height/2,mPaint);
    }
   // 设置进度
    public void setProgress(int mprogress) {
        ValueAnimator anim = ValueAnimator.ofFloat(0, mprogress);
        anim.setDuration(3000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.start();
    }
  //  设置类型
    public void  setType(int mtype){
        type = mtype;
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