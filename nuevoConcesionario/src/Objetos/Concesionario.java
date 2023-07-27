package Objetos;


import java.util.HashMap;

public class Concesionario {
    private HashMap<String,Vendedor> vendedores;
    private HashMap<String,Cliente> clientes;
    private HashMap<String,Coche> coches;
    private HashMap<Integer, Exposicion> exposiciones;
    private HashMap<String,Reserva> cochesReservados;
    private HashMap<String, Venta> cochesVendidos;
    private HashMap<String, Reparacion> cochesReparacion;
    private HashMap<String,Mecanico> mecanicos;
    private DirectorComercial director;



    public Concesionario() {
        this.vendedores = new HashMap<String,Vendedor>();
        this.clientes = new HashMap<String,Cliente>();
        this.coches = new HashMap<String, Coche>();
        this.exposiciones = new HashMap<Integer,Exposicion>();
        this.cochesReservados = new HashMap<String,Reserva>();
        this.cochesVendidos = new HashMap<String, Venta>();
        this.cochesReparacion = new HashMap<>();
        this.mecanicos = new HashMap<>();
    }


    public HashMap<String, Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(HashMap<String, Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(HashMap<String, Cliente> clientes) {
        this.clientes = clientes;
    }

    public HashMap<String, Coche> getCoches() {
        return coches;
    }

    public void setCoches(HashMap<String, Coche> coches) {
        this.coches = coches;
    }

    public HashMap<Integer, Exposicion> getExposiciones() {
        return exposiciones;
    }

    public void setExposiciones(HashMap<Integer, Exposicion> exposiciones) {
        this.exposiciones = exposiciones;
    }

    public HashMap<String, Reserva> getCochesReservados() {
        return cochesReservados;
    }

    public void setCochesReservados(HashMap<String, Reserva> cochesReservados) {
        this.cochesReservados = cochesReservados;
    }

    public HashMap<String, Venta> getCochesVendidos() {
        return cochesVendidos;
    }

    public void setCochesVendidos(HashMap<String, Venta> cochesVendidos) {
        this.cochesVendidos = cochesVendidos;
    }

    public HashMap<String, Reparacion> getCochesReparacion() {
        return cochesReparacion;
    }

    public void setCochesReparacion(HashMap<String, Reparacion> cochesReparacion) {
        this.cochesReparacion = cochesReparacion;
    }

    public HashMap<String, Mecanico> getMecanicos() {
        return mecanicos;
    }

    public void setMecanicos(HashMap<String, Mecanico> mecanicos) {
        this.mecanicos = mecanicos;
    }

    public DirectorComercial getDirector() {
        return director;
    }

    public void setDirector(DirectorComercial director) {
        this.director = director;
    }
}