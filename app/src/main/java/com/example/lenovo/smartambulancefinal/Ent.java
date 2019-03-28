package com.example.lenovo.smartambulancefinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Ent extends AppCompatActivity {


    TextView chaith,cosmo,kerf;
    boolean clicked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ent);
        chaith=findViewById(R.id.chaithanya);
        cosmo=findViewById(R.id.cosmopolitan);
        kerf=findViewById(R.id.kerf);


        //my button clic
        chaith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hosp",chaith.getText().toString());
                editor.commit();
                Intent i=new Intent(Ent.this,BookingPage.class);
                startActivity(i);
            }
        });



        cosmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hosp",cosmo.getText().toString());
                editor.commit();
                Intent i=new Intent(Ent.this,BookingPage.class);
                startActivity(i);
            }
        });


        kerf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hosp",kerf.getText().toString());
                editor.commit();
                Intent i=new Intent(Ent.this,BookingPage.class);
                startActivity(i);
            }
        });

        //then on another method or where you want


    }
}
