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
import MapView from './src/components/MapComponent';
import MapConfig from './src/config/MapConfig.js';
let SQLite = require('react-native-sqlite-storage');

export default class BinzApp extends Component {

  constructor() {
    super();

    this.state = {
      mapType: MapConfig.MAPTYPE_OTHERS,
      zoom: MapConfig.MAP_INIT_ZOOM,
      center: {
        longitude: MapConfig.MAP_CENTER_LON,
        latitude: MapConfig.MAP_CENTER_LAT
      },
      maxAndMinZoom: [MapConfig.MAP_MIN_ZOOM, MapConfig.MAP_MAX_ZOOM],
      onlineTileOverlay: {
        maxLevel: MapConfig.MAP_TILE_MAX_ZOOM,
        minLevel: MapConfig.MAP_TILE_MIN_ZOOM,
        tileTmp: MapConfig.MAP_TILE_TMP,
        onlineUrl: MapConfig.MAP_TILE_ONLINE_URL
      },
      initMarkers: []
    }

    /** 异步获取底图标注信息:SQLite  */
    let db = SQLite.openDatabase(
      {name: 'facilities.db'},
      () => {},
      (err) => {}
    );
    db.transaction((tx) => {
      let sqlStr = this.getSQL(MapConfig.MARKER_TYPE_ATTRACTIONS);
      tx.executeSql(sqlStr, [], (tx, results) => {
          // 获取数据并重新渲染地图
          this.setState({initMarkers: results.rows.raw()});
        });
    });
  }

  getSQL(mkType) {
    return `SELECT type as markerType, primary_location_lat as mLat, primary_location_lng as mLng FROM facilities where type = "${mkType}" or sub_type = "${mkType}"`;
  }

  render() {
    return (
      <View>
        <View style={styles.main_head}></View>
        <MapView
          mapType={this.state.mapType}
          zoom={this.state.zoom}
          center={this.state.center}
          maxAndMinZoom={this.state.maxAndMinZoom}
          onlineTileOverlay={this.state.onlineTileOverlay}
          initMarkers={this.state.initMarkers}
          style={styles.map}>
        </MapView>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  map: {
    width: Dimensions.get('window').width,
    height: Dimensions.get('window').height - 100
  },
  main_head:{
  flexDirection:'row',
  height:100
},
});

AppRegistry.registerComponent('BinzApp', () => BinzApp);
