package com.example.android.practiceset2;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Abhishek Panwar on 7/14/2017.
 */

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    String word = MainActivity.keyword.getText().toString();

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String key = "http://api.alquran.cloud/v1/search/"+word+"/all/en";
            //URL url = new URL("http://api.alquran.cloud/v1/search/patience/all/en");
            URL url = new URL(key);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

//            JSONArray JA = new JSONArray(data);
//            for(int i =0 ;i <JA.length(); i++){
//                JSONObject JO = (JSONObject) JA.get(i);
//                singleParsed =  "Name:" + JO.get("name") + "\n"+
//                        "Password:" + JO.get("password") + "\n"+
//                        "Contact:" + JO.get("contact") + "\n"+
//                        "Country:" + JO.get("country") + "\n";
//
//                dataParsed = dataParsed + singleParsed +"\n" ;
//
//
//            }

           /* JSONObject mainObject = new JSONObject(data);
            JSONObject dataSet = mainObject.getJSONObject("data");
            int counts = dataSet.getInt("count");
            dataParsed = "count: "+ counts;
            JSONArray match = dataSet.getJSONArray("matches");
            for (int i = 0; i<match.length();i++)
            {
                JSONObject JO = match.getJSONObject(i);
                int noInSurah = JO.getInt("numberInSurah");
                JSONObject JOT = JO.getJSONObject("surah");
                String texts = JOT.getString("text");
                String engName = JOT.getString("englishName");

                singleParsed = "Number in surah: "+ noInSurah + "\n"+
                        "text: "+texts+"\n"+
                        "English Name: "+engName+"\n";

                dataParsed = dataParsed + singleParsed +"\n" ;

            }*/
            JSONObject JO1 = new JSONObject(data);
            JSONObject JO2 = JO1.getJSONObject("data");
            int counts = JO2.getInt("count");
            dataParsed = "count: "+ counts +"\n";
            JSONArray JA = JO2.getJSONArray("matches");
            for (int i = 0; i < JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                //"SURAH NAME: " + JO.get("englishName") + "\n\n" +

                JSONObject JOT = JO.getJSONObject("surah");
                singleParsed = "SURAH NAME: " + JOT.get("englishName") + "\n\n" +
                        "AYAH NUMBER : " + JO.get("numberInSurah") + "\n\n" +
                        "TEXT : " + JO.get("text") + "\n\n\n\n";
                dataParsed = dataParsed + singleParsed;

            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.data.setText(this.dataParsed);

    }
}
