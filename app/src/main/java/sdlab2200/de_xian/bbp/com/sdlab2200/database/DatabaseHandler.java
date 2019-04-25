package sdlab2200.de_xian.bbp.com.sdlab2200.database;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper{

	private static final int DATABASE_VERSION=1;
	private static final String DATABASE_NAME="ScanIt";
	private static final String TABLE_HISTORY="history";
	private static final String ID="id";
	private static final String DATE="date";
	private static final String SCAN_HISTORY="ScanHistory";

	public DatabaseHandler(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//Log.v("db created", "yes");
		String CREATE_HISTORY_TABLE="CREATE TABLE "+ TABLE_HISTORY +"("
									+ ID +" INTEGER PRIMARY KEY,"
									+ DATE +" TEXT,"
									+ SCAN_HISTORY +" TEXT" +")";

		db.execSQL(CREATE_HISTORY_TABLE);
		//Log.v("db created", "yes");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_HISTORY);
		onCreate(db);
	}

	public void addHistory(History h){
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "INSERT INTO "+TABLE_HISTORY+"("+DATE+","+SCAN_HISTORY+")" +"VALUES('"+h.getDate()+"','"+h.getHistory()+"')";

		db.execSQL(query);
		db.close();
	}

	public void clearAllHistory(){
		SQLiteDatabase db = getReadableDatabase();
		this.onUpgrade(db,1,1);
	}

	public History getSingleHistory(int id)	{
		SQLiteDatabase db = this.getReadableDatabase();

		String query = "SELECT "+ID+","+DATE+","+SCAN_HISTORY+" FROM "+TABLE_HISTORY+" WHERE "+ID+" = "+id;
		Cursor cursor=db.rawQuery(query, null);

		History h = null;
		if(cursor.moveToFirst()){
			h=new History(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
		}

		return h;
	}

	public List<History> getAllHistory(){
		List<History> historyList=new ArrayList<History>();

		String selectquery="SELECT * FROM "+ TABLE_HISTORY;

		SQLiteDatabase db=this.getReadableDatabase();

		Cursor cursor=db.rawQuery(selectquery, null);

		if(cursor.moveToFirst()){
			do{
				History H= new History(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
				//Log.i("XIAN",H.getId()+" "+H.getHistory()+" "+H.getDate());
				historyList.add(H);
			}while(cursor.moveToNext());
		}

		return historyList;
	}

	public void editHistory(History history){
		SQLiteDatabase DB = this.getWritableDatabase();

		Log.i("XIAN",""+history.getHistory());

		ContentValues data=new ContentValues();
		data.put(SCAN_HISTORY,history.getHistory());

		DB.update(TABLE_HISTORY, data, ID+" =" + history.getId(), null);

		DB.close();
	}

	public void deleteHistory(int id)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "DELETE From "+TABLE_HISTORY+" WHERE  ID="+id;
		db.execSQL(query);
		db.close();
	}

}
