package lzh.myview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import lzh.myview.R;
import lzh.myview.listview.Myadapter;
import lzh.myview.listview.PullToRefreshLayout;
import lzh.myview.listview.PullToRefreshListView;

public class MylistviewActivity extends AppCompatActivity implements   PullToRefreshLayout.LoadDataListener, AdapterView.OnItemClickListener {
 private PullToRefreshListView prv;
    private List<String> list = new ArrayList<>();
    private ListView listView ;
    private Myadapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylistview);
        prv = (PullToRefreshListView) findViewById(R.id.prv);
        listView = prv.getListView();
        prv.setLoadDataListener(this);
        prv.onLoadComplete(true, true);
        init();

    }

    private void init() {
        for (int i = 0;i<10;i++){
            list.add("add"+i);

        }
        myadapter =new Myadapter(this,list);
        listView.setAdapter(myadapter);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
