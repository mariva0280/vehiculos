package objetos;

import java.util.ArrayList;

public class Coche {
    private String marca;
    private String modelo;
    private String matricula;
    private double precioCompra;
    private double precioVenta;
    private TipoVehiculo tipoVehiculo;
    private Estado estado;
    private ArrayList<Reparacion> reparaciones;

    public Coche() {
    }

    public Coche(String marca, String modelo, String matricula, double precioCompra, double precioVenta, TipoVehiculo tipoVehiculo, Estado estado, ArrayList<Reparacion> reparaciones) {

        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.tipoVehiculo = tipoVehiculo;
        this.estado = estado;
        this.reparaciones = reparaciones;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public ArrayList<Reparacion> getReparaciones() {
        return reparaciones;
    }

    public void setReparaciones(ArrayList<Reparacion> reparaciones) {
        this.reparaciones = reparaciones;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", matricula='" + matricula + '\'' +
                ", precioCompra=" + precioCompra +
                ", precioVenta=" + precioVenta +
                ", tipoVehiculo=" + tipoVehiculo +
                ", estado=" + estado +
                ", reparaciones=" + reparaciones +
                '}';
    }
}

