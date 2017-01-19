package com.binzapp.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by binz on 2017/1/19.
 */

public class MapDatas {

    private DBManager dm;
    private SQLiteDatabase db;
    private final String SQL_INITMARKERS = "select type, primary_location_lat, primary_location_lng from facilities where type = ? or sub_type = ?";

    public MapDatas(Context context) {
        dm = new DBManager();
        db = dm.openDatabase(context);
    }

    public List<Map<String, Object>> getInitMarkers(String mkType) {
        List<Map<String, Object>> markers = new ArrayList<Map<String, Object>>();
        Cursor cursor = db.rawQuery(SQL_INITMARKERS, new String[]{mkType, mkType});
        while (cursor.moveToNext()) {
            Map<String, Object> row = new HashMap<String, Object>();
            row.put("markerType", cursor.getString(0));
            row.put("mLat", cursor.getDouble(1));
            row.put("mLng", cursor.getDouble(2));
            markers.add(row);
        }
        return markers;
    }

    public void closeDB(){
        db.close();
    }

}
