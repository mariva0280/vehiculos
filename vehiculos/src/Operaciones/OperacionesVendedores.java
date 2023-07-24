/*
ESTA CLASE EN PRINCIPIO FUNCIONA Y NO HAY NADA QUE REVISAR LOS SOUTS ESTÁN BIEN, SOLO FALTA IMPLEMENTAR EL MENÚ DEFINITIVO AL QUE TIENEN ACCESO LOS VENDEDORES
 */
package Operaciones;

import Exception.EinvalidPropertyException;
import Validaciones.Validar;
import Objetos.Concesionario;
import Objetos.Vendedor;

import java.util.*;

public class OperacionesVendedores {
    private Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;
    private OperacionesVentas opVentas;
    private OperacionesReservas opReservas;
    private OperacionesClientes opClientes;
    private OperacionesCoches opCoches;
    private OperacionesExposicion opExposicion;
    public OperacionesVendedores(Concesionario concesionario) {

        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(concesionario);
        this.opVentas = new OperacionesVentas(concesionario);
        this.opCoches = new OperacionesCoches(concesionario);
        this.opReservas = new OperacionesReservas(concesionario);
        this.opClientes = new OperacionesClientes(concesionario);
    }
    public void menuFinalVendedor() {
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("");
        while (opcion != 6) {
            System.out.println("*****MENU VENDEDORES*****");
            System.out.println("1 - Consultar ventas");
            System.out.println("2 - Consultar reservas.");
            System.out.println("3 - Consultar exposiciones.");
            System.out.println("4 - Consultar coches.");
            System.out.println("5 - Consultar clientes.");
            System.out.println("6 - Salir.");
            System.out.println("");
            System.out.print("Elija una opcion: ");
            try {
                opcion = scan.nextInt();
                switch (opcion) {
                    case (1):
                        opVentas.listarCochesPorVendedor();
                        break;
                    case (2):
                        opReservas.listarReservas();
                        break;
                    case (3):
                        opExposicion.listarExposiciones();
                        break;
                    case (4):
                        opCoches.listarCoches();
                        break;
                    case (5):
                        opClientes.listarClientes();
                        break;
                    case (6):
                        break;
                }
            } catch (Exception ex) {
                scan.nextLine();
            }
        }
    }

