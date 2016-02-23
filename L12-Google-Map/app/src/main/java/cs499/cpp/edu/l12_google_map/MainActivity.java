package cs499.cpp.edu.l12_google_map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private GoogleMap googleMapRef;
    private SupportMapFragment supportMapFragment;
    private GeoFire geoFire;
    private Map<String, Marker> keyMarkerMap = new HashMap<String, Marker>();

    private LocationManager locationManager;
    private Location lastLocation;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        geoFire = new GeoFire(new Firebase("https://fiery-torch-1028.firebaseio.com/"));

        geoFire.setLocation("firebase-hq1", new GeoLocation(37.7853889, -122.4056973));
        geoFire.setLocation("firebase-hq2", new GeoLocation(37.7856889, -122.4053973));
        geoFire.setLocation("firebase-hq3", new GeoLocation(37.7859889, -122.4051973));

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lastLocation = location;
                googleMapRef.addMarker(new MarkerOptions().title("Current").position(new LatLng(location.getLatitude(), location.getLongitude())));
                googleMapRef.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                3);




        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMapRef = googleMap;

                googleMapRef.addMarker(new MarkerOptions().title("First Place").position(new LatLng(34, 56)));
                googleMapRef.addMarker(new MarkerOptions().title("CPP").position(new LatLng(55, -86)));

                googleMapRef.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                    @Override
                    public void onCameraChange(CameraPosition cameraPosition) {
                        LatLng target = cameraPosition.target;
                        GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(target.latitude, target.longitude), 3);
                        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
                            @Override
                            public void onKeyEntered(String key, GeoLocation location) {
                                Log.i("TEST", "Entered: " + key + " " + location);
                                if (!keyMarkerMap.containsKey(key)) {
                                    Marker marker = googleMapRef.addMarker(new MarkerOptions().title(key).position(new LatLng(location.latitude, location.longitude)));
                                    keyMarkerMap.put(key, marker);
                                }
                            }

                            @Override
                            public void onKeyExited(String key) {
                                if (keyMarkerMap.containsKey(key)) {
                                    keyMarkerMap.get(key).remove();
                                    keyMarkerMap.remove(key);
                                }
                            }

                            @Override
                            public void onKeyMoved(String key, GeoLocation location) {
                                Log.i("TEST", "Changed: " + key + " " + location);
                                if (keyMarkerMap.containsKey(key)) {
                                    keyMarkerMap.get(key).remove();
                                    keyMarkerMap.remove(key);
                                }
                                Marker marker = googleMapRef.addMarker(new MarkerOptions().title(key).position(new LatLng(location.latitude, location.longitude)));
                                keyMarkerMap.put(key, marker);
                            }

                            @Override
                            public void onGeoQueryReady() {

                            }

                            @Override
                            public void onGeoQueryError(FirebaseError error) {

                            }
                        });
                    }
                });

//                for(int i = 0; i < 10; i++) {
//
//
//                    Handler handler = new Handler();
//                    Runnable r=new Runnable() {
//                        public void run() {
//                            int x = new Random().nextInt(80);
//                            int y = new Random().nextInt(80);
//
//                            int zoom = new Random().nextInt(16);
//
//                            googleMapRef.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(x, y), zoom));
//                            googleMapRef.addMarker(new MarkerOptions().position(new LatLng(x, y)));
//                        }
//                    };
//                    handler.postDelayed(r, 3000 * i);
//                }

//                googleMapRef.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                    @Override
//                    public void onMapClick(LatLng latLng) {
//                        Log.i("TEST", "Clicked: " + latLng.toString());
//                        Geocoder geocoder;
//                        List<Address> addresses;
//                        geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
//                        try {
//                            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//                            String city = addresses.get(0).getLocality();
//                            String state = addresses.get(0).getAdminArea();
//                            String country = addresses.get(0).getCountryName();
//                            String postalCode = addresses.get(0).getPostalCode();
//                            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
//                            Log.i("TEST", "Country Tanner asked: " + country);
//                            Log.i("TEST", "Country Tanner asked: " + address);
//
//                            googleMapRef.addMarker(new MarkerOptions().position(latLng).title(addresses.get(0).toString()));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                });
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 3, locationListener);
    }
}
