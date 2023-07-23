package Operaciones;

import Objetos.Concesionario;
import Objetos.DirectorComercial;

import java.util.Scanner;

public class OperacionesDirector {

    private  Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;
    private OperacionesCoches opCoches;
    private OperacionesClientes opClientes;
    private OperacionesExposicion opExposiciones;
    private OperacionesInformes opInformes;
    private OperacionesReparacion opReparaciones;
    private OperacionesReservas opReservas;
    private OperacionesVendedores opVendedores;
    private OperacionesVentas opVentas;


    public OperacionesDirector(Concesionario concesionario) {
        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(concesionario);
        this.opCoches = new OperacionesCoches(concesionario);
        this.opClientes = new OperacionesClientes(concesionario);
        this.opExposiciones = new OperacionesExposicion(concesionario);
        this.opInformes = new OperacionesInformes(concesionario);
        this.opReparaciones = new OperacionesReparacion(concesionario);
        this.opReservas = new OperacionesReservas(concesionario);
        this.opVendedores = new OperacionesVendedores(concesionario);
        this.opVentas = new OperacionesVentas(concesionario);
    }

    public void menuDirector() {
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        while (opcion != 7) {
            System.out.println("*****MENÚ DIRECTOR*****");
            System.out.println("1 - Dar de alta,baja,modificar, coches, clientes,vendedores y mecánicos.");
            System.out.println("2 - Dar de baja.");
            System.out.println("3 - Modificar.");
            System.out.println("4 - Consultar vendedores.");
            System.out.println("5 - Consultar clientes.");
            System.out.println("6 - Consultar coches en stock.");
            System.out.println("7 - Salir");
            System.out.println("");
            System.out.print("Elija una opcion: ");
            try {
                opcion = scan.nextInt();
                switch (opcion) {
                    case (1):
                        //opCoches.menuCoches(); podemos ponerlo así por menus para que nos pase por todas las opciones o llamando al metodo que queramos, eso ya lo que decidamos.
                        opCoches.agregar();
                        opVendedores.agregar();
                        opClientes.agregar();
                        opVentas.vender();
                        opReservas.reservar();
                        opReparaciones.agregarReparar();
                        opExposiciones.agregarExposicion();
                        opCoches.agregarCocheExposicion();
                        break;
                    case (2):
                        opCoches.eliminar();
                        opVendedores.eliminar();
                        opClientes.eliminar();
                        opExposiciones.removerExposicion();
                        opCoches.removerCocheExposicion();
                        opReservas.cancelar();
                        break;
                    case (3):
                        opCoches.modificar();
                        opVendedores.modificar();
                        opClientes.modificar();
                        opExposiciones.modificarExposicion();
                        break;
                    /*case (4):
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