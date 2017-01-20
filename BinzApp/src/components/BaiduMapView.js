/**
 * BaiduMap组件
 * @auth binz
 */
'use strict';

import {
  StyleSheet,
  View
} from 'react-native';

import React, {
  Component
} from 'react';

import Dimensions from 'Dimensions';
import MapView from './MapComponent';
import MapConfig from '../config/MapConfig';
import {getInitMarkers} from '../utils/GetSQL.js';
let SQLite = require('react-native-sqlite-storage');

export default class BaiduMapView extends Component {

  // 构造函数:初始化
  constructor() {
    super();

    // 初始化地图信息
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
      {name: 'facilities.db', createFromLocation : "~facilities.db", location: 'Library'},
      () => {console.log("Database OPENED");},
      (err) => {console.log("SQL Error: " + err);}
    );
    let sqlStr = getInitMarkers(MapConfig.MARKER_TYPE_DINING);
    db.transaction((tx) => {
      tx.executeSql(sqlStr, [], (tx, results) => {
          console.log('excuteSQL success');
          // 获取数据并重新渲染地图
          this.setState({initMarkers: results.rows.raw()}, () => {});
      });
    });
  }

  componentWillUpdate(){

  }

  componentWillUnmount(){
    SQLite.close(
      () => {console.log("Database CLOSED");},
      (err) => {console.log("SQL Error: " + err);}
    );
  }

  render() {
    return (
        <MapView
          mapType={this.state.mapType}
          zoom={this.state.zoom}
          center={this.state.center}
          maxAndMinZoom={this.state.maxAndMinZoom}
          onlineTileOverlay={this.state.onlineTileOverlay}
          initMarkers={this.state.initMarkers}
          style={styles.map}>
        </MapView>
    );
  }
}

const styles = StyleSheet.create({
  map: {
    width: Dimensions.get('window').width,
    height: Dimensions.get('window').height
  }
});
