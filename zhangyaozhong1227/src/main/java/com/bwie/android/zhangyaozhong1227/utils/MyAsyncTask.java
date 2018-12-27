package com.bwie.android.zhangyaozhong1227.utils;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyAsyncTask extends AsyncTask<String,Void,String> {
    public interface Icallback{
        void getData(String s);
    }
    private Icallback icallback;

    public MyAsyncTask(Icallback icallback) {
        this.icallback = icallback;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url =new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();
                String dataStr = StreamToString.streamChange(inputStream, "utf-8");
                return dataStr;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        icallback.getData(s);
    }
}
