package lzh.myview.listview;

import android.content.Context;
import android.util.AttributeSet;



/**
 * 自定义下拉刷新
 */
public class PullToRefreshListView extends PullToRefreshLayout {
    private Context context;

    public PullToRefreshListView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public PullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        init();
    }

    public PullToRefreshListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    @Override
    public void init() {
        android.widget.ListView listView = new android.widget.ListView(context);
        listView.setDividerHeight(0);
        listView.setVerticalScrollBarEnabled(true);
        setContentView(listView);
        super.init();
    }

    public android.widget.ListView getListView() {
        return (android.widget.ListView) contentView;
    }


}
