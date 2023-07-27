/*
ESTA CLASE FUNCIONAN TODAS MENOS COCHES EN REPARACIÓN PORQUE TODAVIA NO ESTÁ HECHA
 */
package Operaciones;

import Objetos.*;

import java.util.Scanner;

public class OperacionesInformes {
    private Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;
    private OperacionesCoches opCoches;
    private OperacionesVentas opVentas;
    private OperacionesReservas opReservas;
    private OperacionesReparacion opReparaciones;
    private OperacionesExposicion opExposicion;

    public OperacionesInformes(Concesionario concesionario) {

        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(concesionario);
        this.opCoches = new OperacionesCoches(concesionario);
        this.opVentas = new OperacionesVentas(concesionario);
        this.opReservas = new OperacionesReservas(concesionario);
        this.opReparaciones = new OperacionesReparacion(concesionario);
        this.opExposicion = new OperacionesExposicion(concesionario);
    }
    public void menuInformes(){
        Scanner scan = new Scanner(System.in);
        try{
            int opcion = 0;
            System.out.println("");
            while (opcion != 9) {
                System.out.println("*****MENU INFORMES*****");
                System.out.println("1 - Coches en venta.");
                System.out.println("2 - Coches reservados.");
                System.out.println("3 - Coches en reparación.");
                System.out.println("4 - Coches en exposición.");
                System.out.println("5 - Clientes coches reservados.");
                System.out.println("6 - Clientes coches comprados.");
                System.out.println("7 - Vendedores coches vendidos.");
                System.out.println("8 - Totales por vendedor.");
                System.out.println("9 - Salir.");
                System.out.println("");
                System.out.print("Elija una opcion: ");

                opcion = scan.nextInt();
                switch (opcion) {
                    case (1):
                        opCoches.listarCoches();
                        break;
                    case (2):
                        opReservas.listarReservas();
                        break;
                    case (3):
                        opReparaciones.listarReparaciones();
                        break;
                    case (4):
                        opExposicion.listarExposiciones();
                        break;
                    case (5):
                        opReservas.listarReservasCliente();
                        break;
                    case (6):
                        opVentas.listarClientePorModelo();
                        break;
                    case (7):
                        opVentas.listarCochesPorVendedor();
                        break;
                    case (8):
                        opVentas.totalesVendedores();
                        break;
                    case (9):
                        break;
                }
            }
        }catch (Exception ex) {
            scan.nextLine();
        }
    }
}
