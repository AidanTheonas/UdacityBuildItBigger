package com.udacity.gradle.builditbigger.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * JokeTellingApp Created by aidan on 21/07/2018.
 */
public class EndPointsAsyncTask extends AsyncTask<Void, Void, String> {
    @SuppressLint("StaticFieldLeak")
    private Context mContext;
    private JokeResponse jokeResponse;
    private static MyApi apiService = null;

    public EndPointsAsyncTask(Context context, JokeResponse jokeResponse) {
        mContext = context;
        this.jokeResponse = jokeResponse;
    }

    @Override
    protected String doInBackground(Void... params) {
        // If myApiService is null, get an instance of it
        try {
        if (apiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(mContext.getResources().getString(R.string.gce_url))
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) {
                            request.setDisableGZipContent(true);
                        }
                    });

            apiService = builder.build();
        }
            return apiService.retrieveJoke().execute().getData();
        } catch (Exception e) {
            return mContext.getResources().getString(R.string.server_error);
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        super.onPostExecute(joke);
        jokeResponse.jokeResponse(joke);
    }
}
