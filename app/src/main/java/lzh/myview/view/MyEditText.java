package lzh.myview.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.EditText;
import lzh.myview.R;
import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * Created by Administrator on 2017/7/20 0020.
 */

public class MyEditText extends EditText {
    private Context mContext;

    private  OnPayListener listener;

   /**
    * 最大输入位数
    */
   private int maxCount = 6;
    /**
     * 第一个圆开始绘制的圆心坐标
     */
    private float startX;
    private float startY;

    /**
     * 实心圆的半径
     */
    private int radius = 10;
    /**
     * view的高度
     */
    private int height;
    private int width;

    /**
     * 当前输入密码位数
     */
    private int textLength = 0;
    /**
     * 圆的颜色   默认BLACK
     */
    private int circleColor = Color.BLACK;

    /**
     * 分割线的颜色
     */
    private int borderColor = Color.GRAY;
    /**
     * 分割线的画笔
     */
    private Paint borderPaint;
    /**
     * 分割线开始的坐标x
     */
    private int divideLineWStartX;

    /**
     * 分割线的宽度  默认2
     */
    private int divideLineWidth = 0;
    /**
     * 竖直分割线的颜色
     */
    private int divideLineColor = Color.GRAY;
    private RectF rectF = new RectF();


    /**
     * 矩形边框的圆角
     */
    private int rectAngle = 0;
    /**
     * 竖直分割线的画笔
     */
    private Paint divideLinePaint;
    /**
     * 圆的画笔
     */
    private Paint circlePaint;


    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        getAtt(attrs);
        initPaint();
        this.setCursorVisible(false);
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxCount)});
    }

    private void getAtt(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.MyEditText);
        maxCount = typedArray.getInt(R.styleable.MyEditText_maxCount, maxCount);
        circleColor = typedArray.getColor(R.styleable.MyEditText_circleColor, circleColor);
        radius = typedArray.getDimensionPixelOffset(R.styleable.MyEditText_radius, radius);
        divideLineWidth = typedArray.getDimensionPixelSize(R.styleable.MyEditText_divideLineWidth, divideLineWidth);
        divideLineColor = typedArray.getColor(R.styleable.MyEditText_divideLineColor, divideLineColor);
        rectAngle = typedArray.getDimensionPixelOffset(R.styleable.MyEditText_rectAngle, rectAngle);
        typedArray.recycle();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {

        circlePaint = getPaint(5, Paint.Style.FILL, circleColor);

        borderPaint = getPaint(3, Paint.Style.STROKE, borderColor);

   divideLinePaint = getPaint(divideLineWidth, Paint.Style.STROKE,R.color.line );

    }
    /**
     * 设置画笔
     *
     * @param strokeWidth 画笔宽度
     * @param style       画笔风格
     * @param color       画笔颜色
     * @return
     */
    private Paint getPaint(int strokeWidth, Paint.Style style, int color) {
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(strokeWidth);
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
        divideLineWStartX = w / maxCount;
        startX = w / maxCount / 2;
        startY = h / 2;

        rectF.set(0, 0, width, height);


    }

    @Override
    protected void onDraw(Canvas canvas) {
       //super.onDraw(canvas);
        drawWeChatBorder(canvas);
        drawPsdCircle(canvas);
    }

    /**
     * 画微信支付密码的样式
     *
     * @param canvas
     */
    private void drawWeChatBorder(Canvas canvas) {

        //canvas.drawRoundRect(rectF, rectAngle, rectAngle, borderPaint);
        for (int i = 0; i < maxCount - 1; i++) {
            canvas.drawLine((i + 1) * divideLineWStartX, 0, (i + 1) * divideLineWStartX, height,
                    divideLinePaint);
        }

    }


    /**
     * 画密码实心圆
     *
     * @param canvas
     */
    private void drawPsdCircle(Canvas canvas) {
        for (int i = 0; i < textLength; i++) {
            canvas.drawCircle(startX + i * 2 * startX,
                    startY,
                    radius,
                    circlePaint);
        }
    }


    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        textLength = text.toString().length();
        if (textLength == maxCount){
        if (null != listener)listener.onGo(getPasswordString());
        }
        invalidate();

    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);

        //保证光标始终在最后
        if (selStart == selEnd) {
            setSelection(getText().length());
        }
    }
    public interface OnPayListener {
        void onGo(String pas);
    }

    public  void  setListener(OnPayListener mlistener){
        this.listener = mlistener;
    }
    /**
     * 获取输入的密码
     */
    public String getPasswordString() {
        return getText().toString().trim();
    }

}
