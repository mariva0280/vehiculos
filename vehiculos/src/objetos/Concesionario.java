package objetos;

import java.util.ArrayList;
import java.util.HashMap;

public class Concesionario {
    private HashMap<String,Vendedor> vendedores;
    private HashMap<String,Cliente> clientes;

    public Concesionario() {
        this.vendedores = new HashMap<>();
        this.clientes = new HashMap<>();
    }

    public void agregarVendedor(Vendedor vendedor){
        vendedores.put(vendedor.getDni(),vendedor);
    }
    public void eliminarVendedor(String dni) {
        vendedores.remove(dni);
    }
    public HashMap<String,Vendedor> listarVendedores(){
        return vendedores;
    }

    public void agregarCliente(Cliente cliente){
        clientes.put(cliente.getDni(), cliente);
    }
    public void eliminarCliente(Cliente cliente) {
        clientes.remove(cliente.getDni());
    }
    public HashMap<String,Cliente> listarClientes() { return clientes;}
}
