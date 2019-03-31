package com.example.lenovo.smartambulancefinal;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewBloodDonors extends AppCompatActivity {

    AsyncHttpClient client;
    RequestParams params;

    ArrayList<String> donor;
    ArrayList<String>donornamelist;
    ArrayList<String>donoragelist;
    ArrayList<String>donorgrouplist;
    ArrayList<String>phonelist;
    ArrayList<String>addlist;

    ListView listview;
    LayoutInflater inflate;

    String url="http://srishti-systems.info/projects/smartambulance/viewdonor.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_blood_donors);

        listview=findViewById(R.id.lview);

        client=new AsyncHttpClient();
        params=new RequestParams();

        donor=new ArrayList<>();
        donornamelist=new ArrayList<>();
        donoragelist=new ArrayList<>();
        donorgrouplist=new ArrayList<>();
        phonelist=new ArrayList<>();
        addlist=new ArrayList<>();

        Log.e("In","Out");

        client.get(url,params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                try{
                    Log.e("inn","outt");
                    JSONObject jobjmain=new JSONObject(content);
                    if (jobjmain.getString("status").equals("success")){
                        JSONArray jarray=jobjmain.getJSONArray("Blood Donor Details");
                        for(int i=0;i<jarray.length();i++){
                            JSONObject jobj=jarray.getJSONObject(i);
                            String nam=jobj.getString("name");
                            donornamelist.add("Name :"+nam);
                            String donorage=jobj.getString("age");
                            donoragelist.add("Age :" +donorage);
                            String group=jobj.getString("blood");
                            donorgrouplist.add("Blood Group :"+group);
                            String phn=jobj.getString("phone");
                            phonelist.add("Contact No. :"+phn);
                            String add=jobj.getString("address");
                            addlist.add("Address :"+add);




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
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder AB = new AlertDialog.Builder(ViewBloodDonors.this);
                AB.setMessage("Contact blood donar ? The Donar will get an sms with yor requirement").setCancelable(false).setPositiveButton("SEND SMS ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String number=phonelist.get(position).substring(13);
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(number, null, "Urgent requirement for blood from the sender of this sms. Please contact back .", null, null);


                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog A = AB.create();
                A.setTitle("EXIT SCREEN");
                A.show();







                     }
        });




    }

    class adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return donornamelist.size();
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
            convertView=inflate.inflate(R.layout.donorlviewxml,null);
            TextView name=convertView.findViewById(R.id.donornamexml);
            name.setText(donornamelist.get(position));
            TextView donorage=convertView.findViewById(R.id.donoragexml);
            donorage.setText(donoragelist.get(position));
            TextView donorgroup=convertView.findViewById(R.id.donorgroupxml);
            donorgroup.setText(donorgrouplist.get(position));
            TextView donorphonelist=convertView.findViewById(R.id.donorphonexml);
            donorphonelist.setText(phonelist.get(position));
            TextView donoradd=convertView.findViewById(R.id.donoraddxml);
            donoradd.setText(addlist.get(position));

            return convertView;
        }
    }
}

