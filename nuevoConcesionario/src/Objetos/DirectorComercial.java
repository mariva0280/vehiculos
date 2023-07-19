package Objetos;

public class DirectorComercial extends Persona{
    public DirectorComercial() {
    }

    public DirectorComercial(String nombre, String direccion, String dni, String telefono) {
        super(nombre, direccion, dni, Integer.valueOf(telefono));
    }

}