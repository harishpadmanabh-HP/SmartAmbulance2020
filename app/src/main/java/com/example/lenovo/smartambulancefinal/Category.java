package com.example.lenovo.smartambulancefinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }

    public void generalmedicine(View view) {


        Intent intent=new Intent(Category.this,GeneralMedicine.class);
        startActivity(intent);
    }

    public void ent(View view) {
        Intent j=new Intent(Category.this,Ent.class);
        startActivity(j);
    }

    public void paediatrician(View view) {
       Intent k=new Intent(Category.this,Paediatrician.class);
       startActivity(k);
    }

    public void physiotherapy(View view) {
        Intent k=new Intent(Category.this,GeneralMedicine.class);
        startActivity(k);
    }

    public void neurology(View view) {
        Intent k=new Intent(Category.this,GeneralMedicine.class);
        startActivity(k);
    }

    public void cardiology(View view) {
        Intent k=new Intent(Category.this,GeneralMedicine.class);
        startActivity(k);
    }

    public void nephrology(View view) {
        Intent k=new Intent(Category.this,GeneralMedicine.class);
        startActivity(k);
    }

    public void gynaecology(View view) {
        Intent k=new Intent(Category.this,GeneralMedicine.class);
        startActivity(k);
    }
}
