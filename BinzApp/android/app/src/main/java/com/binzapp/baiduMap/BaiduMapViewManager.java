package com.binzapp.baiduMap;

import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

/**
 * Created by binz on 2017/1/4.
 */

public class BaiduMapViewManager extends ViewGroupManager<MapView> {

    private static final String REACT_CLASS = "RCTBaiduMapView";
    private ThemedReactContext mReactContext;
    private ReadableArray childrenPoints;

    public String getName() {
        return REACT_CLASS;
    }

    public void initSDK(Context context) {
        SDKInitializer.initialize(context);
    }

    public MapView createViewInstance(ThemedReactContext context) {
        mReactContext = context;
        MapView mapView =  new MapView(context);
        return mapView;
    }

}
