package lzh.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lzh.myview.R;
import lzh.myview.view.MyNotificationView;

public class NotificationActivity extends AppCompatActivity {

    MyNotificationView mnv;
    private List<String> listStr = new ArrayList<>(); // 公告内容集合
    private List<String> listtime = new ArrayList<>();//公告时间集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mnv = findViewById(R.id.mnv);
        init();
    }

    private void init() {
        for (int i = 0 ; i<5;i++){
            listStr.add("欢迎光临，下次再来"+i);
            listtime.add("2018-04-02 " + i);
        }
        mnv.setList(listStr, listtime);
        mnv.setOnItemClickListener(new MyNotificationView.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(NotificationActivity.this,"第   "+position  +"个",Toast.LENGTH_LONG).show();
            }
        });

    }
}
