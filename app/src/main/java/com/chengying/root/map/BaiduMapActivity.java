package com.chengying.root.map;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;


import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.navisdk.adapter.BaiduNaviManager;
import com.chengying.root.zhongcha.R;

/**
 * the valiad way to get SHA1 Code:
 * 1. Create a sined apk,and upakege it
 * 2. to find CERT.RSA
 * 3.Open a Teminal, :cd ./android
 * keytoll -printcert -file CERT.RSA
 * then get the md5 code and SHA1 code
 */
public class BaiduMapActivity extends Activity {
    MapView mMapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());
         setContentView(R.layout.activity_baidu_map);
        mMapView = (MapView) findViewById(R.id.bmapView);
        BaiduMap mBaiduMap = mMapView.getMap();


        //1标记
        LatLng point = new LatLng(30.4244870000,114.3712710000);
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.icon_marka);
        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
        mBaiduMap.addOverlay(option);
        //2设定中心点坐标
        LatLng cenpt =  new LatLng(30.4244870000,114.3712710000);
        //定义地图状态,比例尺
        MapStatus mMapStatus = new MapStatus.Builder().target(cenpt).zoom(100)
                .build();
                //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化

        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);









//        double mLat1 = 39.915;
//        double mLon1 = 116.404;
//        double mLat2 = 32.032;
//        double mLon2 = 118.799;
//
//        int lat = (int) (mLat1 *1E6);
//        int lon = (int) (mLon1 *1E6);
//        GeoPoint pt1 = new GeoPoint(lat, lon);
//        lat = (int) (mLat2 *1E6);
//        lon = (int) (mLon2 *1E6);
//        GeoPoint pt2 = new GeoPoint(lat, lon);
//
//        NaviPara para = new NaviPara();
//        para.startPoint = pt1;
//        para.startName= "从这里开始";
//        para.endPoint  = pt2;
//        para.endName   = "到这里结束";
//
//        try {
//
//            BaiduMapNavigation.openBaiduMapNavi(para, this);
//
//        } catch (BaiduMapAppNotSupportNaviException e) {
//            e.printStackTrace();
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setMessage("您尚未安装百度地图app或app版本过低，点击确认安装？");
//            builder.setTitle("提示");
//            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//
//
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                    BaiduMapNavigation.GetLatestBaiduMapApp(this);
//                }
//            });
//
//            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//
//            builder.create().show();
//        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
         mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }


}

