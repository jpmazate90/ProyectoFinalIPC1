
package Datos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jpmazate
 */
public class Devolucion implements Serializable {
    
    private Date fechaDevolucion;
    private Date fechaCompra;
    private String nombreCliente;
    private Factura factura;
    private String muebleDevuelto;
    private double precioVenta;
    private double precioCosto;
    private String identificador;
    private double perdida;
    private int numeroFactura;
// constructor de la clase
    public Devolucion(Date fechaDevolucion, Date fechaCompra, String nombreCliente, String muebleDevuelto, double precioVenta, double precioCosto, String identificador, int numeroFactura) {
        this.fechaDevolucion = fechaDevolucion;
        this.fechaCompra = fechaCompra;
        this.nombreCliente = nombreCliente;
        this.muebleDevuelto = muebleDevuelto;
        this.precioVenta = precioVenta;
        this.precioCosto = precioCosto;
        this.identificador = identificador;
        this.numeroFactura = numeroFactura;
        
    }
    // getters y setters
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getMuebleDevuelto() {
        return muebleDevuelto;
    }

    public void setMuebleDevuelto(String muebleDevuelto) {
        this.muebleDevuelto = muebleDevuelto;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
    
    
    
    
}
