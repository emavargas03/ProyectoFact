package appFacturacion;

import java.util.ArrayList;
import java.util.Date;

public class Corporativo extends Cliente {

    private String telefono;
    private String email;

    public Corporativo(String telefono, String email, String Nombre, String Sexo) {
        super(Nombre, Sexo);
        this.telefono = telefono;
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
