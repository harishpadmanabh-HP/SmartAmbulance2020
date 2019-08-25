package com.example.lenovo.smartambulancefinal;

import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApprovalActivity extends AppCompatActivity {

    TextInputLayout name,phone;
    Button accept;
    AsyncHttpClient client,client2;
    RequestParams params,params2;
    String locresponse,latresponse,longresponse,accidentid,status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);

        client=new AsyncHttpClient();
        params=new RequestParams();
        client2=new AsyncHttpClient();
        params2=new RequestParams();

        name=findViewById(R.id.approvalname);
        phone=findViewById(R.id.approvalphone);
        accept=findViewById(R.id.approve);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String names=name.getEditText().getText().toString();
                final String phones=phone.getEditText().getText().toString();

                if(!names.equalsIgnoreCase("")&&(!phones.equalsIgnoreCase("")))
                {

                    client.get("http://srishti-systems.info/projects/smartambulance/view_location.php",params,new AsyncHttpResponseHandler(){
                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);
                            try{

                                JSONObject jsonObject=new JSONObject(content);

                                String stat=jsonObject.getString("status");
                                if(stat.equalsIgnoreCase("success"))
                                {

                                    JSONArray jsonArray=jsonObject.getJSONArray("Accident Location");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        final JSONObject obj = jsonArray.getJSONObject(i);

                                        accidentid=obj.getString("id");
                                        latresponse=obj.getString("lat");
                                        longresponse=obj.getString("log");
                                        locresponse=obj.getString("location");
                                        status=obj.getString("status");


                                           params2.put("id",accidentid);
                                           params2.put("driver",phones);
                                           params2.put("name",names);

                                           client2.get("http://srishti-systems.info/projects/smartambulance/approve.php?",params2,new AsyncHttpResponseHandler(){
                                               @Override
                                               public void onSuccess(String content) {
                                                   super.onSuccess(content);


                                                   try{

                                                       JSONObject objectapprove =new JSONObject(content);
                                                       String s=objectapprove.getString("status");

                                                       if (s.equalsIgnoreCase("success"))
                                                       {
                                                           AlertDialog.Builder AB = new AlertDialog.Builder(ApprovalActivity.this);
                                                           AB.setMessage("You have accepted this rescue . We Thank you for your effort. ").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                               @Override
                                                               public void onClick(DialogInterface dialog, int which) {

                                                                  dialog.cancel();

                                                               }
                                                           }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                               @Override
                                                               public void onClick(DialogInterface dialog, int which) {
                                                                   dialog.cancel();
                                                               }
                                                           });
                                                           AlertDialog A = AB.create();
                                                           A.setTitle("ACCEPT RESCUE");
                                                           A.show();


                                                       }


                                                   }catch (Exception e)
                                                   {
                                                       Toast.makeText(ApprovalActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                                                   }




                                               }
                                           });





                                    }


                                }else
                                {
                                    Toast.makeText(ApprovalActivity.this, "Could'nt fetch location .", Toast.LENGTH_SHORT).show();
                                }

                            }catch (Exception e)
                            {
                                Toast.makeText(ApprovalActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }else
                {
                    Toast.makeText(ApprovalActivity.this, "Please enter credntials.", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }
}
