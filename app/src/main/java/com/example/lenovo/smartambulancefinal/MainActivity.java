package com.example.lenovo.smartambulancefinal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {
    LocationManager locationManager;
    String locurl="http://srishti-systems.info/projects/smartambulance/location.php?";
    AsyncHttpClient asyncHttpClient,pushclient;
    RequestParams Params,pushparam;
    boolean GpsStatus ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        asyncHttpClient=new AsyncHttpClient();
        pushclient=new AsyncHttpClient();
        Params=new RequestParams();
        pushparam=new RequestParams();

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//        SmsManager smsManager = SmsManager.getDefault();
//        smsManager.sendTextMessage("9188138688", null, refreshedToken, null, null);

//        Toast.makeText(this, "FirebaseInstanceId"+FirebaseInstanceId.getInstance().getToken(), Toast.LENGTH_SHORT).show();
//        Log.e("TOKENREF",FirebaseInstanceId.getInstance().getToken());
//        System.out.println("Token"+refreshedToken);






        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }


    }

    public void hospitalclick(View view) {
//        LayoutInflater inflat=LayoutInflater.from(MainActivity.this);
//        View cuslay=inflat.inflate(R.layout.hospitalalert,null);
//
//        CardView cview=cuslay.findViewById(R.id.card1);
//        TextView tview=cuslay.findViewById(R.id.hreg);
//        TextView viewhosp=cuslay.findViewById(R.id.hview);
//        AlertDialog.Builder AB=new AlertDialog.Builder(MainActivity.this);
//        AB.setView(cuslay);
//        final AlertDialog A=AB.create();
//        A.show();
//        tview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(MainActivity.this,HospitalRegistration.class);
//                startActivity(i);
//            }
//        });
//
//        viewhosp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent k=new Intent(MainActivity.this,ViewHospitals.class);
//                startActivity(k);
//            }
//        });

        startActivity(new Intent(getApplicationContext(),ViewHospitals.class));

    }


    public void driverclick(View view) {

        Intent j=new Intent(MainActivity.this,DriverLogin.class);
        startActivity(j);

    }

    public void donorclick(View view) {
        LayoutInflater inflat=LayoutInflater.from(MainActivity.this);
        View cuslay=inflat.inflate(R.layout.blooddonor_alert,null);

        CardView cview=cuslay.findViewById(R.id.bcard1);
        TextView textview=cuslay.findViewById(R.id.breg);
        TextView bloodview=cuslay.findViewById(R.id.bview);
        AlertDialog.Builder AB=new AlertDialog.Builder(MainActivity.this);
        AB.setView(cuslay);
        final AlertDialog A=AB.create();
        A.show();
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Donor_Registration.class);
                startActivity(i);
            }
        });
        bloodview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ViewBloodDonors.class);
                startActivity(i);
            }
        });
    }


    public void driverregister(View view) {
        Intent i=new Intent(MainActivity.this,DriverRegistration.class);
        startActivity(i);
    }

    public void bookop(View view) {
        Intent t=new Intent(MainActivity.this,OPRegistration.class);
        startActivity(t);
    }


    public void Alerted(View view) {


//....................CHECK INTERNET

        ConnectivityManager cm =
                (ConnectivityManager)getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected==false)
        {
            // Toast.makeText(getApplicationContext(), "Please check your internet connection !", Toast.LENGTH_SHORT).show();

            System.out.println("internet false");
        }
        else
        {
            System.out.println("INTERNET STATUS TRUE "+isConnected);
        }

//...................CHECK INTERET ENDS
        //.............................GPS STATUS

        CheckGpsStatus() ;

//        if(GpsStatus == true)
//        {
//            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();        }
//        else {
//            Toast.makeText(this, "Please enable location services.", Toast.LENGTH_SHORT).show();

