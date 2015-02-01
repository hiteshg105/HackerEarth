package com.shoto.shotohackerearth.app;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by PW on 2/1/2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<NewsFeedListRowHolder> {


    private Map<Integer,FeedItem> feedItemList;
    private Context mContext;

    public RecyclerAdapter(Context context, Map<Integer,FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public NewsFeedListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        NewsFeedListRowHolder mh = new NewsFeedListRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(NewsFeedListRowHolder feedListRowHolder, int i) {
        FeedItem mFeed = feedItemList.get(i);

        //Start fetching if not fetched already
       if(TextUtils.isEmpty(mFeed.getTitle())) {

           Log.v("Hitesh","AsyncTask :"+mFeed.getTitle());
           new fetchData().execute(i);

       }
        else {
           //Load data
           feedListRowHolder.title.setText(Html.fromHtml(mFeed.getTitle()));
           feedListRowHolder.author.setText(mFeed.getAuthor());
           feedListRowHolder.mUrl =mFeed.getDestinationUrl();
           feedListRowHolder.mContext=mContext;
       }
    }



    @Override
    public int getItemCount() {


        return (null != feedItemList ? feedItemList.size() : 0);
    }


    private class fetchData extends AsyncTask<Integer, Void, Boolean> {



        @Override
        protected Boolean doInBackground(Integer... params) {

            int index= params[0];
            FeedItem mItem=feedItemList.get(index);
            String mUrl=mItem.getUrl();

            try {
                /* forming th java.net.URL object */
                HttpURLConnection urlConnection = null;
                StringBuilder response = new StringBuilder();

                URL url = new URL(mUrl);
                Log.v("Hitesh","Itenm Count:"+url);
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
                    fromJson(response.toString(),index);
                    return true; // Successful
                } else {
                    return false; //"Failed to fetch data!";
                }

            } catch (Exception e) {
                Log.v("Hitesh", e.getLocalizedMessage() + "two");
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {

               notifyDataSetChanged();
            }

        }
        private void fromJson(String s,int index) {

            try {
                JSONObject jsonFeed = new JSONObject(s);
                FeedItem mItem=feedItemList.get(index);
                if (jsonFeed.has("by"))
                    mItem.setAuthor(jsonFeed.getString("by"));
                if (jsonFeed.has("title"))
                    mItem.setTitle(jsonFeed.getString("title"));
                if (jsonFeed.has("type"))
                    mItem.setType(jsonFeed.getString("type"));
                if (jsonFeed.has("url"))
                    mItem.setDestinationUrl(jsonFeed.getString("url"));
                if (jsonFeed.has("time"))
                    mItem.setTimeStamp(jsonFeed.getLong("time"));
                if (jsonFeed.has("score"))
                    mItem.setScore(jsonFeed.getInt("score"));
                feedItemList.put(index,mItem);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}

