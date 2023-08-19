package com.example.map524assignment2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ListView listView;
    TextView itemName;
    TextView quantNum;
    TextView totAmount;
    Button buyButton;
    Button managerButton;
    History history;
    ItemsManager prodManager;
    NumberPicker picker;
    ListItemAdapter adapter;
    Items selectedItem;
    int selected;
    double total = 0.0;
    Items pants = new Items("Pants", 10, 20.44);
    Items shoes = new Items("Shoes", 100, 10.44);
    Items hats = new Items("Hats", 50, 5.6);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buyButton = findViewById(R.id.buy);
        managerButton = findViewById(R.id.manager);
        picker = findViewById(R.id.numberPicker);
        picker.setMaxValue(100);
        picker.setMinValue(1);
        picker.setValue(1);

        prodManager = ((MyApp)getApplication()).itemManager;
        history = ((MyApp)getApplication()).historyManager;
        // Setting List View
        listView = findViewById(R.id.listview);
        // Setting Text Views
        itemName = findViewById(R.id.itemname);
        quantNum = findViewById(R.id.qty);
        totAmount = findViewById(R.id.total);
        // Setting Items
        prodManager.addProducts(pants);
        prodManager.addProducts(shoes);
        prodManager.addProducts(hats);
        picker.setOnValueChangedListener(((numberPicker, i, i1) -> {
            quantNum.setText(String.valueOf(i1));
            if(itemName.getText().toString().equals("Product Type")){
                CharSequence text = "Item is not selected";
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            }else{
                if(selectedItem.quant < i1){
                    CharSequence text = "Not enough products in stock";
                    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                }
                total = selectedItem.price * Double.parseDouble(quantNum.getText().toString());
                totAmount.setText(String.valueOf(total));
            }
        }));
        adapter = new ListItemAdapter(prodManager.products,this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(((adapterView, view, i, l) -> {
            adapter.notifyDataSetChanged();
            selected = i;
            selectedItem = prodManager.products.get(selected);
            itemName.setText(selectedItem.name);
            if(!quantNum.getText().toString().equals("Quantity")) {
                total = selectedItem.price * Double.parseDouble(quantNum.getText().toString());
                totAmount.setText(String.valueOf(total));
            }
        }));
        buyButton.setOnClickListener(this);
        managerButton.setOnClickListener(this);
    }
    public boolean validate(){
        boolean valid = false;
        if(!itemName.getText().toString().equals("Product Type")
                && !totAmount.getText().toString().equals("Total")
                && !quantNum.getText().toString().equals("Quantity")){
            valid = true;
        }
        return valid;
    }
    public boolean isEnough(){
        boolean enough = false;
        if(Integer.parseInt(quantNum.getText().toString()) <= selectedItem.quant){
            enough = true;
        }
        return enough;
    }
    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.buy:
                if(validate()){
                    if(isEnough()){
                        int qty = Integer.parseInt(quantNum.getText().toString());
                        total = selectedItem.price * Double.parseDouble(quantNum.getText().toString());
                        history.addItem(itemName.getText().toString(), qty, total);
                        AlertDialog.Builder build = new AlertDialog.Builder(this);
                        build.setTitle("Thank You for purchase");
                        build.setMessage("Your purchase is " + qty + " " + selectedItem.name + " for " + totAmount.getText().toString());
                        build.show();
                        switch (selectedItem.name) {
                            case "Pants":
                                prodManager.products.get(0).quant-=qty;
                                break;
                            case "Shoes":
                                prodManager.products.get(1).quant-=qty;
                                break;
                            case "Hats":
                                prodManager.products.get(2).quant-=qty;
                                break;
                        }
                        adapter.notifyDataSetChanged();
                    }else{
                        CharSequence text = "Not enough products in stock";
                        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    CharSequence text = "All fields are required";
                    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.manager:
                Intent myIntent = new Intent(this, ManagerActivity.class);
                startActivity(myIntent);
                break;
        }
    }

}