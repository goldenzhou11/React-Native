'use strict';

export function getInitMarkers(mkType) {
  return `SELECT type as markerType, primary_location_lat as mLat, primary_location_lng as mLng FROM facilities where type = "${mkType}" or sub_type = "${mkType}"`;
}
