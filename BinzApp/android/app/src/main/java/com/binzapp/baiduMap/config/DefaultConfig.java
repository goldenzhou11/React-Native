package com.binzapp.baiduMap.config;

/**
 * Created by binz on 2017/1/12.
 */

public class DefaultConfig {

    // 默认纬度
    public static final double DEF_LAT = 31.149882885166999D;
    // 默认经度
    public static final double DEF_LNG = 121.66596790837001D;
    // 默认缩放等级
    public static final float DEF_ZOOM = 19.0F;
    // 默认最大缩放等级
    public static final float DEF_MAX_ZOOM = 21.0F;
    // 默认最小缩放等级
    public static final float DEF_MIN_ZOOM = 3.0F;
    // 默认在线瓦片图层URL
    public static final String DEF_ONLINE_TILES_URL = "http://api0.map.bdimg.com/customimage/tile"
            + "?&x={x}&y={y}&z={z}&udt=20150601&customid=light";
    // 设置瓦片图的在线缓存大小，默认为20 M
    public static final int DEF_TILE_TMP = 52428800;

}
