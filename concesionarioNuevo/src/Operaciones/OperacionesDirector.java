package Operaciones;

import objetos.Concesionario;
import objetos.DirectorComercial;

import java.util.Scanner;

public class OperacionesDirector {
    int opcion;
    private final Concesionario concesionario;

    public OperacionesDirector(Concesionario concesionario) {
        this.concesionario = concesionario;
    }

    public void menuDirector() {
        Scanner scan = new Scanner(System.in);
        while (opcion != 7) {
            System.out.println("1 - Dar de alta");
            System.out.println("2 - Dar de baja");
            System.out.println("3 - Modificar");
            System.out.println("4 - Consultar vendedores");
            System.out.println("5 - Consultar clientes");
            System.out.println("6 - Consultar coches en stock");
            System.out.println("7 - Salir");
            System.out.println("");
            System.out.print("Elija una opcion: ");
            try {
                opcion = scan.nextInt();
                switch (opcion) {
                    /*case (1):
                        agregar();
                        break;
                    case (2):
                        eliminar();
                        break;
                    case (3):
                        modificar();
                        break;
                    case (4):
                        test();
                        break;
                    case (5):
                        imprimirClientes();
                        break;
                    case (6):
                        imprimirCoches();*/
                }
            } catch (Exception ex) {
                scan.nextLine();
            }
        }
    }

    /*private void agregar() {
        DirectorComercial director = new DirectorComercial();
        Scanner scan = new Scanner(System.in);
        try {
            System.out.print("Introduzca el nombre del director: ");
            director.setNombre(scan.nextLine());
            System.out.println("Introduzca la direccion del director: ");
            director.setDireccion(scan.nextLine());
            System.out.println("Introduzca el DNI del director: ");
            director.setDni(scan.nextLine());
            System.out.println("Introduzca el telefono del director: ");
            director.setTelefono(scan.nextInt());
            concesionario.agregarDirector(director);
            System.out.println("Director añadido correctamente.");
        } catch (Exception ex) {
            System.out.println(" ");
            System.out.println("Telefono incorrecto");
            agregar();
        }
        test();
    }*/
    /*private void test(){
        DirectorComercial directorComercial = concesionario.listarDirector();
        if ( directorComercial == null) System.out.println("No hay un director comercial asignado");
        else System.out.println(directorComercial.toString());
    }*/
}