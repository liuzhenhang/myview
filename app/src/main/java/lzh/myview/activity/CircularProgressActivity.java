package lzh.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lzh.myview.R;
import lzh.myview.view.MyCircularProgressView;

public class CircularProgressActivity extends AppCompatActivity {

    @BindView(R.id.circle_progress_view)
    MyCircularProgressView circleProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_progress);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.bt)
    public void onViewClicked() {
        int progress = (int) (Math.random() * 100);
        circleProgressView.setProgress(progress);
    }
}
