package com.example.lenovo.smartambulancefinal;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;

    AsyncHttpClient client;
    RequestParams params;

    String locresponse,latresponse,longresponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        client=new AsyncHttpClient();
        params=new RequestParams();


    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        client.get("http://srishti-systems.info/projects/smartambulance/view_location.php",params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                try{

                    JSONObject jsonObject=new JSONObject(content);

                    String stat=jsonObject.getString("status");
                    if(stat.equalsIgnoreCase("success"))
                    {

                        JSONArray  jsonArray=jsonObject.getJSONArray("Accident Location");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);

                            latresponse=obj.getString("lat");
                            longresponse=obj.getString("log");
                            locresponse=obj.getString("location");


                            double lat=Double.parseDouble(latresponse);
                            double log=Double.parseDouble(longresponse);


                            // Add a marker in Sydney and move the camera
                          //  LatLng accidentspot = new LatLng(8.5299707, 76.9382294);
                             LatLng accidentspot = new LatLng(lat, log);

                            mMap.addMarker(new MarkerOptions().position(accidentspot).title("Accident Spoted"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(accidentspot));

                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .target(accidentspot)      // Sets the center of the map to Mountain View
                                    .zoom(17)                   // Sets the zoom
                                    .bearing(90)                // Sets the orientation of the camera to east
                                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                                    .build();                   // Creates a CameraPosition from the builder
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                        }


                    }else
                    {
                        Toast.makeText(MapsActivity.this, "Could'nt fetch location .", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e)
                {
                    Toast.makeText(MapsActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }
        });



//        double lat=Double.parseDouble(latresponse);
//        double log=Double.parseDouble(longresponse);


//        // Add a marker in Sydney and move the camera
//       LatLng accidentspot = new LatLng(8.5299707, 76.9382294);
//       // LatLng accidentspot = new LatLng(lat, log);
//
//        mMap.addMarker(new MarkerOptions().position(accidentspot).title("Accident Spoted"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(accidentspot));
//
//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(accidentspot)      // Sets the center of the map to Mountain View
//                .zoom(20)                   // Sets the zoom
//                .bearing(90)                // Sets the orientation of the camera to east
//                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
//                .build();                   // Creates a CameraPosition from the builder
//        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}