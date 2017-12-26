package lzh.myview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import lzh.myview.R;

/**
 * Created by LZH on 2017-12-21.
 */

public class AuditprogressView extends View {
    private Context mcontext;
    private float av_imageWidth; // 图片宽高
    private float av_linehight; // 线的高度
    private int av_lineColor; // 线的颜色
    private float av_Textsize; // 进度文字展示
    private float av_timeTextsize; //时间 文字
    private  int av_Textcolor; // 进度文字的颜色
    private  int av_timeTextcolor; // 时间文字的颜色
    private  int progressCount = 4; // 进度个数
    private Paint paint ; // 画笔
    //  确定绘制的图片
    private Bitmap audit_drawBitmap;
    /*
       * view的高度
       */
    private int height;
    private int width;

    /**
     * 第一个图的坐标
     */
    private float startX;
    private float startY;

    private RectF imageRectF; // 图片绘制的区域


    private ArrayList<String>  textlist ; // 进度文字list

    private ArrayList<String>  ttextlist  ; // 时间文字list

    private int progress = 2; //  当前进度

    private  boolean isProgress;// 当前进度是成功还是失败

    public AuditprogressView(Context context) {
        super(context);
    }

    public AuditprogressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mcontext =context;
        getAtt(attrs);
        initPaint();
    }

    public AuditprogressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void getAtt(AttributeSet attrs) {
        TypedArray typedArray = mcontext.obtainStyledAttributes(attrs, R.styleable.AuditprogressView);
        progressCount = typedArray.getInt(R.styleable.AuditprogressView_av_progressCount, progressCount);
        av_Textcolor = typedArray.getColor(R.styleable.AuditprogressView_av_Textcolor,  Color.parseColor("#df0781"));
        av_lineColor = typedArray.getColor(R.styleable.AuditprogressView_av_lineColor,  Color.parseColor("#df0781"));
        av_timeTextcolor =typedArray.getColor(R.styleable.AuditprogressView_av_timeTextcolor,  Color.parseColor("#df0781"));
        av_imageWidth = typedArray.getDimension(R.styleable.AuditprogressView_av_imageWidth,100);
        av_linehight = typedArray.getDimension(R.styleable.AuditprogressView_av_linehight,10);
        av_Textsize = typedArray.getDimension(R.styleable.AuditprogressView_av_Textsize,10);
        av_timeTextsize = typedArray.getDimension(R.styleable.AuditprogressView_av_timeTextsize,10);
        typedArray.recycle();
    }
    /**
     * 初始化画笔
     */
    private void initPaint() {
        imageRectF = new RectF();
        paint = new Paint();
        // 抗锯齿
        paint.setAntiAlias(true);
//        // 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        paint.setDither(true);
//        // 空心
//        paint.setStyle(Paint.Style.STROKE);
        // 文字居中
        paint.setTextAlign(Paint.Align.CENTER);
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
        startX =av_imageWidth+av_imageWidth/2;
        startY = av_imageWidth+av_imageWidth/2;
        paint.setTextSize(av_Textsize);
        if (ttextlist.size()>0&&textlist.size()>0){
            drawLine(canvas);
            drawImage(canvas);
            drawText(canvas);
            drawTimeText(canvas);
        }


    }
    // 画文本
    private void drawTimeText(Canvas canvas) {
        paint.setColor(av_Textcolor);
        for (int i = 0; i < progressCount; i++) {
            canvas.drawText(ttextlist.get(i),startX + i * (width- 2*(av_imageWidth+av_imageWidth/2))/(progressCount-1),3*startY, paint);
        }
    }

    // 画时间文本
    private void drawText(Canvas canvas) {
        paint.setColor(av_timeTextcolor);
        for (int i = 0; i < progressCount; i++) {
            canvas.drawText(textlist.get(i),startX + i * (width- 2*(av_imageWidth+av_imageWidth/2))/(progressCount-1),2*startY, paint);
        }
    }



    // 画图片
    private void drawImage(Canvas canvas) {
        for (int i = 0; i < progressCount; i++) {
            if (i<=progress-1){
                audit_drawBitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.finsh);
                if (i ==progress-1&& !isProgress){
                    audit_drawBitmap =BitmapFactory.decodeResource(getResources(), R.drawable.defeated);
                }
//
            }else {
                audit_drawBitmap =BitmapFactory.decodeResource(getResources(), R.drawable.no_finsh);
//
            }
            // 绘制图片
//            canvas.drawBitmap(audit_drawBitmap,startX -av_imageWidth/2+i * (width- 2*(av_imageWidth+av_imageWidth/2))/(progressCount-1), startY-av_imageWidth/2, paint);

            imageRectF.set((float)(startX -av_imageWidth/2+i * (width- 2*(av_imageWidth+av_imageWidth/2))/(progressCount-1)),(float)(startY-av_imageWidth/2),(float)(startX +av_imageWidth/2+i * (width- 2*(av_imageWidth+av_imageWidth/2))/(progressCount-1)),(float)(startY+av_imageWidth/2));
            canvas.drawBitmap(audit_drawBitmap, null, imageRectF, null);
        }
    }

    // 画线
    private void drawLine(Canvas canvas) {
        for (int i = 0; i < progressCount-1; i++) {
            if (i<=progress-2){
                paint.setColor(av_lineColor);
            }else {
                paint.setColor(Color.BLACK);;
            }
            canvas.drawLine(2*av_imageWidth/3+startX + i * (width- 2*(av_imageWidth+av_imageWidth/2))/(progressCount-1),startY,startX-2*av_imageWidth/3 + (i+1) * (width- 2*(av_imageWidth+av_imageWidth/2))/(progressCount-1),startY,paint);

        }
    }

    public  void  setDate(ArrayList<String> mtextlist,ArrayList<String> mttextlist ,int mProgress,boolean isprogress){
        textlist = mtextlist;
        ttextlist = mttextlist;
        progress = mProgress;
        isProgress = isprogress;
        invalidate();
    }

}
