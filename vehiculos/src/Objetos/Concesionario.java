package Objetos;


import java.util.ArrayList;
import java.util.HashMap;

public class Concesionario {
    private HashMap<String,Vendedor> vendedores;
    private HashMap<String,Cliente> clientes;
    private HashMap<String, Coche> coches;
    private HashMap<Integer, Exposicion> exposiciones;

    public Concesionario() {
        this.vendedores = new HashMap<>();
        this.clientes = new HashMap<>();
        this.coches = new HashMap<>();
        this.exposiciones = new HashMap<>();
    }

    public HashMap<String, Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(HashMap<String, Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public HashMap<Integer, Exposicion> getExposiciones() {
        return exposiciones;
    }

    public void setExposiciones(HashMap<Integer, Exposicion> exposiciones) {
        this.exposiciones = exposiciones;
    }

    public HashMap<String, Coche> getCoches() {
        return coches;
    }

    public void setCoches(HashMap<String, Coche> coches) {
        this.coches = coches;
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(HashMap<String, Cliente> clientes) {
        this.clientes = clientes;
    }
}