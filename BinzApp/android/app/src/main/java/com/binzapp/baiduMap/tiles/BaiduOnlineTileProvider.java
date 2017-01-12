package com.binzapp.baiduMap.tiles;

import com.baidu.mapapi.map.UrlTileProvider;
import com.binzapp.baiduMap.config.DefaultConfig;

/**
 * Created by binz on 2017/1/12.
 */

public class BaiduOnlineTileProvider extends UrlTileProvider {

    public int getMaxDisLevel()
    {
        return (int) DefaultConfig.DEF_MAX_ZOOM;
    }

    public int getMinDisLevel()
    {
        return (int) DefaultConfig.DEF_MIN_ZOOM;
    }

    public String getTileUrl()
    {
        return DefaultConfig.DEF_ONLINE_TILES_URL;
    }
}
