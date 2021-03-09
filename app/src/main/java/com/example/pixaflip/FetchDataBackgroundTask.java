package com.example.pixaflip;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.HashMap;

public class FetchDataBackgroundTask extends AsyncTask<String,String,String> {

    String url = "https://api.rootnet.in/covid19-in/stats/latest";
    String res = "";
    Context context;
    JSONObject jsonObject = null;
    JSONParse jsonParse = null;
    String status;

    public FetchDataBackgroundTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            HashMap<String, String> para = new HashMap<String, String>();

//            para.put("name", name); // format to put parameter in map

            jsonParse = new JSONParse();

            res = jsonParse.makeHttpRequest(url, para); // getting response from URL in res

            jsonObject = new JSONObject(res);
            status = jsonObject.getString("success");

            return "done";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "null";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s.equalsIgnoreCase("done")){
            System.out.println(res); //Printing the response from API
            System.out.println("Status : "+status);
        }
    }
}
