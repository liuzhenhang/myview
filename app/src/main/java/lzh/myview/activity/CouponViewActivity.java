package lzh.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import lzh.myview.R;
import lzh.myview.view.MyCouponView;

public class CouponViewActivity extends AppCompatActivity {

    MyCouponView mcv;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_view);
        mcv = findViewById(R.id.mcv);
        bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mcv.setColor("111111");
            }
        });
    }

}
