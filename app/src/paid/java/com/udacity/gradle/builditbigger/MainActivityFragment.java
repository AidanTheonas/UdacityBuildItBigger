package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.udacity.gradle.builditbigger.helpers.JokeResponse;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeResponse {
    
    public MainActivityFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        return root;
    }
    
    @OnClick(R.id.btn_tell_joke)
    void tellJoke() {
        new EndPointsAsyncTask(getContext(),this).execute();
    }
    
    
    @Override
    public void jokeResponse(String joke) {
        Intent intent = new Intent(getContext(), ViewJoke.class);
        intent.putExtra(JOKE_INTENT,joke);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
