package com.chengying.root.homePage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chengying.root.login.LoginActivity;
import com.chengying.root.map.BaiduMapActivity;
import com.chengying.root.registe.RegistActivity;
import com.chengying.root.zhongcha.R;
import com.goodsmanage.UpdateGoodsActivity;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for login test
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });
        this.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, RegistActivity.class));
            }
        });
        this.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(HomeActivity.this, BaiduMapActivity.class));
            }
        });
        this.findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this, UpdateGoodsActivity.class));
            }
        });
    }
}
