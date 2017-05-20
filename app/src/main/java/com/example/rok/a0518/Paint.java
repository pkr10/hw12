package com.example.rok.a0518;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class Paint extends AppCompatActivity {
    CheckBox c1;
    Button b1,b2,b3,b4;
    int value = 0;
    Mypainter mypainter;
    String filename = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        mypainter = (Mypainter) findViewById(R.id.mypainter);
        c1 = (CheckBox)findViewById(R.id.checkBox);
        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                  if(isChecked){
                      mypainter.setCheckable(1);
                  }
                  else{
                      mypainter.setCheckable(0);

              }
            }
        });
        b1 = (Button)findViewById(R.id.button8);
        b2 = (Button)findViewById(R.id.button7);
        b3 = (Button)findViewById(R.id.button6);
        b4 = (Button)findViewById(R.id.button5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypainter.setOperation(1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypainter.setOperation(2);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.bluring:
                value = 1;
                item.setChecked(true);
                mypainter.bluring(item,value);
                break;
            case R.id.coloring:
                value = 1;
                item.setChecked(true);
                mypainter.coloring(item,value);
                break;
            case R.id.bigpen:
                value =1;
                item.setChecked(true);
                mypainter.Bigpen(item,value);
                break;
            case R.id.RED:
                mypainter.setRed();
                break;
            case R.id.Blue:
                mypainter.setBlue();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onmyclick(View view) {
        switch(view.getId()){
            case R.id.button4:
                mypainter.clear();
                break;
            case R.id.button5:
                mypainter.screw();
                break;
            case R.id.button6:
                mypainter.scale();
                break;
            case R.id.button2:
                mypainter.Save(filename);
                break;
        }
    }
}
