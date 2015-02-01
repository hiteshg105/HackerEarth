package com.shoto.shotohackerearth.app;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by PW on 2/1/2015.
 */
public class FeedItem {

    String mTitle;
    String mAuthor;
    int mScore;
    long mTimeStamp;
    String mType;
    String mUrl;
    String mDestinationUrl;
    private RecyclerAdapter mAdapter;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int mScore) {
        this.mScore = mScore;
    }

    public long getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(long mTimeStamp) {
        this.mTimeStamp = mTimeStamp;
    }


    public String getDestinationUrl() {
        return mDestinationUrl;
    }

    public void setDestinationUrl(String mDestinationUrl) {
        this.mDestinationUrl = mDestinationUrl;
    }

//    public void setAdapter(RecyclerAdapter mAdapter) {
//
//        this.mAdapter = mAdapter;
//    }
//
//    public void loadData() {
//        if (mUrl == null)
//            return;
//        else {
//            new fetchData().execute(mUrl);
//
//        }
//    }
//
//    private class fetchData extends AsyncTask<String, Void, Boolean> {
//
//        @Override
//        protected Boolean doInBackground(String... params) {
//            String mUrl = params[0];
//            try {
//                /* forming th java.net.URL object */
//                HttpURLConnection urlConnection = null;
//                StringBuilder response = new StringBuilder();
//
//                URL url = new URL(mUrl);
//                Log.v("Hitesh","Itenm Count:"+url);
//                urlConnection = (HttpURLConnection) url.openConnection();
//
//                /* for Get request */
//                urlConnection.setRequestMethod("GET");
//                int statusCode = urlConnection.getResponseCode();
//
//                Log.v("Hitesh", String.valueOf(statusCode) + "one");
//                /* 200 represents HTTP OK */
//                if (statusCode == 200) {
//                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//
//                    String line;
//                    while ((line = r.readLine()) != null) {
//                        response.append(line);
//                    }
//
//                    Log.v("Hitesh", response.toString() + "two");
//                    fromJson(response.toString());
//                    return true; // Successful
//                } else {
//                    return false; //"Failed to fetch data!";
//                }
//
//            } catch (Exception e) {
//                Log.v("Hitesh", e.getLocalizedMessage() + "two");
//            }
//            return false;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean result) {
//            super.onPostExecute(result);
//            if (result) {
//
//                mAdapter.notifyDataSetChanged();
//            }
//
//        }
//    }
//
//    private void fromJson(String s) {
//
//        try {
//            JSONObject jsonFeed = new JSONObject(s);
//            if (jsonFeed.has("by"))
//                mAuthor = jsonFeed.getString("by");
//            if (jsonFeed.has("title"))
//                mTitle = jsonFeed.getString("title");
//            if (jsonFeed.has("type"))
//                mType = jsonFeed.getString("type");
//            if (jsonFeed.has("url"))
//                mDestinationUrl = jsonFeed.getString("url");
//            if (jsonFeed.has("time"))
//                mTimeStamp = jsonFeed.getLong("time");
//            if (jsonFeed.has("score"))
//                mScore = jsonFeed.getInt("score");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }


}
