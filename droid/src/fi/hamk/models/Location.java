package fi.hamk.models;

import android.location.LocationManager;

/**
 * @author Sami Hostikka
 */
public class Location {

	public Double altitude = null, latitude = null, longitude = null;

	public Location(LocationManager locationManager) {
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			// http://stackoverflow.com/a/2227299
			android.location.Location location = locationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (location != null) {
				altitude = location.getAltitude();
				latitude = location.getLatitude();
				longitude = location.getLongitude();
			}
		}
	}
}