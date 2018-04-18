package lzh.myview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lzh.myview.activity.AuditprogressActivity;
import lzh.myview.activity.CircularProgressActivity;
import lzh.myview.activity.CouponViewActivity;
import lzh.myview.activity.EditTextActivity;
import lzh.myview.activity.GoodActivity;
import lzh.myview.activity.LinearProgressViewActivity;
import lzh.myview.activity.MyScheduleActivity;
import lzh.myview.activity.NotificationActivity;
import lzh.myview.activity.ScaleViewActivity;
import lzh.myview.activity.ScrollerViewActivity;
import lzh.myview.activity.SelectActivity;
import lzh.myview.activity.SlideConflictActivity;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   Button bt,bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10,bt11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
//        SophixManager.getInstance().queryAndLoadNewPatch();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        bt =findViewById(R.id.bt_good);
        bt1 =findViewById(R.id.slideconflict);
        bt2 =findViewById(R.id.Auditprogress);
        bt3 =findViewById(R.id.CircularProgressView);
        bt4 =findViewById(R.id.MyCouponView);
        bt5 =findViewById(R.id.MyEditText);
        bt6 =findViewById(R.id.MyLinearProgressView);
        bt7 =findViewById(R.id.ScaleView);
        bt8 =findViewById(R.id.ScrollerView);
        bt9 =findViewById(R.id.MyScheduleView);
        bt10 = findViewById(R.id.SelectView);
        bt11 = findViewById(R.id.bt_notification);
        bt.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        bt10.setOnClickListener(this);
        bt11.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SophixStubApplication.isIs()) Toast.makeText(getApplicationContext(),"补丁下载成功请重新启动", Toast.LENGTH_LONG).show();
    }

    private void setIntent(Class clas){
        Intent intent = new Intent(this,clas);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Auditprogress: // 审核进度view
                setIntent(AuditprogressActivity.class);
                break;
            case R.id.CircularProgressView: // 环形进度条
                setIntent(CircularProgressActivity.class);
                break;
            case R.id.MyCouponView: // 优惠劵view
                setIntent(CouponViewActivity.class);
                break;
            case R.id.MyEditText: // 自定义密码输入框
                setIntent(EditTextActivity.class);
                break;
            case R.id.MyLinearProgressView: // 线性百分比进度view
                setIntent(LinearProgressViewActivity.class) ;
                break;
            case R.id.ScaleView: // 自定义刻度尺
                setIntent(ScaleViewActivity.class);
                break;
            case R.id.ScrollerView: // Scroller仿写简单的Viewpage效果
                setIntent(ScrollerViewActivity.class);
                break;
            case R.id.MyScheduleView: // vip等级 展示view
                setIntent(MyScheduleActivity.class);
                break;
            case R.id.SelectView: //单项选择器
                setIntent(SelectActivity.class);
                break;
            case R.id.slideconflict: //滑动冲突
                setIntent(SlideConflictActivity.class);
                break;
            case R.id.bt_good: // 点赞控件
                setIntent(GoodActivity.class);
            case R.id.bt_notification:
                setIntent(NotificationActivity.class);
                break;
        }
    }
}
