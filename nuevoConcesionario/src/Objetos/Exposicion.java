package Objetos;

import java.util.ArrayList;

public class Exposicion {
    private int numeroExposicion;
    private String direccion;
    private String telefono;
    private String ciudad;
    private ArrayList<Coche> coches;

    public Exposicion() {
    }

    public Exposicion(int numeroExposicion, String direccion, String telefono, String ciudad) {
        this.numeroExposicion = numeroExposicion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.coches = new ArrayList<>();
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public ArrayList<Coche> getCochesExposicion() {
        return coches;
    }

    public void setCochesExposicion(ArrayList<Coche> cochesExposicion) {
        this.coches = cochesExposicion;
    }
    public void agregarCoche(Coche coche){
        coches.add(coche);
        coche.setEstado(Estado.EN_EXPOSICION);
    }

    @Override
    public String toString() {
        return "Exposicion{" +
                "numeroExposicion=" + numeroExposicion +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                ", ciudad='" + ciudad + '\'' +
                ", cochesExposicion=" + coches +
                '}';
    }
}