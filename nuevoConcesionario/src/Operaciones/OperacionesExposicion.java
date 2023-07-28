package Operaciones;

import Objetos.*;
import Validaciones.Validar;
import Exception.EinvalidPropertyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OperacionesExposicion {
    private Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;
    private OperacionesCoches opCoches;
    private Validar validar;

    public OperacionesExposicion(Concesionario concesionario) {
        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(concesionario);
        this.opCoches = new OperacionesCoches(concesionario);
        this.validar = new Validar(concesionario);
    }
    public void menuExposiciones(){
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("");
        while (opcion != 6) {
            System.out.println("*****MENU EXPOSICIONES*****");
            System.out.println("1 - Dar de alta Exposición.");
            System.out.println("2 - Dar de baja Exposición.");
            System.out.println("3 - Modificar Exposición.");
            System.out.println("4 - Listado Exposiciones.");
            System.out.println("5 - Eliminar coche exposición.");
            System.out.println("6 - Salir.");
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
                        opCoches.removerCocheExposicion();
                        break;
                    case (6):
                        break;
                }
            } catch (Exception ex) {
                scan.nextLine();
            }
        }
    }

    public void agregarExposicion() {
        try {

            Scanner scan = new Scanner(System.in);
            opConcesionario = new OperacionesConcesionario(concesionario);
            HashMap<String,Coche> cochesStock = opConcesionario.listarCoches();
            if(cochesStock.isEmpty()){
                System.out.println("No hay coches disponibles.Debes dar de alta al menos un coche.");
                return;
            }
            Exposicion exposicion = new Exposicion();
            System.out.print("Introduzca un número a la exposición: ");
            int numero = scan.nextInt();
            if (!validar.validarNumero(numero)) {
                throw new EinvalidPropertyException("Introduzca un número de exposición válido.");
            }
            if (validar.verificarNumRep(numero)) {
                System.out.println("El número de exposición ya está en uso. Introduzca un número diferente.");
                return;
            }
            exposicion.setNumeroExposicion(numero);
            scan.nextLine();

            System.out.print("Introduzca una dirección a la exposición: ");
            String direccion = scan.nextLine();
            if (!validar.validateDireccion(direccion)) {
                throw new EinvalidPropertyException("Dirección incorrecta.");
            }
            exposicion.setDireccion(direccion);

            System.out.print("Introduzca un teléfono de contacto para la exposición: ");
            String telefono = scan.nextLine();
            if (!validar.validateTelefono(telefono)) {
                throw new EinvalidPropertyException("Teléfono incorrecto.");
            }
            int movil = Integer.parseInt(telefono);
            if(!validar.verificarTlfRep(movil)) throw new EinvalidPropertyException("El teléfono ya está registrado");
            exposicion.setTelefono(telefono);

            System.out.print("Introduzca una ciudad de destino: ");
            String ciudad = scan.nextLine();
            if (!validar.validateCiudad(ciudad)) {
                throw new EinvalidPropertyException("Ciudad incorrecta.");
            }
            exposicion.setCiudad(ciudad);

            opConcesionario.agregarExposicion(exposicion);
            System.out.println("Exposición agregada correctamente.");

            System.out.print("¿Desea agregar un coche a la exposición? (S / N): ");
            String respuesta = scan.nextLine();
            if(respuesta.equalsIgnoreCase("S")) {
                OperacionesCoches opCoches = new OperacionesCoches(concesionario);
                opCoches.agregarCocheExposicion();
            }

        } catch (EinvalidPropertyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void removerExposicion() {
        try {
            if (concesionario.getExposiciones().isEmpty()) throw new EinvalidPropertyException("No existen exposiciones");
            else {
                Scanner scanner = new Scanner(System.in);
                opConcesionario = new OperacionesConcesionario(concesionario);
                HashMap<Integer, Exposicion> exposiciones = concesionario.getExposiciones();
                System.out.print("Introduzca el número de exposición a eliminar: ");
                int numero = scanner.nextInt();
                if (!exposiciones.containsKey(numero)) {
                    throw new EinvalidPropertyException("Número de exposición incorrecto.");
                }
                Exposicion exposicion = exposiciones.get(numero);
                int tamanio = exposicion.getCochesExposicion().size();
                if (tamanio == 0) {
                    opConcesionario.removerExposicion(exposicion);
                    System.out.println("Exposición eliminada correctamente.");
                } else throw new EinvalidPropertyException("No se puede eliminar la exposción tiene coches agregados.");
            }
        } catch (EinvalidPropertyException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarExposicion() {
        try {
            Scanner scan = new Scanner(System.in);
            int opcion;
            System.out.print("Elija la exposición a modificar: ");
            HashMap<Integer, Exposicion> exposiciones = concesionario.getExposiciones();
            ArrayList<Exposicion> indices = new ArrayList<>();
            for (Exposicion exposicion : exposiciones.values()) {
                indices.add(exposicion);
            }
            indicesExposiciones(indices);
            opConcesionario = new OperacionesConcesionario(concesionario);
            opcion = scan.nextInt();
            if (opcion > (indices.size() + 1)) {
                System.out.println("Opción Incorrecta.");
                modificarExposicion();
            } else if (opcion == indices.size() + 1) {

            } else {
                Exposicion exposicion = indices.get(opcion - 1);
                System.out.println("");
                while (opcion != 5) {
                    System.out.println("");
                    System.out.println("1 - Modificar numero de exposición.");
                    System.out.println("2 - Modificar dirección.");
                    System.out.println("3 - Modificar teléfono.");
                    System.out.println("4 - Modificar ciudad.");
                    System.out.println("5 - Guardar cambios.");
                    System.out.print("Elija una opcion: ");
                    opcion = scan.nextInt();
                    scan = new Scanner(System.in);
                    switch (opcion) {
                        case 1:
                            System.out.print("Nuevo número de exposición: ");
                            int numero = scan.nextInt();
                            if (!validar.validarNumero(numero)) {
                                throw new EinvalidPropertyException("Numero incorrecto.");
                            }
                            exposicion.setNumeroExposicion(numero);
                            break;
                        case 2:
                            System.out.print("Nueva dirección: ");
                            String direccion = scan.nextLine();
                            if (!validar.validateDireccion(direccion)) {
                                throw new EinvalidPropertyException("Dirección incorrecta.");
                            }
                            exposicion.setDireccion(direccion);
                            break;
                        case 3:
                            System.out.print("Nuevo teléfono: ");
                            String telefono = scan.nextLine();
                            if (!validar.validateTelefono(telefono)) {
                                throw new EinvalidPropertyException("Teléfono incorrecto.");
                            }
                            int movil = Integer.parseInt(telefono);
                            if(!validar.verificarTlfRep(movil)) throw new EinvalidPropertyException("El teléfono ya está registrado");
                            exposicion.setTelefono(telefono);
                            break;
                        case 4:
                            System.out.print("Nueva ciudad: ");
                            String ciudad = scan.nextLine();
                            if (!validar.validateCiudad(ciudad)) {
                                throw new EinvalidPropertyException("Ciudad incorrecta.");
                            }
                            exposicion.setCiudad(ciudad);
                            break;
                    }
                }
                System.out.println("Exposición modificada correctamente.");
            }
        } catch (EinvalidPropertyException e) {
            System.out.println(e.getMessage());
        }
    }
    public void indicesExposiciones(ArrayList<Exposicion> indices){
        System.out.println("");
        System.out.println("*****LISTA EXPOSICIONES*****");
        System.out.println("");
        for(int i = 0; i < indices.size(); i++){
            System.out.println((i + 1) + " - " + indices.get(i).toString());
            System.out.println("");
        }
        System.out.println(indices.size() + 1 + " - Salir.");
        System.out.println("");
    }
    public void listarExposiciones() {
        System.out.println("");
        System.out.println("*****LISTA EXPOSICIONES*****");
        System.out.println("");
        HashMap<Integer, Exposicion> exposiciones = opConcesionario.listarExposiciones();

        if (exposiciones.isEmpty()) {
            System.out.println("No hay exposiciones registradas.");
        } else {
            for (Exposicion exposicion : exposiciones.values()) {
                System.out.println(exposicion.toString());
                System.out.println("");
            }
        }
        System.out.println("");
    }
}