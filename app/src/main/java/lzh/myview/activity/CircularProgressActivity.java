package lzh.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import lzh.myview.R;
import lzh.myview.view.MyCircularProgressView;

public class CircularProgressActivity extends AppCompatActivity {
    MyCircularProgressView circleProgressView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_progress);
        circleProgressView = findViewById(R.id.circle_progress_view);
        button = findViewById(R.id.bt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int progress = (int) (Math.random() * 100);
                circleProgressView.setProgress(progress);
            }
        });

    }


}
