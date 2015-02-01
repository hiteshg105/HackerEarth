package com.shoto.shotohackerearth.app;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    RecyclerAdapter mAdapter;
    HashMap<Integer, FeedItem> topStories = new HashMap<Integer, FeedItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        final String url = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";
        new AsyncDownloadFromHackerNews().execute(url);
    }

    public class AsyncDownloadFromHackerNews extends AsyncTask<String, Void, Boolean> {
        StringBuilder response = new StringBuilder();

        @Override
        protected Boolean doInBackground(String... params) {
            InputStream inputStream = null;
            Boolean result = false;

            HttpURLConnection urlConnection = null;

            try {
                /* forming th java.net.URL object */
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                /* for Get request */
                urlConnection.setRequestMethod("GET");
                int statusCode = urlConnection.getResponseCode();

                Log.v("Hitesh", String.valueOf(statusCode) + "one");
                /* 200 represents HTTP OK */
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }

                    Log.v("Hitesh", response.toString() + "two");
                    fromJson(response.toString());
                    result = true; // Successful
                } else {
                    result = false; //"Failed to fetch data!";
                }

            } catch (Exception e) {
                Log.v("Hitesh", e.getLocalizedMessage() + "two");
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {

                mAdapter = new RecyclerAdapter(MainActivity.this, topStories);
                mRecyclerView.setAdapter(mAdapter);


            }

        }
    }

    private void fromJson(String s) throws JSONException {

        JSONArray jsonResult = new JSONArray(s);
        for (int i = 0; i < jsonResult.length(); i++) {
            int mId = jsonResult.getInt(i);
            FeedItem mFeed = new FeedItem();
            mFeed.setUrl("https://hacker-news.firebaseio.com/v0/item/" + mId + ".json?print=pretty");
//            mFeed.setAdapter(mAdapter);
            Log.v("Hitesh", String.valueOf(mId));
            topStories.put(i, mFeed);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
