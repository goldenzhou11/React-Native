export default {
  // MAPTYPE地图加载模式
  MAPTYPE_NONE: 0,
  MAPTYPE_NORMAL: 1,
  MAPTYPE_SATELLITE: 2,
  MAPTYPE_OTHERS: 3,

  // CENTER坐标
  MAP_CENTER_LON: 121.666110,
  MAP_CENTER_LAT: 31.150011,

  // 最大,最小缩放等级,初始缩放等级
  MAP_MAX_ZOOM: 21,
  MAP_MIN_ZOOM: 14,
  MAP_INIT_ZOOM: 19,

  // 自定义瓦片图层参数
  MAP_TILE_MAX_ZOOM: 21,
  MAP_TILE_MIN_ZOOM: 3,
  MAP_TILE_TMP: 52428800,
  MAP_TILE_ONLINE_URL: 'https://secure.cdn1.wdpromedia.cn/media/maps/prod/shdr-baidu-mob/10/{z}/{x}/{y}.jpg',

  // Marker类型
  MARKER_TYPE_ATTRACTIONS: 'Attraction',
  MARKER_TYPE_ENTERTAINM: 'Entertainment',
  MARKER_TYPE_DINING: 'restaurant',
  MARKER_TYPE_SHOPPING: 'MerchandiseFacility',
  MARKER_TYPE_RESTROOMS: 'Restroom',
  MARKER_TYPE_PHOTOPASS: 'PhotoPass',
  MARKER_TYPE_TOURS: 'tour',
  MARKER_TYPE_GUESTSERVICES: 'guest-service',
  MARKER_TYPE_RESORTS: 'resort',
  MARKER_TYPE_RECREATION: 'Recreation'

};
