package com.example.lenovo.smartambulancefinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Donor_Registration extends AppCompatActivity {
    TextInputLayout name,age,phone,address;
    AppCompatButton signup;
    Spinner spinner;
    TextView blood;
    List<String> list;
    AsyncHttpClient client;
    RequestParams params;
    String bgroup;
    JSONObject object;
    String url="http://srishti-systems.info/projects/smartambulance/donorreg.php?name=Adarsh&age=22&group=B-&phone=99854367888&address=Poovar&device_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor__registration);

        name=(TextInputLayout)findViewById(R.id.input_name);
        age=(TextInputLayout)findViewById(R.id.input_age);
        phone=(TextInputLayout)findViewById(R.id.input_phone);
        address=(TextInputLayout)findViewById(R.id.input_address);
        spinner = (Spinner) findViewById(R.id.bloodgroup);
        signup=findViewById(R.id.btn_signup);
        blood = findViewById(R.id.input_blood);
        list = new ArrayList<String>();
        list.add("O positive");
        list.add("O negative");
        list.add("A positive");
        list.add("A negative");
        list.add("B positive");
        list.add("B negative");
        list.add("AB positive");
        list.add("AB negative");
        client=new AsyncHttpClient();
        params=new RequestParams();

        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);
        // mobiles.addAll(Arrays.asList(mobileArray));

        spinner.setAdapter(adapt);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                switch(arg2) {

                    case 0 :
                        blood.setText("O positive");

                    break;
                    case 1 :
                        blood.setText("O negative");

                        break;
                    case 2 :
                        blood.setText("A positive");

                        break;
                    case 3 :
                        blood.setText("A negative");

                        break;
                    case 4:
                        blood.setText("B positive");
                        break;
                    case  5:
                        blood.setText("B negative");
                        break;
                    case 6:
                        blood.setText("AB positive");
                        break;
                    case 7:
                        blood.setText("AB negative");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getEditText().getText().toString().equals(""))
                {
                    name.setError("Input details");
                }
                else if (age.getEditText().getText().toString().equals(""))
                {
                    age.setError("Input details");
                }

                else if (phone.getEditText().getText().toString().equals(""))
                {
                    phone.setError("Input details");
                }
                else if (address.getEditText().getText().toString().equals(""))
                {
                    address.setError("Input details");
                }


                else {
                       bgroup=blood.getText().toString();
                    params.put("name", name.getEditText().getText().toString());
                    params.put("age", age.getEditText().getText().toString());
                    params.put("blood",bgroup);
                    params.put("phone", phone.getEditText().getText().toString());
                    params.put("address", address.getEditText().getText().toString());


                    client.get(url, params, new AsyncHttpResponseHandler() {

                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);

                            try {
                                object = new JSONObject(content);

                                if (object.getString("status").equals("success")) {
                                    Toast.makeText(Donor_Registration.this, "" + object.getString("status"), Toast.LENGTH_SHORT).show();

                                    Intent l = new Intent(Donor_Registration.this, MainActivity.class);
                                    startActivity(l);
                                } else {
                                    Toast.makeText(Donor_Registration.this, "" + object.getString("status"), Toast.LENGTH_SHORT).show();
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
