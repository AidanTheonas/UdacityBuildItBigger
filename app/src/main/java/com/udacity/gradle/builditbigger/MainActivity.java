package com.udacity.gradle.builditbigger;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.servicebomba.jokesdisplay.ViewJoke;
import com.udacity.gradle.builditbigger.helpers.EndPointsAsyncTask;
import com.udacity.gradle.builditbigger.helpers.JokeResponse;
import com.udacity.gradle.builditbigger.helpers.JokeTellingIdlingResource;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.servicebomba.jokesdisplay.ViewJoke.JOKE_INTENT;


public class MainActivity extends AppCompatActivity implements JokeResponse {
    JokeTellingIdlingResource jokeTellingIdlingResource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getJokeTellingIdlingResource();
    }

    public JokeTellingIdlingResource getJokeTellingIdlingResource() {
        if (jokeTellingIdlingResource == null) {
            jokeTellingIdlingResource = new JokeTellingIdlingResource();
        }
        return jokeTellingIdlingResource;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_tell_joke)
    void tellJoke() {
        jokeTellingIdlingResource.setIdleState(false);
        new EndPointsAsyncTask(this,this).execute();
    }


    @Override
    public void jokeResponse(String joke) {
        Intent intent = new Intent(this, ViewJoke.class);
        intent.putExtra(JOKE_INTENT,joke);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        jokeTellingIdlingResource.setIdleState(true);
    }
}
