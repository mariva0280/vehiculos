package Proyecto;

import Objetos.Concesionario;
import Operaciones.*;

import java.util.Scanner;

public class ProyectoConcesionario {

    public ProyectoConcesionario (){
    }

    public static void main(String[] args) {
        mostrarMenu();
        System.out.println("Gracias por su visita.");

    }
    public static void mostrarMenu() {
        Concesionario concesionario = new Concesionario();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        System.out.println("******************************************");
        System.out.println("*****Bienvenido al concesionario VV*****");
        System.out.println("*****Pulse la opción deseada para acceder*****");
        System.out.println("");
        while (opcion != 5) {
            try {
                System.out.println("1 - Director.");
                System.out.println("2 - Vendedor.");
                System.out.println("3 - Cliente.");
                System.out.println("4 - Mecánico.");
                System.out.println("5 - Salir.");
                System.out.println(" ");
                System.out.print("Eliga la opción: ");
                opcion = scanner.nextInt();

                if (opcion > 5) {
                    System.out.println("La opción no  es valida");
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

                            OperacionesExposicion opExposiciones = new OperacionesExposicion(concesionario);
                            opExposiciones.menuExposiciones();

                            break;
                        case (5):
                            System.exit(0);
                    }
                }
            } catch (Exception ex) {
                System.out.println("*****Opción inválida.*****");
                mostrarMenu();
            }
        }
    }
}