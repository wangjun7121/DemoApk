package com.example.wangjun.demoapk.LocationDemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangjun.demoapk.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;


// LocationManager 的基本用法:
//    使用方法：
//        1. 获得 LoacationManager 对象：getSystemService
//        2. 获得可用的定位方式：
//            LocationManager.getProviders()
//        3. 获得位置信息：
//            静态：Location = LocationManager.getLastKnownLocation
//            动态：LocationManager.requestLocationUpdates

public class CLocationManagerActivity extends AppCompatActivity {
    public static final int SHOW_LOCATION = 0;

    private TextView positionTextView;
    private TextView originalPositionTextView;

    private LocationManager locationManager;

    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locationdemo_clocationmanager);

        originalPositionTextView = (TextView) findViewById(R.id.originalPositionTextView);
        positionTextView = (TextView) findViewById(R.id.position_text_view);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // 获取所有可用的位置提供器: GPS/Network
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            // 当没有可用的位置提供器时，弹出Toast提示用户
            Toast.makeText(this, "No location provider to use", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this, "No Permission", Toast.LENGTH_SHORT).show();
            return;
        }
        // 获得设备最后的定位信息
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            // 显示当前设备的位置信息
            showLocation(location);
        }
        // 指定位置提供者：GPS/Netwok, 设置 Location 位置变量监听函数
        locationManager.requestLocationUpdates(provider, 5000, 1,locationListener);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            // 关闭程序时将监听器移除
            locationManager.removeUpdates(locationListener);
        }
    }

    LocationListener locationListener = new LocationListener() {

        // 当 LocationProvider 的状态发生变化时调用
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        // 当用户在设置中启动或禁止相关 LocationProvider 后被调用
        @Override
        public void onProviderEnabled(String provider) {
        }
        @Override
        public void onProviderDisabled(String provider) {
        }


        @Override
        public void onLocationChanged(Location location) {
            // 更新当前设备的位置信息
            showLocation(location);
        }
    };

    private void showLocation(final Location location) {

        String currentPosition = "纬度：" + location.getLatitude() + "\n"
                + "经度：" + location.getLongitude();
        originalPositionTextView.setText(currentPosition);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    // 请求格式：
                    //      http://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.96145&sensor=false

                    // 返回格式：
//                    {
//                        "results" : [
//                        {
//                            "address_components" : [
//                            {
//                                "long_name" : "1600",
//                                    "short_name" : "1600",
//                                    "types" : [ "street_number" ]
//                            },
//                            {
//                                "long_name" : "Amphitheatre Pkwy",
//                                    "short_name" : "Amphitheatre Pkwy",
//                                    "types" : [ "route" ]
//                            },
//                            {
//                                "long_name" : "Mountain View",
//                                    "short_name" : "Mountain View",
//                                    "types" : [ "locality", "political" ]
//                            },
//                            {
//                                "long_name" : "Santa Clara",
//                                    "short_name" : "Santa Clara",
//                                    "types" : [ "administrative_area_level_2", "political" ]
//                            },
//                            {
//                                "long_name" : "California",
//                                    "short_name" : "CA",
//                                    "types" : [ "administrative_area_level_1", "political" ]
//                            },
//                            {
//                                "long_name" : "United States",
//                                    "short_name" : "US",
//                                    "types" : [ "country", "political" ]
//                            },
//                            {
//                                "long_name" : "94043",
//                                    "short_name" : "94043",
//                                    "types" : [ "postal_code" ]
//                            }],
//
//                            "formatted_address" : "1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA",
//                                "geometry" : {
//                                "location" : {
//                                    "lat" : 37.42291810,
//                                            "lng" : -122.08542120
//                                },
//                                "location_type" : "ROOFTOP",
//                                        "viewport" : {
//                                    "northeast" : {
//                                        "lat" : 37.42426708029149,
//                                                "lng" : -122.0840722197085
//                                    },
//                                    "southwest" : {
//                                        "lat" : 37.42156911970850,
//                                                "lng" : -122.0867701802915
//                                    }
//                                }
//                            },
//                            "types" : [ "street_address" ]
//                        }],
//                        "status" : "OK"
//                    }

                    // 组装反向地理编码的接口地址
                    StringBuilder url = new StringBuilder();
                    url.append("http://maps.googleapis.com/maps/api/geocode/json?latlng=");
                    url.append(location.getLatitude()).append(",")
                            .append(location.getLongitude());
                    url.append("&sensor=false");
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(url.toString());
                    // 在请求消息头中指定语言，保证服务器会返回中文数据
                    httpGet.addHeader("Accept-Language", "zh-CN");
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    Log.v("WJWind",String.valueOf(httpResponse.getStatusLine().getStatusCode()));
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "utf-8");
                        JSONObject jsonObject = new JSONObject(response);
                        // 获取 results 节点下的位置信息
                        JSONArray resultArray = jsonObject.getJSONArray("results");
                        if (resultArray.length() > 0) {
                            JSONObject subObject = resultArray.getJSONObject(0);
                            // 取出格式化后的位置信息
                            String address = subObject.getString("formatted_address");
                            Message message = new Message();
                            message.what = SHOW_LOCATION;
                            message.obj = address;
                            handler.sendMessage(message);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_LOCATION:
                    String currentPosition = (String) msg.obj;
                    positionTextView.setText(currentPosition);
                    break;
                default:
                    break;
            }
        }

    };



}
