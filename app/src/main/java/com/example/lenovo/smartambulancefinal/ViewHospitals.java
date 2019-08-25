package com.example.lenovo.smartambulancefinal;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewHospitals extends AppCompatActivity {
    AsyncHttpClient client;
    RequestParams params;

    ArrayList<String> hosp;
    ArrayList<String>hospnamelist;
    ArrayList<String>hospnumberlist;
    ArrayList<String>maillist;
    ArrayList<String>addlist;
    ArrayList<String>citylist;

    ListView listview;
    LayoutInflater inflate;

    String url="http://srishti-systems.info/projects/smartambulance/viewhosp.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hospitals);
        listview=findViewById(R.id.lviewhosp);

        client=new AsyncHttpClient();
        params=new RequestParams();

        hosp=new ArrayList<>();
        hospnamelist=new ArrayList<>();
        hospnumberlist=new ArrayList<>();
        maillist=new ArrayList<>();
        addlist=new ArrayList<>();
        citylist=new ArrayList<>();

        Log.e("In","Out");

        client.get(url,params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                try{
                    Log.e("inn","outt");
                    JSONObject jobjmain=new JSONObject(content);
                    if (jobjmain.getString("status").equals("success")){
                        JSONArray jarray=jobjmain.getJSONArray("Hospital_details");
                        for(int i=0;i<jarray.length();i++){
                            JSONObject jobj=jarray.getJSONObject(i);
                            String nam=jobj.getString("name");
                            hospnamelist.add(""+nam);
                            String num=jobj.getString("contact");
                            hospnumberlist.add("" +num);
                            String mail=jobj.getString("mail");
                            maillist.add(""+mail);
                            String add=jobj.getString("address");
                            addlist.add(""+add);
                            String city=jobj.getString("city");
                            citylist.add(""+city);




                        }

                    }
                    adapter adp=new adapter();
                    listview.setAdapter(adp);


                }catch (Exception e ){

                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewCompat.setElevation(parent,130);
                Toast.makeText(ViewHospitals.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });


    }

    class adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return hospnamelist.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            inflate=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflate.inflate(R.layout.lviewhospxml,null);
            final TextView name=convertView.findViewById(R.id.hospnamexml);
            name.setText(hospnamelist.get(position));
            TextView number=convertView.findViewById(R.id.hospnumberxml);
            number.setText(hospnumberlist.get(position));
            TextView maill=convertView.findViewById(R.id.hospmailxml);
            maill.setText(maillist.get(position));
            TextView address=convertView.findViewById(R.id.hospaddxml);
            address.setText(addlist.get(position));
            TextView cityy=convertView.findViewById(R.id.hospcityxml);
            cityy.setText(citylist.get(position));

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ViewCompat.setElevation(name,100);
                }
            });

            return convertView;
        }
    }
}
