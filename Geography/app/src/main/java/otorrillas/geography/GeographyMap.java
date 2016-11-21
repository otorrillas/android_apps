package otorrillas.geography;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GeographyMap extends FragmentActivity implements OnMapClickListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Geocoder gcd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geography_map);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    private void displayEurope() {

        LatLng icelandBorder = new LatLng(65.0110779, -26.2500329);

        GoogleMapOptions options = new GoogleMapOptions();
        LatLng myPos = new LatLng(56.2339845, 6.8388672);
        float myZoom = 3;
        CameraPosition myCamPos = new CameraPosition.Builder()
                .target(myPos)
                .zoom(myZoom)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(myCamPos));
        displayWorldMap();
    }

    private void displayAndorra() {
        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(42.569962, 1.78172 ))
                .add(new LatLng(42.509438, 1.723611))  // North of the previous point, but at the same longitude
                .add(new LatLng(42.601944, 1.445833))  // Same latitude, and 30km to the west
                .add(new LatLng(42.569962, 1.78172)) // Same longitude, and 16km to the south
                .fillColor(Color.TRANSPARENT)
                .strokeColor(Color.CYAN);

        mMap.addPolygon(rectOptions);
    }

    private void displayWorldMap() {
        DataSaver ds = new DataSaver();
        Map<String, ArrayList<PolygonOptions>> worldMap = ds.openWorldMap();
        Log.w("displayWorldMap", "DataSaver readen");
        Log.i("worldMap", worldMap.toString());
        for (Map.Entry<String, ArrayList<PolygonOptions>> entry : worldMap.entrySet()) {
            for(PolygonOptions elem : entry.getValue()) {
                elem.strokeColor(Color.CYAN);
                elem.fillColor(0x77FF00FF);
                mMap.addPolygon(elem);
            }
        }


    }
    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        gcd = new Geocoder(this.getBaseContext(), Locale.getDefault());
        mMap.setOnMapClickListener(this);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        displayEurope();
    }


    public void onMapClick(LatLng point) {
        List<Address> addresses = null;
        String countryName = "none";
        try {
            addresses = gcd.getFromLocation(point.latitude, point.longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses.size() > 0)
        {
            countryName=addresses.get(0).getCountryName();
        }
        Log.v("onMapClick", "point clicked: " + point + "at country: " + countryName);
    }
}
