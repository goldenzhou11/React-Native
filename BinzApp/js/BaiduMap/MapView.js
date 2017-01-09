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

// 导入常量设定 - MapTypes
import MapTypes from './MapTypes';

export default class MapView extends Component {
  /**
  * 定义参数propTypes - 名字:类型
  */
  static propTypes = {
    ...View.propTypes,
    mapType: PropTypes.number,
    zoom: PropTypes.number,
    center: PropTypes.object,
    maxAndMinZoom: PropTypes.array
  };

  /**
  * 初始化参数
  */
  static defaultProps = {
    mapType: MapTypes.NORMAL,
    zoom: 15,
    center: {
              'longitude': 121.667917,
              'latitude': 31.149712
            },
    maxAndMinZoom: [14, 21]
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
