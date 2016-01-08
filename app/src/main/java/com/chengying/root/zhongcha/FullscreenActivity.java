package com.chengying.root.zhongcha;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.chengying.root.homePage.HomeActivity;
import com.chengying.root.myPage.MyActivity;
import com.chengying.root.shaoyishaoPage.ShaoyiShaoActivity;

public class FullscreenActivity extends TabActivity implements   View.OnClickListener {
    LinearLayout m_home;
    LinearLayout m_shaoyishao;
    LinearLayout m_my;
    ImageView m_h;
    ImageView m_s;
    ImageView m_m;
    TabHost tabHost;
    public static String TAB_HOME="home";
    public static String TAB_SHAOYISHAO="shaoyishao";
    public static String TAB_MY="my";
    private void initView(){
        m_home= (LinearLayout) this.findViewById(R.id.home);
        m_shaoyishao=(LinearLayout)this.findViewById(R.id.shaoyishao);
        m_my=(LinearLayout)this.findViewById(R.id.my);
        m_h= (ImageView) this.findViewById(R.id.m_h);
        m_s= (ImageView) this.findViewById(R.id.m_s);
        m_m= (ImageView) this.findViewById(R.id.m_m);
        tabHost=this.getTabHost();

        m_h.setOnClickListener(this);
        m_home.setOnClickListener(this);
        m_s.setOnClickListener(this);
        m_shaoyishao.setOnClickListener(this);
        m_m.setOnClickListener(this);
        m_my.setOnClickListener(this);


         TabSpec tabspac_home=tabHost.newTabSpec(TAB_HOME).setIndicator(TAB_HOME);
         TabSpec tabspac_shaoyishao=tabHost.newTabSpec(TAB_SHAOYISHAO).setIndicator(TAB_SHAOYISHAO);
         TabSpec tabspac_my=tabHost.newTabSpec(TAB_MY).setIndicator(TAB_MY);

        tabspac_home.setContent(new Intent(this, HomeActivity.class));
        tabspac_shaoyishao.setContent(new Intent(this, ShaoyiShaoActivity.class));
        tabspac_my.setContent(new Intent(this, MyActivity.class));

        tabHost.addTab(tabspac_home);
        tabHost.addTab(tabspac_shaoyishao);
        tabHost.addTab(tabspac_my);
tabHost.setCurrentTabByTag(TAB_HOME);
        tabHost.setup();



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        initView();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.home:
            case R.id.m_h:
                Toast.makeText(this,"home",Toast.LENGTH_LONG).show();
                tabHost.setCurrentTabByTag(TAB_HOME);;
            break;
            case R.id.shaoyishao:

            case R.id.m_s:

                tabHost.setCurrentTabByTag(TAB_SHAOYISHAO);

                break;
            case R.id.my:
            case R.id.m_m:
                Toast.makeText(this,"my",Toast.LENGTH_LONG).show();
                tabHost.setCurrentTabByTag(TAB_MY);
                break;


        }
    }
}
