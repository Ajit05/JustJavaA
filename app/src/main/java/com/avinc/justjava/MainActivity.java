package com.avinc.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int noCoffee = 2;
    int BaseCostCoffee = 5;
    int costOfCoffee = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View v){
        noCoffee = noCoffee + 1;
        computeCost(noCoffee);
        //cost.setText(NumberFormat.getCurrencyInstance().format(costOfTea));

    }

    public void decrement(View v){
        if(noCoffee > 1) {
            noCoffee = noCoffee - 1;
        }
        else{
            Toast.makeText(MainActivity.this,
                    "Number of tea can't be zero", Toast.LENGTH_LONG).show();
        }

        computeCost(noCoffee);
    }
    private void computeCost(int noCups){
        int quantity = noCups;
        costOfCoffee = quantity * BaseCostCoffee;
        displayOrder(quantity, costOfCoffee);
    }

    private void displayOrder(int quan, int totalPrice){
        TextView tea = (TextView)findViewById(R.id.valueP);
        tea.setText("" +quan);
        TextView cost = (TextView)findViewById(R.id.priceP);
        cost.setText(NumberFormat.getCurrencyInstance().format(totalPrice));
    }

    public void orderSummary(View v){
        String stateW, stateC;
        CheckBox whippedCream = (CheckBox)findViewById(R.id.hasWhippedCream);
        CheckBox chocolate = (CheckBox)findViewById(R.id.hasChocolate);
        EditText customerName = (EditText)findViewById(R.id.customerName);
        String cName = customerName.getText().toString();
        boolean hasWhippedCream = whippedCream.isChecked();
        boolean hasChocolate = chocolate.isChecked();
        if(hasWhippedCream) {
            stateW = "Yes!";
        }else{
            stateW = "Nope";
        }
        if(hasChocolate) {
            stateC = "Yes!";
        }else{
            stateC = "Nope";
        }
        TextView order = (TextView)findViewById(R.id.customer_order);
        String orderSummary = cName+", Your " + noCoffee +" cups of coffee with :" + "\n Chocolate - "+ stateC + "\n Whipped cream - "+ stateW +  "\nwill be served on your table." + "\n Pay :" + costOfCoffee + "\nThank you!";
        order.setText(" "+orderSummary);
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
}
