package gsa.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

/**
 * Created by Equipo on 26/04/2016.
 */
public class GSADataSource {

    private GSAReaderDbHelper openHelper;

    private SQLiteDatabase database;

    public static final String NAME_TABLE_CLIENTES = "Clientes";
    public static final String NAME_TABLE_USUARIOS = "Usuarios";
    public static final String NAME_TABLE_TRABAJADORES = "Trabajadores";
    public static final String NAME_TABLE_USUARIOSSISTEMA = "UsuariosSistema";
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";

    //Campos de la tabla AgregarUsuariosSistemaActivity
    public static class ColumnUsuariosSistema {
        public static final String ID_USU = BaseColumns._ID;
        public static final String NOMBRE = "Nombre";
        public static final String PASSWORD = "Password";
        public static final String ACCESO = "Acceso";
    }

    public static class ColumnClientes {
        public static final String ID_CLIENTE = BaseColumns._ID;
        public static final String NOMBRE = "Nombre";
        public static final String APELLIDOS = "Apellidos";
        public static final String DNI = "DNI";
        public static final String NUMCUENTA = "NumCuenta";
        public static final String DIRECCION = "Direccion";
        public static final String POBLACION = "Poblacion";
        public static final String PROVINCIA = "Provincia";
        public static final String CODPOSTAL = "CodPostal";
        public static final String EMAIL = "Email";
        public static final String TELEFONO = "Telefono";
    }

    public static class ColumnUsuarios {
        public static final String ID_USUARIO = BaseColumns._ID;
        public static final String ID_SERVICIO = "IDServicio";
        public static final String ID_CLIENTE = "IDCliente";
        public static final String NOMBRE = "Nombre";
        public static final String APELLIDOS = "Apellidos";
        public static final String DNI = "DNI";
        public static final String DIRECCION = "Direccion";
        public static final String POBLACION = "Poblacion";
        public static final String PROVINCIA = "Provincia";
        public static final String CODPOSTAL = "CodPostal";
        public static final String EMAIL = "Email";
        public static final String TELEFONO = "Telefono";
    }


    public static class ColumnTrabajadores {
        public static final String ID_TRABAJADOR = BaseColumns._ID;
        public static final String NOMBRE = "Nombre";
        public static final String APELLIDOS = "Apellidos";
        public static final String DNI = "DNI";
        public static final String SSN = "SSN";
        public static final String DIRECCION = "Direccion";
        public static final String POBLACION = "Poblacion";
        public static final String PROVINCIA = "Provincia";
        public static final String CODPOSTAL = "CodPostal";
        public static final String EMAIL = "Email";
        public static final String TELEFONO = "Telefono";
        public static final String NACIONALIDAD = "Nacionalidad";
        public static final String ESTUDIOS = "Estudios";
        public static final String ESPECIFICACION = "Especificacion";
        public static final String OBSERVACIONES = "Observaciones";
        public static final String VEHICULO = "Vehiculo";
        public static final String DISPONIBLIDAD = "Disponibilidad";
        public static final String VALORACION = "Valoracion";
        public static final String COSTE = "Coste";
        public static final String ESPECIALIDAD = "Especialidad";
    }

    //Script de Creación de la tabla Trabajadores
    public static final String CREATE_TRABAJADORES_SCRIPT =
            "create table "+ NAME_TABLE_TRABAJADORES +"(" +
                    ColumnTrabajadores.ID_TRABAJADOR +" "+INT_TYPE+" primary key autoincrement," +
                    ColumnTrabajadores.NOMBRE +" "+STRING_TYPE+" not null, " +
                    ColumnTrabajadores.APELLIDOS +" "+STRING_TYPE+" not null, " +
                    ColumnTrabajadores.DNI +" "+STRING_TYPE+" not null, "+
                    ColumnTrabajadores.SSN +" "+STRING_TYPE+" not null," +
                    ColumnTrabajadores.DIRECCION +" "+STRING_TYPE+" not null, "+
                    ColumnTrabajadores.POBLACION +" "+STRING_TYPE+" not null," +
                    ColumnTrabajadores.PROVINCIA +" "+STRING_TYPE+" not null, "+
                    ColumnTrabajadores.CODPOSTAL +" "+INT_TYPE+" null, " +
                    ColumnTrabajadores.EMAIL +" "+STRING_TYPE+" null, "+
                    ColumnTrabajadores.TELEFONO +" "+INT_TYPE+" null"+
                    ColumnTrabajadores.NACIONALIDAD +" "+INT_TYPE+" null," +
                    ColumnTrabajadores.ESTUDIOS +" "+STRING_TYPE+" null, " +
                    ColumnTrabajadores.ESPECIFICACION +" "+STRING_TYPE+" null, " +
                    ColumnTrabajadores.OBSERVACIONES +" "+STRING_TYPE+" null, "+
                    ColumnTrabajadores.VEHICULO +" "+STRING_TYPE+" not null," +
                    ColumnTrabajadores.DISPONIBLIDAD +" "+STRING_TYPE+" not null, "+
                    ColumnTrabajadores.VALORACION +" "+STRING_TYPE+" null," +
                    ColumnTrabajadores.COSTE +" "+INT_TYPE+" not null, "+
                    ColumnTrabajadores.ESPECIALIDAD +" "+INT_TYPE+" not null) ";


