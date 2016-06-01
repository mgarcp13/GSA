package gsa.database;

import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Equipo on 16/05/2016.
 */
public class GSADatabase implements Parcelable{

    SQLiteDatabase database;
    ArrayList<SQLiteDatabase> db;

    public GSADatabase(){

    }

    public GSADatabase (SQLiteDatabase db){
        this.database = db;
    }

    protected GSADatabase(Parcel in) {
        db = new ArrayList<>();
        db.add(this.database);
    }

    public static final Creator<GSADatabase> CREATOR = new Creator<GSADatabase>() {
        @Override
        public GSADatabase createFromParcel(Parcel in) {
            return new GSADatabase(in);
        }

        @Override
        public GSADatabase[] newArray(int size) {
            return new GSADatabase[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(db);
    }
}
