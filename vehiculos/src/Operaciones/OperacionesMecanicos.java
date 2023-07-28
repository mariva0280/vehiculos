package Operaciones;

import Objetos.Concesionario;
import Objetos.Mecanico;
import Objetos.Vendedor;
import Validaciones.Validar;
import Exception.EinvalidPropertyException;

import java.util.Scanner;

public class OperacionesMecanicos {
    private Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;
    private OperacionesReparacion opReparacion;
    private Validar validar;
    public OperacionesMecanicos(Concesionario concesionario) {

        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(concesionario);
        this.opReparacion = new OperacionesReparacion(concesionario);
        this.validar = new Validar(concesionario);
    }
    public void menuFinalMecanico() {
        Scanner scan = new Scanner(System.in);
        try{
            int opcion = 0;
            System.out.println("");
            while(opcion != 4){
                System.out.println("*****MENU MECANICOS*****");
                System.out.println("1 - Consultar coches reparación.");
                System.out.println("2 - Añadir reparación.");
                System.out.println("3 - Modificar estado reparación.");
                System.out.println("4 - Salir.");
                System.out.print("Elija la opción: ");
                opcion = scan.nextInt();
                switch (opcion) {
                    case (1):
                        opReparacion.listarReparaciones();
                        break;
                    case (2):
                        opReparacion.agregarReparar();
                        break;
                    case (3):
                        opReparacion.modificarEstado();
                        break;
                    case (4):
                        break;
                }
            }
        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
    public void menuMecanico(){
        Scanner scan = new Scanner(System.in);
        try{
            int opcion = 0;
            System.out.println("");
            while (opcion != 2) {
                System.out.println("*****MENU MECANICOS*****");
                System.out.println("1 - Dar de alta.");
                System.out.println("2 - Salir.");
                System.out.println("");
                System.out.print("Elija una opción: ");

                opcion = scan.nextInt();
                switch (opcion) {
                    case (1):
                        agregarM();
                        break;
                    case (2):
                        break;
                }
            }
        } catch (Exception ex) {
            scan.nextLine();
        }
    }
    public void agregarM() {
        try {
            opConcesionario = new OperacionesConcesionario(concesionario);
            Mecanico mecanico = new Mecanico();
            Scanner scan = new Scanner(System.in);

            String nombre, direccion, dni, telefonoStr;
            int telefono;

            System.out.print("Introduzca el nombre del mecánico: ");
            while (true) {
                nombre = scan.nextLine();
                if (!validar.validateName(nombre)) {
                    throw new EinvalidPropertyException("Nombre incorrecto.Ingrese el nombre de nuevo");
                }else break;
            }
            mecanico.setNombre(nombre);

            System.out.print("Introduzca el DNI del mecánico: ");
            while (true) {
                dni = scan.nextLine();
                if (!validar.validateDni(dni)) {
                    System.out.println("DNI incorrecto.Ingrese el DNI de nuevo.");
                } else if (validar.verificarDniRep(dni)) {
                    System.out.println("DNI duplicado.Ingrese el DNI de nuevo.");
                } else {
                    break;
                }
            }
            mecanico.setDni(dni);

            System.out.print("Introduzca el teléfono del mecánico: ");
            while (true) {
                telefonoStr = scan.nextLine();
                if (!validar.validateTelefono(telefonoStr)) {
                    System.out.println("Error: Teléfono incorrecto.Ingrese el teléfono de nuevo.");
                } else {
                    telefono = Integer.parseInt(telefonoStr);
                    if (!validar.verificarTlfRep(telefono)) {
                        System.out.println("Error: Teléfono duplicado.Ingrese el teléfono de nuevo.");
                    } else {
                        break;
                    }
                }
            }
            mecanico.setTelefono(telefono);

            opConcesionario.agregarMecanico(mecanico);
            System.out.println("Mecánico agregado correctamente.");
        } catch (EinvalidPropertyException ex) {
            System.out.println("Error: " + ex.getMessage());
            agregarM();
        }
    }
}
