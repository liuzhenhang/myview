package lzh.myview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.AnimRes;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import lzh.myview.R;

/**
 * Created by lzh on 2018-04-02.
 */

public class MyNotificationView extends ViewFlipper {
    private int interval = 3000;
    private boolean singleLine = true;
    private int textSize = 20;
    private int TimetextSize = 20;
    private int textColor = Color.BLUE;
    private int TimetextColor = Color.RED;
    private int animDuration = 1000;
    private boolean hasSetAnimDuration = false;
    @AnimRes
    private int inAnimResId = R.anim.anim_notificationview_bottom_in;
    @AnimRes
    private int outAnimResId = R.anim.anim_notificationview_top_out;

    private List<String> notices = new ArrayList<>();
    private List<String> noticestime = new ArrayList<>();
    private int position;
    private OnItemClickListener onItemClickListener;


    public MyNotificationView(Context context) {
        super(context);
    }

    public MyNotificationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyNotificationView);
        interval = typedArray.getInteger(R.styleable.MyNotificationView_mvInterval, interval);
        singleLine = typedArray.getBoolean(R.styleable.MyNotificationView_mvSingleLine, false);
        if (typedArray.hasValue(R.styleable.MyNotificationView_mvTextSize)) {
            textSize = (int) typedArray.getDimension(R.styleable.MyNotificationView_mvTextSize, textSize);
            textSize = px2sp(context, textSize);
        }
        if (typedArray.hasValue(R.styleable.MyNotificationView_mvTimeTextSize)) {
            TimetextSize = (int) typedArray.getDimension(R.styleable.MyNotificationView_mvTimeTextSize, textSize);
            TimetextSize = px2sp(context, TimetextSize);
        }
        textColor = typedArray.getColor(R.styleable.MyNotificationView_mvTextColor, textColor);
        TimetextColor = typedArray.getColor(R.styleable.MyNotificationView_mvTimeTextColor, TimetextColor);
        typedArray.recycle();
        setFlipInterval(interval);
    }
    // 将px值转换为sp值
    private   int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);

    }

    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public int getPosition() {
        return (int) getCurrentView().getTag();
    }
    public void setNotices(List<String> notices) {
        this.notices = notices;
    }
    public void setNoticesTime(List<String> mnoticestime) {
        this.noticestime = mnoticestime;
    }

    public void setList(List<String> notices, List<String> noticestime) {
        if (isEmpty(notices)){
            addView(createLayout("",""));
        }else {
            setNotices(notices);
            setNoticesTime(noticestime);
           StartAnimation(inAnimResId, outAnimResId);
        }
    }

    private void StartAnimation(final int inAnimResId, final int outAnimResId) {
        post(new Runnable() {
            @Override
            public void run() {
                start(inAnimResId, outAnimResId);
            }
        });
    }
    private boolean isAnimStart = false;

    private void start(final @AnimRes int inAnimResId, final @AnimRes int outAnimResID) {
        removeAllViews();
        clearAnimation();
        position = 0;
        addView(createLayout(notices.get(position),noticestime.get(position)));
        if (notices.size() > 1) {
            setInAndOutAnimation(inAnimResId, outAnimResID);
            startFlipping();
        }

        if (getInAnimation() != null) {
            getInAnimation().setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (isAnimStart) {
                        animation.cancel();
                    }
                    isAnimStart = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    position++;
                    if (position >= notices.size()) {
                        position = 0;
                    }
                    View view = createLayout(notices.get(position),noticestime.get(position));
                    if (view.getParent() == null) {
                        addView(view);
                    }
                    isAnimStart = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
    }


    private View createLayout(CharSequence text, CharSequence texttime) {
        RelativeLayout relativeLayout = (RelativeLayout) getChildAt((getDisplayedChild() + 1) % 3);
        TextView textView = null;
        TextView textView02 = null;
        if (relativeLayout == null) {
            relativeLayout = new RelativeLayout(getContext());
            relativeLayout.setGravity(RelativeLayout.CENTER_IN_PARENT);
        }else {
            if (relativeLayout.getChildCount()>0)  relativeLayout.removeAllViews();
            relativeLayout.setGravity(RelativeLayout.CENTER_IN_PARENT);
        }
        RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams mLayoutParams02 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        textView = new TextView(getContext());
        textView02 = new TextView(getContext());
        textView.setTextColor(textColor);
        textView.setTextSize(textSize);
        textView.setSingleLine(singleLine);
        textView02.setTextColor(TimetextColor);
        textView02.setTextSize(TimetextSize);
        textView02.setSingleLine(singleLine);
        textView.setMaxEms(16);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        if (TextUtils.isEmpty(text)) {
            textView.setText("钱美美欢迎您!!!");
        }else {
            textView.setText(text);
        }
        if (!TextUtils.isEmpty(texttime)) textView02.setText(texttime);
        relativeLayout.setTag(position);
        mLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        relativeLayout.addView(textView,mLayoutParams);
        mLayoutParams02.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mLayoutParams02.addRule(RelativeLayout.CENTER_VERTICAL);
        relativeLayout.addView(textView02,mLayoutParams02);
        relativeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(getPosition());
                }
            }
        });
        return relativeLayout;

    }
    /**
     * 设置进入动画和离开动画
     *
     * @param inAnimResId  进入动画的resID
     * @param outAnimResID 离开动画的resID
     */
    private void setInAndOutAnimation(@AnimRes int inAnimResId, @AnimRes int outAnimResID) {
        Animation inAnim = AnimationUtils.loadAnimation(getContext(), inAnimResId);
        inAnim.setDuration(animDuration);
        setInAnimation(inAnim);

        Animation outAnim = AnimationUtils.loadAnimation(getContext(), outAnimResID);
        outAnim.setDuration(animDuration);
        setOutAnimation(outAnim);
    }
}
