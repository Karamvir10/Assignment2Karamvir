package com.example.map524assignment2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateStock extends AppCompatActivity implements View.OnClickListener{
    ItemsManager productManager;
    Button update;
    Button cancel;
    EditText qtyNumber;
    TextView text;
    int selected = -1;
    int qty = 0;
    ListView products;
    ListItemAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_stock);

         update = findViewById(R.id.updateQty);

         cancel = findViewById(R.id.updateCancel);

         qtyNumber = findViewById(R.id.input_qty);

         text = findViewById(R.id.prodType);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

         products = findViewById(R.id.productList);
         productManager = ((MyApp)getApplication()).itemManager;

         productAdapter = new ListItemAdapter(productManager.products, this);
         products.setAdapter(productAdapter);

         products.setOnItemClickListener((adapterView, view, i, l) -> {
             selected = i;
             text.setText(productManager.products.get(selected).name);
         });
         update.setOnClickListener(this);
         cancel.setOnClickListener(this);
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
    public void isNumber(){
        try {
            int q = Integer.parseInt(qtyNumber.getText().toString());
            if(q >= 0){
                qty = q;
                Log.d("Stock", " QTY: " + qty);
            }
        }catch (Exception error){
            CharSequence text = "All fields required";
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.updateQty:
                isNumber();
                if(selected >= 0){
                   productManager.products.get(selected).quant+=qty;
                   productAdapter.notifyDataSetChanged();
                   qty = 0;
                }else{
                    CharSequence text = "Please select a product";
                    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.updateCancel:
                this.finish();
                break;
        }
    }
}