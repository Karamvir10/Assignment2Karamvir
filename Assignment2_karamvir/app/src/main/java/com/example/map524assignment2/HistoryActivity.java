package com.example.map524assignment2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView historyRec;
    History historyManager;
    HistoryAdapter adapter;
    private HistoryAdapter.RecycleViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setOnClickListener();
        historyRec = findViewById(R.id.historyList);
        historyManager = ((MyApp)getApplication()).historyManager;
        adapter = new HistoryAdapter(historyManager.operationHistory,this, listener);
        
        historyRec.setAdapter(adapter);
        historyRec.setLayoutManager(new LinearLayoutManager(this));
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
    private void setOnClickListener() {
        listener = new HistoryAdapter.RecycleViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(),Details.class);
                //Log.d("Product", historyManager.operationHistory.get(position).name);
                intent.putExtra("Product", historyManager.operationHistory.get(position).name);
                intent.putExtra("Price", String.valueOf(
                        historyManager.operationHistory.get(position).price));
                intent.putExtra("Date", String.valueOf(
                        historyManager.operationHistory.get(position).date));
                startActivity(intent);
            }
        };
    }
}
