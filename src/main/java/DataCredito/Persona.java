package DataCredito;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Yonathan
 */
public class Persona implements Serializable{
    
    private String nombre;
    private String apellido;
    private int identificacion;
    
    HashMap<Integer,Reporte>reporte;

    public Persona(int identificacion, String apellido, String nombre) {        
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        reporte=new HashMap();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public HashMap<Integer, Reporte> getReporte() {
        return reporte;
    }

    public void setReporte(HashMap<Integer, Reporte> reporte) {
        this.reporte = reporte;
    }
    
}
