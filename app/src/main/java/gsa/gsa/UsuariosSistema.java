package gsa.gsa;

/**
 * Created by Mario on 03/06/2016.
 */
public class UsuariosSistema {

    private String usuario;
    private String password;
    private String acceso;

    public UsuariosSistema(String usuario, String password, String acceso){
        this.usuario = usuario;
        this.password = password;
        this.acceso = acceso;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public String getAcceso() {
        return acceso;
    }
}
