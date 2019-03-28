package com.example.lenovo.smartambulancefinal;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void hospitalclick(View view) {
        LayoutInflater inflat=LayoutInflater.from(MainActivity.this);
        View cuslay=inflat.inflate(R.layout.hospitalalert,null);

        CardView cview=cuslay.findViewById(R.id.card1);
        TextView tview=cuslay.findViewById(R.id.hreg);
        TextView viewhosp=cuslay.findViewById(R.id.hview);
        AlertDialog.Builder AB=new AlertDialog.Builder(MainActivity.this);
        AB.setView(cuslay);
        final AlertDialog A=AB.create();
        A.show();
        tview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,HospitalRegistration.class);
                startActivity(i);
            }
        });

        viewhosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(MainActivity.this,ViewHospitals.class);
                startActivity(k);
            }
        });

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


}
