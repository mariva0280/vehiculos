package objetos;

import java.util.ArrayList;
import java.util.HashMap;

public class Concesionario {
    private HashMap<String,Vendedor> vendedores;
    private HashMap<String,Cliente> clientes;

    public Concesionario() {
        this.vendedores = new HashMap<String,Vendedor>();
        this.clientes = new HashMap<String,Cliente>();
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
}
