package sdlab2200.de_xian.bbp.com.sdlab2200;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sdlab2200.de_xian.bbp.com.sdlab2200.database.History;

/**
 * Created by De Xian on 03-Feb-18.
 */

public class CustomHistoryListAdapter extends ArrayAdapter<History>{

    private Context context;
    private List<History> H;

    @Nullable
    @Override
    public History getItem(int position) {
        return super.getItem(position);
    }

    public CustomHistoryListAdapter(Context context, List<History> H) {
        super(context, R.layout.custom_listview_history, H);
        this.H = H;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View listViewItem = inflater.inflate(R.layout.custom_listview_history, parent, false);

        TextView TV_history = listViewItem.findViewById(R.id.TV_history);
        TextView TV_date = listViewItem.findViewById(R.id.TV_date);
        TextView TV_id = listViewItem.findViewById(R.id.TV_id);

        Log.i("XIAN","position "+position);

        /*for(int i=0;i<H.size();i++){
            Log.i("XIAN",H.get(i).getId()+" "+H.get(i).getHistory()+" "+H.get(i).getDate());
        }*/

        History h = H.get(position);

        Log.i("XIAN",h.getId()+" "+h.getHistory()+" "+h.getDate());


        TV_id.setText(""+h.getId());
        TV_history.setText(h.getHistory());
        TV_date.setText(h.getDate());

        return listViewItem;
    }
}