    //Script de Creación de la tabla AgregarUsuariosSistemaActivity
    public static final String CREATE_USUARIOSSISTEMA_SCRIPT =
            "create table "+ NAME_TABLE_USUARIOSSISTEMA +"(" +
                    ColumnUsuariosSistema.ID_USU +" "+INT_TYPE+" primary key autoincrement," +
                    ColumnUsuariosSistema.NOMBRE +" "+STRING_TYPE+" not null, " +
                    ColumnUsuariosSistema.PASSWORD +" "+STRING_TYPE+" not null, " +
                    ColumnUsuariosSistema.ACCESO +" "+INT_TYPE+" not null) ";


    //Script de Creación de la tabla Usuarios
    public static final String CREATE_USUARIOS_SCRIPT =
            "create table "+ NAME_TABLE_USUARIOS +"(" +
                    ColumnUsuarios.ID_USUARIO +" "+INT_TYPE+" primary key autoincrement," +
                    ColumnUsuarios.ID_SERVICIO +" "+INT_TYPE+" null, " +
                    ColumnUsuarios.ID_CLIENTE +" "+INT_TYPE+" not null, " +
                    ColumnUsuarios.NOMBRE +" "+STRING_TYPE+" not null, "+
                    ColumnUsuarios.APELLIDOS +" "+STRING_TYPE+" not null," +
                    ColumnUsuarios.DNI +" "+STRING_TYPE+" not null," +
                    ColumnUsuarios.DIRECCION +" "+STRING_TYPE+" null, "+
                    ColumnUsuarios.POBLACION +" "+STRING_TYPE+" null," +
                    ColumnUsuarios.PROVINCIA +" "+STRING_TYPE+" null, "+
                    ColumnUsuarios.CODPOSTAL +" "+INT_TYPE+" null, " +
                    ColumnUsuarios.EMAIL +" "+STRING_TYPE+" null, "+
                    ColumnUsuarios.TELEFONO +" "+INT_TYPE+" null)";


    //Script de Creación de la tabla Clientes
    public static final String CREATE_CLIENTES_SCRIPT =
            "create table "+ NAME_TABLE_CLIENTES +"(" +
                    ColumnClientes.ID_CLIENTE +" "+INT_TYPE+" primary key autoincrement," +
                    ColumnClientes.NOMBRE +" "+STRING_TYPE+" not null, " +
                    ColumnClientes.APELLIDOS +" "+STRING_TYPE+" not null, " +
                    ColumnClientes.DNI +" "+STRING_TYPE+" not null, "+
                    ColumnClientes.NUMCUENTA +" "+STRING_TYPE+" not null," +
                    ColumnClientes.DIRECCION +" "+STRING_TYPE+" not null, "+
                    ColumnClientes.POBLACION +" "+STRING_TYPE+" not null," +
                    ColumnClientes.PROVINCIA +" "+STRING_TYPE+" not null, "+
                    ColumnClientes.CODPOSTAL +" "+INT_TYPE+" not null, " +
                    ColumnClientes.EMAIL +" "+STRING_TYPE+" not null, "+
                    ColumnClientes.TELEFONO +" "+INT_TYPE+" not null)";

    //Scripts de inserción por defecto
    public static final String INSERT_DEFAULTSISTEMUSER_SCRIPT = "insert into " + NAME_TABLE_USUARIOSSISTEMA +
            "(NOMBRE, PASSWORD, ACCESO) values('admin','12345',0);";

    public static final String INSERT_DEFAULTSISTEMUSER2_SCRIPT = "insert into " + NAME_TABLE_USUARIOSSISTEMA +
            "(NOMBRE, PASSWORD, ACCESO) values('gestor','12345',1);";

    /*public static final String INSERT_QUOTES_SCRIPT =
            "insert into "+ NAME_TABLE_CLIENTES +" values(" +
                    "null," +
                    "\"El ignorante afirma, el sabio duda y reflexiona\"," +
                    "\"Aristóteles\")," +
                    "(null," +
                    "\"Hay derrotas que tienen mas dignidad que la victoria\"," +
                    "\"Jorge Luis Borges\")," +
                    "(null," +
                    "\"Si buscas resultados distintos, no hagas siempre lo mismo\"," +
                    "\"Albert Einstein\")," +
                    "(null," +
                    "\"Donde mora la libertad, allí está mi patria\"," +
                    "\"Benjamin Franklin\")," +
                    "(null," +
                    "\"Ojo por ojo y todo el mundo acabará ciego\"," +
                    "\"Mahatma Gandhi\")";*/

}
