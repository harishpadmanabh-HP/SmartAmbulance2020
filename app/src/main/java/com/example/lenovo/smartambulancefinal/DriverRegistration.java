package com.example.lenovo.smartambulancefinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

//import com.example.accidentalert.R;

public class DriverRegistration extends AppCompatActivity {
    EditText name,phone,license,password,confirm;
    Button reg;
    AsyncHttpClient client;
    RequestParams params;
    JSONObject object;
    String url="http://srishti-systems.info/projects/smartambulance/reg.php?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_registration);
//
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        license=findViewById(R.id.licenseno);
        password=findViewById(R.id.password);
        confirm=findViewById(R.id.confirm);
        reg=findViewById(R.id.signup);
        client=new AsyncHttpClient();
        params=new RequestParams();


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals(""))
                {
                 name.setError("Input details");
                }
                else if (phone.getText().toString().equals(""))
                {
                    phone.setError("Input details");
                }
                else if (license.getText().toString().equals(""))
                {
                    license.setError("Input details");
                }
                else if (password.getText().toString().equals(""))
                {
                    password.setError("Input details");
                }
                else if (confirm.getText().toString().equals(""))
                {
                    confirm.setError("Input details");
                }
                else if(!confirm.getText().toString().equals(password.getText().toString()))
                {
                    confirm.setError("Passwords not matching");
                }
                else {

                    params.put("name", name.getText().toString());
                    params.put("phone", phone.getText().toString());
                    params.put("license", license.getText().toString());
                    params.put("password", password.getText().toString());


                    client.get(url, params, new AsyncHttpResponseHandler() {

                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);

                            try {
                                object = new JSONObject(content);

                                if (object.getString("status").equals("success")) {
                                    Toast.makeText(DriverRegistration.this, "" + object.getString("status"), Toast.LENGTH_SHORT).show();

                                    Intent l = new Intent(DriverRegistration.this, MainActivity.class);
                                    startActivity(l);
                                } else {
                                    Toast.makeText(DriverRegistration.this, "" + object.getString("status"), Toast.LENGTH_SHORT).show();
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