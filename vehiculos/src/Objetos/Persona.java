package Objetos;

public class Persona {
    private String nombre;
    private String direccion;
    private String dni;
    private int telefono;

    public Persona() {
    }

    public Persona(String nombre, String direccion, String dni, int telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.dni = dni;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return
                "Nombre: " + nombre + '\'' +
                        ". Direccion: " + direccion + '\'' +
                        ". DNI: " + dni + '\'' +
                        ". Telefono: " + telefono;
    }
}
