package lzh.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import lzh.myview.R;
import lzh.myview.view.ScaleView;

public class ScaleViewActivity extends AppCompatActivity {

    TextView tv;
    private ScaleView scaleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_view);
        tv = findViewById(R.id.tv);
        scaleView = (ScaleView) findViewById(R.id.scaleview);
        scaleView.setchange();
        scaleView.setOnScrollListener(new ScaleView.OnScrollListener() {
            @Override
            public void onScaleScroll(int scale) {
                tv.setText("" + scale);
            }
        });
    }
}
