package com.binzapp.baiduMap.tiles;

import com.baidu.mapapi.map.UrlTileProvider;

/**
 * Created by binz on 2017/1/11.
 */

public class BaiduOnlineTileProvider extends UrlTileProvider {

    public int getMaxDisLevel()
    {
        return 21;
    }

    public int getMinDisLevel()
    {
        return 14;
    }

    public String getTileUrl()
    {
        return "https://secure.cdn1.wdpromedia.cn/media/maps/prod/shdr-baidu-mob/10/{z}/{x}/{y}.jpg";
    }
}
