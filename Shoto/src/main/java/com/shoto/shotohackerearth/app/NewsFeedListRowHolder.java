package com.shoto.shotohackerearth.app;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by PW on 2/1/2015.
 */
public class NewsFeedListRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected TextView title;
    protected TextView author;
    protected String mUrl;
    protected Context mContext;

    public NewsFeedListRowHolder(View view) {
        super(view);
        view.setOnClickListener(this);
        this.title = (TextView) view.findViewById(R.id.title);
        this.author = (TextView) view.findViewById(R.id.author);
    }

    @Override
    public void onClick(View v) {
        if (!mUrl.startsWith("http://") && !mUrl.startsWith("https://"))
            mUrl = "http://" + mUrl;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrl));
        try {
            mContext.startActivity(browserIntent);
        } catch (ActivityNotFoundException ex) {

        }
    }
}
