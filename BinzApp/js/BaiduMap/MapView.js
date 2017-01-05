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

export default class MapView extends Component {
  static propTypes = {
    ...View.propTypes
  };

  constructor() {
    super();
  }

  render() {
    return <BaiduMapView {...this.props}/>;
  }
}

const BaiduMapView = requireNativeComponent('RCTBaiduMapView', MapView);
