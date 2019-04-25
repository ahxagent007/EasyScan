package sdlab2200.de_xian.bbp.com.sdlab2200;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sdlab2200.de_xian.bbp.com.sdlab2200.database.DatabaseHandler;
import sdlab2200.de_xian.bbp.com.sdlab2200.database.History;

public class ActivityHistory extends AppCompatActivity {

    private Button btn_cleaHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btn_cleaHistory = findViewById(R.id.btn_cleaHistory);

        final DatabaseHandler DB = new DatabaseHandler(getApplicationContext());

        final List<History> H = DB.getAllHistory();


        btn_cleaHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.clearAllHistory();
                finish();
                startActivity(getIntent());
            }
        });

        /*for(int i=0;i<H.size();i++){
            Log.i("XIAN",H.get(i).getId()+" "+H.get(i).getHistory()+" "+H.get(i).getDate());
        }*/

        final CustomHistoryListAdapter adapter = new CustomHistoryListAdapter(getApplicationContext(),H);
        final ListView LV_history = findViewById(R.id.LV_History);
        LV_history.setAdapter(adapter);

        LV_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //final int iID = i+1;

                final History hoo = adapter.getItem(i);
                final String HISTORY = hoo.getHistory();
                //Toast.makeText(getApplicationContext(),"ITEM: "+id, Toast.LENGTH_LONG).show();
                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityHistory.this);

                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage(HISTORY)
                        .setTitle("HISTORY");

                // Add the buttons
                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, int id) {
                        // User clicked OK button

                        AlertDialog.Builder myBuilder = new AlertDialog.Builder(ActivityHistory.this);
                        View myView = getLayoutInflater().inflate(R.layout.custom_dialog_history_edit, null);

                        final EditText editHistory = myView.findViewById(R.id.et_editHistory);
                        Button editDone = myView.findViewById(R.id.btn_editDone);

                        editHistory.setText(HISTORY);
                        myBuilder.setView(myView);
                        final AlertDialog Dialog = myBuilder.create();

                        editDone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                History hhhh = new History(hoo.getId(),hoo.getDate(),editHistory.getText().toString());
                                DB.editHistory(hhhh);
                                Toast.makeText(getApplicationContext(),"History Updated",Toast.LENGTH_SHORT).show();
                                Dialog.cancel();
                                finish();
                                startActivity(getIntent());
                            }
                        });
                        Dialog.show();
                    }
                });
                builder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        DB.deleteHistory(hoo.getId());
                        finish();
                        startActivity(getIntent());
                    }
                });

                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

    }
}

