'use strict';

import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  TouchableHighlight
} from 'react-native';

import React, {
  Component
} from 'react';

import Dimensions from 'Dimensions';
import BaiduMapView from './src/components/BaiduMapView';

export default class BinzApp extends Component {

  constructor() {
    super();
  }

  render() {
    return (
      <View>
        <BaiduMapView />
      </View>
    );
  }
}

AppRegistry.registerComponent('BinzApp', () => BinzApp);
