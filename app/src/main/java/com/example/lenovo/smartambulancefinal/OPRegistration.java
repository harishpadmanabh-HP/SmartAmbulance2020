package com.example.lenovo.smartambulancefinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OPRegistration extends AppCompatActivity {
TextInputLayout name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opregistration);

        name=findViewById(R.id.bookname);
        email=findViewById(R.id.bookemail);



    }

    public void bookingbutton(View view) {
        SharedPreferences sharedPreference=getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreference.edit();
        editor.putString("key1",email.getEditText().getText().toString());
        editor.commit();
        if(name.getEditText().getText().toString().isEmpty())
        {
            name.setError("enter name");
        }
        else if(email.getEditText().getText().toString().isEmpty())
        {
            email.setError("enter email");
        }
       else {
            Intent i = new Intent(OPRegistration.this, Category.class);

            startActivity(i);

        }
    }
}
