export default {
  // MAPTYPE地图加载模式
  MAPTYPE_NONE: 0,
  MAPTYPE_NORMAL: 1,
  MAPTYPE_SATELLITE: 2,
  MAPTYPE_OTHERS: 3,

  // CENTER坐标
  MAP_CENTER_LON: 121.667917,
  MAP_CENTER_LAT: 31.149712,

  // 最大,最小缩放等级,初始缩放等级
  MAP_MAX_ZOOM: 21,
  MAP_MIN_ZOOM: 14,
  MAP_INIT_ZOOM: 19,

  // 自定义瓦片图层参数
  MAP_TILE_MAX_ZOOM: 21,
  MAP_TILE_MIN_ZOOM: 3,
  MAP_TILE_TMP: 52428800,
  MAP_TILE_ONLINE_URL: 'https://secure.cdn1.wdpromedia.cn/media/maps/prod/shdr-baidu-mob/10/{z}/{x}/{y}.jpg'

};
