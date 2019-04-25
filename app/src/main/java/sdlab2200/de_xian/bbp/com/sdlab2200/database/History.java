package sdlab2200.de_xian.bbp.com.sdlab2200.database;

/**
 * Created by De Xian on 03-Feb-18.
 */

public class History {

    int id;
    String date;
    String history;

    public History(){}

    public History(int id, String date, String history){

        this.id = id;
        this.date = date;
        this.history = history;

    }

    public History(String date, String history){

        this.id = id;
        this.date = date;
        this.history = history;

    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getHistory() {
        return history;
    }
}

