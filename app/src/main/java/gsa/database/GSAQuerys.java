package gsa.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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

    public void addServicio(String nombre, int coste) {
        database = getWritableDatabase();
        database.execSQL("INSERT INTO SERVICIOS (NOMBRE, COSTE) VALUES ('" + nombre + "', '" + coste + "');");
    }

    public void addTrabajador(String nombre, String apellidos, String dni, String dir, String poblacion,
                              String provincia, String cpostal, String email, String telefono,
                              String nacionalidad, String vehiculo, String especialidad) {
        database = getWritableDatabase();
        database.execSQL("INSERT INTO TRABAJADORES (NOMBRE, APELLIDOS, DNI, DIRECCION, POBLACION, PROVINCIA, CODPOSTAL, " +
                "EMAIL, TELEFONO, NACIONALIDAD, VEHICULO, ESPECIALIDAD) VALUES ('" + nombre + "', '" + apellidos + "', '" +
                dni + "', '" + dir + "', '" + poblacion + "', '" + provincia + "', '" + cpostal + "', '" + email + "', '" +
                telefono + "', '" + nacionalidad + "', '" + vehiculo + "', '" + especialidad + "');");
    }

    public void addContrato(int idCliente, int idTrabajador, int idServicio, int horas, int coste) {
        database = getWritableDatabase();
        database.execSQL("INSERT INTO CONTRATOS (ID_CLIENTE, ID_TRABAJADOR, ID_SERVICIO, HORAS, COSTE) " +
                "VALUES ('" + idCliente + "', '" + idTrabajador + "', '" + idServicio + "', '" + horas + "', '" +
                coste + "');");
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

    public void editarTrabajador(String nombre, String apellidos, String dni, String dir, String poblacion,
                                 String provincia, String cpostal, String email, String telefono,
                                 String nacionalidad, String vehiculo, String especialidad, int id) {
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
        valores.put("NACIONALIDAD", nacionalidad);
        valores.put("VEHICULO", vehiculo);
        valores.put("ESPECIALIDAD", especialidad);

        database.update("TRABAJADORES", valores, "ID_TRABAJADOR="+id+"", null);
    }

    public void editarServicio(String nombre, int coste, int id) {
        database = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("NOMBRE", nombre);
        valores.put("COSTE", coste);

        database.update("SERVICIOS", valores, "ID_SERVICIO=" + id + "", null);

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

    public int getTrabajadorID(String dni) {
        database = getReadableDatabase();
        int id = -1;
        Cursor c = database.rawQuery("SELECT ID_TRABAJADOR FROM TRABAJADORES WHERE (DNI = '" + dni + "')" , null);

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                c.getInt(0);
                id = c.getInt(0);
            }
            while(c.moveToNext());
        }

        return id;
    }


    public int getServicioID(String nombre) {
        database = getReadableDatabase();
        int id = -1;
        Cursor c = database.rawQuery("SELECT ID_SERVICIO FROM SERVICIOS WHERE (NOMBRE= '" + nombre + "')", null);
        if(c.moveToFirst()){
            do{
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

        Cursor c = database.rawQuery("SELECT NOMBRE, APELLIDOS, DNI FROM CLIENTES", null);

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

    public ArrayList<String> getContratos() {
        database = getReadableDatabase();
        int campos = 6;
        ArrayList<String> elementos = new ArrayList<>();

        Cursor c = database.rawQuery("SELECT ID_CONTRATO, ID_CLIENTE, ID_TRABAJADOR, ID_SERVICIO, HORAS, COSTE FROM CONTRATOS", null);

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

    public ArrayList<String> getServicios(){
        database = getReadableDatabase();
        int campos = 2;
        ArrayList<String> elementos = new ArrayList<>();

        Cursor c = database.rawQuery("SELECT NOMBRE, COSTE FROM SERVICIOS", null);

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

    public ArrayList<String> getFacturas() {
        int campos = 4;
        ArrayList<String> elementos = new ArrayList<>();
        database = getReadableDatabase();

        Cursor c = database.rawQuery("SELECT ID_FACTURA, ID_CLIENTE, IMPORTE, PAGADO FROM FACTURAS", null);

        if(c.moveToFirst()){
            do{
                for(int i=0; i<campos; i++)
                    elementos.add(c.getString(i));
            }
            while(c.moveToNext());
        }
        return elementos;
    }

    public ArrayList<String> getTrabajadores(){
        database = getReadableDatabase();
        int campos = 5;
        ArrayList<String> elementos = new ArrayList<>();

        Cursor c = database.rawQuery("SELECT NOMBRE, APELLIDOS, DNI, VEHICULO, ESPECIALIDAD FROM TRABAJADORES", null);

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


    public String[] getCliente(int id) {
        String[] resultado = new String[9];
        database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT NOMBRE, APELLIDOS, DNI, DIRECCION, POBLACION, PROVINCIA, " +
                " CODPOSTAL, EMAIL, TELEFONO FROM CLIENTES WHERE (ID_CLIENTE=" + id + ")", null);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                for(int i=0; i<resultado.length; i++){
                    resultado[i] = c.getString(i);
                }

            }
            while(c.moveToNext());
        }

        return resultado;
    }

    public String[] getServicio(int id) {
        String[] resultado = new String[2];
        database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT NOMBRE, COSTE FROM SERVICIOS " +
                "WHERE (ID_SERVICIO=" + id + ")", null);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                for(int i=0; i<resultado.length; i++){
                    resultado[i] = c.getString(i);
                }

            }
            while(c.moveToNext());
        }

        return resultado;
    }

    public String[] getTrabajador(int id) {
        String[] resultado = new String[12];
        database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT NOMBRE, APELLIDOS, DNI, DIRECCION, POBLACION, PROVINCIA, " +
                " CODPOSTAL, EMAIL, TELEFONO, NACIONALIDAD, VEHICULO, ESPECIALIDAD FROM TRABAJADORES " +
                "WHERE (ID_TRABAJADOR=" + id + ")", null);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                for(int i=0; i<resultado.length; i++){
                    resultado[i] = c.getString(i);
                }

            }
            while(c.moveToNext());
        }

        return resultado;
    }

    public void eliminarUsuario(int id) {
        database = getWritableDatabase();
        database.delete("USUARIOSSISTEMA","ID_USU=" + id + "", null);
    }

    public void eliminarCliente(int id) {
        database = getWritableDatabase();
        database.delete("CLIENTES", "ID_CLIENTE=" + id + "", null);
    }

    public void eliminarTrabajador(int id) {
        database = getWritableDatabase();
        database.delete("TRABAJADORES", "ID_TRABAJADOR=" + id + "", null);
    }

    public void eliminarServicio(int id) {
        database = getWritableDatabase();
        database.delete("SERVICIOS", "ID_SERVICIO=" + id + "", null);
    }

    public void eliminarContrato(int id) {
        database = getWritableDatabase();
        database.delete("CONTRATOS", "ID_CONTRATO=" + id + "", null);
    }
    public void eliminarFactura(int id){
        database = getWritableDatabase();
        database.delete("FACTURAS", "ID_FACTURA=" + id + "", null);
    }


    public String getNombreCliente(int id) {
        database = getWritableDatabase();
        String nombre = "";
        Cursor c = database.rawQuery("SELECT NOMBRE, APELLIDOS FROM CLIENTES WHERE (ID_CLIENTE=" + id + ")", null);

        if(c.moveToFirst()){
            do{
                nombre = c.getString(0) + " " + c.getString(1);

            }
            while(c.moveToNext());
        }
        return nombre;
    }

    public String getNombreTrabajador(int id) {
        database = getWritableDatabase();
        String nombre = "";
        Cursor c = database.rawQuery("SELECT NOMBRE, APELLIDOS FROM TRABAJADORES WHERE (ID_TRABAJADOR=" + id + ")", null);

        if(c.moveToFirst()){
            do{
                nombre = c.getString(0) + " " + c.getString(1);

            }
            while(c.moveToNext());
        }
        return nombre;
    }

    public String getNombreServicio(int id) {
        database = getWritableDatabase();
        String nombre = "";
        Cursor c = database.rawQuery("SELECT NOMBRE FROM SERVICIOS WHERE (ID_SERVICIO=" + id + ")", null);

        if(c.moveToFirst()){
            do{
                nombre = c.getString(0);

            }
            while(c.moveToNext());
        }
        return nombre;
    }

    public ArrayList<String> getNombreClientes() {
        ArrayList<String> elementos = new ArrayList<>();
        database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT NOMBRE, APELLIDOS FROM CLIENTES ORDER BY ID_CLIENTE", null);

        if(c.moveToFirst()){
            do{
                String nombre = c.getString(0) + " " + c.getString(1);
                elementos.add(nombre);
            }
            while(c.moveToNext());
        }
        return elementos;
    }

    public ArrayList<String> getNombreTrabajadores() {
        ArrayList<String> elementos = new ArrayList<>();
        database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT NOMBRE FROM TRABAJADORES ORDER BY ID_TRABAJADOR", null);

        if (c.moveToFirst()) {
            do {
                elementos.add(c.getString(0));
            }
            while (c.moveToNext());
        }
        return elementos;
    }

    public ArrayList<String> getNombreServicios() {
        ArrayList<String> elementos = new ArrayList<>();
        database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT NOMBRE FROM SERVICIOS ORDER BY ID_SERVICIO", null);

        if (c.moveToFirst()) {
            do {
                elementos.add(c.getString(0));
            }
            while (c.moveToNext());
        }
        return elementos;
    }

    public ArrayList<Integer> getClientesIds() {
        ArrayList<Integer> elementos = new ArrayList<>();
        database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT ID_CLIENTE FROM CLIENTES ORDER BY ID_CLIENTE", null);

        if(c.moveToFirst()){
            do{
                elementos.add(c.getInt(0));
            }
            while(c.moveToNext());
        }
        return elementos;
    }

    public ArrayList<Integer> getTrabajadoresIds() {
        ArrayList<Integer> elementos = new ArrayList<>();
        database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT ID_TRABAJADOR FROM TRABAJADORES ORDER BY ID_TRABAJADOR", null);

        if(c.moveToFirst()){
            do{
                elementos.add(c.getInt(0));
            }
            while(c.moveToNext());
        }
        return elementos;
    }

    public ArrayList<Integer> getServiciosIds() {
        ArrayList<Integer> elementos = new ArrayList<>();
        database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT ID_SERVICIO FROM SERVICIOS ORDER BY ID_SERVICIO", null);

        if(c.moveToFirst()){
            do{
                elementos.add(c.getInt(0));
            }
            while(c.moveToNext());
        }
        return elementos;
    }

    public int getServicioCoste(int idServicio) {
        int coste = 0;
        database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT COSTE FROM SERVICIOS WHERE (ID_SERVICIO=" + idServicio + ")", null);
        if(c.moveToNext())
            coste = c.getInt(0);
        return coste;
    }

    public void pagarFactura(int id) {
        database = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("PAGADO", "SI");

        database.update("FACTURAS", valores, "ID_FACTURA=" + id + "", null);
    }

    public ArrayList<Integer> getContratosCliente(int id) {
        ArrayList<Integer> importes = new ArrayList<>();
        database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT COSTE FROM CONTRATOS WHERE (ID_CLIENTE=" + id + ")", null);

        if(c.moveToFirst()) {
            do {
                importes.add(c.getInt(0));
            }

            while (c.moveToNext());
        }
        return importes;
    }

    public void generarFactura(int idCliente, int importe, String pagado) {
        database = getWritableDatabase();
        database.execSQL("INSERT INTO FACTURAS (ID_CLIENTE, IMPORTE, PAGADO) VALUES ('" +
                idCliente + "', '" + importe + "', '" + pagado + "');");
    }

}
