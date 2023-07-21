/*
ESTA CLASE EN PRINCIPIO FUNCIONA PORQUE LA HE IDO PROBANDO POCO A POCO Y EN EL
METODO LLENAR CONCESIONARIO ME HA FUNCIONADA AL AGREGAR COCHES, PERO SERÁ LA
ULTIMA QUE COMPROBAREMOS CUANDO TERMINEMOS
 */
package Operaciones;

import Objetos.*;
import Validaciones.Validar;
import Exception.EinvalidPropertyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OperacionesCoches {
    private Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;

    public OperacionesCoches(Concesionario concesionario) {

        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(concesionario);
    }

    public void menuCoches() {
        try {
            int opcion = 0;
            Scanner scan = new Scanner(System.in);
            System.out.println("");
            while (opcion != 5) {
                System.out.println("*****MENU GESTION COCHES*****");
                System.out.println("1 - Dar de alta.");
                System.out.println("2 - Dar de baja.");
                System.out.println("3 - Modificar.");
                System.out.println("4 - Listado Coches.");
                System.out.println("5 - Salir.");
                System.out.println("");
                System.out.print("Elija una opcion: ");

                opcion = scan.nextInt();
                switch (opcion) {
                    case (1):
                        agregar();
                        break;
                    case (2):
                        eliminar();
                        break;
                    case (3):
                        modificar();
                        break;
                    case (4):
                        listarCoches();
                        break;
                    case (5):
                        break; // Salir del ciclo while y regresar al método mostrarMenu

                }
            }
        }
        catch (Exception ex) {
                menuCoches();
            }
        }


    public void agregar() {
        try {
            opConcesionario = new OperacionesConcesionario(concesionario);
            Coche coche = new Coche();
            Scanner scan = new Scanner(System.in);
            System.out.print("Introduzca la marca del coche: ");
            String marca = scan.nextLine();
            if (!Validar.validateMarca(marca)) {
                throw new EinvalidPropertyException("Marca incorrecta no admite campos nulos.");
            }
            coche.setMarca(marca);

            System.out.print("Introduzca el modelo del coche: ");
            String modelo = scan.nextLine();
            if (!Validar.validateModelo(modelo)) {
                throw new EinvalidPropertyException("Modelo incorrecto no admite campos nulos.");
            }
            coche.setModelo(modelo);

            System.out.print("Introduzca la matricula del coche: ");
            String matricula = scan.nextLine();
            if (!Validar.validarMatricula(matricula)) {
                throw new EinvalidPropertyException("Matricula incorrecta.");
            }
            if (verificarMatriculaRep(matricula)) {
                throw new EinvalidPropertyException("Matrícula repetida.");
            }

            coche.setMatricula(matricula);

            System.out.print("Introduzca el precio de compra del coche: ");
            double precioCompra = scan.nextDouble();
            if (!Validar.validarPrecioCompra(precioCompra)) {
                throw new EinvalidPropertyException("El precio de compra no puede ser 0");
            }
            coche.setPrecioCompra(precioCompra);

            System.out.print("Introduzca el precio de venta del coche: ");
            double precioVenta = scan.nextDouble();
            if (!Validar.validarPrecioVenta(precioCompra, precioVenta)) {
                throw new EinvalidPropertyException("El precio de venta no puede ser 0 ni inferior al precio de compra.");
            }
            coche.setPrecioVenta(precioVenta);

            System.out.print("Introduzca el tipo de coche: ");
            String tipoCocheStr = scan.next();
            TipoVehiculo tipoCoche = TipoVehiculo.valueOf(tipoCocheStr.toUpperCase());
            if (!Validar.validarTipoVehiculo(tipoCoche)) {
                throw new EinvalidPropertyException("El tipo de vehiculo solo puede ser turismo, todoterreno o industrial.");
            }
            coche.setTipoVehiculo(tipoCoche);


            System.out.print("Introduca el estado del coche: ");
            String estadoStr = scan.next();
            Estado estado = Estado.valueOf(estadoStr.toUpperCase());
            if (!Validar.validarEstado(estado)) {
                throw new EinvalidPropertyException("El estado del vehículo solo puede ser en stock o reservado.");
            }
            coche.setEstado(estado);

            opConcesionario.agregarCoche(coche);
            System.out.println("Coche añadido correctamente.");


        } catch (EinvalidPropertyException ex) {
            System.out.println("Error: " + ex.getMessage());
            agregar();
        }
    }

    public void eliminar() {
        try {
        opConcesionario = new OperacionesConcesionario(concesionario);
        Scanner scan = new Scanner(System.in);
        int opcion;
        HashMap<String, Coche> coches = opConcesionario.listarCoches();
        // HACEMOS ARRAYLIST PARA TRABAJAR CON INDICES EN VEZ DE CON DNI
        ArrayList<Coche> indices = new ArrayList<>();
        //COMPLETAMOS EL ARRAYLIST DE INDICES CON LOS VALORES VENDEDORES DEL HASHMAP
        for (Coche item : coches.values()) {
            indices.add(item);
        }
        //PASAMOS ARRALIST AL METODO LISTARVENDEDORES Y REUTILIZAR CODIGO
        indicesCoches(indices);
        System.out.print("Elija el coche a eliminar: ");
        //System.out.println("Si no desea eliminar vendedor pulse 3 para salir: ");

            opcion = scan.nextInt();
            if (opcion > (indices.size() + 1)) { // Si la opcion es mayor que el tamaño del ARRAYLIST, indices.size() + 1 nos dice que nos salimos de las posibles opciones del menu
                System.out.println("Opción incorrecta.");
                eliminar();
            } else if (opcion == indices.size() + 1) { // Si la opcion es una posicion mas que el tamaño de la lista significa que es la opcion "salir" y no hacemos nada

            } else {
                opConcesionario.eliminarCoche(indices.get(opcion - 1)); // Pasamos al metodo eliminarVendedor del concesionario, el vendedor elegido en la lista
                System.out.println("Coche eliminado correctamente.");
                eliminar();
            }
        } catch (Exception ex) {
            System.out.println("Opción incorrecta.");
            eliminar();
        }

    }

    public void modificar() {
        int opcion;
        try {
            Scanner scan = new Scanner(System.in);
            opConcesionario = new OperacionesConcesionario(concesionario);
            HashMap<String, Coche> coches = opConcesionario.listarCoches();
            ArrayList<Coche> indices = new ArrayList<>();
            for (Coche coche : coches.values()) {
                indices.add(coche);
            }
            indicesCoches(indices);
            System.out.print("Elija el coche a modificar: ");
            opcion = scan.nextInt();
            if (opcion > (indices.size() + 1)) {
                System.out.println("Opción Incorrecta.");
                modificar();
            } else if (opcion == indices.size() + 1) {

            } else {
                Coche coche = indices.get(opcion - 1);
                System.out.println("");
                while (opcion != 8) {
                    System.out.println("");
                    System.out.println("1 - Modificar marca. ");
                    System.out.println("2 - Modificar modelo.");
                    System.out.println("3 - Modificar matrícula.");
                    System.out.println("4 - Modificar precio de Compra.");
                    System.out.println("5 - Modificar precio de Venta.");
                    System.out.println("6 - Modificar tipo de vehículo.");
                    System.out.println("7 - Modificar estado del vehículo.");
                    System.out.println("8 - Guardar cambios");
                    System.out.print("Elija una opcion: ");
                    opcion = scan.nextInt();
                    scan = new Scanner(System.in);
                    switch (opcion) {
                        case 1:
                            System.out.print("Nueva marca: ");
                            String nuevaMarca = scan.nextLine();
                            if (!Validar.validateMarca(nuevaMarca)) {
                                throw new EinvalidPropertyException("Nueva marca incorrecta.");
                            }
                            coche.setMarca(nuevaMarca);
                            break;
                        case 2:
                            System.out.print("Nuevo modelo: ");
                            String nuevoModelo = scan.nextLine();
                            if (!Validar.validateModelo(nuevoModelo)) {
                                throw new EinvalidPropertyException("Nuevo modelo incorrecto.");
                            }
                            coche.setModelo(nuevoModelo);
                            break;
                        case 3:
                            System.out.print("Nueva matrícula: ");
                            String nuevaMatricula = scan.nextLine();
                            if (!Validar.validarMatricula(nuevaMatricula)) {
                                throw new EinvalidPropertyException("Matrícula incorrecta.");
                            }
                            if (verificarMatriculaRep(nuevaMatricula)) {
                                throw new EinvalidPropertyException("La matrícula está repetida.");
                            }
                            coche.setMatricula(nuevaMatricula);
                            break;
                        case 4:
                            System.out.print("Nuevo precio de compra: ");
                            double nuevoPrecioCompra = scan.nextDouble();
                            if (!Validar.validarPrecioCompra(nuevoPrecioCompra)) {
                                throw new EinvalidPropertyException("Precio de compra no puede ser 0.");
                            }
                            coche.setPrecioCompra(nuevoPrecioCompra);
                            break;
                        case 5:
                            System.out.print("Nuevo precio de venta: ");
                            double nuevoPrecioVenta = scan.nextDouble();
                            if (!Validar.validarPrecioVenta(coche.getPrecioCompra(), nuevoPrecioVenta)) {
                                throw new EinvalidPropertyException("Precio de venta no puede ser 0, ni menos que el precio de compra.");
                            }
                            coche.setPrecioVenta(nuevoPrecioVenta);
                            break;
                        case 6:
                            System.out.print("Nuevo tipo de vehículo: ");
                            String nuevoTipoStr = scan.next();
                            TipoVehiculo nuevoTipo = TipoVehiculo.valueOf(nuevoTipoStr.toUpperCase());
                            if (!Validar.validarTipoVehiculo(nuevoTipo)) {
                                throw new EinvalidPropertyException("Tipo de vehículo incorrecto, solo puede ser turismo, todoterreno o industrial.");
                            }
                            coche.setTipoVehiculo(nuevoTipo);
                            break;
                        case 7:
                            System.out.print("Nuevo estado del vehiculo: ");
                            String nuevoEstadoStr = scan.next();
                            Estado nuevoEstado = Estado.valueOf(nuevoEstadoStr.toUpperCase());
                            if (!Validar.validarEstado(nuevoEstado)) {
                                throw new EinvalidPropertyException("Estado incorrecto.");
                            }
                            coche.setEstado(nuevoEstado);
                            break;
                    }
                }
                opConcesionario.modificarCoche(coche);
                System.out.println("Coche modificado correctamente.");
            }
        } catch (EinvalidPropertyException ex) {
            System.out.println("Opción incorrecta.");
            modificar();
        }

    }

    public void agregarCocheExposicion() { //No lo retira del stock
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Elija una exposición para agregar el coche");
            int opcion;
            HashMap<Integer, Exposicion> exposiciones = concesionario.getExposiciones();
            ArrayList<Exposicion> indices = new ArrayList<>();
            for (Exposicion exposicion : exposiciones.values()) {
                indices.add(exposicion);
            }
            OperacionesExposicion opExposiciones = new OperacionesExposicion(concesionario);
            opExposiciones.indicesExposiciones(indices);
            opConcesionario = new OperacionesConcesionario(concesionario);
            opcion = scanner.nextInt();
            if (opcion > (indices.size() + 1)) {
                System.out.println("Opcion Incorrecta !!");
                agregarCocheExposicion();
            } else if (opcion == indices.size() + 1) {

            } else {
                Exposicion exposicion = indices.get(opcion - 1);
                System.out.println("");

                System.out.println("Elija el coche a agregar a la exposición");
                HashMap<String, Coche> coches = concesionario.getCoches();
                ArrayList<Coche> indiceCoche = new ArrayList<>();
                for (Coche coche : coches.values()) {
                    indiceCoche.add(coche);
                }
                indicesCoches(indiceCoche);
                opcion = scanner.nextInt();
                if (opcion > (indiceCoche.size() + 1)) {
                    System.out.println("Opcion Incorrecta !!");
                    agregarCocheExposicion();
                } else if (opcion == indiceCoche.size() + 1) {

                } else {
                    Coche coche = indiceCoche.get(opcion - 1);
                    ArrayList<Coche> cochesExposicion = new ArrayList<>();
                    cochesExposicion.add(coche);
                    exposicion.setCochesExposicion(cochesExposicion);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            agregarCocheExposicion();
        }
    }

    public void removerCocheExposicion() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Elija una exposición para remover el coche");
            int opcion;
            HashMap<Integer, Exposicion> exposiciones = concesionario.getExposiciones();
            ArrayList<Exposicion> indices = new ArrayList<>();
            for (Exposicion exposicion : exposiciones.values()) {
                indices.add(exposicion);
            }
            OperacionesExposicion opExposiciones = new OperacionesExposicion(concesionario);
            opExposiciones.indicesExposiciones(indices);
            opConcesionario = new OperacionesConcesionario(concesionario);
            opcion = scanner.nextInt();
            if (opcion > (indices.size() + 1)) {
                System.out.println("Opcion Incorrecta !!");
                agregarCocheExposicion();
            } else if (opcion == indices.size() + 1) {

            } else {
                Exposicion exposicion = indices.get(opcion - 1);
                System.out.println("");
                System.out.println("Elija el coche a remover a la exposición");
                HashMap<String, Coche> coches = concesionario.getCoches();
                ArrayList<Coche> indiceCoche = new ArrayList<>();
                for (Coche coche : coches.values()) {
                    indiceCoche.add(coche);
                }
                indicesCoches(indiceCoche);
                opcion = scanner.nextInt();
                if (opcion > (indiceCoche.size() + 1)) {
                    System.out.println("Opcion Incorrecta !!");
                    agregarCocheExposicion();
                } else if (opcion == indiceCoche.size() + 1) {

                } else {
                    Coche coche = indiceCoche.get(opcion - 1);
                    ArrayList<Coche> cochesExposicion = new ArrayList<>();
                    cochesExposicion.remove(coche);
                    exposicion.setCochesExposicion(cochesExposicion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            removerCocheExposicion();
        }
    } //No lo vuelve a agregar al stock porque nunca salió

    public void indicesCoches(ArrayList<Coche> indices) {
        System.out.println("");
        System.out.println("*****LISTA COCHES*****");
        System.out.println("");
        for (int i = 0; i < indices.size(); i++) {
            System.out.println((i + 1) + " - " + indices.get(i).toString());
        }
        System.out.println(indices.size() + 1 + " - Salir");
        System.out.println("");
    }

    public void listarCoches() {
        System.out.println("");
        System.out.println("*****LISTA COCHES*****");
        System.out.println("");
        HashMap<String, Coche> coches = opConcesionario.listarCoches();
        if (coches.isEmpty()) {
            System.out.println("No hay coches registrados.");
        } else {
            for (Coche coche : coches.values()) {
                System.out.println(coche.toString());
                System.out.println("");
            }
        }
        System.out.println("");
    }

    public boolean verificarMatriculaRep(String matricula) {
        HashMap<String, Coche> coches = opConcesionario.listarCoches();
        for (Coche coche : coches.values()) {
            if (coche.getMatricula().equalsIgnoreCase(matricula)) {
                return true; // Matrícula repetida
            }
        }
        return false; // Matrícula no repetida
    }
}