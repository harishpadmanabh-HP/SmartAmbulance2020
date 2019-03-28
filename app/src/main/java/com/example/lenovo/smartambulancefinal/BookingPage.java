package com.example.lenovo.smartambulancefinal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class BookingPage extends AppCompatActivity {
    AsyncHttpClient client;
    TextView textv;
    EditText edittext;
    Calendar myCalendar;
    RequestParams params;
    JSONObject object;
    Button button;
    String url="http://srishti-systems.info/projects/smartambulance/bookticket.php?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page);
        textv=findViewById(R.id.tv);
        edittext=findViewById(R.id.Birthday);
        button=findViewById(R.id.ok);
        client=new AsyncHttpClient();
        params=new RequestParams();

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
       String hospital= sharedPreferences.getString("hosp",null);
       textv.setText(hospital);
         myCalendar = Calendar.getInstance();

        SharedPreferences sharedPreference=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
        String email= sharedPreferences.getString("key1",null);

        final EditText edittext= (EditText) findViewById(R.id.Birthday);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(BookingPage.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        params.put("hname",hospital);
        params.put("email", email);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.put("date", edittext.getText().toString());

                client.get(url, params, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);

                        try {
                            object = new JSONObject(content);

                            if (object.getString("status").equals("success")) {
                                Toast.makeText(BookingPage.this, "" + object.getString("status"), Toast.LENGTH_SHORT).show();

                                Intent l = new Intent(BookingPage.this, MainActivity.class);
                                startActivity(l);
                            } else {
                                Toast.makeText(BookingPage.this, "" + object.getString("status"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {

                        }


                    }
                });
            }
        });
    }


    private void updateLabel() {
        String myFormat = "yy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
        String datee=edittext.getText().toString();
    }
}
