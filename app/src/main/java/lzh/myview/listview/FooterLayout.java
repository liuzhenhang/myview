package lzh.myview.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import lzh.myview.R;


/**
 * 下拉刷新，上拉加载更多的脚布局
 * Created by neal on 2014/12/23.
 */
public class FooterLayout extends FrameLayout {

    private Context context;
    public LinearLayout llLoadMore;
    public TextView tvRichBottom;

    public FooterLayout(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public FooterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public FooterLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_footer_view,
            this);
        llLoadMore = (LinearLayout) view.findViewById(R.id.ll_load_more);
        tvRichBottom = (TextView) view.findViewById(R.id.tv_rich_bttom);
    }
}
