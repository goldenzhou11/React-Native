package com.binzapp.baiduMap;

import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.TileOverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.binzapp.baiduMap.tiles.BaiduOnlineTileProvider;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

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
        MapView mapView = new MapView(context);
        // mapView.setCustomMapStylePath("/data/data/com.binzapp/files/custom_config");
        this.setCustTileOverlay(mapView);
        return mapView;
    }

    /**
     * 地图类型：普通 - MAP_TYPE_NORMAL
     * 卫星 - MAP_TYPE_SATELLITE
     * 空白 - MAP_TYPE_NON
     *
     * @param mapView
     * @param mapType
     */
    @ReactProp(name = "mapType")
    public void setMapType(MapView mapView, int mapType) {
        mapView.getMap().setMapType(mapType);
    }

    /**
     * 地图比例尺：3 - 21
     *
     * @param mapView
     * @param zoom
     */
    @ReactProp(name = "zoom")
    public void setZoom(MapView mapView, float zoom) {
        MapStatus mapStatus = new MapStatus.Builder().zoom(zoom).build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mapView.getMap().setMapStatus(mapStatusUpdate);
    }


    /**
     * 设置地图最大以及最小缩放级别,地图支持的最大最小级别分别为[3-21]
     *
     * @param mapView
     * @param maxAndMinZoom
     */
    @ReactProp(name = "maxAndMinZoom")
    public void setMaxAndMinZoom(MapView mapView, ReadableArray maxAndMinZoom) {
        // 默认地图缩放最大，最小等级
        float minLevel = 3f;
        float maxLevel = 21f;
        if (!maxAndMinZoom.isNull(0)) {
            minLevel = maxAndMinZoom.getInt(0);
        }
        if (!maxAndMinZoom.isNull(1)) {
            maxLevel = maxAndMinZoom.getInt(1);
        }
        mapView.getMap().setMaxAndMinZoomLevel(maxLevel, minLevel);
    }

    /**
     * 设置地图中心
     *
     * @param mapView
     * @param position
     */
    @ReactProp(name = "center")
    public void setCenter(MapView mapView, ReadableMap position) {
        if (position != null) {
            double latitude = position.getDouble("latitude");
            double longitude = position.getDouble("longitude");
            LatLng point = new LatLng(latitude, longitude);
            MapStatus mapStatus = new MapStatus.Builder()
                    .target(point)
                    .build();
            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
            mapView.getMap().setMapStatus(mapStatusUpdate);
        }
    }

    private void setCustTileOverlay(MapView mapView) {
        TileOverlayOptions localTileOverlayOptions = new TileOverlayOptions();
        localTileOverlayOptions.tileProvider(new BaiduOnlineTileProvider()).setMaxTileTmp(52428800);
        mapView.getMap().addTileLayer(localTileOverlayOptions);
    }

}
