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

import MapView from './js/BaiduMap/MapView';
import Dimensions from 'Dimensions';

export default class BinzApp extends Component {

  constructor() {
    super();
  }

  render() {
    return (
      <View style={styles.container}>
        <MapView style={styles.map}>
        </MapView>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  map: {
    width: Dimensions.get('window').width,
    height: Dimensions.get('window').height
  }
});

AppRegistry.registerComponent('BinzApp', () => BinzApp);
