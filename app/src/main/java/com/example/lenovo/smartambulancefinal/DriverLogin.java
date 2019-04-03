package com.example.lenovo.smartambulancefinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

public class DriverLogin extends AppCompatActivity {
    AsyncHttpClient client;
    RequestParams params;
    JSONObject obj1;
   TextInputLayout userphone,passp;
    Button loginbtn,signup;
    String url="http://srishti-systems.info/projects/smartambulance/login.php?";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        client=new AsyncHttpClient();
        params=new RequestParams();


       userphone=(TextInputLayout)findViewById(R.id.tuser);

        passp=(TextInputLayout)findViewById(R.id.tpass);
        loginbtn=findViewById(R.id.login);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                params.put("phone",userphone.getEditText().getText().toString());
                params.put("password",passp.getEditText().getText().toString());

                client.get(url,params,new AsyncHttpResponseHandler()
                {

                    @Override
                    public void onSuccess(String content)
                    {
                        super.onSuccess(content);

                        try
                        {
                            Log.e("innn","in");

                            obj1=new JSONObject(content);

                            String s=obj1.getString("status");
                           // String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                            Toast.makeText(DriverLogin.this, ""+s,
                                    Toast.LENGTH_SHORT).show();
                            if (s.equals("Success"))
                            {
                                JSONObject obj2=obj1.getJSONObject("User_data");
                                SharedPreferences sp_doc_log=getApplicationContext()
                                        .getSharedPreferences("d1",MODE_PRIVATE);
                                SharedPreferences.Editor ed=sp_doc_log.edit();
                                ed.putString("did",obj2.getString("id"));
                                ed.putString("dname",obj2.getString("name"));
                                ed.putString("dl",obj2.getString("license"));
                                ed.putString("dp",obj2.getString("phone"));
                                ed.putString("dpass",obj2.getString("password"));

                                ed.commit();

//                                Intent i=new Intent(DriverLogin.this,DriverHome.class);
//                                startActivity(i);

                                Toast.makeText(DriverLogin.this, "You have signed in . You will be notified when an accident is reported ", Toast.LENGTH_SHORT).show();


                            }


                        }
                        catch (Exception e)
                        {

                        }
                    }
                });










            }
        });


        signup=findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(DriverLogin.this,DriverRegistration.class);
                startActivity(j);
            }
        });
    }

    public void viewdriver(View view) {
        Intent i=new Intent(DriverLogin.this,ViewDriver.class);
        startActivity(i);
    }
}
