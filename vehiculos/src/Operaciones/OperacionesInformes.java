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

    public OperacionesInformes(Concesionario concesionario) {

        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(concesionario);
        this.opCoches = new OperacionesCoches(concesionario);
        this.opVentas = new OperacionesVentas(concesionario);
        this.opReservas = new OperacionesReservas(concesionario);

    }
    public void menuInformes(){
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("");
        while (opcion != 7) {
            System.out.println("*****MENU INFORMES*****");
            System.out.println("1 - Coches en venta.");
            System.out.println("2 - Coches reservados.");
            System.out.println("3 - Coches en reparación.");
            System.out.println("4 - Clientes coches reservados.");
            System.out.println("5 - Clientes coches comprados.");
            System.out.println("6 - Vendedores coches vendidos.");
            System.out.println("7 - Salir.");
            System.out.println("");
            System.out.print("Elija una opcion: ");
            try {
                opcion = scan.nextInt();
                switch (opcion) {
                    case (1):
                        opCoches.listarCoches();
                        break;
                    case (2):
                        opReservas.listarReservas();
                        break;
                    case (3):
                        //modificar();
                        break;
                    case (4):
                        opReservas.listarReservasCliente();
                        break;
                    case (5):
                        opVentas.listarClientePorModelo();
                        break;
                    case (6):
                        opVentas.listarCochesPorVendedor();
                        break;
                }
            } catch (Exception ex) {
                scan.nextLine();
            }
        }

    }


}
