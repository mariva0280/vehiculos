package Objetos;

import java.util.ArrayList;

public class Cliente extends Persona{
    private ArrayList<Coche> cochesComprados;
    private ArrayList<Coche> cochesReservados;

    public Cliente() {
    }

    public Cliente(String nombre, String direccion, String dni, int telefono) {
        super(nombre, direccion, dni,telefono);

    }

    public ArrayList<Coche> getCochesComprados() {
        return cochesComprados;
    }

    public void setCochesComprados(ArrayList<Coche> cochesComprados) {
        this.cochesComprados = cochesComprados;
    }

    public ArrayList<Coche> getCochesReservados() {
        return cochesReservados;
    }

    public void setCochesReservados(ArrayList<Coche> cochesReservados) {
        this.cochesReservados = cochesReservados;
    }
}