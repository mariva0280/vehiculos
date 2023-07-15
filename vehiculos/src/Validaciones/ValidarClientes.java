package Validaciones;

import objetos.Cliente;
import operaciones.OperacionesConcesionario;

import java.util.HashMap;

public class ValidarClientes {

    public static OperacionesConcesionario opConcesionario;

    public static boolean validateName(String nombre){
        if(nombre == null || nombre.isEmpty()){
            return false;
        }
        return true;
    }
    public static boolean validateDireccion(String direccion) {
        if(direccion == null || direccion.isEmpty()){
            return false;
        }
        return true;
    }
    public static boolean validateDni(String dni,OperacionesConcesionario opConcesionario) {
        if(dni == null || dni.length() != 9) {
            return false;
        }
        String regex = "\\d{8}[A-Z]";
        if(!dni.matches(regex)){
            return false;
        }
        HashMap<String, Cliente> clientes = opConcesionario.listarClientes();
        for(Cliente cliente : clientes.values()){
            if(cliente.getDni().equals(dni)){
                return false;
            }
        }

        return true;
    }
    public static boolean validateTelefono(String telefono, OperacionesConcesionario opConcesionario) {
        if(telefono == null || telefono.length() != 9) {
            return false;
        }
        for(char c : telefono.toCharArray()) {
            if(!Character.isDigit(c)){
                return false;
            }
        }
        HashMap<String, Cliente> clientes = opConcesionario.listarClientes();
        for (Cliente cliente : clientes.values()){
            if(String.valueOf(cliente.getTelefono()).equals(telefono)){
                return false;
            }
        }
        return true;
    }
}