/*
ESTA CLASE FUNCIONA SE AGREGAN, SE CANCELAN RESERVAS Y VEMOS EL LISTADO DE COCHES RESERVADOS POR CLIENTE, SOLO QUEDA REPASAR LOS SOUT PARA QUE IMPRIMA BIEN
POR LO DEMÁS FUNCIONA.
 */
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
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("");
        while (opcion != 4) {
            System.out.println("*****MENU RESERVAS*****");
            System.out.println("1 - Reservar.");
            System.out.println("2 - Cancelar reserva.");
            System.out.println("3 - Lista reservas.");
            System.out.println("4 - Salir.");
            System.out.print("Elija una opcion: ");
            try {
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
            } catch (Exception ex) {
                scan.nextLine();
            }
        }
    }
    public void reservar() {
        HashMap<String, Coche> coches = opConcesionario.listarCoches();
        HashMap<String, Cliente> clientes = opConcesionario.listarClientes();

        Reserva reserva = new Reserva();
        reserva.setCoche(coches.get(verCoches(coches)));
        reserva.setCliente(clientes.get(verClientes(clientes)));

        opConcesionario.agregarReserva(reserva);
    }
    public void cancelar(){
        opConcesionario = new OperacionesConcesionario(concesionario);
        Scanner scan = new Scanner(System.in);
        int opcion;
        HashMap<String,Reserva> reservas = opConcesionario.listarReservas();
        // HACEMOS ARRAYLIST PARA TRABAJAR CON INDICES EN VEZ DE CON DNI
        ArrayList<Reserva> lista = new ArrayList<>();
        //COMPLETAMOS EL ARRAYLIST DE INDICES CON LOS VALORES CLIENTES DEL HASHMAP
        for (Reserva item : reservas.values()) {
            lista.add(item);
        }

        try {
            System.out.println("*****LISTA RESERVAS*****");
            System.out.println("");
            for(int i = 0; i < lista.size(); i++){
                System.out.println((i + 1) + lista.get(i).toString());
            }
            System.out.println((lista.size()+1) + " - Salir.");
            System.out.println("");
            System.out.print("Elija la reserva a cancelar: ");
            System.out.println((lista.size()+ 1 ) + " - Salir.");
            opcion = scan.nextInt();
            if(opcion == lista.size()+1){
                System.out.println("Volviendo al menú reservas.");
            }else if(opcion >= 1 && opcion <= lista.size()){
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
        System.out.println("*****LISTA COCHES*****");
        System.out.println("");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).toString());
        }
        System.out.println(lista.size() + 1 + " - Salir.");   // Para que sea dinámico haremos que la opcion salir sea una posicion mas que el tamaño de la lista
        System.out.println("");
        System.out.print("Elija el coche deseado por el cliente para reservar: ");
        System.out.println((lista.size()+1) + " - Salir.");
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
        System.out.println(lista.size() + 1 + " - Salir.");   // Para que sea dinámico haremos que la opcion salir sea una posicion mas que el tamaño de la lista
        System.out.println("");
        System.out.print("Elija el cliente: ");
        System.out.println((lista.size()+1) + " - Salir.");
        try {
            int opcion = scan.nextInt();
            if(opcion == lista.size() + 1) {

            }else {
                cliente = lista.get(opcion - 1);
            }

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
                System.out.println(index + " - " + reserva.toString());
                index++;
            }
        }
    }
}