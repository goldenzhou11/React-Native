package com.binzapp.baiduMap.Utils;

import com.baidu.mapapi.model.LatLng;
import com.facebook.react.bridge.ReadableMap;

/**
 * Created by binz on 2017/1/16.
 */

public class MarkerUtil {


    /**
     * 获取经纬度点
     * @param option
     * @return
     */
    private static LatLng getLatLngFromOption(ReadableMap option) {
        double latitude = option.getDouble("latitude");
        double longitude = option.getDouble("longitude");
        return new LatLng(latitude, longitude);

    }
}
