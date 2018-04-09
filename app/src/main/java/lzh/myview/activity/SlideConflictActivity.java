package lzh.myview.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import lzh.myview.R;

public class SlideConflictActivity extends AppCompatActivity {
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<View> vpList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_conflict);
        ButterKnife.bind(this);
        LayoutInflater layoutInflater = getLayoutInflater().from(this);
        @SuppressLint("InflateParams")
        View vp01 = layoutInflater.inflate(R.layout.item_load01, null);
        @SuppressLint("InflateParams")
        View vp02 = layoutInflater.inflate(R.layout.item_load02, null);
        @SuppressLint("InflateParams")
        View vp03 = layoutInflater.inflate(R.layout.item_load03, null);
        vpList.add(vp01);
        vpList.add(vp02);
        vpList.add(vp03);
        if (0 != vpList.size()) vp.setAdapter(new MyPagerAdapter());
    }

    /**
     * viewpager的数据适配器
     */
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return vpList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = vpList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(vpList.get(position));
        }
    }

}
