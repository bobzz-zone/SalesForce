package mtisfa.com.sfa.Utility;

/**
 * Created by Windows 8.1 on 26/10/2017.
 */


import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;


public class GPSServices {

    public static Double lat=0.0;
    public static Double longi = 0.0;
    public static GoogleApiClient mGoogleApiClient;
    public static LocationRequest mLocationRequest;
    public static Location mLastLocation;
}
