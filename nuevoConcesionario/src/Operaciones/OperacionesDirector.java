package Operaciones;

import Objetos.Cliente;
import Objetos.Concesionario;
import Objetos.DirectorComercial;
import Validaciones.Validar;
import Exception.EinvalidPropertyException;

import java.util.HashMap;
import java.util.Scanner;

public class OperacionesDirector {

    private Concesionario concesionario;
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
        Scanner scan = new Scanner(System.in);
        try {
            int opcion = 0;
            while (opcion != 7) {
                System.out.println("*****MENÚ DIRECTOR*****");
                System.out.println("1 - Dar de alta,baja,modificar, coches, clientes,vendedores y mecánicos.");
                System.out.println("2 - Ventas.");
                System.out.println("3 - Exposiciones.");
                System.out.println("4 - Reparaciones.");
                System.out.println("5 - Reservas.");
                System.out.println("6 - Informes.");
                System.out.println("7 - Salir");
                System.out.println("");
                System.out.print("Elija una opcion: ");

                opcion = scan.nextInt();
                switch (opcion) {
                    case (1):
                        opCoches.menuCoches();
                        opVendedores.menuVendedores();
                        opClientes.menuClientes();
                        //opCoches.menuCoches(); podemos ponerlo así por menus para que nos pase por todas las opciones o llamando al metodo que queramos, eso ya lo que decidamos.
                        /*opCoches.agregar();
                        opVendedores.agregar();
                        opClientes.agregar();
                        opVentas.vender();
                        opReservas.reservar();
                        opReparaciones.agregarReparar();
                        opExposiciones.agregarExposicion();
                        opCoches.agregarCocheExposicion();*/
                        break;
                    case (2):
                        opVentas.menuVentas();
                        /*opVentas.listarClientePorModelo();
                        opVentas.listarCochesPorVendedor();
                        opVentas.vender();
                        opCoches.eliminar();
                        opVendedores.eliminar();
                        opClientes.eliminar();
                        opExposiciones.removerExposicion();
                        opCoches.removerCocheExposicion();
                        opReservas.cancelar();*/
                        break;
                    case (3):
                        opExposiciones.menuExposiciones();
                        /*opCoches.modificar();
                        opVendedores.modificar();
                        opClientes.modificar();
                        opExposiciones.modificarExposicion();*/
                        break;
                    case (4):
                        opReparaciones.menuReparaciones();
                        break;
                    case (5):
                        opReservas.menuReservas();
                        break;
                    case (6):
                        opInformes.menuInformes();
                        break;
                    case (7):
                        break;
                }
            }
        }
        catch(Exception ex){
                scan.nextLine();
            }
        }
}