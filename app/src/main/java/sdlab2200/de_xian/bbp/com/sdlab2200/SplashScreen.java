package sdlab2200.de_xian.bbp.com.sdlab2200;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        LogoLauncher LG = new LogoLauncher();

        LG.start();


    }

    class LogoLauncher extends Thread{
        @Override
        public void run() {
            try{
                sleep(1000);
            }catch(Exception e){
                Log.i("XIAN",""+e);
            }

            Intent i = new Intent(SplashScreen.this,MainActivity.class);
            startActivity(i);
            SplashScreen.this.finish();

        }
    }


}


