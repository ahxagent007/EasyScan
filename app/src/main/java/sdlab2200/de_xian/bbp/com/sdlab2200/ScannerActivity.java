package sdlab2200.de_xian.bbp.com.sdlab2200;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import sdlab2200.de_xian.bbp.com.sdlab2200.database.DatabaseHandler;
import sdlab2200.de_xian.bbp.com.sdlab2200.database.History;

import static android.Manifest.permission.CAMERA;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;

    private DatabaseHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       /* //TESTING
        String dateTime = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
        dateTime = converDateTime(dateTime);
        String test = "Fixed TEST";
        DB = new DatabaseHandler(getApplicationContext());
        History h = new History(dateTime,test);
        DB.addHistory(h);

        //TESTING END*/


        //making a View for activity to show
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if(checkPermission()){
                Toast.makeText(ScannerActivity.this,"Permission is granted!!",Toast.LENGTH_LONG).show();
            }else{
                requestPermission();
            }
        }
    }

    private boolean checkPermission(){
        //Check if the permission is granted or not
        return(ContextCompat.checkSelfPermission(ScannerActivity.this, CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission(){
        //ask for permission
        ActivityCompat.requestPermissions(this,new String[]{CAMERA},REQUEST_CAMERA);
    }

    //Starting the app first time will ask for permission (only one time)

    @Override
    public void onRequestPermissionsResult(int requestCode, String permission[], int grandResults[]){

        switch (requestCode){
            case REQUEST_CAMERA:
                if(grandResults.length > 0){
                    boolean cameraAccepted = grandResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted){
                        Toast.makeText(ScannerActivity.this,"Permissin Granted",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(ScannerActivity.this,"Permissin Denied",Toast.LENGTH_LONG).show();
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            if(shouldShowRequestPermissionRationale(CAMERA)){
                                displayAlertMessage("You need to allow access for both permission", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
                                        }
                                    }
                                });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkPermission()){
                if(scannerView == null){
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }else{
                requestPermission();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //if the activity stop camera will get free
        scannerView.stopCamera();
    }

    //function for popup alert message
    public void displayAlertMessage(String message, DialogInterface.OnClickListener listener){
        new AlertDialog.Builder(ScannerActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", listener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void handleResult(Result result) {
        final String scanResult = result.getText();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                scannerView.resumeCameraPreview(ScannerActivity.this);
            }
        });
        builder.setNeutralButton("Visit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent at = new Intent(Intent.ACTION_VIEW, Uri.parse(scanResult));
                startActivity(at);
            }
        });
        String dateTime = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());

        dateTime = converDateTime(dateTime);

        DB = new DatabaseHandler(getApplicationContext());
        History h = new History(dateTime,scanResult);
        DB.addHistory(h);
        Log.i("XIAN",""+dateTime);

        builder.setMessage(scanResult);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private String converDateTime(String dt) {

        String dateTime = "";
        //01 23 4567 8 910 1112 1314
        dateTime+= "Time: "+dt.substring(9,11)+":"+dt.substring(11,13);
        dateTime+= " Date: "+dt.substring(0,2)+"/"+dt.substring(2,4)+"/"+dt.substring(4,8);

        return dateTime;

    }
}


















