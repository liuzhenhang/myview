package lzh.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import lzh.myview.R;
import lzh.myview.view.MyEditText;

public class EditTextActivity extends AppCompatActivity {

    MyEditText myet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        myet = findViewById(R.id.myet);

        // 获取焦点弹出软键盘
        myet.setFocusable(true);
        myet.setFocusableInTouchMode(true);
        myet.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


        myet.setListener(new MyEditText.OnPayListener() {
            @Override
            public void onGo(String pas) {
                Toast.makeText(getApplicationContext(),pas, Toast.LENGTH_LONG).show();
            }
        });

    }
}

