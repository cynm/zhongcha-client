package com.chengying.root.shaoyishaoPage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.style.SuggestionSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chengying.root.network.AppConfig;
import com.chengying.root.network.MySocketClient;
import com.chengying.root.suggess.SuggessAdapter;
import com.chengying.root.tools.Tools;
import com.zbar.lib.CaptureActivity;
import com.chengying.root.zhongcha.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShaoyiShaoActivity extends Activity {
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            goodsFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            goodsFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    goodsFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            goodsFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    public View goodsFormView;
    public  View mProgressView;


    public TextView tvGoodsName;
    public TextView tvGoodsPrice;
    public TextView tvUpdateTime;

    public ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shaoyi_shao);
        goodsFormView=(LinearLayout)this.findViewById(R.id.goods_form_view);
        mProgressView = findViewById(R.id.login_progress);
        tvGoodsName=(TextView)this.findViewById(R.id.v0_name);
        tvGoodsPrice=(TextView)this.findViewById(R.id.v1_name);
        tvUpdateTime=(TextView)this.findViewById(R.id.update_time);
        listView=(ListView)this.findViewById(R.id.thesuggesstion);
        this.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ShaoyiShaoActivity.this, CaptureActivity.class));
                showProgress(true);
                GoodsGetTask mAuthTask = new GoodsGetTask("0001");
                  mAuthTask.execute((Void) null);
            }
        });

    }
    public void fillGoodsInfoForm(Map<String,String> map){
        tvGoodsName.setText(map.get("goodsname"));
        tvGoodsPrice.setText(map.get("price"));
        tvUpdateTime.setText(map.get("last_rfeash_time"));

    }
    public class GoodsGetTask extends AsyncTask<Void,Void,Boolean>{
        public  int resultCode=0;
        Map<String,String> resultMap=null;
        Map<String,String> goodsInfo=null;
        List<Map<String,String>> data;
        String ecode;
        public GoodsGetTask(String ecode){
            this.ecode=ecode;
        }
        @Override
        protected Boolean doInBackground(Void... params) {
            JSONArray ja=new JSONArray();
            JSONObject jo=new JSONObject();
            try {
                jo.put("goods_ecode",ecode);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ja.put(jo);
            String resultStr= MySocketClient.getInstance().send("GetGoodsInfoProcesser",ja);
            if(resultStr==null){
                resultCode=4;//net work error
                return false;
            }
            resultMap= Tools.JArrayToMap(resultStr,0);
            goodsInfo=Tools.JArrayToMap(resultStr,1);
            if(resultMap.get("result").equals("true")){
                //  ok

                resultCode=0;
                return true;
            }
            else if(resultMap.get("result").equals("false")){
                if(resultMap.get("resultCode").equals("1")){
                    // goods is not exist
                    resultCode=1;
                    return false;

                }


            }
            return false;
        }
        @Override
        protected void onPostExecute(final Boolean success) {
            showProgress(false);
            if (success) {

                fillGoodsInfoForm(goodsInfo);
                // for test
                data=new ArrayList<Map<String,String>>();
                for(int i=0;i<10;i++) {
                    Map<String, String> map = new HashMap<>();
                    map.put("shopName", "武昌量贩"+i);
                    map.put("goodsNmae", "鸡蛋");
                    map.put("price", "0.5");
                    map.put("unit", "个");
                    map.put("distance", "300");
                    data.add(map);
                }
                SuggessAdapter adapter=new SuggessAdapter(ShaoyiShaoActivity.this,data);
                listView.setAdapter(adapter);


            }else {
                if(resultCode==4)
                {
                    Toast.makeText(ShaoyiShaoActivity.this, MySocketClient.STATE_TIME_OUT, Toast.LENGTH_SHORT).show();

                } else if(resultCode==1){

                    Toast.makeText(ShaoyiShaoActivity.this, "not exist", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
