package com.example.pixaflip;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pixaflip.sql.MyDbHelper;
import com.example.pixaflip.sql.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class DisplayCovidActivity extends AppCompatActivity {
    private static String context;
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private String getDateTime()
    {
        SimpleDateFormat sd=new SimpleDateFormat ("yyyy-MM-ddcHH:mm:ss", Locale.getDefault ());
        Date d=new Date ();
        return sd.format(d);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_covid);
        mTextViewResult = findViewById(R.id.text_view_result);
        //  Button buttonParse = findViewById(R.id.showStatewise);
        final   Button state=findViewById ( R.id.showStatewise );

        MyDbHelper nn=new MyDbHelper ( DisplayCovidActivity.this );
        // adduseract
        nn.adduseract ( getLocalClassName (),getPackageCodePath (),getDateTime () );

        // SubmitButton state=findViewById(R.id.showStatewise);

        mQueue = Volley.newRequestQueue(this);
        jsonParse();
        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDbHelper nn=new MyDbHelper ( DisplayCovidActivity.this );
                nn.adduseract( getLocalClassName (),getPackageCodePath (),getDateTime () );
                MyDbHelper db = new MyDbHelper(DisplayCovidActivity.this);
                model m=new model();
                String from2 = m.setFrom1 ( "DisplayCovidActivity" );
                String to2=m.setTo1 ( "ShowStates" );
                String timestamp2 =m.setTimestamp ( getDateTime () );
                // model pro = new model(from2,to2,timestamp2);
                db.Adduser (m);
                startActivity(new Intent(DisplayCovidActivity.this, ShowStates.class));
            }
        });
    }








    public void jsonParse() {
        String url = "https://api.rootnet.in/covid19-in/stats/latest";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject offerObject = response.getJSONObject("data");
                            JSONObject dataObject = offerObject.getJSONObject("summary");

                            String total = dataObject.getString("total");
                            String caseI=dataObject.getString("confirmedCasesIndian");
                            String caseF=dataObject.getString("confirmedCasesForeign");
                            String discharged=dataObject.getString("discharged");
                            String deaths=dataObject.getString("deaths");
                            String notLoc=dataObject.getString("confirmedButLocationUnidentified");

                            mTextViewResult.append("total:"+total+"\ncase in india:"+caseI+"\ncase in foreign"+caseF+
                                    "\ndischarged:"+discharged+"\ndeaths:"+deaths+"\nconfirmed but location undefine:"+notLoc+
                                    "\n\n\n");

                            //  JSONArray obj = offerObject.getJSONArray("unofficial-summary");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}