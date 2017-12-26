package lzh.myview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import lzh.myview.R;
import lzh.myview.view.AuditprogressView;

public class AuditprogressActivity extends AppCompatActivity {
  private AuditprogressView av;
  private Button bt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditprogress);
        av= (AuditprogressView) findViewById(R.id.av);
        bt = (Button) findViewById(R.id.bt);
        final ArrayList<String > textlist = new ArrayList<>();
        textlist.add("提交成功");
        textlist.add("提交成功");
        textlist.add("提交成功");
        textlist.add("提交成功");
        textlist.add("提交成功");
        final ArrayList<String> timelist = new ArrayList<>();
        timelist.add("12-18 11:20");
        timelist.add("12-19 11:20");
        timelist.add("12-20 11:20");
        timelist.add("12-21 11:20");
        timelist.add("12-21 11:20");
        av.setDate(textlist,timelist,3,true);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                av.setDate(textlist,timelist, (int) (Math.random() * 6),true);
            }
        });

    }
}
