package Proyecto;

import Objetos.Concesionario;
import operaciones.*;

import java.util.Scanner;

public class ProyectoConcesionario {
    private final Concesionario concesionario = new Concesionario();
    private OperacionesClientes opClientes;
    private OperacionesConcesionario opConcesionario;
    private OperacionesDirector opDirector;
    private OperacionesExposicion opExposicion;
    private OperacionesInformes opInformes;
    //private OperacionesReparacion opReparacion = new OperacionesReparacion();
    private OperacionesReservas opReservas;
    private OperacionesVendedores opVendedores;
    private OperacionesVentas opVentas;



    public static void main(String[] args) {
        while(opcion != 5) {
            mostrarMenu();
        }
        System.out.println("Gracias por su visita");

    }
    public static void mostrarMenu() {
        System.out.println("Bienvenido al concesionario VV");
        System.out.println("¿Quién desea acceder al concesionario?");
        System.out.println("Pulse 1 para director");
        System.out.println("Pulse 2 para vendedor");
        System.out.println("Pulse 3 para cliente");
        System.out.println("Pulse 4 para mecanico");
        System.out.println("Pulse 5 para salir");
        Scanner scanner = new Scanner(System.in);

        System.out.println(" ");
        System.out.print("Eliga la opcion: ");
        opcion = scanner.nextInt();

        if(opcion > 5 ) {
            System.out.println("La opcion no  es valida");
            mostrarMenu();
        } else if (opcion < 5) {
            switch (opcion) {
                case (1):
                    break;
                case (2):
                    OperacionesVendedores opVendedores = new OperacionesVendedores(concesionario);
                    opVendedores.menuVendedores();
                    break;
                case (3):
                    OperacionesClientes opClientes = new OperacionesClientes(concesionario);
                    opClientes.menuClientes();
                    break;
                case (4):
                    break;
            }
        }
    }
}