package Objetos;

import java.util.ArrayList;

public class Vendedor extends Persona {
    private ArrayList<Coche> cochesVendidos;

    public Vendedor() {
    }

    public Vendedor(String nombre, String direccion, String dni, int telefono) {
        super(nombre, direccion, dni,telefono);

    }

    public ArrayList<Coche> getCochesVendidos() {
        return cochesVendidos;
    }

    public void setCochesVendidos(ArrayList<Coche> cochesVendidos) {
        this.cochesVendidos = cochesVendidos;
    }


}
