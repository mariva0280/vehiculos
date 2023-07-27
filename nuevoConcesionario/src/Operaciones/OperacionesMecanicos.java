package Operaciones;

import Objetos.Concesionario;
import Objetos.Mecanico;
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
                System.out.print("Elija una opcion: ");

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
        opConcesionario = new OperacionesConcesionario(concesionario);
        Mecanico mecanico = new Mecanico();
        Scanner scan = new Scanner(System.in);
        try {
            System.out.print("Introduzca el nombre del mecánico: ");
            String nombre = scan.nextLine();
            if(!validar.validateName(nombre)){
                throw new EinvalidPropertyException("Nombre incorrecto.");
            }
            mecanico.setNombre(nombre);

            System.out.print("Introduzca el DNI del mecánico: ");
            String dni =(scan.nextLine());
            if(!validar.validateDni(dni)){
                throw new EinvalidPropertyException("DNI incorrecto.");
            }
            if(validar.verificarDniRep(dni)){
                throw new EinvalidPropertyException("DNI duplicado.");
            }
            mecanico.setDni(dni);

            System.out.print("Introduzca el teléfono del mecánico: ");
            String telefonoStr = scan.nextLine();
            if(!validar.validateTelefono(telefonoStr)){
                throw new EinvalidPropertyException("Teléfono incorrecto.");
            }
            int telefono = Integer.parseInt(telefonoStr);
            if(!validar.verificarTlfRep(telefono)) throw new EinvalidPropertyException("El teléfono ya está registrado");
            mecanico.setTelefono(telefono);

            opConcesionario.agregarMecanico(mecanico);
            System.out.println("Mecánico  agregado correctamente.");
        } catch (EinvalidPropertyException ex){
            System.out.println("Error: " + ex.getMessage());
            menuFinalMecanico();
        }
    }
}