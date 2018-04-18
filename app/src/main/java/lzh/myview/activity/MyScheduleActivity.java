package lzh.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import lzh.myview.R;
import lzh.myview.view.MyScheduleView;

public class MyScheduleActivity extends AppCompatActivity {

    MyScheduleView msv;
   Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_schedule);
        msv = findViewById(R.id.msv);
        msv.setProgress(1);
        bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msv.setProgress((int) (Math.random() * 5));
            }
        });
    }

}
