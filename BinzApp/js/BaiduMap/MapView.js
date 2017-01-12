import {
  requireNativeComponent,
  View,
  NativeModules,
  Platform,
  DeviceEventEmitter
} from 'react-native';

import React, {
  Component,
  PropTypes
} from 'react';

// 导入常量设定 - MapConfig
import MapConfig from './MapConfig';

export default class MapView extends Component {
  /**
  * 定义参数propTypes - 名字:类型
  */
  static propTypes = {
    ...View.propTypes,
    mapType: PropTypes.number,
    zoom: PropTypes.number,
    center: PropTypes.object,
    maxAndMinZoom: PropTypes.array,
    onlineTileOverlay: PropTypes.object
  };

  /**
  * 初始化参数
  */
  static defaultProps = {
    mapType: MapConfig.MAPTYPE_OTHERS,
    zoom: MapConfig.MAP_INIT_ZOOM,
    center: {
              'longitude': MapConfig.MAP_CENTER_LON,
              'latitude': MapConfig.MAP_CENTER_LAT
            },
    maxAndMinZoom: [MapConfig.MAP_MIN_ZOOM, MapConfig.MAP_MAX_ZOOM],
    onlineTileOverlay: {
      'maxLevel': MapConfig.MAP_TILE_MAX_ZOOM,
      'minLevel': MapConfig.MAP_TILE_MIN_ZOOM,
      'tileTmp': MapConfig.MAP_TILE_TMP,
      'onlineUrl': MapConfig.MAP_TILE_ONLINE_URL
    }
  }

  constructor() {
    super();
  }

  render() {
    // 初始化地图组件
    return <BaiduMapView {...this.props}/>;
  }
}

const BaiduMapView = requireNativeComponent('RCTBaiduMapView', MapView);
