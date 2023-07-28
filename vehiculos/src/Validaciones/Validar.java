package Validaciones;

import Objetos.*;
import Operaciones.OperacionesConcesionario;

import java.util.HashMap;

public class Validar {
    private OperacionesConcesionario opConcesionario;
    private Concesionario concesionario;
    // DirectorComercial director;

    public Validar (Concesionario concesionario) {
        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(this.concesionario);
        //this.director = concesionario.getDirector();
    }


    public boolean validateName(String nombre){
        if(nombre == null || nombre.isEmpty()){
            return false;
        }
        for(char c : nombre.toCharArray()) {
            if(Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
    public  boolean validateDireccion(String direccion) {
        if(direccion == null || direccion.isEmpty()){
            return false;
        }
        return true;
    }
    public  boolean validateDni(String dni) {
        if(dni == null || dni.length() != 9) {
            return false;
        }
        String patron = "\\d{8}[A-Z]";
        if(!dni.matches(patron)){
            return false;
        }

        return true;
    }
    public  boolean verificarDniRep(String dni){
        HashMap<String, Vendedor> vendedores = opConcesionario.listarVendedores();
        HashMap<String,Cliente> clientes = opConcesionario.listarClientes();
        HashMap<String, Mecanico> mecanicos = opConcesionario.listarMecanicos();
        DirectorComercial director = concesionario.getDirector();

        if( director.getDni().equals(dni)){
            return true;
        }
        if(vendedores.containsKey(dni) || clientes.containsKey(dni) || mecanicos.containsKey(dni)){
            return true;
        }
        return false;

    }
    public  boolean validateTelefono(String telefono) {
        if(telefono == null || telefono.length() != 9) {
            return false;
        }
        for(char c : telefono.toCharArray()) {
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
    public boolean verificarTlfRep(int telefono){
        HashMap<String,Cliente> clientes = concesionario.getClientes();
        HashMap<String,Vendedor> vendedores = concesionario.getVendedores();
        HashMap<String,Mecanico> mecanicos = concesionario.getMecanicos();
        HashMap<Integer, Exposicion> exposiciones = concesionario.getExposiciones();


        if(concesionario.getDirector() == null) return true;
        if(concesionario.getDirector().getTelefono() == telefono) return false;

        if(!vendedores.isEmpty()){
            for(Vendedor vendedor : vendedores.values()){
                if(vendedor.getTelefono() == telefono) return false;
            }
        }
        if(!clientes.isEmpty()){
            for(Cliente cliente : clientes.values()){
                if(cliente.getTelefono() == telefono) return false;
            }
        }
        if(!mecanicos.isEmpty()){
            for(Mecanico mecanico : mecanicos.values()){
                if(mecanico.getTelefono() == telefono) return false;
            }
        }
        if(!exposiciones.isEmpty()){
            for(Exposicion exposicion : exposiciones.values()){
                int numero = Integer.parseInt(exposicion.getTelefono());
                if(numero == telefono) return false;
            }
        }
        return true;

    }
    public  boolean validateMarca(String marca){
        if(marca == null || marca.isEmpty()){
            return false;
        }
        if(marca.matches(".\\d+.*")){
            return false;
        }
        return true;
    }
    public  boolean validateModelo(String modelo){
        if(modelo == null || modelo.isEmpty()){
            return false;
        }
        return true;
    }
    public  boolean validarMatricula(String matricula){
        return matricula.matches("\\d{4}[A-Z]{3}");
    }
    public boolean verificarMatriculaRep(String matricula) {
        HashMap<String, Coche> coches = opConcesionario.listarCoches();
        for (Coche coche : coches.values()) {
            if (coche.getMatricula().equalsIgnoreCase(matricula)) {
                return true;
            }
        }
        return false;
    }
    public  boolean validarPrecioCompra(double precioCompra){
        if(precioCompra == 0){
            return false;
        }
        return true;
    }
    public  boolean validarPrecioVenta(double precioCompra,double precioVenta){
        if(precioVenta == 0 || precioVenta < precioCompra) {
            return false;
        }
        return true;
    }

    public  boolean validarTipoVehiculo(Enum tipoVehiculo){
        return tipoVehiculo != null;
    }
    public  boolean validarEstado(Enum estado) {
        return estado != null;
    }
    public  boolean validarNumero (int numero){
        if (numero < 1 || numero > 99) return false;
        else return true;
    }
    public boolean verificarNumRep (int numero){
        HashMap<Integer, Exposicion> exposiciones = concesionario.getExposiciones();
        return exposiciones.containsKey(numero);
    }
    public  boolean validateCiudad(String ciudad) {
        for (char caracter : ciudad.toCharArray()) {
            if (Character.isDigit(caracter)) {
                return false;
            }
        }
        return true;
    }
}