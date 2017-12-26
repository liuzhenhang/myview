package lzh.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lzh.myview.R;
import lzh.myview.view.MyScheduleView;

public class MyScheduleActivity extends AppCompatActivity {

    @BindView(R.id.msv)
    MyScheduleView msv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_schedule);
        ButterKnife.bind(this);
        msv.setProgress(1);
    }

    @OnClick(R.id.bt)
    public void onViewClicked() {
        msv.setProgress((int) (Math.random() * 5));
    }
}
