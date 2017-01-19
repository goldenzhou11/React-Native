package com.binzapp.baiduMap;

import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.TileOverlayOptions;
import com.baidu.mapapi.map.TileProvider;
import com.baidu.mapapi.map.UrlTileProvider;
import com.baidu.mapapi.model.LatLng;
import com.binzapp.baiduMap.BitmapDescriptor.MarkerDescriptor;
import com.binzapp.baiduMap.Utils.MarkerUtil;
import com.binzapp.baiduMap.config.DefaultConfig;
import com.binzapp.sqlite.MapDatas;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.List;
import java.util.Map;

/**
 * Created by binz on 2017/1/4.
 */

public class BaiduMapViewManager extends ViewGroupManager<MapView> {

    private static final String REACT_CLASS = "RCTBaiduMapView";
    private ThemedReactContext mReactContext;
    private ReadableArray childrenPoints;
    /** 瓦片图层默认参数设定 */
    private int tileTmp = DefaultConfig.DEF_TILE_TMP;
    private int maxLevel = (int) DefaultConfig.DEF_MAX_ZOOM;
    private int minLevel = (int) DefaultConfig.DEF_MIN_ZOOM;
    private String onlineUrl = "";

    public String getName() {
        return REACT_CLASS;
    }

    public void initSDK(Context context) {
        SDKInitializer.initialize(context);
    }

    public MapView createViewInstance(ThemedReactContext context) {
        mReactContext = context;
        MapView mapView = new MapView(context);
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
        float curZoom = DefaultConfig.DEF_ZOOM;
        if (zoom != 0f) {
            curZoom = zoom;
        }
        MapStatus mapStatus = new MapStatus.Builder().zoom(curZoom).build();
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
        float minLevel = DefaultConfig.DEF_MIN_ZOOM;
        float maxLevel = DefaultConfig.DEF_MAX_ZOOM;
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
        double latitude = DefaultConfig.DEF_LAT;
        double longitude = DefaultConfig.DEF_LNG;
        if (position != null) {
            latitude = position.getDouble("latitude");
            longitude = position.getDouble("longitude");
        }
        LatLng point = new LatLng(latitude, longitude);
        MapStatus mapStatus = new MapStatus.Builder()
                .target(point)
                .build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mapView.getMap().setMapStatus(mapStatusUpdate);
    }

    /**
     * 自定义瓦片图层
     * @param mapView
     * @param onlineTileOverlay
     */
    @ReactProp(name = "onlineTileOverlay")
    public void setOnlineTileOverlay (MapView mapView, ReadableMap onlineTileOverlay) {
        if (onlineTileOverlay != null) {
            if (!onlineTileOverlay.isNull("maxLevel")) {
                maxLevel = onlineTileOverlay.getInt("maxLevel");
            }
            if (!onlineTileOverlay.isNull("minLevel")) {
                minLevel = onlineTileOverlay.getInt("minLevel");
            }
            if (!onlineTileOverlay.isNull("tileTmp")) {
                tileTmp = onlineTileOverlay.getInt("tileTmp");
            }
            if (!onlineTileOverlay.isNull("onlineUrl")) {
                onlineUrl = onlineTileOverlay.getString("onlineUrl");
            }
        }

        /**
         * 定义瓦片图的在线Provider，并实现相关接口
         * MAX_LEVEL、MIN_LEVEL 表示地图显示瓦片图的最大、最小级别
         * urlString 表示在线瓦片图的URL地址
         */
        TileProvider tileProvider = new UrlTileProvider() {
            @Override
            public int getMaxDisLevel() {
                return maxLevel;
            }
            @Override
            public int getMinDisLevel() {
                return minLevel;
            }
            @Override
            public String getTileUrl() {
                return onlineUrl;
            }
        };

        TileOverlayOptions localTileOverlayOptions = new TileOverlayOptions();
        localTileOverlayOptions.tileProvider(tileProvider).setMaxTileTmp(tileTmp);
        mapView.getMap().addTileLayer(localTileOverlayOptions);
    }

    /**
     * 初始化自定义覆盖物
     * @param mReactContext
     * @param mapView
     * @param markerOptions
     */
    @ReactProp(name = "initMarkers")
    public static void initMarkers(MapView mapView, ReadableArray markerOptions) {
        if (markerOptions != null && markerOptions.size() != 0) {
            for (int i = 0; i < markerOptions.size(); i++) {
                ReadableMap markerOption = markerOptions.getMap(i);
                String markerType = markerOption.getString("markerType");
                double mLat = markerOption.getDouble("mLat");
                double mLng = markerOption.getDouble("mLng");
                BitmapDescriptor md = null;
                if (DefaultConfig.DEF_MARKER_TYPE_ATTRACTIONS.equals(markerType)) {
                    md = MarkerDescriptor.getAttractionsDescriptor();
                }
                else if (DefaultConfig.DEF_MARKER_TYPE_DINING.equals(markerType)) {
                    md = MarkerDescriptor.getDiningDescriptor();
                }
                else if (DefaultConfig.DEF_MARKER_TYPE_ENTERTAINM.equals(markerType)) {
                    md = MarkerDescriptor.getEntertainmDescriptor();
                }
                else if (DefaultConfig.DEF_MARKER_TYPE_GUESTSERVICES.equals(markerType)) {
                    md = MarkerDescriptor.getGuestservicesDescriptor();
                }
                else if (DefaultConfig.DEF_MARKER_TYPE_PHOTOPASS.equals(markerType)) {
                    md = MarkerDescriptor.getPhotopassDescriptor();
                }
                else if (DefaultConfig.DEF_MARKER_TYPE_RECREATION.equals(markerType)) {
                    md = MarkerDescriptor.getRecreationDescriptor();
                }
                else if (DefaultConfig.DEF_MARKER_TYPE_RESORTS.equals(markerType)) {
                    md = MarkerDescriptor.getResortsDescriptor();
                }
                else if (DefaultConfig.DEF_MARKER_TYPE_RESTROOMS.equals(markerType)) {
                    md = MarkerDescriptor.getRestroomsDescriptor();
                }
                else if (DefaultConfig.DEF_MARKER_TYPE_SHOPPING.equals(markerType)) {
                    md = MarkerDescriptor.getShoppingDescriptor();
                }
                else if (DefaultConfig.DEF_MARKER_TYPE_TOURS.equals(markerType)) {
                    md = MarkerDescriptor.getToursDescriptor();
                } else {
                    continue;
                }
                MarkerUtil.addMarker(mapView, mLat, mLng, md);
            }

        }
        // 初始化地图标注点的Native实现方式如下
        else {
//            MapDatas md = new MapDatas(mapView.getContext());
//            List<Map<String, Object>> markers = md.getInitMarkers(DefaultConfig.DEF_MARKER_TYPE_ATTRACTIONS);
//            md.closeDB();
//            BitmapDescriptor attractions_md = MarkerDescriptor.getAttractionsDescriptor();
//            for (int i = 0; i < markers.size(); i++) {
//                double mLat = (double) markers.get(i).get("mLat");
//                double mLng = (double) markers.get(i).get("mLng");
//                MarkerUtil.addMarker(mapView, mLat, mLng, attractions_md);
//            }
        }
    }

}
