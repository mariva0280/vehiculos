package objetos;

import java.util.ArrayList;

public class Exposicion {
    private int numeroExposicion;
    private String direccion;
    private int telefono;
    private String ciudad;
    private boolean estado;
    private ArrayList<Coche> cochesExposicion;

    public Exposicion() {
    }

    public Exposicion(int numeroExposicion, String direccion, int telefono, String ciudad, ArrayList<Coche> cochesExposicion,boolean estado) {
        this.numeroExposicion = numeroExposicion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.cochesExposicion = cochesExposicion;
        this.estado = estado;
    }

    public int getNumeroExposicion() {
        return numeroExposicion;
    }

    public void setNumeroExposicion(int numeroExposicion) {
        this.numeroExposicion = numeroExposicion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public ArrayList<Coche> getCochesExposicion() {
        return cochesExposicion;
    }

    public void setCochesExposicion(ArrayList<Coche> cochesExposicion) {
        this.cochesExposicion = cochesExposicion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Exposicion{" +
                "numeroExposicion=" + numeroExposicion +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                ", ciudad='" + ciudad + '\'' +
                ", cochesExposicion=" + cochesExposicion +
                '}';
    }
}