    public void menuVendedores(){
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("");
        while (opcion != 5) {
            System.out.println("*****MENU VENDEDORES*****");
            System.out.println("1 - Dar de alta.");
            System.out.println("2 - Dar de baja.");
            System.out.println("3 - Modificar.");
            System.out.println("4 - Listado Vendedores.");
            System.out.println("5 - Salir.");
            System.out.println("");
            System.out.print("Elija una opcion: ");
            try {
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
                        listarVendedores();
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

    public void agregar() {
        opConcesionario = new OperacionesConcesionario(concesionario);
        Vendedor vendedor = new Vendedor();
        Scanner scan = new Scanner(System.in);

        try {
            System.out.print("Introduzca el nombre del vendedor: ");
            String nombre = scan.nextLine();
            if (!Validar.validateName(nombre)) {
                throw new EinvalidPropertyException("Nombre incorrecto.");
            }
            vendedor.setNombre(nombre);

            System.out.print("Introduzca la dirección del vendedor: ");
            String direccion = scan.nextLine();
            if (!Validar.validateDireccion(direccion)) {
                throw new EinvalidPropertyException("Dirección incorrecta.");
            }
            vendedor.setDireccion(direccion);

            System.out.print("Introduzca el DNI del vendedor: ");
            String dni = scan.nextLine();
            if (!Validar.validateDni(dni)) {
                throw new EinvalidPropertyException("DNI incorrecto.");
            }
            if (verificarDniRep(dni)) {
                throw new EinvalidPropertyException("DNI duplicado.");
            }
            vendedor.setDni(dni);

            System.out.print("Introduzca el teléfono del vendedor: ");
            String telefonoStr = scan.nextLine();
            if (!Validar.validateTelefono(telefonoStr)) {
                throw new EinvalidPropertyException("Teléfono incorrecto.");
            }
            int telefono = Integer.parseInt(telefonoStr);
            if (verificarTlfRep(telefono)) {
                throw new EinvalidPropertyException("Teléfono duplicado");
            }
            vendedor.setTelefono(telefono);

            opConcesionario.agregarVendedor(vendedor);
            System.out.println("Vendedor añadido correctamente.");

            } catch (EinvalidPropertyException ex) {
                System.out.println("Error: " + ex.getMessage());
                agregar();
            } catch (Exception ex) {
                System.out.println("Error desconocido: " + ex.getMessage());
                agregar();
            }

    }

    public void eliminar() {
        opConcesionario = new OperacionesConcesionario(concesionario);
        Scanner scan = new java.util.Scanner(System.in);
        int opcion;
        HashMap<String, Vendedor> vendedores = opConcesionario.listarVendedores();
        ArrayList<Vendedor> lista = new ArrayList<>();
        for (Vendedor item : vendedores.values()) {
            lista.add(item);
        }
        indicesVendedores(lista);
        System.out.print("Elija el número del vendedor a eliminar: ");
        try {
            opcion = scan.nextInt();
            if (opcion > (lista.size() + 1)) {
                System.out.println("Opción incorrecta.");
                eliminar();
            } else if (opcion == lista.size() + 1) {

            } else {
                opConcesionario.eliminarVendedor(lista.get(opcion - 1));
                System.out.println("Vendedor eliminado correctamente.");
                eliminar();
            }

        } catch (Exception ex) {
            System.out.println("Opción incorrecta.");
            eliminar();
        }
    }

    public void modificar() {
        opConcesionario = new OperacionesConcesionario(concesionario);
        Scanner scan = new Scanner(System.in);
        int opcion;
        HashMap<String, Vendedor> vendedores = opConcesionario.listarVendedores();
        ArrayList<Vendedor> lista = new ArrayList<>();
        for (Vendedor item : vendedores.values()) {
            lista.add(item);
        }
        indicesVendedores(lista);
        System.out.print("Elija el número del vendedor a modificar: ");

        try {
            opcion = scan.nextInt();
            if (opcion > (lista.size() + 1)) {
                System.out.println("Opción incorrecta.");
                modificar();
            } else if (opcion == lista.size() + 1) {

            } else {
                Vendedor vendedor = lista.get(opcion - 1);
                System.out.println("");
                while (opcion != 4) {
                    System.out.println("1 - Modificar nombre.");
                    System.out.println("2 - Modificar dirección.");
                    System.out.println("3 - Modificar teléfono.");
                    System.out.println("4 - Guardar cambios.");
                    System.out.print("Elija una opción: ");
                    opcion = scan.nextInt();

                    scan = new Scanner(System.in);
                    switch (opcion) {
                        case (1):
                            System.out.print("Nuevo nombre: ");
                            String nuevoNombre = scan.nextLine();
                            if(!Validar.validateName(nuevoNombre)){
                                throw new Exception("Nombre incorrecto.");
                            }
                            vendedor.setNombre(nuevoNombre);
                            break;
                        case (2):
                            System.out.print("Nueva dirección: ");
                            String nuevaDireccion = scan.nextLine();
                            if(!Validar.validateDireccion(nuevaDireccion)){
                                throw new Exception("Dirección incorrecta.");
                            }
                            vendedor.setDireccion(nuevaDireccion);
                            break;
                        case (3):
                            System.out.print("Indique nuevo teléfono: ");
                            String nuevoTelefono = scan.nextLine();
                            if(!Validar.validateTelefono(nuevoTelefono)){
                                throw new Exception("Teléfono incorrecto.");
                            }
                            int telefonoNuevo = Integer.parseInt(nuevoTelefono);
                            if(verificarTlfRep(telefonoNuevo)){
                                throw new Exception("Teléfono duplicado.");
                            }
                            vendedor.setTelefono(telefonoNuevo);
                            break;
                    }
                }
                opConcesionario.modificarVendedor(vendedor);
                System.out.println("Vendedor modificado correctamente.");
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            modificar();
        }
    }
    public void indicesVendedores(ArrayList<Vendedor> lista) {
        System.out.println("");
        System.out.println("*****LISTA VENDEDORES*****");
        System.out.println("");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).toString());
        }
        System.out.println(lista.size() + 1 + " - Salir.");   // Para que sea dinámico haremos que la opcion salir sea una posicion mas que el tamaño de la lista
        System.out.println("");
    }
    public void listarVendedores() {
        System.out.println("");
        System.out.println("*****LISTA VENDEDORES*****");
        System.out.println("");
        HashMap<String, Vendedor> vendedores = opConcesionario.listarVendedores();

        if (vendedores.isEmpty()) {
            System.out.println("No hay vendedores registrados.");
        } else {
            for (Vendedor vendedor : vendedores.values()) {
                System.out.println(vendedor.toString());
                System.out.println("");
            }
        }
        System.out.println("");
    }
    public boolean verificarDniRep(String dni){
        HashMap<String,Vendedor> vendedores = opConcesionario.listarVendedores();
        for(Vendedor vendedor : vendedores.values()){
            if(vendedor.getDni().equals(dni)){
                return true;
            }
        }
        return false;
    }
    public boolean verificarTlfRep(int telefono){
        HashMap<String,Vendedor> vendedores = opConcesionario.listarVendedores();
        for(Vendedor vendedor : vendedores.values()){
            if(vendedor.getTelefono() == telefono){
                return true;
            }
        }
        return false;
    }
}
