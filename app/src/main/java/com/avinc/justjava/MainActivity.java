package com.avinc.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import static com.avinc.justjava.R.id.hasChocolate;
import static com.avinc.justjava.R.id.hasWhippedCream;


public class MainActivity extends AppCompatActivity {

    int noCoffee = 2;
    int BaseCostCoffee = 5;
    int costOfCoffee = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View v){
        noCoffee = noCoffee + 1;
        costOfCoffee = noCoffee * BaseCostCoffee;
        displayQuantityandPrice(noCoffee, costOfCoffee);
        //cost.setText(NumberFormat.getCurrencyInstance().format(costOfCoffee));

    }


    public void decrement(View v){
        if(noCoffee > 1) {
            noCoffee = noCoffee - 1;
        }
        else{
            Toast.makeText(MainActivity.this,
                    "cups of coffee can't be zero", Toast.LENGTH_LONG).show();
        }
        costOfCoffee = noCoffee * BaseCostCoffee;
        displayQuantityandPrice(noCoffee, costOfCoffee);
    }
    public void submitOrder(View view){
        TextView coffee = (TextView)findViewById(R.id.valueP);
        String noOfCupsA = coffee.getText().toString();
        int x = Integer.parseInt(noOfCupsA); // x is no og coffee cups
        TextView cost = (TextView)findViewById(R.id.priceP);
        String coffeePriceA = cost.getText().toString();
        int y = Integer.parseInt(coffeePriceA); //price of total cups
        CheckBox whippedCream = (CheckBox)findViewById(hasWhippedCream);
        CheckBox chocolate = (CheckBox)findViewById(hasChocolate);
        String stateW, stateC = "Nope!";
        boolean hasWhippedCream = whippedCream.isChecked();
        boolean hasChocolate = chocolate.isChecked();
        if(hasWhippedCream) {
            stateW = "Yes!";
            int whippedCreamCost = x  * 1;
            y = whippedCreamCost + y;
        }else{
            stateW = "Nope";
        }
        if(hasChocolate) {
            stateC = "Yes!";
            int chocolateCost = x  * 2;
            y = chocolateCost + y;
        }else{
            stateC = "Nope";
        }
        EditText customerName = (EditText)findViewById(R.id.customerName);
        String cName = customerName.getText().toString();
        if(cName.isEmpty()){
            Toast.makeText(this,"Please tell us your name!",Toast.LENGTH_SHORT).show();
        }else {
            orderSummary(cName,stateC,stateW,x,y);
        }
    }

    private void displayQuantityandPrice(int quan, int totalPrice){
        TextView coffeeA = (TextView)findViewById(R.id.valueP);
        coffeeA.setText(""+quan);
        TextView cost = (TextView)findViewById(R.id.priceP);
        cost.setText(""+totalPrice);
    }

    public void orderSummary(String cName, String stateC, String stateW, int x, int y){
//            TextView order = (TextView) findViewById(R.id.customer_order);
             String orderSummary = "Name :" + cName + "\nCups of Coffee :" + x + " cups" + "\n With added Chocolate - " + stateC + "\n With added Whipped cream - " + stateW +"\n\nPrice : "+y+"\n Thanks";
//            order.setText(" " + orderSummary);

        Intent emailLaunch = new Intent(Intent.ACTION_SENDTO);
        emailLaunch.setData(Uri.parse("mailto:"));
        emailLaunch.putExtra(Intent.EXTRA_EMAIL, "justjava@gmail.com");
        emailLaunch.putExtra(Intent.EXTRA_SUBJECT, cName + " Coffee order");
        emailLaunch.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if(emailLaunch.resolveActivity(getPackageManager()) != null){
            startActivity(emailLaunch);
        }
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
