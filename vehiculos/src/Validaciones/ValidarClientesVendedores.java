package Validaciones;

import Objetos.Cliente;
import operaciones.OperacionesConcesionario;

import java.util.HashMap;

public class ValidarClientesVendedores {
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
    public static boolean validateDni(String dni) {
        if(dni == null || dni.length() != 9) {
            return false;
        }
        String regex = "\\d{8}[A-Z]";
        if(!dni.matches(regex)){
            return false;
        }
        return true;
    }
    public static boolean validateTelefono(String telefono) {
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
}