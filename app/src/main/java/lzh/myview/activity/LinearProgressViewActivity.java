package lzh.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import lzh.myview.R;
import lzh.myview.view.MyLinearProgressView;

public class LinearProgressViewActivity extends AppCompatActivity {

    MyLinearProgressView mlpv;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_progress_view);
        mlpv = findViewById(R.id.mlpv);
        bt = findViewById(R.id.mlpv);
        mlpv.setProgress(0);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlpv.setProgress(50);
                mlpv.setType(2);
            }
        });
    }

}
