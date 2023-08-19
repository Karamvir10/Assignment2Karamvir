package com.example.map524assignment2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ManagerActivity extends AppCompatActivity implements View.OnClickListener{
    Button history_btn;
    Button stock_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manager);

        history_btn = findViewById(R.id.historyBtn);

        stock_btn = findViewById(R.id.stockBtn);

        history_btn.setOnClickListener(this);

        stock_btn.setOnClickListener(this);

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
    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.historyBtn:
                Intent myIntent = new Intent(this, HistoryActivity.class);
                startActivity(myIntent);
                break;
            case R.id.stockBtn:
                Intent stock = new Intent(this, UpdateStock.class);
                startActivity(stock);
                break;
        }
    }
}