package Operaciones;

import Objetos.Coche;
import Objetos.Concesionario;
import Objetos.Exposicion;
import Objetos.Vendedor;
import Validaciones.Validar;
import Exception.EinvalidPropertyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OperacionesExposicion {
    private Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;

    public OperacionesExposicion(Concesionario concesionario) {
        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(concesionario);
    }
    public void menuExposiciones(){
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("");
        while (opcion != 5) {
            System.out.println("*****MENU EXPOSICIONES*****");
            System.out.println("1 - Dar de alta Exposición");
            System.out.println("2 - Dar de baja Exposición");
            System.out.println("3 - Modificar Exposición");
            System.out.println("4 - Listado Exposiciones");
            System.out.println("5 - Salir");
            System.out.println("");
            System.out.print("Elija una opcion: ");
            try {
                opcion = scan.nextInt();
                switch (opcion) {
                    case (1):
                        agregarExposicion();
                        break;
                    case (2):
                        removerExposicion();
                        break;
                    case (3):
                        modificarExposicion();
                        break;
                    case (4):
                        listarExposiciones();
                        break;
                    case (5):
                        break;
                    default:
                        System.out.println("Opción invalida.");
                        break;
                }
            } catch (Exception ex) {
                scan.nextLine();
            }
        }
    }

    public void agregarExposicion() {
        opConcesionario = new OperacionesConcesionario(concesionario);
        Scanner scanner = new Scanner(System.in);
        Exposicion exposicion = new Exposicion();
        try {
            System.out.println("Introduzca un número a la exposición:");
            int numero = scanner.nextInt();
            if (!Validar.validarNumero(numero)) {
                throw new EinvalidPropertyException("Introduzca un numero de exposición válido");
            }
            exposicion.setNumeroExposicion(numero);

            System.out.println("Introduzca una dirección a la exposición:");
            String direccion = scanner.nextLine();
            if (!Validar.validateDireccion(direccion)) {
                throw new EinvalidPropertyException("Dirección incorrecta");
            }
            exposicion.setDireccion(direccion);

            System.out.println("Introduzca un teléfono de contacto para la exposición:");
            String telefono = scanner.nextLine();
            if (!Validar.validateTelefono(telefono)) {
                throw new EinvalidPropertyException("Teléfono incorrecto");
            }
            exposicion.setTelefono(telefono);

            System.out.println("Introduzca una ciudad de destino:");
            String ciudad = scanner.nextLine();
            if (!Validar.validateCiudad(ciudad)) {
                throw new EinvalidPropertyException("Ciudad incorrecta");
            }
            exposicion.setCiudad(ciudad);
            opConcesionario.agregarExposicion(exposicion);
        } catch (EinvalidPropertyException e) {
            System.out.println(e.getMessage());
            agregarExposicion();
            System.out.println("Exposición agregada correctamente");
        }
    }

    public void removerExposicion() {
        opConcesionario = new OperacionesConcesionario(concesionario);
        Scanner scanner = new Scanner(System.in);
        try {
            HashMap<Integer, Exposicion> exposiciones = concesionario.getExposiciones();
            System.out.println("Introduzca el número de exposición a remover");
            int numero = scanner.nextInt();
            if (!exposiciones.containsKey(numero)) {
                throw new EinvalidPropertyException("Número de exposición incorrecto");
            }
            Exposicion exposicion = exposiciones.get(numero);
            opConcesionario.removerExposicion(exposicion);
            System.out.println("Exposición removida correctamente");
        } catch (EinvalidPropertyException e) {
            System.out.println(e.getMessage());
            removerExposicion();
        }
    }

    public void modificarExposicion() {
        opConcesionario = new OperacionesConcesionario(concesionario);
        Scanner scan = new Scanner(System.in);
        int opcion;
        HashMap<Integer, Exposicion> exposiciones = concesionario.getExposiciones();
        ArrayList<Exposicion> indices = new ArrayList<>();
        for (Exposicion exposicion : exposiciones.values()) {
            indices.add(exposicion);
        }
        indicesExposiciones(indices);
        System.out.println("Elija la exposición a modificar: ");
        try {
            opcion = scan.nextInt();
            if (opcion > (indices.size() + 1)) {
                System.out.println("Opcion Incorrecta !!");
                modificarExposicion();
            } else if (opcion == indices.size() + 1) {

            } else {
                Exposicion exposicion = indices.get(opcion - 1);
                System.out.println("");
                while (opcion != 5) {
                    System.out.println("");
                    System.out.println("");
                    System.out.println("1 - Modificar numero de exposición.");
                    System.out.println("2 - Modificar dirección.");
                    System.out.println("3 - Modificar teléfono.");
                    System.out.println("4 - Modificar ciudad.");
                    System.out.println("5 - Guardar cambios");
                    System.out.println("Elija una opcion: ");
                    opcion = scan.nextInt();
                    scan = new Scanner(System.in);
                    switch (opcion) {
                        case 1:
                            System.out.println("Nuevo número de exposición:");
                            int numero = scan.nextInt();
                            if (!Validar.validarNumero(numero)){
                                throw new EinvalidPropertyException("Numero incorrecto");
                            }
                            exposicion.setNumeroExposicion(numero);
                            break;
                        case 2:
                            System.out.println("Nueva dirección:");
                            String direccion = scan.nextLine();
                            if(!Validar.validateDireccion(direccion)){
                                throw new EinvalidPropertyException("Dirección incorrecta");
                            }
                            exposicion.setDireccion(direccion);
                            break;
                        case 3:
                            System.out.println("Nuevo teléfono:");
                            String telefono = scan.nextLine();
                            if(!Validar.validateTelefono(telefono)){
                                throw new EinvalidPropertyException("Teléfono incorrecto");
                            }
                            exposicion.setTelefono(telefono);
                            break;
                        case 4:
                            System.out.println("Nueva ciudad:");
                            String ciudad = scan.nextLine();
                            if(!Validar.validateCiudad(ciudad)){
                                throw new EinvalidPropertyException("Ciudad incorrecta");
                            }
                            exposicion.setCiudad(ciudad);
                            break;
                    }
                }
                System.out.println("Exposición modificada correctamente!!!");
            }
        } catch (EinvalidPropertyException e) {
            System.out.println(e.getMessage());
            modificarExposicion();
        }
    }
    private void indicesExposiciones(ArrayList<Exposicion> indices){
        System.out.println("");
        System.out.println("");
        System.out.println("----------------------------");
        System.out.println("-------LISTA EXPOSICIONES-----");
        System.out.println("----------------------------");
        for(int i = 0; i < indices.size(); i++){
            System.out.println((i + 1) + " - " + indices.get(i).toString());
            System.out.println("---------------------------------------------");
        }
        System.out.println(indices.size() + 1 + " - Salir");
        System.out.println("");
    }
    private void listarExposiciones() {
        System.out.println("");
        System.out.println("------------LISTA EXPOSICIONES-------------------");
        System.out.println("");
        HashMap<Integer, Exposicion> exposiciones = opConcesionario.listarExposiciones();

        if (exposiciones.isEmpty()) {
            System.out.println("No hay vendedores registrados.");
        } else {
            for (Exposicion exposicion : exposiciones.values()) {
                System.out.println(exposicion.toString());
                System.out.println("----------------------------------------");
            }
        }
        System.out.println("");
    }
}