//
//            AlertDialog.Builder AB = new AlertDialog.Builder(MainActivity.this);
//            AB.setMessage("You need to enable your GPS to get the accurate location of your reported accident.").setCancelable(false).setPositiveButton("TURN ON GPS From Settings", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                   // turnGPSOn();
//                    dialog.cancel();
//
//
//                }
//            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
//                }
//            });
//            AlertDialog A = AB.create();
//            A.setTitle("ALERT !!!");
//            A.show();
//
//
//
//
//
//



       // }

        //............................GPD STATUS ENDS


        if(isConnected==false&&GpsStatus==true)
        {
            LinearLayout linearLayout=findViewById(R.id.ll);
            Snackbar snackbar = Snackbar
                    .make(linearLayout, "Check your internet connection", Snackbar.LENGTH_LONG);
            snackbar.show();        }
        else if(isConnected==true&&GpsStatus==false)
        {
            LinearLayout linearLayout=findViewById(R.id.ll);
            Snackbar snackbar = Snackbar
                    .make(linearLayout, "Enable location services", Snackbar.LENGTH_LONG);
            snackbar.show();        }
        else if(isConnected==false&&GpsStatus==false)
        {
            LinearLayout linearLayout=findViewById(R.id.ll);
            Snackbar snackbar = Snackbar
                    .make(linearLayout, "Check your internet connection and enable location services", Snackbar.LENGTH_LONG);
            snackbar.show();
        }




        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //Toast.makeText(getApplicationContext(), ""+refreshedToken, Toast.LENGTH_SHORT).show();
//        SmsManager smsManager = SmsManager.getDefault();
//        smsManager.sendTextMessage("7012069385", null, refreshedToken, null, null);


        getLocation();



    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, (LocationListener) this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        //locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
String latlong="Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude();
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
          //  locationText.setText(locationText.getText() + "\n"+addresses.get(0).getAddressLine(0)+", "+
                   // addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));

       String locadd=latlong + "\n"+addresses.get(0).getAddressLine(0)+", "+
             addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2);

            Toast.makeText(getApplicationContext(), "Location spotted"+locadd, Toast.LENGTH_SHORT).show();
Double latdouble=location.getLatitude();
Double logdouble=location.getLongitude();

            String latstring = Double.toString(latdouble);
            String longstring = Double.toString(logdouble);
            Params.put("lat",latstring);
            Params.put("log",longstring);
            Params.put("location",locadd);
            Log.e("Loc Params",Params.toString());
            asyncHttpClient.get(locurl,Params,new AsyncHttpResponseHandler(){
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);


                  try  {

                      JSONObject jsonObject=new JSONObject(content);
                      String status=jsonObject.getString("status");

                      if( status.equalsIgnoreCase("success") )
                      {
                          Toast.makeText(MainActivity.this, "Location has been updated and the accident is reported .\n Ambulance drivers are notified !. \n Thank you for reporting.", Toast.LENGTH_SHORT).show();
                      }else
                      {
                          Toast.makeText(MainActivity.this, "Something went wrong . Please try Again", Toast.LENGTH_SHORT).show();

                      }


                    }catch (Exception e)
                  {
                      Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();

                  }
                }
            });


            pushclient.post("http://srishti-systems.info/projects/smartambulance/push_notification.php",pushparam,new AsyncHttpResponseHandler(){
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);

                    try{

                        JSONObject jo=new JSONObject(content);

                        Log.e("PUSH REsPOnse",jo.toString());

                        String sn=jo.getString("success");

                        Toast.makeText(MainActivity.this, "Notification has ben delivered to "+sn+" Ambulance drivers.", Toast.LENGTH_SHORT).show();

                    }catch (Exception e)
                    {
                        Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                    }

                }
            });





        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();

    }

    public void CheckGpsStatus(){

        locationManager = (LocationManager)getApplication().getSystemService(Context.LOCATION_SERVICE);

        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void turnGPSOn(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(!provider.contains("gps")){ //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
    }

    private void turnGPSOff(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(provider.contains("gps")){ //if gps is enabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
    }
}
