package com.example.lenovo.smartambulancefinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class GeneralMedicine extends AppCompatActivity {
    boolean clicked=false;
    TextView nirmala,credence,sasikala,sct,prs,sut,meditrina,kerala,gg,medical;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_medicine);
        nirmala=findViewById(R.id.nirmala);
        credence=findViewById(R.id.credencee);
        sasikala=findViewById(R.id.sasikala);
        sct=findViewById(R.id.sct);
        prs=findViewById(R.id.prs);
        sut=findViewById(R.id.sut);
        meditrina=findViewById(R.id.meditrina);
        kerala=findViewById(R.id.kerala);
        gg=findViewById(R.id.gg);
        medical=findViewById(R.id.medical);
        nirmala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hosp",nirmala.getText().toString());
                editor.commit();
                Intent i=new Intent(GeneralMedicine.this,BookingPage.class);
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
                editor.putString("hospi",credence.getText().toString());
                editor.commit();
                Intent i=new Intent(GeneralMedicine.this,BookingPage.class);
                startActivity(i);
            }
        });


        sasikala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hosp",sasikala.getText().toString());
                editor.commit();
                Intent i=new Intent(GeneralMedicine.this,BookingPage.class);
                startActivity(i);
            }
        });

        sct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hosp",sct.getText().toString());
                editor.commit();
                Intent i=new Intent(GeneralMedicine.this,BookingPage.class);
                startActivity(i);
            }
        });

        prs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hosp",prs.getText().toString());
                editor.commit();
                Intent i=new Intent(GeneralMedicine.this,BookingPage.class);
                startActivity(i);
            }
        });

        sut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hosp",sut.getText().toString());
                editor.commit();
                Intent i=new Intent(GeneralMedicine.this,BookingPage.class);
                startActivity(i);
            }
        });

        meditrina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hosp",meditrina.getText().toString());
                editor.commit();
                Intent i=new Intent(GeneralMedicine.this,BookingPage.class);
                startActivity(i);
            }
        });

        sct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hosp",sct.getText().toString());
                editor.commit();
                Intent i=new Intent(GeneralMedicine.this,BookingPage.class);
                startActivity(i);
            }
        });

        kerala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hosp",kerala.getText().toString());
                editor.commit();
                Intent i=new Intent(GeneralMedicine.this,BookingPage.class);
                startActivity(i);
            }
        });

        gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hosp",gg.getText().toString());
                editor.commit();
                Intent i=new Intent(GeneralMedicine.this,BookingPage.class);
                startActivity(i);
            }
        });
        medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                clicked=true;
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("hosp",medical.getText().toString());
                editor.commit();
                Intent i=new Intent(GeneralMedicine.this,BookingPage.class);
                startActivity(i);
            }
        });
    }


}

