package sdlab2200.de_xian.bbp.com.sdlab2200;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import sdlab2200.de_xian.bbp.com.sdlab2200.database.DatabaseHandler;
import sdlab2200.de_xian.bbp.com.sdlab2200.database.History;

public class MainActivity extends AppCompatActivity {

    private Button btn_Scanner;
    private  Button btn_ocr;
    //private  Button btn_assingment01;
    private Button btn_encryptDecrypt;
    private Button btn_history;
    private Button btn_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TESTING
        String dateTime = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
        dateTime = converDateTime(dateTime);
        DatabaseHandler DB = new DatabaseHandler(getApplicationContext());
        History h = new History(dateTime,"Application Started");
        DB.addHistory(h);
        //TESTING END


        //initializing buttons
        btn_Scanner = findViewById(R.id.btn_scanner);
        btn_ocr = findViewById(R.id.btn_ocr);
        //btn_assingment01 = findViewById(R.id.btn_assingment01);
        btn_encryptDecrypt = findViewById(R.id.btn_encryptDecrypt);
        btn_history = findViewById(R.id.btn_history);
        btn_about = findViewById(R.id.btn_about);

        btn_Scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent at = new Intent(MainActivity.this, ScannerActivity.class);
                startActivity(at);
            }
        });

        btn_ocr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent at = new Intent(MainActivity.this, OcrActivity.class);
                startActivity(at);
            }
        });

        btn_encryptDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent at = new Intent(MainActivity.this, ActivityEncryptDecrypt.class);
                startActivity(at);
            }
        });

        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent at = new Intent(MainActivity.this, ActivityHistory.class);
                startActivity(at);
            }
        });

        /*btn_assingment01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent at = new Intent(MainActivity.this, ActivityAssinment01.class);
                startActivity(at);
            }
        });*/

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent at = new Intent(MainActivity.this, Activity_About.class);
                startActivity(at);
            }
        });

    }

    private String converDateTime(String dt) {

        String dateTime = "";
        //01 23 4567 8 910 1112 1314
        dateTime+= "Time: "+dt.substring(9,11)+":"+dt.substring(11,13);
        dateTime+= " Date: "+dt.substring(0,2)+"/"+dt.substring(2,4)+"/"+dt.substring(4,8);

        return dateTime;

    }
}
