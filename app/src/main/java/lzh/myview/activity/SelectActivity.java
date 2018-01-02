package lzh.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import lzh.myview.R;
import lzh.myview.view.SelectView;

public class SelectActivity extends AppCompatActivity {

    @BindView(R.id.sv)
    SelectView sv;
    ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        ButterKnife.bind(this);
        list.add("天");
        list.add("月");
        list.add("天");
        list.add("月");
        list.add("天");
        list.add("月");
        sv.setCount(list);
        sv.setListener(new SelectView.OnPayListener() {
            @Override
            public void onGetPoint(int pas) {
                Toast.makeText(getApplicationContext(),""+pas, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
