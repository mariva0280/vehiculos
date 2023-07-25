package Operaciones;

import Objetos.Cliente;
import Objetos.Concesionario;
import Objetos.DirectorComercial;
import Validaciones.Validar;
import Exception.EinvalidPropertyException;

import java.util.HashMap;
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
    private OperacionesMecanicos opMecanicos;


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
        this.opMecanicos = new OperacionesMecanicos(concesionario);
    }

    public void menuDirector() {
        Scanner scan = new Scanner(System.in);
        try {
            int opcion = 0;
            while (opcion != 7) {
                System.out.println("*****MENÚ DIRECTOR*****");
                System.out.println("1 - Acceso a menú coches,vendedores,clientes y mecánicos.");
                System.out.println("2 - Acceso a menú ventas.");
                System.out.println("3 - Acceso a menú exposiciones.");
                System.out.println("4 - Acceso a menú reparaciones.");
                System.out.println("5 - Acceso a menú reservas.");
                System.out.println("6 - Acceso a menú informes.");
                System.out.println("7 - Salir.");
                System.out.println("");
                System.out.print("Elija una opcion: ");
                opcion = scan.nextInt();
                switch (opcion) {
                    case (1):
                        try {
                            while(opcion != 5) {
                                System.out.println("*****ALTAS,BAJAS,MODIFICACIONES*****");
                                System.out.println("1 - Menú Coches.");
                                System.out.println("2 - Menú Vendedores.");
                                System.out.println("3 - Menú Clientes.");
                                System.out.println("4 - Menú Mecánicos.");
                                System.out.println("5 - Salir.");
                                System.out.print("Elija una opción: ");
                                opcion = scan.nextInt();
                                switch (opcion){
                                    case (1):
                                        opCoches.menuCoches();
                                        break;
                                    case (2):
                                        opVendedores.menuVendedores();
                                        break;
                                    case (3):
                                        opClientes.menuClientes();
                                        break;
                                    case (4):
                                        opMecanicos.menuMecanico();
                                        break;
                                    case (5):
                                        break;
                                    /*default:
                                        System.out.println("Opción incorrecta. Por favor, elija una opción válida.");
                                        break;*/
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println("Opción incorrecta: " + ex.getMessage());
                            menuDirector();
                        }
                        break;

                    case (2):
                        opVentas.menuVentas();
                        break;
                    case (3):
                        opExposiciones.menuExposiciones();
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
        }    catch (Exception ex) {
                scan.nextLine();
            }
        }
    }


