package lzh.myview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.ButterKnife;
import butterknife.OnClick;
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
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
//        SophixManager.getInstance().queryAndLoadNewPatch();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SophixStubApplication.isIs()) Toast.makeText(getApplicationContext(),"补丁下载成功请重新启动", Toast.LENGTH_LONG).show();
    }

    @OnClick({R.id.bt_good,R.id.slideconflict,R.id.Auditprogress, R.id.CircularProgressView, R.id.MyCouponView, R.id.MyEditText, R.id.MyLinearProgressView,
            R.id.ScaleView, R.id.ScrollerView, R.id.MyScheduleView,R.id.SelectView,R.id.bt_notification})
    public void onViewClicked(View view) {
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

    private void setIntent(Class clas){
        Intent intent = new Intent(this,clas);
        startActivity(intent);
    }

}
