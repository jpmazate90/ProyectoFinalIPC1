
package Logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HTML {
        //atributos de la clase necesarios para crear la sintaxis del codigo HTML
    private static final String ESTILO_TABLA="<table style=\"border-collapse: collapse;\">";
    private static final String INICIO_OPERADOR_TR="<tr>";
    private static final String FIN_OPERADOR_TR="</tr>";
    private static final String ESTILO_LINEA_INICIAL="<th style=\"border: 1px solid #000000;\">";
    private static final String FIN_OPERADOR_TH="</th>";
    private static final String ESTILO_LINEA_ATRIBUTO="<td style=\"border: 1px solid #000000;\">";
    private static final String FIN_OPERADOR_TD="</td>";
    private static final String FIN_OPERADOR_TABLA="</table>";
    private static final String INICIO_OPERADOR_HTML="<html>";
    private static final String FIN_OPERADOR_HTML="</html>";
    private static final String INICIO_OPERADOR_H="<h1>";
    private static final String FIN_OPERADOR_H="</h1>";
    private static final String INICIO_OPERADOR_P="<p>";
    private static final String FIN_OPERADOR_P="</p>";
    private static final String PLANTILLA_FECHA_INICIAL="FECHA INICIAL";
    private static final String PLANTILLA_FECHA_FINAL="FECHA FINAL";
    private static final String PLANTILLA_MUEBLE="MUEBLE";
    private static final String PLANTILLA_IDENTIFICADOR="IDENTIFICADOR";
    private static final String PLANTILLA_TOTAL="TOTAL";
    private static final String PLANTILLA_USUARIO="USUARIO";
    private static final String PLANTILLA_CLIENTE="CLIENTE";
    private static final String PlANTILLA_NIT="NIT";
    private static final String PlANTILLA_FECHA="FECHA";
    private static final String PlANTILLA_DINERO_INGRESADO="DINERO INGRESADO";
    private static final String PlANTILLA_DINERO_DEVUELTO="DINERO DEVUELTO";
    private static final String PLANTILLA_FECHA_COMPRA="FECHA COMPRA";
    private static final String PLANTILLA_FECHA_DEVOLUCION="FECHA DEVOLUCION";
    private static final String PLANTILLA_COSTO="COSTO";
    private static final String PLANTILLA_NUMERO_FACTURA="NUMERO FACTURA";
    private static final String PLANTILLA_USUARIO_QUE_REGISTRO_REPORTE="USUARIO QUE REGISTRO EL REPORTE";
    private static final String PLANTILLA_GANANCIAS_TIEMPO_ESPECIFICADO="GANANCIAS EN EL INTERVALO DE TIEMPO ASIGNADO";
    private static final String PLANTILLA_USUARIO_QUE_MAS_VENDIO="USUARIO QUE MAS VENDIO EN EL INTERVALO DE TIEMPO ASIGNADO";
    private static final String PLANTILLA_MUEBLE_MAS_VENDIDO="MUEBLE_MAS_VENDIDO";
    private static final String PLANTILLA_MUEBLE_MENOS_VENDIDO="MUEBLE MENOS VENDIDO";
    private static final String PLANTILLA_VECES_VENDIDO="VECES VENDIDO";
    // abre el filechooser para que el usuario escoja donde guardar el html
    public static File usarFileChooser(){
        JFileChooser guardarComo = new JFileChooser();
        guardarComo.setApproveButtonText("Guardar");
        guardarComo.showSaveDialog(null);
        File archivo = new File (guardarComo.getSelectedFile()+".html");
        return archivo;
    }
    // dice si se genero el archivo correctamente
    public static void decirQueSeGeneroElArchivo(){
        JOptionPane.showMessageDialog(null, "Se genero el archivo html correctamente");
    }
    // genera el titulo de un archivo 
    public static void generarTitulo(File archivo, String titulo){
        try{// abre la via para escribir en el archivo
            File archivoReportes = archivo;
            FileWriter escritura = new FileWriter(archivoReportes, true);
            BufferedWriter escritor = new BufferedWriter(escritura);
            // crea unicamente el titulo de un archivo
            escritor.write(INICIO_OPERADOR_HTML);
            escritor.write(INICIO_OPERADOR_H);
            escritor.write(titulo);
            escritor.write(FIN_OPERADOR_H);
            escritor.newLine(); 
            
            escritor.write(FIN_OPERADOR_HTML);
                
            escritor.flush();
            escritor.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    // genera el reporte de ventas en el tiempo especificado
    public static void generarReporteVentas(File archivo, DefaultTableModel modelo, String fechaMinima, String fechaMaxima, String dineroIngresado, String usuario){
        try{// abre el archivo para su uso
                File archivoReportes = archivo;
                FileWriter escritura = new FileWriter(archivoReportes, true);
                BufferedWriter escritor = new BufferedWriter(escritura);
            // añade las fechas iniciales y finales 
                escritor.write(INICIO_OPERADOR_HTML);
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_FECHA_INICIAL+": "+ fechaMinima);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();
                
                escritor.write(INICIO_OPERADOR_HTML);
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_FECHA_FINAL+": "+ fechaMaxima);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();

                escritor.write(ESTILO_TABLA);
                escritor.newLine();
                escritor.write(INICIO_OPERADOR_TR);
                escritor.newLine();
                //agarra todo lo de la tabla en un arreglo
                String[] plantillas ={PLANTILLA_MUEBLE, PLANTILLA_IDENTIFICADOR, PLANTILLA_TOTAL,PLANTILLA_USUARIO, PLANTILLA_CLIENTE, PlANTILLA_NIT, PlANTILLA_FECHA};
            //ciclo para agilizar la escritura
            for(int j=0;j<plantillas.length;j++){
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor .write(FIN_OPERADOR_TR);
            escritor .newLine();
            
            // ciclo por el cual añade los valores a la tabla
            for(int i=0;i<modelo.getRowCount();i++){
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[7];
                datos[0] = (String) modelo.getValueAt(i,0);
                datos[1] = (String) modelo.getValueAt(i,1);
                datos[2] = (String) modelo.getValueAt(i,2);
                datos[3] = (String) modelo.getValueAt(i,3);
                datos[4] = (String) modelo.getValueAt(i,4);
                datos[5] = (String) modelo.getValueAt(i,5);
                datos[6] = (String) modelo.getValueAt(i,6);
                // añade los datos a la tabla
                for(int k=0; k<datos.length;k++){
                      escritor.write(ESTILO_LINEA_ATRIBUTO);
                      escritor.write(datos[k]);
                      escritor.write(FIN_OPERADOR_TD);
                      escritor.newLine();
                  }
                escritor .write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor .write(FIN_OPERADOR_TABLA);
            // muestra el dinero que ingreso
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PlANTILLA_DINERO_INGRESADO+": "+ dineroIngresado);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor .write(FIN_OPERADOR_HTML);
            escritor .flush();
            escritor.close();
            
            decirQueSeGeneroElArchivo();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    // genera el reporte de devoluciones en el intervalo de tiempo especificado
    public static void generarReporteDevoluciones(File archivo, DefaultTableModel modelo, String fechaMinima, String fechaMaxima, String dineroDevuelto, String usuario){
        try{// abre el camino para usar el archiivo
                File archivoReportes = archivo;
                FileWriter escritura = new FileWriter(archivoReportes, true);
                BufferedWriter escritor = new BufferedWriter(escritura);
            // empieza la sintaxis del html
                escritor.write(INICIO_OPERADOR_HTML);
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_FECHA_INICIAL+": "+ fechaMinima);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();
                
                escritor.write(INICIO_OPERADOR_HTML);
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_FECHA_FINAL+": "+ fechaMaxima);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();

                escritor.write(ESTILO_TABLA);
                escritor.newLine();
                escritor.write(INICIO_OPERADOR_TR);
                escritor.newLine();
                //agarra todo lo de la tabla en un arreglo
                String[] plantillas ={PLANTILLA_FECHA_COMPRA, PLANTILLA_FECHA_DEVOLUCION, PLANTILLA_IDENTIFICADOR,PLANTILLA_MUEBLE, PLANTILLA_COSTO, PLANTILLA_NUMERO_FACTURA};
            //ciclo para agilizar la escritura
            for(int j=0;j<plantillas.length;j++){
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor .write(FIN_OPERADOR_TR);
            escritor .newLine();
            
            // ciclo para agarrar los datos de la tabla
            for(int i=0;i<modelo.getRowCount();i++){
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[6];
                datos[0] = (String) modelo.getValueAt(i,0);
                datos[1] = (String) modelo.getValueAt(i,1);
                datos[2] = (String) modelo.getValueAt(i,2);
                datos[3] = (String) modelo.getValueAt(i,3);
                datos[4] = (String) modelo.getValueAt(i,4);
                datos[5] = (String) modelo.getValueAt(i,5);
                // ciclo que añade los datos 
                for(int k=0; k<datos.length;k++){
                      escritor.write(ESTILO_LINEA_ATRIBUTO);
                      escritor.write(datos[k]);
                      escritor.write(FIN_OPERADOR_TD);
                      escritor.newLine();
                  }
                escritor .write(FIN_OPERADOR_TR);
            }
            // fin de la tabla
            escritor .write(FIN_OPERADOR_TABLA);
            // muestra el dinero que se devolvio 
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PlANTILLA_DINERO_DEVUELTO+": "+ dineroDevuelto);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            // muestra quien hizo el reporte
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            // termina el html
            escritor .write(FIN_OPERADOR_HTML);
            escritor .flush();
            escritor.close();
            decirQueSeGeneroElArchivo();
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }
    // reporte de ganancias en un intervalo de tiempo
    public static void generarReporteGanancias(File archivo, String fechaMinima, String fechaMaxima, String ganancias, String usuario){
        try{// abre el camino para un archivo 
                File archivoReportes = archivo;
                FileWriter escritura = new FileWriter(archivoReportes, true);
                BufferedWriter escritor = new BufferedWriter(escritura);
            // empieza la sintaxis del html
                escritor.write(INICIO_OPERADOR_HTML);
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_FECHA_INICIAL+": "+ fechaMinima);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();
                
                escritor.write(INICIO_OPERADOR_HTML);
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_FECHA_FINAL+": "+ fechaMaxima);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();
                
                escritor.write(INICIO_OPERADOR_HTML);
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_GANANCIAS_TIEMPO_ESPECIFICADO+": "+ ganancias);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();
                
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_REPORTE+": "+ usuario);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();
                
                escritor .write(FIN_OPERADOR_HTML);
                escritor .flush();
                escritor.close();
                decirQueSeGeneroElArchivo();
                
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    // genera el reporte del usuario que mas vendio 
    public static void generarReporteUsuarioMasVentas(File archivo, String fechaMinima, String fechaMaxima, String usuarioQueMasVendio, String usuario){
        try{// abre el archivo para usarlo 
                File archivoReportes = archivo;
                FileWriter escritura = new FileWriter(archivoReportes, true);
                BufferedWriter escritor = new BufferedWriter(escritura);
            // inicia la sintaxis 
                escritor.write(INICIO_OPERADOR_HTML);
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_FECHA_INICIAL+": "+ fechaMinima);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();
                
                escritor.write(INICIO_OPERADOR_HTML);
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_FECHA_FINAL+": "+ fechaMaxima);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();
                // muestra el usuario que mas vendio 
                escritor.write(INICIO_OPERADOR_HTML);
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_USUARIO_QUE_MAS_VENDIO+": "+ usuarioQueMasVendio);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();
                
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_REPORTE+": "+ usuario);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();
                // termina la sintaxis
                escritor .write(FIN_OPERADOR_HTML);
                escritor .flush();
                escritor.close();
                decirQueSeGeneroElArchivo();
                
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }
    // genera el reporte del mueble dependiendo si es el mas vendido o el menos 
    public static void generarReporteMueble(File archivo, DefaultTableModel modelo, String fechaMinima, String fechaMaxima, String nombreMueble, String vecesVendido, String usuario, boolean masVendido) {
        try{// abre el archivo especificado para su uso
                File archivoReportes = archivo;
                FileWriter escritura = new FileWriter(archivoReportes, true);
                BufferedWriter escritor = new BufferedWriter(escritura);
            // inicia la sintaxis 
                escritor.write(INICIO_OPERADOR_HTML);
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_FECHA_INICIAL+": "+ fechaMinima);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();
                
                escritor.write(INICIO_OPERADOR_HTML);
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_FECHA_FINAL+": "+ fechaMaxima);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();
                // dependiendo la booleana del parametro muestra el mas vendido o el menos vendido
                escritor.write(INICIO_OPERADOR_HTML);
                escritor.write(INICIO_OPERADOR_P);
                if(masVendido==true){
                    escritor.write(PLANTILLA_MUEBLE_MAS_VENDIDO+": "+nombreMueble);
                }else{
                    escritor.write(PLANTILLA_MUEBLE_MENOS_VENDIDO+": "+nombreMueble);
                }
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();
                // muestra las veces vendido 
                escritor.write(INICIO_OPERADOR_HTML);
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_VECES_VENDIDO+": "+vecesVendido);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();
// inicia la tabla 
                escritor.write(ESTILO_TABLA);
                escritor.newLine();
                escritor.write(INICIO_OPERADOR_TR);
                escritor.newLine();
                //agarra todo lo de la tabla en un arreglo
                String[] plantillas ={PLANTILLA_MUEBLE, PLANTILLA_IDENTIFICADOR, PLANTILLA_TOTAL,PLANTILLA_USUARIO, PLANTILLA_CLIENTE, PlANTILLA_NIT, PlANTILLA_FECHA};
            //ciclo para agilizar la escritura
            for(int j=0;j<plantillas.length;j++){
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor .write(FIN_OPERADOR_TR);
            escritor .newLine();
            
            // agarra los valores de la tabla 
            for(int i=0;i<modelo.getRowCount();i++){
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[7];
                datos[0] = (String) modelo.getValueAt(i,0);
                datos[1] = (String) modelo.getValueAt(i,1);
                datos[2] = (String) modelo.getValueAt(i,2);
                datos[3] = (String) modelo.getValueAt(i,3);
                datos[4] = (String) modelo.getValueAt(i,4);
                datos[5] = (String) modelo.getValueAt(i,5);
                datos[6] = (String) modelo.getValueAt(i,6);
                // pone los datos en la tabla 
                for(int k=0; k<datos.length;k++){
                      escritor.write(ESTILO_LINEA_ATRIBUTO);
                      escritor.write(datos[k]);
                      escritor.write(FIN_OPERADOR_TD);
                      escritor.newLine();
                  }
                escritor .write(FIN_OPERADOR_TR);
            }
            // termina la tabla 
            escritor .write(FIN_OPERADOR_TABLA);
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            // termina la sintaxis 
            escritor .write(FIN_OPERADOR_HTML);
            escritor .flush();
            escritor.close();
            
            decirQueSeGeneroElArchivo();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    } 
}
