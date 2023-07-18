package Operaciones;

import Objetos.*;

import java.util.HashMap;

public class OperacionesConcesionario {
    private Concesionario concesionario;
    public OperacionesConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
    }
    public void agregarVendedor(Vendedor vendedor) {
        HashMap<String, Vendedor> listaVendedores = concesionario.getVendedores();
        listaVendedores.put(vendedor.getDni(),vendedor);
        concesionario.setVendedores(listaVendedores);

    }
    public void eliminarVendedor(Vendedor vendedor) {
        HashMap<String, Vendedor> listaVendedores = concesionario.getVendedores();
        listaVendedores.remove(vendedor.getDni());
        concesionario.setVendedores(listaVendedores);
    }
    public void modificarVendedor(Vendedor vendedor) {
        HashMap<String, Vendedor> listaVendedores = concesionario.getVendedores();
        listaVendedores.replace(vendedor.getDni(),vendedor);
        concesionario.setVendedores(listaVendedores);
    }
    public HashMap listarVendedores(){
        return concesionario.getVendedores();
    }

    public void agregarCliente(Cliente cliente){
        HashMap<String, Cliente> listaClientes = concesionario.getClientes();
        listaClientes.put(cliente.getDni(), cliente);
        concesionario.setClientes(listaClientes);
    }
    public void eliminarCliente(Cliente cliente) {
        HashMap<String, Cliente> listaClientes = concesionario.getClientes();
        listaClientes.remove(cliente.getDni());
        concesionario.setClientes(listaClientes);
    }
    public void modificarCliente(Cliente cliente) {
        HashMap<String,Cliente> listaClientes = concesionario.getClientes();
        listaClientes.replace(cliente.getDni(), cliente);
        concesionario.setClientes(listaClientes);
    }
    public HashMap listarClientes() { return concesionario.getClientes();}
    public void agregarCoche (Coche coche) {
        HashMap<String, Coche> listaCoches = concesionario.getCoches();
        listaCoches.put(coche.getMatricula(),coche);
        concesionario.setCoches(listaCoches);
    }
    public void eliminarCoche (Coche coche) {
        HashMap<String, Coche> listarCoches = concesionario.getCoches();
        listarCoches.remove(coche.getMatricula(),coche);
        concesionario.setCoches(listarCoches);
    }
    public void modificarCoche (Coche coche) {
        HashMap<String,Coche> listaCoches = concesionario.getCoches();
        listaCoches.replace(coche.getMatricula(),coche);
        concesionario.setCoches(listaCoches);
    }
    public HashMap<String,Coche> listarCoches(){ return concesionario.getCoches();}
    public void agregarExposicion(Exposicion exposicion) {
        HashMap<Integer, Exposicion> listaExposiciones = concesionario.getExposiciones();
        listaExposiciones.put(exposicion.getNumeroExposicion(), exposicion);
        concesionario.setExposiciones(listaExposiciones);
    }

    public void removerExposicion(Exposicion exposicion) {
        HashMap<Integer, Exposicion> listaExposiciones = concesionario.getExposiciones();
        listaExposiciones.remove(exposicion.getNumeroExposicion(), exposicion);
        concesionario.setExposiciones(listaExposiciones);

    }
    public void modificarExposicion(Exposicion exposicion){
        HashMap<Integer, Exposicion> listaExposiciones = concesionario.getExposiciones();
        listaExposiciones.replace(exposicion.getNumeroExposicion(), exposicion);
        concesionario.setExposiciones(listaExposiciones);
    }


    public HashMap<Integer,Exposicion> listarExposiciones(){
        return concesionario.getExposiciones();
    }
}
