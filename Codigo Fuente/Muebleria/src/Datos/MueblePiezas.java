
package Datos;

import java.io.Serializable;

/**
 *
 * @author jpmazate
 */

// esta clase es un poco distinta al a de mueblePiezas1 no se utilizo, pero puede servir, tiene una logica un tanto diferente
public class MueblePiezas implements Serializable{
    //atributos de la clase
    private String nombre;
    private int piezasDiferentes;
    private String[] nombrePieza;
    private int[] cantidadPieza;
    private String[] piezas;
// constructor
    public MueblePiezas(String nombre, int piezasDiferentes, String[] piezas) {
        this.nombre = nombre;
        this.piezasDiferentes = piezasDiferentes;
        this.piezas = piezas;
        this.nombrePieza = new String[piezasDiferentes];
        this.cantidadPieza = new int[piezasDiferentes];
        asignarValores();
    }
// getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPiezasDiferentes() {
        return piezasDiferentes;
    }

    public void setPiezasDiferentes(int piezasDiferentes) {
        this.piezasDiferentes = piezasDiferentes;
    }

    public String[] getPiezas() {
        return piezas;
    }

    public void setPiezas(String[] piezas) {
        this.piezas = piezas;
    }

    public String[] getNombrePieza() {
        return nombrePieza;
    }

    public void setNombrePieza(String[] nombrePieza) {
        this.nombrePieza = nombrePieza;
    }

    public int[] getCantidadPieza() {
        return cantidadPieza;
    }

    public void setCantidadPieza(int[] cantidadPieza) {
        this.cantidadPieza = cantidadPieza;
    }

    
        
    // asigna valores
    public void asignarValores(){
        boolean flag1=true;
        boolean flag2=false;
        int contador1=0;
        int contador2=0;
        
        for(int i=0; i<piezas.length;i++){
            if(flag1==true){
                nombrePieza[contador1]= piezas[i];
                contador1++;
                flag1=false;
                flag2=true;
            }else{
                cantidadPieza[contador2]=Integer.parseInt(piezas[i]);
                contador2++;
                flag2=false;
                flag1=true;
            }
        }
        
    }
    
    
    
    
    
    
    
    
    
}
