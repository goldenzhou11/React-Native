import React, {
  Component,
  PropTypes
} from 'react';

let SQLite = require('react-native-sqlite-storage')

export default class DataSQLite extends Component {

  constructor(props) {
    super(props);

    this.state = {
      record: null
    }
    console.log("DataSQLite Start");
    let db = SQLite.openDatabase({name: 'facilities.db', createFromLocation : "~facilities.db", location: 'Library'}, this.openCB, this.errorCB);
    db.transaction((tx) => {
      tx.executeSql('SELECT type, primary_location_lat, primary_location_lng FROM facilities where type = "Attraction" or sub_type = "Attraction"', [], (tx, results) => {
          console.log("Query completed");

          // Get rows with Web SQL Database spec compliance.
          this.setState({record: results.rows.raw()});
        });
    });

  }

  errorCB(err) {
    console.log("SQL Error: " + err);
  }

  successCB() {
    console.log("SQL executed fine");
  }

  openCB() {
    console.log("Database OPENED");
  }

  render() {
    return this.state.record;
  }
}
