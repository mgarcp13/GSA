package gsa.database;

import android.content.ContentValues;
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

    public void addCliente(String nombre, String apellidos, String dni, String dir, String poblacion,
                           String provincia, String cpostal, String email, String telefono) {
        database = getWritableDatabase();
        database.execSQL("INSERT INTO CLIENTES (NOMBRE, APELLIDOS, DNI, DIRECCION, POBLACION, PROVINCIA, CODPOSTAL, " +
                "EMAIL, TELEFONO) VALUES ('" + nombre + "', '" + apellidos + "', '" + dni + "', '" + dir + "', '" +
                poblacion + "', '" + provincia + "', '" + cpostal + "', '" + email + "', '" + telefono + "');");
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

    public void editarCliente(String nombre, String apellidos, String dni, String dir, String poblacion,
                              String provincia, String cpostal, String email, String telefono, int id) {
        database = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("NOMBRE", nombre);
        valores.put("APELLIDOS", apellidos);
        valores.put("DNI", dni);
        valores.put("DIRECCION", dir);
        valores.put("POBLACION", poblacion);
        valores.put("PROVINCIA", provincia);
        valores.put("CODPOSTAL", cpostal);
        valores.put("EMAIL", email);
        valores.put("TELEFONO", telefono);

        database.update("CLIENTES", valores, "ID_CLIENTE="+id+"", null);

        /*database.execSQL("UPDATE USUARIOSSISTEMA SET NOMBRE=" + nombre + ", APELLIDOS=" + apellidos +
                ", DNI=" + dni + ", DIRECCION=" + dir + ", POBLACION=" + poblacion +
                ", PROVINCIA=" + provincia + ", CODPOSTAL=" + cpostal + ", EMAIL=" + email + ", TELEFONO=" + telefono +
                "WHERE ID=" + id + "", null);*/
    }

    public void editarUsuarioSistema(String usuario, String password, int acceso, int id) {
        database = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("NOMBRE", usuario);
        valores.put("PASSWORD", password);
        valores.put("ACCESO", acceso);

        database.update("USUARIOSSISTEMA", valores, "ID_USU="+id+"", null);
    }

    public int getUsuarioSistemaID(String usuario, String password, int acceso) {
        database = getReadableDatabase();
        int id = -1;
              Cursor c = database.rawQuery("SELECT ID_USU FROM USUARIOSSISTEMA WHERE (NOMBRE = '" + usuario + "')"
                + " AND (PASSWORD = '" + password +"') AND (ACCESO = '" + acceso + "')", null);

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                id = c.getInt(0);
            }
            while(c.moveToNext());
        }

        return id;
    }

    public int getClienteID(String dni) {
        database = getReadableDatabase();
        int id = -1;
        Cursor c = database.rawQuery("SELECT ID_CLIENTE FROM CLIENTES WHERE (DNI = '" + dni + "')" , null);

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                id = c.getInt(0);
            }
            while(c.moveToNext());
        }

        return id;
    }

    public ArrayList<String> getClientes(){
        database = getReadableDatabase();
        int campos = 3;
        ArrayList<String> elementos = new ArrayList<>();
        //String[] entrada = new String[campos];

        Cursor c = database.rawQuery("SELECT NOMBRE, APELLIDOS, EMAIL FROM CLIENTES", null);

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                for(int i=0; i<campos; i++){
                    elementos.add(c.getString(i));
                }

            }
            while(c.moveToNext());
        }
        return elementos;
    }

    public void eliminarUsuario(int id) {
        database = getWritableDatabase();

        database.delete("USUARIOSSISTEMA","ID_USU=" + id + "", null);
    }
}
