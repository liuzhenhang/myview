package lzh.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import lzh.myview.R;
import lzh.myview.view.ScrollerView;

public class ScrollerViewActivity extends AppCompatActivity {
    ScrollerView slv;
    LinearLayout llPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller_view);
        slv = findViewById(R.id.slv);
        llPoint = findViewById(R.id.ll_point);
        slv.setCount(4);
        LinearLayout.LayoutParams  lparams = new LinearLayout.LayoutParams(20,20);
        lparams.leftMargin = 10;
        for (int i= 0 ;i< 4;i++){
            TextView textView = new TextView(this);
            textView.setLayoutParams(lparams);
            textView.setBackgroundResource(R.drawable.point);
            llPoint.addView(textView);
        }
        llPoint.getChildAt(0).setSelected(true);

        slv.setListener(new ScrollerView.OnPayListener() {
            @Override
            public void onGetPoint(int pas) {
                for (int i = 0; i<4;i++){
                    if (i==pas){
                        llPoint.getChildAt(pas).setSelected(true);
                    }else {
                        llPoint.getChildAt(i).setSelected(false);
                    }
                }
            }
        });
    }
}
