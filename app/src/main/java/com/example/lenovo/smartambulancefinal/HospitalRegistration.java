package com.example.lenovo.smartambulancefinal;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

public class HospitalRegistration extends AppCompatActivity {

    TextInputLayout name,contact,email,address,city;
    Button register;
    AsyncHttpClient client;
    RequestParams params;
    JSONObject object;
    String url="http://srishti-systems.info/projects/smartambulance/hopreg.php?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_registration);
      name=findViewById(R.id.input_name);
      contact=findViewById(R.id.input_contact);
      email=findViewById(R.id.input_email);
      address=findViewById(R.id.input_address);
      city=findViewById(R.id.input_city);
      register=findViewById(R.id.btn_signup);
        client=new AsyncHttpClient();
        params=new RequestParams();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getEditText().getText().toString().equals(""))
                {
                    name.setError("Input details");
                }
                else if (contact.getEditText().getText().toString().equals(""))
                {
                    contact.setError("Input details");
                }
                else if (email.getEditText().getText().toString().equals(""))
                {
                    email.setError("Input details");
                }
                else if (address.getEditText().getText().toString().equals(""))
                {
                    address.setError("Input details");
                }
                else if (city.getEditText().getText().toString().equals(""))
                {
                    city.setError("Input details");
                }

                else {

                    params.put("name", name.getEditText().getText().toString());
                    params.put("contact", contact.getEditText().getText().toString());
                    params.put("mail", email.getEditText().getText().toString());
                    params.put("address", address.getEditText().getText().toString());
                    params.put("city", city.getEditText().getText().toString());


                    client.get(url, params, new AsyncHttpResponseHandler() {

                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);

                            try {
                                object = new JSONObject(content);

                                if (object.getString("status").equals("success")) {
                                    Toast.makeText(HospitalRegistration.this, "" + object.getString("status"), Toast.LENGTH_SHORT).show();

                                    Intent l = new Intent(HospitalRegistration.this, MainActivity.class);
                                    startActivity(l);
                                } else {
                                    Toast.makeText(HospitalRegistration.this, "" + object.getString("status"), Toast.LENGTH_SHORT).show();
                                }

                            } catch (Exception e) {

                            }


                        }
                    });
                }
            }
        });
    }
}