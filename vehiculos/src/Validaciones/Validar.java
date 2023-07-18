package Validaciones;

import Operaciones.OperacionesConcesionario;

public class Validar {
    public OperacionesConcesionario opConcesionario;

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
        String patron = "\\d{8}[A-Z]";
        if(!dni.matches(patron)){
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
    public static boolean validateMarca(String marca){
        if(marca == null || marca.isEmpty()){
            return false;
        }
        return true;
    }public static boolean validateModelo(String modelo){
        if(modelo == null || modelo.isEmpty()){
            return false;
        }
        return true;
    }
    public static boolean validarMatricula(String matricula){
        return matricula.matches("\\d{4}[A-Z]{3}");
    }
    public static boolean validarPrecioCompra(double precioCompra){
        if(precioCompra == 0){
            return false;
        }
        return true;
    }
    public static boolean validarPrecioVenta(double precioCompra,double precioVenta){
        if(precioVenta == 0 || precioVenta < precioCompra) {
            return false;
        }
        return true;
    }

    public static boolean validarTipoVehiculo(Enum tipoVehiculo){
        return tipoVehiculo != null;
    }
    public static boolean validarEstado(Enum estado) {
        return estado != null;
    }
}