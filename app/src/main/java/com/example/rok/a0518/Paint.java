package com.example.rok.a0518;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    Button b1,b2,b3,b4,b5,b6;
    int value = 0;
    Mypainter mypainter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        mypainter = (Mypainter) findViewById(R.id.mypainter);
        checkpermission();
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
        b5 = (Button)findViewById(R.id.button2);
        b6 = (Button)findViewById(R.id.button3);
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
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypainter.setOperation(3);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypainter.screw();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = getExternalPath();
                mypainter.Save(path + "canvas.jpeg");

                Toast.makeText(getApplicationContext(), "저장완료!", Toast.LENGTH_LONG).show();


            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mypainter.clear();
                String path = getExternalPath();
                mypainter.Open(path+"canvas.jpeg");
                Toast.makeText(getApplicationContext(),"오픈 완료",Toast.LENGTH_SHORT).show();
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

                if(item.isChecked()){
                    item.setChecked(false);
                    value = 0;
                    mypainter.bluring(item,value);
                }
                else{
                    value = 1;
                    item.setChecked(true);
                    mypainter.bluring(item,value);
                }
                break;
            case R.id.coloring:
                if(item.isChecked()){
                    value = 0;
                    item.setChecked(false);
                    mypainter.coloring(item,value);
                }
                else {


                    value = 1;
                    item.setChecked(true);
                    mypainter.coloring(item, value);
                }
                break;
            case R.id.bigpen:
                if(item.isChecked()) {
                    value = 0;
                    item.setChecked(false);
                    mypainter.Bigpen(item,value);
                }
                else{
                    value = 1;
                    item.setChecked(true);
                    mypainter.Bigpen(item, value);
                }
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
                Toast.makeText(getApplicationContext(),"eraser",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void checkpermission() {

        int permissioninfo = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissioninfo == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "sd카드 쓰기권한있음", Toast.LENGTH_SHORT).show();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "권한 설명", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        }
    }

    public String getExternalPath() {
        String sdPath = "";
        String ext = Environment.getExternalStorageState();
        if (ext.equals(Environment.MEDIA_MOUNTED)) {
            sdPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";

        } else
            sdPath = getFilesDir() + ";" +
                    "";
        Toast.makeText(getApplicationContext(), sdPath, Toast.LENGTH_SHORT).show();
        return sdPath;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        String str = null;
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                str = "Sdcard쓰기권한 승인";
            else str = "SD card 쓰기권한 거부";
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

