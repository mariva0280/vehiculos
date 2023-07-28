/*
ESTA CLASE EN PRINCIPIO FUNCIONA Y NO HAY NADA QUE REVISAR LOS SOUTS ESTÁN BIEN, SOLO FALTA IMPLEMENTAR EL MENÚ DEFINITIVO AL QUE TIENEN ACCESO LOS VENDEDORES
 */
package Operaciones;

import Exception.EinvalidPropertyException;
import Objetos.Cliente;
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
    private Validar validar;
    public OperacionesVendedores(Concesionario concesionario) {

        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(concesionario);
        this.opVentas = new OperacionesVentas(concesionario);
        this.opCoches = new OperacionesCoches(concesionario);
        this.opExposicion = new OperacionesExposicion(concesionario);
        this.opReservas = new OperacionesReservas(concesionario);
        this.opClientes = new OperacionesClientes(concesionario);
        this.validar = new Validar(concesionario);
    }
    public void menuFinalVendedor() {
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("");
        while (opcion != 6) {
            System.out.println("*****MENU VENDEDORES*****");
            System.out.println("1 - Acceder a menú ventas.");
            System.out.println("2 - Acceder a menú reservas.");
            System.out.println("3 - Acceder a consultas exposiciones.");
            System.out.println("4 - Acceder a consultas coches.");
            System.out.println("5 - Acceder a consultas clientes.");
            System.out.println("6 - Salir.");
            System.out.println("");
            System.out.print("Elija una opción: ");
            try {
                opcion = scan.nextInt();
                switch (opcion) {
                    case (1):
                        opVentas.menuVentas();
                        break;
                    case (2):
                        opReservas.menuReservas();
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
            System.out.print("Elija una opción: ");
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
                }
            } catch (Exception ex) {
                scan.nextLine();
            }
        }
    }

    public void agregar() {
        try {
            opConcesionario = new OperacionesConcesionario(concesionario);
            Vendedor vendedor = new Vendedor();
            Scanner scan = new Scanner(System.in);

            String nombre, direccion, dni, telefonoStr;
            int telefono;

            System.out.print("Introduzca el nombre del vendedor: ");
            while (true) {
                nombre = scan.nextLine();
                if (!validar.validateName(nombre)) {
                    throw new EinvalidPropertyException("Nombre incorrecto.Ingrese el nombre de nuevo");
                }else break;
            }
            vendedor.setNombre(nombre);


            while(true) {
                System.out.print("Introduzca la dirección del vendedor: ");
                direccion = scan.nextLine();
                if (!validar.validateDireccion(direccion)) {
                    throw new EinvalidPropertyException("Dirección incorrecta.Ingrese la dirección de nuevo.");
                } else break;
            }
            vendedor.setDireccion(direccion);

            System.out.print("Introduzca el DNI del vendedor: ");
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
            vendedor.setDni(dni);

            System.out.print("Introduzca el teléfono del vendedor: ");
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
            vendedor.setTelefono(telefono);

            opConcesionario.agregarVendedor(vendedor);
            System.out.println("Vendedor agregado correctamente.");
        } catch (EinvalidPropertyException ex) {
            System.out.println("Error: " + ex.getMessage());
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
            //eliminar();
            menuVendedores();
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
                            if(!validar.validateName(nuevoNombre)){
                                throw new Exception("Nombre incorrecto.");
                            }
                            vendedor.setNombre(nuevoNombre);
                            break;
                        case (2):
                            System.out.print("Nueva dirección: ");
                            String nuevaDireccion = scan.nextLine();
                            if(!validar.validateDireccion(nuevaDireccion)){
                                throw new Exception("Dirección incorrecta.");
                            }
                            vendedor.setDireccion(nuevaDireccion);
                            break;
                        case (3):
                            System.out.print("Indique nuevo teléfono: ");
                            String nuevoTelefono = scan.nextLine();
                            if(!validar.validateTelefono(nuevoTelefono)){
                                throw new Exception("Teléfono incorrecto.");
                            }
                            int telefonoNuevo = Integer.parseInt(nuevoTelefono);
                            if(!validar.verificarTlfRep((telefonoNuevo))){
                                throw new EinvalidPropertyException("Teléfono duplicado.");
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
           // modificar();
            menuVendedores();
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
}
