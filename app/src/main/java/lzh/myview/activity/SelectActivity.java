package lzh.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import lzh.myview.R;
import lzh.myview.view.SelectView;

public class SelectActivity extends AppCompatActivity {

    @BindView(R.id.sv)
    SelectView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        ButterKnife.bind(this);
        sv.setCount(2);
        sv.setListener(new SelectView.OnPayListener() {
            @Override
            public void onGetPoint(int pas) {
                Toast.makeText(getApplicationContext(),""+pas, Toast.LENGTH_LONG).show();
            }
        });
    }
}
