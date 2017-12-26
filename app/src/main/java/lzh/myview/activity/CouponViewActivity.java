package lzh.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lzh.myview.R;
import lzh.myview.view.MyCouponView;

public class CouponViewActivity extends AppCompatActivity {

    @BindView(R.id.mcv)
    MyCouponView mcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_view);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt)
    public void onViewClicked() {
        mcv.setColor("111111");
    }
}
