package gsa.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Equipo on 26/04/2016.
 */
public class GSAReaderDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GSA.db";
    private static final int DATABASE_VERSION = 1;

    public GSAReaderDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crear la tabla Clientes
        db.execSQL(GSADataSource.CREATE_CLIENTES_SCRIPT);
        //Crear la tabla Usuarios
        db.execSQL(GSADataSource.CREATE_USUARIOS_SCRIPT);
        //Crear la tabla UsuariosSistema
        db.execSQL(GSADataSource.CREATE_USUARIOSSISTEMA_SCRIPT);
        //Crear la tabla Trabajadores
        db.execSQL(GSADataSource.CREATE_TRABAJADORES_SCRIPT);
        //Insertar registros iniciales
        db.execSQL(GSADataSource.INSERT_DEFAULTSISTEMUSER_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Añade los cambios que se realizarán en el esquema
    }
}