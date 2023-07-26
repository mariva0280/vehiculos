package Operaciones;

import Objetos.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OperacionesReservas {
    private Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;

    public OperacionesReservas(Concesionario concesionario) {

        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(concesionario);
    }
    public void menuReservas(){
        Scanner scan = new Scanner(System.in);
        try {
            int opcion = 0;
            System.out.println("");
            while (opcion != 4) {
                System.out.println("*****MENU RESERVAS*****");
                System.out.println("1 - Reservar.");
                System.out.println("2 - Cancelar reserva.");
                System.out.println("3 - Lista reservas.");
                System.out.println("4 - Salir.");
                System.out.print("Elija una opcion: ");

                opcion = scan.nextInt();
                switch (opcion) {
                    case (1):
                        reservar();
                        break;
                    case (2):
                        cancelar();
                        break;
                    case (3):
                        listarReservas();
                        break;
                    case (4):
                        break;
                }
            }
        }catch (Exception ex) {
            scan.nextLine();
        }
    }
    public void reservar() {

        HashMap<String, Coche> coches = opConcesionario.listarCoches();
        HashMap<String, Cliente> clientes = opConcesionario.listarClientes();

        Reserva reserva = new Reserva();
        reserva.setCliente(clientes.get(verClientes(clientes)));
        Coche coche = coches.get(verCoches(coches));
        reserva.setCoche(coche);
        opConcesionario.agregarReserva(reserva);
        opConcesionario.eliminarCoche(coche);
    }
    public void cancelar(){
        try{
            opConcesionario = new OperacionesConcesionario(concesionario);
            Scanner scan = new Scanner(System.in);
            int opcion;
            HashMap<String,Reserva> reservas = opConcesionario.listarReservas();
            ArrayList<Reserva> lista = new ArrayList<>();
            for (Reserva item : reservas.values()) {
                lista.add(item);
            }

            System.out.println("*****LISTA RESERVAS*****");
            System.out.println("");
            for(int i = 0; i < lista.size(); i++){
                System.out.println((i + 1) + lista.get(i).toString());
            }
            System.out.println((lista.size()+1) + " - Salir.");
            System.out.println("");
            System.out.print("Elija la reserva a cancelar de la lista o pulse " + (lista.size()+1) + " para salir: ");
            opcion = scan.nextInt();
            if(opcion == lista.size()+1){
                System.out.println("Volviendo al menú reservas.");
            }else if(opcion >= 1 && opcion <= lista.size()){
                Reserva reserva = lista.get(opcion-1);
                Coche coche = reserva.getCoche();
                coche.setEstado(Estado.STOCK);
                opConcesionario.agregarCoche(coche);
                opConcesionario.eliminarReserva(lista.get(opcion - 1));
                System.out.println("Reserva cancelada correctamente.");
            }else{
                System.out.println("Opción incorrecta, vuelva a intentarlo.");
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            cancelar();
        }
    }

    public String verCoches(HashMap<String, Coche> coches) {
        ArrayList<Coche> lista = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        Coche coche = new Coche();

        for (Coche item : coches.values()) {
            lista.add(item);
        }
        System.out.println("*****LISTA COCHES PARA RESERVAR*****");
        System.out.println("");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).toString());
        }
        System.out.println(lista.size() + 1 + " - Salir.");
        System.out.println("");
        System.out.print("Elija el coche deseado por el cliente de la lista para reservar o pulse " + (lista.size() + 1) + " para salir: ");
        try {
            int opcion = scan.nextInt();
            if(opcion == lista.size() + 1) {

            }else {
                coche = lista.get(opcion - 1);
            }


        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return coche.getMatricula();
    }
    public String verClientes(HashMap<String, Cliente> clientes) {
        ArrayList<Cliente> lista = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        Cliente cliente = new Cliente();

        for (Cliente item : clientes.values()) {
            lista.add(item);
        }
        System.out.println("*****LISTA CLIENTES*****");
        System.out.println("");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).toString());
        }
        System.out.println(lista.size() + 1 + " - Salir.");
        System.out.println("");
        System.out.print("Elija de la lista el cliente que desea reservar el coche o pulse " + (lista.size() + 1) + " para salir: ");

        try {
            int opcion = scan.nextInt();
            if(opcion == lista.size() + 1) {

            }else {
                cliente = lista.get(opcion - 1);
            }
            System.out.println("Reserva realizada correctamente.");

        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return cliente.getDni();
    }
    public void listarReservas(){
        HashMap<String,Reserva> reservas = opConcesionario.listarReservas();
        if(reservas.isEmpty()){
            System.out.println("No hay reservas realizadas.");
        }else {
            System.out.println("*****LISTA DE RESERVAS*****");
            System.out.println("");
            int index = 1;
            for(Reserva reserva : reservas.values()){
                System.out.println(index + " - " + reserva.toString1());
                index++;
            }
        }
    }
    public void listarReservasCliente() {
        HashMap<String,Reserva> reservas = opConcesionario.listarReservas();
        ArrayList<Reserva> lista = new ArrayList<>();
        for (Reserva reserva : reservas.values()) {
            lista.add(reserva);
        }
        System.out.println("*****LISTA DE COCHES RESERVADOS*****");
        for(int i = 0; i < lista.size(); i++){
            System.out.println((i + 1) + " - " + lista.get(i).getCoche().toString());
        }
        System.out.println("");
        System.out.print("Escoga el coche de la lista o pulse " + (lista.size()+1) + " para salir: ");

        Scanner scan = new Scanner(System.in);

        try{
            int opcion = scan.nextInt();
            System.out.println("Cliente que ha reservado el coche: " + lista.get(opcion-1).getCliente().toString());

        }catch (Exception ex){
            System.out.println("Error: " +ex.getMessage());
        }
    }

}