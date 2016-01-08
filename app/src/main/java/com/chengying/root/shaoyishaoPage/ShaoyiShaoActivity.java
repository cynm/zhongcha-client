package com.chengying.root.shaoyishaoPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zbar.lib.CaptureActivity;
import com.chengying.root.zhongcha.R;

public class ShaoyiShaoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shaoyi_shao);
        this.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShaoyiShaoActivity.this, CaptureActivity.class));
            }
        });
    }
}
