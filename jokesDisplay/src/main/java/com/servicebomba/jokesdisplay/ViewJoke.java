package com.servicebomba.jokesdisplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewJoke extends AppCompatActivity {
    public static final String JOKE_INTENT = "joke_intent";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_joke);
        TextView tvDisplayJoke = findViewById(R.id.tv_display_joke);
        Intent intent = getIntent();
        if(intent.hasExtra(JOKE_INTENT)){
            String joke = intent.getStringExtra(JOKE_INTENT);
            tvDisplayJoke.setText(joke);
        }
    }
}
