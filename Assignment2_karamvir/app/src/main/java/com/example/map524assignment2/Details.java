package com.example.map524assignment2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView textView = findViewById(R.id.transaction_details);

        String title = "Title is not set";
        String date = "Time is not set";
        String price = "Price is not set";
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            title = extras.getString("Product");
            date = extras.getString("Date");
            price = extras.getString("Price");
        }
        textView.setText("Product Name: " + title + "\nAmount: " + price + "\nDate: " + date);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}