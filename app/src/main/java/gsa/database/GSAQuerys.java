package gsa.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Equipo on 16/05/2016.
 */
public class GSAQuerys extends GSAReaderDbHelper{

    SQLiteDatabase database;

    public GSAQuerys(Context context) {
        super(context);
    }

    public int checkLogin (String user, String password) {
        database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT ACCESO FROM USUARIOSSISTEMA WHERE NOMBRE='" + user + "' AND PASSWORD='" +
                password + "';", null);

        int acceso = -1;

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                acceso = c.getInt(0);
            }
            while(c.moveToNext());
        }
        return acceso;
    }

    public void addUsuarioSistema(String usuario, String password, int acceso){
        database = getWritableDatabase();
        database.execSQL("INSERT INTO USUARIOSSISTEMA (NOMBRE, PASSWORD, ACCESO) VALUES ('" +
        usuario + "', '" + password + "', '" + acceso + "');");
    }

    public ArrayList<String> getUsuariosSistema() {
        database = getReadableDatabase();
        int campos = 3;
        ArrayList<String> elementos = new ArrayList<>();
        //String[] entrada = new String[campos];

        Cursor c = database.rawQuery("SELECT * FROM USUARIOSSISTEMA", null);

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                for(int i=0; i<campos; i++){
                    elementos.add(c.getString(i+1));
                }

            }
            while(c.moveToNext());
        }
        return elementos;
    }
}
