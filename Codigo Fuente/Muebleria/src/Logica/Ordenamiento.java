
package Logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class Ordenamiento {
    // atributos de la clase
    private static SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private static SimpleDateFormat formatoSinHora= new SimpleDateFormat("dd/MM/yyy");
    // ordenamiento por quicksort
    public static ArrayList<Integer> ordenar(ArrayList<Integer> arregloNumeros, int primero, int ultimo){
         
        int i, j, pivote, auxiliar=0;
        i=primero;
        j=ultimo;
        pivote= arregloNumeros.get((primero+ultimo)/2);
        do{// mientras el pivote sea mayor que i sigue
            while(arregloNumeros.get(i)<pivote){
                i++;
            }// mientras el pivot sea enor  que j sigue 
            while(arregloNumeros.get(j)>pivote){
                j--;
            }
            if(i<=j){// aregla datos si va del  lado izquierdo 
               auxiliar=arregloNumeros.get(i);
               arregloNumeros.set(i, arregloNumeros.get(j));
               arregloNumeros.set(j, auxiliar);
               
               /*
               arregloNumeros.get(i) = arregloNumeros.get(j);
               arregloNumeros.get(j)=auxiliar;
               */
               i++;
               j--;
            }// mientras i sea menor que j sigue ordenando 
        } while(i<=j);
        if(primero<j){
            ordenar(arregloNumeros, primero, j);
            
        }// usa recursividad 
        if(i<ultimo){
            ordenar(arregloNumeros, i, ultimo);
        }
        
    return arregloNumeros;
    }
    // ordena por burbuja 
    public static ArrayList<Date> ordenamientoPorBurbuja(ArrayList<Date> arregloNumerosDesordenados){
        Date variableAuxiliar;
        boolean cambios=false;
        // ordena las fechas por medio de burbuja 
        while(true){
            cambios=false;
            for(int i=1;i<arregloNumerosDesordenados.size();i++){
                if(arregloNumerosDesordenados.get(i).compareTo(arregloNumerosDesordenados.get(i-1))<0){
                    variableAuxiliar=arregloNumerosDesordenados.get(i);
                    arregloNumerosDesordenados.set(i, arregloNumerosDesordenados.get(i-1));
                    arregloNumerosDesordenados.set(i-1, variableAuxiliar);
                    cambios=true;
                }
            }
            if(cambios==false){
                break;
            }
        } 
        return arregloNumerosDesordenados;
    }
    // formatea una fecha 
    public static Date formatearFecha(String fecha){
        Date date = null;
        try {
                date = formato.parse(fecha);
            } catch (ParseException e) {
                try {
                    date = formatoSinHora.parse(fecha);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "No es una fecha");
                }
            } 
        return date;
    }
    // orddna un mueble por medio de burbuja 
    public static ArrayList<ControladorMuebleVendido> ordenamientoMueble(ArrayList<ControladorMuebleVendido> arregloNumerosDesordenados){
        ControladorMuebleVendido variableAuxiliar;
        boolean cambios=false;
        // hace un ordenamiento de los muebles mas vendidos y menos 
        while(true){
            cambios=false;
            // si unno es menor que el otro hace cambio 
            for(int i=1;i<arregloNumerosDesordenados.size();i++){
                if(arregloNumerosDesordenados.get(i).getVecesVendido()<arregloNumerosDesordenados.get(i-1).getVecesVendido()){
                    variableAuxiliar=arregloNumerosDesordenados.get(i);
                    arregloNumerosDesordenados.set(i, arregloNumerosDesordenados.get(i-1));
                    arregloNumerosDesordenados.set(i-1, variableAuxiliar);
                    cambios=true;
                }
            }
            if(cambios==false){
                break;
            }
        } 
        return arregloNumerosDesordenados;
    }
}
