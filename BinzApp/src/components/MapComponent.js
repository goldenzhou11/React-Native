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

export default class MapComponent extends Component {
  constructor() {
    super();
  }

  /**
  * 定义参数propTypes - 名字:类型
  */
  static propTypes = {
    ...View.propTypes,
    mapType: PropTypes.number,
    zoom: PropTypes.number,
    center: PropTypes.object,
    maxAndMinZoom: PropTypes.array,
    onlineTileOverlay: PropTypes.object,
    initMarkers: PropTypes.array
  };

  /**
  * 初始化参数
  */
  static defaultProps = {
    mapType: 1,
    zoom: 14,
    center: null,
    maxAndMinZoom: [],
    onlineTileOverlay: null,
    initMarkers: []
  }

  render() {
    // 初始化地图组件
    return <BaiduMapView {...this.props}/>;
  }
}

// NativeModules-BaiduMap
const BaiduMapView = requireNativeComponent('RCTBaiduMapView', MapComponent);
