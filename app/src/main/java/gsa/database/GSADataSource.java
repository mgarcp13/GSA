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
    public static final String NAME_TABLE_SERVICIOS = "Servicios";
    public static final String NAME_TABLE_SERVICIOSCONTRATADOS = "ServiciosContratados";
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";

    //Campos de la tabla AgregarUsuariosSistemaActivity
    public static class ColumnUsuariosSistema {
        public static final String ID_USU = "ID_USU";
        public static final String NOMBRE = "Nombre";
        public static final String PASSWORD = "Password";
        public static final String ACCESO = "Acceso";
    }

    public static class ColumnServicios {
        public static final String ID_SERVICIO = "ID_SERVICIO";
        public static final String NOMBRE = "Nombre";
        public static final String COSTE = "Coste";
    }

    public static class ColumnServicioContratado {
        public static final String ID_CONTRATO = "ID_CONTRATO";
        public static final String ID_SERVICIO = "ID_USU";
        public static final String ID_CLIENTE = "ID_CLIENTE";
        public static final String ID_TRABAJADOR = "ID_TRABAJADOR";
        public static final String HORAS = "HORAS";
    }

    public static class ColumnClientes {
        public static final String ID_CLIENTE = "ID_CLIENTE";
        public static final String ID_SERVICIO = "IDServicio";
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
        public static final String ID_USUARIO = "ID_USUARIO";
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
        public static final String ID_TRABAJADOR = "ID_TRABAJADOR";
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
                    //ColumnTrabajadores.SSN +" "+STRING_TYPE+" not null," +
                    ColumnTrabajadores.DIRECCION +" "+STRING_TYPE+" not null, "+
                    ColumnTrabajadores.POBLACION +" "+STRING_TYPE+" not null," +
                    ColumnTrabajadores.PROVINCIA +" "+STRING_TYPE+" not null, "+
                    ColumnTrabajadores.CODPOSTAL +" "+STRING_TYPE+" null, " +
                    ColumnTrabajadores.EMAIL +" "+STRING_TYPE+" null, "+
                    ColumnTrabajadores.TELEFONO +" "+STRING_TYPE+" null,"+
                    ColumnTrabajadores.NACIONALIDAD +" "+STRING_TYPE+" null," +
                    //ColumnTrabajadores.ESTUDIOS +" "+STRING_TYPE+" null, " +
                    //ColumnTrabajadores.ESPECIFICACION +" "+STRING_TYPE+" null, " +
                    //ColumnTrabajadores.OBSERVACIONES +" "+STRING_TYPE+" null, "+
                    ColumnTrabajadores.VEHICULO +" "+STRING_TYPE+" not null," +
                    //ColumnTrabajadores.DISPONIBLIDAD +" "+STRING_TYPE+" not null, "+
                    //ColumnTrabajadores.VALORACION +" "+STRING_TYPE+" null," +
                    //ColumnTrabajadores.COSTE +" "+INT_TYPE+" not null, "+
                    ColumnTrabajadores.ESPECIALIDAD +" "+STRING_TYPE+" not null) ";


    //Script de Creación de la tabla AgregarUsuariosSistemaActivity
    public static final String CREATE_USUARIOSSISTEMA_SCRIPT =
            "create table "+ NAME_TABLE_USUARIOSSISTEMA +"(" +
                    ColumnUsuariosSistema.ID_USU +" "+INT_TYPE+" primary key autoincrement," +
                    ColumnUsuariosSistema.NOMBRE +" "+STRING_TYPE+" not null, " +
                    ColumnUsuariosSistema.PASSWORD +" "+STRING_TYPE+" not null, " +
                    ColumnUsuariosSistema.ACCESO +" "+INT_TYPE+" not null)";

    //Script de Creación de la tabla Servicios
    public static final String CREATE_SERVICIOS_SCRIPT =
            "create table "+ NAME_TABLE_SERVICIOS +"(" +
                    ColumnServicios.ID_SERVICIO +" "+INT_TYPE+" primary key autoincrement," +
                    ColumnServicios.NOMBRE +" "+STRING_TYPE+" not null, " +
                    ColumnServicios.COSTE +" "+INT_TYPE+" not null)";

    //Script de Creación de la tabla AgregarUsuariosSistemaActivity
    public static final String CREATE_SERVICIOSCONTRATADO_SCRIPT =
            "create table "+ NAME_TABLE_SERVICIOSCONTRATADOS +"(" +
                    ColumnServicioContratado.ID_CONTRATO +" "+INT_TYPE+" primary key autoincrement," +
                    ColumnServicioContratado.ID_SERVICIO +" "+INT_TYPE+" not null, " +
                    ColumnServicioContratado.ID_CLIENTE +" "+INT_TYPE+" not null, " +
                    ColumnServicioContratado.ID_TRABAJADOR +" "+INT_TYPE+" not null, " +
                    ColumnServicioContratado.HORAS +""+INT_TYPE+" not null)";

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
                    ColumnClientes.ID_SERVICIO +" "+INT_TYPE+" null," +
                    ColumnClientes.NOMBRE +" "+STRING_TYPE+" not null, " +
                    ColumnClientes.APELLIDOS +" "+STRING_TYPE+" not null, " +
                    ColumnClientes.DNI +" "+STRING_TYPE+" not null, "+
                    ColumnClientes.NUMCUENTA +" "+STRING_TYPE+" null," +
                    ColumnClientes.DIRECCION +" "+STRING_TYPE+" not null, "+
                    ColumnClientes.POBLACION +" "+STRING_TYPE+" not null," +
                    ColumnClientes.PROVINCIA +" "+STRING_TYPE+" not null, "+
                    ColumnClientes.CODPOSTAL +" "+STRING_TYPE+" not null, " +
                    ColumnClientes.EMAIL +" "+STRING_TYPE+" not null, "+
                    ColumnClientes.TELEFONO +" "+STRING_TYPE+" not null)";

    //Scripts de inserción por defecto
    public static final String INSERT_DEFAULTSISTEMUSER_SCRIPT = "insert into " + NAME_TABLE_USUARIOSSISTEMA +
            "(NOMBRE, PASSWORD, ACCESO) values('admin','12345',0);";

    public static final String INSERT_DEFAULTSISTEMUSER2_SCRIPT = "insert into " + NAME_TABLE_USUARIOSSISTEMA +
            "(NOMBRE, PASSWORD, ACCESO) values('gestor','12345',1);";

}
