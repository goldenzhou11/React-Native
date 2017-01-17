package com.binzapp.baiduMap.Utils;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by binz on 2017/1/16.
 */

public class MarkerUtil {

    public static Marker addMarker(MapView mapView, double mLat, double mLng, BitmapDescriptor bitmap) {
        LatLng position = new LatLng(mLat, mLng);
        OverlayOptions overlayOptions = new MarkerOptions()
                .icon(bitmap)
                .position(position);

        Marker marker = (Marker)mapView.getMap().addOverlay(overlayOptions);
        return marker;
    }
}
