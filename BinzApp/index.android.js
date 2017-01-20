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
        <View style={{height:100,position:'absolute'}}><Text>123123123123</Text></View>
        <View style={{backgroundColor:'#fefefe', width:50, height:50, borderRadius: 25, position:'absolute'}}></View>
      </View>
    );
  }
}

AppRegistry.registerComponent('BinzApp', () => BinzApp);
