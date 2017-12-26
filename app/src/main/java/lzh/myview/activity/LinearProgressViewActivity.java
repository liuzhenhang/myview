package lzh.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lzh.myview.R;
import lzh.myview.view.MyLinearProgressView;

public class LinearProgressViewActivity extends AppCompatActivity {


    @BindView(R.id.mlpv)
    MyLinearProgressView mlpv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_progress_view);
        ButterKnife.bind(this);
        mlpv.setProgress(50);
    }

    @OnClick(R.id.bt)
    public void onViewClicked() {
        mlpv.setProgress(80);
        mlpv.setType(2);
    }
}
