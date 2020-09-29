
package Datos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jpmazate
 */
public class Factura implements Serializable{
    //atributos privados de la clase
    private int NIT;
    private String nombreCliente;
    private String direccion;
    private Date fechaCompra;
    private Date fechaMaximaDevolucion;
    private int numeroFactura;
    private String nombreMueble;
    private String identificador;
    private double total;
    private String usuarioVenta;
    private double precioCosto;
    private boolean sePuedeDevolver;
    
    private static final int NUMERO_LIMITE_DEVOLUCION=7;

    //constructor
    public Factura(int NIT, String nombreCliente, String direccion, Date fechaCompra, int numeroFactura, String nombreMueble, String identificador, double total, String usuarioVenta, double precioCosto) {
        this.NIT = NIT;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.fechaCompra = fechaCompra;
        this.numeroFactura = numeroFactura;
        this.nombreMueble = nombreMueble;
        this.identificador = identificador;
        this.total = total;
        this.usuarioVenta = usuarioVenta;
        this.precioCosto=precioCosto;
        this.sePuedeDevolver=true;
        //asigna ua fecha de devolucion
        asignarFechaDevolucion();
        
    }
//getters and setters
    public int getNIT() {
        return NIT;
    }

    public void setNIT(int NIT) {
        this.NIT = NIT;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getNombreMueble() {
        return nombreMueble;
    }

    public void setNombreMueble(String nombreMueble) {
        this.nombreMueble = nombreMueble;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUsuarioVenta() {
        return usuarioVenta;
    }

    public void setUsuarioVenta(String usuarioVenta) {
        this.usuarioVenta = usuarioVenta;
    }

    public Date getFechaMaximaDevolucion() {
        return fechaMaximaDevolucion;
    }

    public void setFechaMaximaDevolucion(Date fechaMaximaDevolucion) {
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }

    public double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public boolean isSePuedeDevolver() {
        return sePuedeDevolver;
    }

    public void setSePuedeDevolver(boolean sePuedeDevolver) {
        this.sePuedeDevolver = sePuedeDevolver;
    }
    
    //asigna automaticamente la fecha de devolucion
    public void asignarFechaDevolucion(){
        this.fechaMaximaDevolucion = (Date) this.fechaCompra.clone();
        this.fechaMaximaDevolucion.setDate(fechaMaximaDevolucion.getDate()+NUMERO_LIMITE_DEVOLUCION);
    }
    
}
