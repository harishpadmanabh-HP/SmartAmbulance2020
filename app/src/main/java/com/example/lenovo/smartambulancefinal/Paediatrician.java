package com.example.lenovo.smartambulancefinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Paediatrician extends AppCompatActivity {

    TextView aarjav,credence,sat,rr;
    boolean clicked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paediatrician);
        aarjav=findViewById(R.id.aarjav);
        credence=findViewById(R.id.credence);
        sat=findViewById(R.id.sat);
        rr=findViewById(R.id.rr);


        //my button clic
        aarjav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hosp",aarjav.getText().toString());
                editor.commit();
                Intent i=new Intent(Paediatrician.this,BookingPage.class);
                startActivity(i);
            }
        });



        credence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hosp",credence.getText().toString());
                editor.commit();
                Intent i=new Intent(Paediatrician.this,BookingPage.class);
                startActivity(i);
            }
        });


        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hosp",sat.getText().toString());
                editor.commit();
                Intent i=new Intent(Paediatrician.this,BookingPage.class);
                startActivity(i);
            }
        });

        rr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hospi",rr.getText().toString());
                editor.commit();
                Intent i=new Intent(Paediatrician.this,BookingPage.class);
                startActivity(i);
            }
        });

    }


}
