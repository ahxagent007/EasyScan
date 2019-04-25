package sdlab2200.de_xian.bbp.com.sdlab2200;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityAssinment01 extends AppCompatActivity {

    Button btn_LinearLay,btn_RelativeLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assinment01);

        btn_LinearLay = findViewById(R.id.btn_LiniearLay);
        btn_RelativeLay = findViewById(R.id.btn_RelativeLay);

        btn_LinearLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.assignment01_linear_layout);
            }
        });

        btn_RelativeLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.assignment01_relative_layout);
            }
        });
    }

    /*
    @Override
    public void onBackPressed() {
        setContentView(R.layout.activity_assinment01);

        onCreate(new Bundle());
    }
    */
}
