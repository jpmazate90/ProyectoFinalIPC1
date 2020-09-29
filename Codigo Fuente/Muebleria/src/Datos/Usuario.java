
package Datos;

import java.io.Serializable;

/**
 *
 * @author jpmazate
 */
public class Usuario implements Serializable{
    //atributos privados
    private String nombre;
    private String password;
    private int tipo;
    private String area;
    private int historialVentas;
    private boolean estaActivo;
//constructor
    public Usuario(String nombre, String password, int tipo) {
        this.nombre = nombre;
        this.password = password;
        this.tipo = tipo;
        this.estaActivo=true;
        asignarArea(tipo);
        
    }
//getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getHistorialVentas() {
        return historialVentas;
    }

    public void setHistorialVentas(int historialVentas) {
        this.historialVentas = historialVentas;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }
//asigna el area
    public void asignarArea(int tipo){
        if(tipo==1){
            this.area= "fabrica";
        }else if(tipo==2){
            this.area ="punto de venta";
            this.historialVentas=0;
        }else if(tipo==3){
            this.area = "financiero";
        }else{
            this.area=null;
        }
        
        
    }
}
