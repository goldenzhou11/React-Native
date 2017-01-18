'use strict';

// SQLite组件
let SQLite = require('react-native-sqlite-storage');
// DB对象
let db = SQLite.openDatabase(
  {
    name: 'facilities.db',
    createFromLocation : "~facilities.db",
    location: 'Library'
  },
  () => {console.log("Database OPENED");},
  err => {console.log("SQL Error: " + err);});

// 检索DB-SQL
export function getMarkersInfo(mkType) {
  let sqlStr = `SELECT type, primary_location_lat, primary_location_lng FROM facilities where type = "${mkType}" or sub_type = "${mkType}"`;

  db.transaction((tx) => {
      tx.executeSql(sqlStr, [], (tx, results) => {
          console.log("Query completed");

          var len = results.rows.length;
          var mkArr = new Array(len);
          for (let i = 0; i < len; i++) {
            let row = results.rows.item(i);
            let mkObj = {
                'markerType': row.type,
                'mLat': row.primary_location_lat,
                'mLng': row.primary_location_lng
            }
            mkArr[i] = mkObj;
          }
          console.log(mkArr[0]);
          return mkArr;
        });
    });
}
