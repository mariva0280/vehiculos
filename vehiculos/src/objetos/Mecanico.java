package objetos;

public class Mecanico extends Persona{
    public Mecanico() {
    }

    public Mecanico(String nombre, String direccion, String dni, String telefono) {
        super(nombre, direccion, dni, Integer.valueOf(telefono));
    }
}
