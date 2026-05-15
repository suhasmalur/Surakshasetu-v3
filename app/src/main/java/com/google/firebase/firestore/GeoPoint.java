package com.google.firebase.firestore;

import com.google.firebase.firestore.util.Util;

/* JADX INFO: loaded from: classes10.dex */
public class GeoPoint implements Comparable<GeoPoint> {
    private final double latitude;
    private final double longitude;

    public GeoPoint(double latitude, double longitude) {
        if (Double.isNaN(latitude) || latitude < -90.0d || latitude > 90.0d) {
            throw new IllegalArgumentException("Latitude must be in the range of [-90, 90]");
        }
        if (Double.isNaN(longitude) || longitude < -180.0d || longitude > 180.0d) {
            throw new IllegalArgumentException("Longitude must be in the range of [-180, 180]");
        }
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    @Override // java.lang.Comparable
    public int compareTo(GeoPoint other) {
        int comparison = Util.compareDoubles(this.latitude, other.latitude);
        if (comparison == 0) {
            return Util.compareDoubles(this.longitude, other.longitude);
        }
        return comparison;
    }

    public String toString() {
        return "GeoPoint { latitude=" + this.latitude + ", longitude=" + this.longitude + " }";
    }

    public boolean equals(Object o) {
        if (!(o instanceof GeoPoint)) {
            return false;
        }
        GeoPoint geoPoint = (GeoPoint) o;
        return this.latitude == geoPoint.latitude && this.longitude == geoPoint.longitude;
    }

    public int hashCode() {
        long temp = Double.doubleToLongBits(this.latitude);
        int result = (int) ((temp >>> 32) ^ temp);
        long temp2 = Double.doubleToLongBits(this.longitude);
        return (result * 31) + ((int) ((temp2 >>> 32) ^ temp2));
    }
}
