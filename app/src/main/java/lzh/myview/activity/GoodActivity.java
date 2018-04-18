package lzh.myview.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

import lzh.myview.R;

import static android.widget.RelativeLayout.ALIGN_PARENT_BOTTOM;
import static android.widget.RelativeLayout.CENTER_HORIZONTAL;
import static android.widget.RelativeLayout.TRUE;

public class GoodActivity extends AppCompatActivity {

    ImageView IV;
    RelativeLayout rl;
    private Random mRandom;
    private int width,hight;
    private RelativeLayout.LayoutParams mParams;
    private PathMeasure mPathMeasure;
    private float[] mCurrentPosition = new float[2];
    Path path = new Path();
    /**
     * 桃心
     */
    private int[] heartRes = new int[]{
            R.drawable.resource_heart0, R.drawable.resource_heart1, R.drawable.resource_heart2,
            R.drawable.resource_heart3, R.drawable.resource_heart3, R.drawable.resource_heart5,
            R.drawable.resource_heart6, R.drawable.resource_heart7, R.drawable.resource_heart8,
            R.drawable.resource_heart9, R.drawable.resource_heart10
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good);
        IV = findViewById(R.id.iv);
        rl = findViewById(R.id.rl);
        mRandom = new Random();
        IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initview();
            }
        });
    }

    private void initview() {
        int startLoc[] = new int[2];
        IV.getLocationInWindow(startLoc);
        int parent[] = new int[2];
        rl.getLocationInWindow(parent);
        final ImageView iv = getHeartView(randomHeartResource());
        rl.addView(iv);
        float startX = startLoc[0];
        float startY = startLoc[1];;
        Log.d("======","xxxxxxxx="+startX+"yyyyyyyyyy="+startY);

        float toX = parent[0] ;
        float toY = parent[1];
        path.moveTo(startX, startY);
        //cubicTo()方法从上一个点为起点开始绘制三阶贝塞尔曲线，其中（x1，y1）,( x2, y2 )为辅助控制点，（x3，y3）为终点。
        //quadTo()方法从上一个点为起点开始绘制贝塞尔曲线，其中（x1，y1）为辅助控制点，（x2，y2）为终点。
        path.cubicTo(-startX/2,-startY/2,-startX/2,0,0,-startY);
//        path.quadTo(startX/2, startY/2, -startX, -startY);

        mPathMeasure = new PathMeasure(path, false);
        Log.d("======","xxxxxxxx="+startX+"yyyyyyyyyy="+startY);
        //属性动画实现
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(3000);
        // 匀速插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                // 获取当前点坐标封装到mCurrentPosition
                mPathMeasure.getPosTan(value, mCurrentPosition, null);
                iv.setTranslationX(mCurrentPosition[0]);
                iv.setTranslationY(mCurrentPosition[1]);
                //透明度 从不透明到完全透明
//                iv.setAlpha(1.0f - animation.getAnimatedFraction() * animation.getAnimatedFraction());
            }
        });
        valueAnimator.start();


        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rl.removeView(iv);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
    /**
     * 获取一个桃心
     * @param resId
     * @return
     */
    private ImageView getHeartView(@DrawableRes int resId){
        mParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mParams.addRule(ALIGN_PARENT_BOTTOM,TRUE);
        mParams.addRule(CENTER_HORIZONTAL,TRUE);
        ImageView iv = new ImageView(getApplicationContext());
        iv.setLayoutParams(mParams);
        iv.setImageResource(resId);
        return iv;
    }
    /**
     * 随机一个桃心
     *
     * @return
     */
    private int randomHeartResource() {
        return heartRes[mRandom.nextInt(heartRes.length)];
    }


}
