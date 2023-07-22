/*
REVISAR EL METODO ELIMINAR CLIENTE PORQUE NO ME FUNCIONA BIEN CUANDO HAY CLIENTES CON COCHES RESERVADOS O COMPRADOS, PORQUE ME SALTA EL SOUT
OPCION INCORRECTA PERO AL FINAL SI QUE ELIMINA AL CLIENTE DE LA LISTA, LO DEMÁS FUNCIONA, FALTA IMPLEMENTAR EL MENÚ DEFINITIVO QUE ES AL QUE TIENEN
ACCESO LOS CLIENTES EN EL QUE SOLO PUEDEN CONSULTAR LOS COCHES EN STOCK
 */
package Operaciones;


import Validaciones.Validar;
import Objetos.Concesionario;
import Objetos.Cliente;
import Exception.EinvalidPropertyException;


import java.util.*;

public class OperacionesClientes {

    private Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;

    public OperacionesClientes(Concesionario concesionario) {

        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(concesionario);
    }

    public void menuClientes() {
        Scanner scan = new Scanner(System.in);
        try {
            int opcion = 0;
            System.out.println("");
            while (opcion != 5) {
                System.out.println("*****MENU CLIENTES*****");
                System.out.println("1 - Dar de alta.");
                System.out.println("2 - Dar de baja.");
                System.out.println("3 - Modificar.");
                System.out.println("4 - Listado Clientes.");
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
                        listarClientes();
                        break;
                    case (5):
                        break;
                    default:
                        System.out.println("Opción inválida.");
                        break;
                }
            }
        } catch (Exception ex) {
            scan.nextLine();
        }
    }

    public void agregar() {
        try {
            opConcesionario = new OperacionesConcesionario(concesionario);
            Cliente cliente = new Cliente();
            Scanner scan = new Scanner(System.in);

            System.out.print("Introduzca el nombre del cliente: ");
            String nombre = scan.nextLine();
            if (!Validar.validateName(nombre)) {
                throw new EinvalidPropertyException("Nombre incorrecto.");
            }
            cliente.setNombre(nombre);

            System.out.print("Introduzca la dirección del cliente: ");
            String direccion = scan.nextLine();
            if (!Validar.validateDireccion(direccion)) {
                throw new EinvalidPropertyException("Dirección incorrecta.");
            }
            cliente.setDireccion(direccion);

            System.out.print("Introduzca el DNI del cliente: ");
            String dni = (scan.nextLine());
            if (!Validar.validateDni(dni)) {
                throw new EinvalidPropertyException("DNI incorrecto.");
            }
            if (verificarDniRep(dni)) {
                throw new EinvalidPropertyException("DNI duplicado.");
            }
            cliente.setDni(dni);

            System.out.print("Introduzca el teléfono del cliente: ");
            String telefonoStr = scan.nextLine();
            if (!Validar.validateTelefono(telefonoStr)) {
                throw new EinvalidPropertyException("Teléfono incorrecto.");
            }
            int telefono = Integer.parseInt(telefonoStr);
            if (verificarTlfRep(telefono)) {
                throw new EinvalidPropertyException("Teléfono duplicado.");
            }
            cliente.setTelefono(telefono);

            opConcesionario.agregarCliente(cliente);
            System.out.println("Cliente agregado correctamente.");
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
        HashMap<String, Cliente> clientes = opConcesionario.listarClientes();
        // HACEMOS ARRAYLIST PARA TRABAJAR CON INDICES EN VEZ DE CON DNI
        ArrayList<Cliente> lista = new ArrayList<>();
        //COMPLETAMOS EL ARRAYLIST DE INDICES CON LOS VALORES CLIENTES DEL HASHMAP
        for (Cliente item : clientes.values()) {
            lista.add(item);
        }
        //PASAMOS ARRAYLIST AL METODO INDICECLIENTES Y REUTILIZAR CODIGO
        indicesClientes(lista);
        System.out.print("Elija el número del cliente a eliminar: ");

            opcion = scan.nextInt();
            if (opcion > (lista.size() + 1)) {   // Si la opcion es mayor que lista.size + 1 significa que nos salimos de las posibles opciones del menu
                System.out.println("Opcion incorrecta.");
                eliminar();
            } else if (opcion == lista.size() + 1) {    // Si la opcion es una posicion mas que el tamaño de la lista significa que es la opcion "salir" y no hacemos nada

            } else {
                Cliente cliente = lista.get(opcion - 1);
                if (cliente.getCochesReservados().isEmpty() && cliente.getCochesComprados().isEmpty()) {
                    System.out.println("Cliente eliminado correctamente.");
                    opConcesionario.eliminarCliente(lista.get(opcion - 1));
                } else {
                    System.out.println("No se puede eliminar el cliente. Tiene coches reservados o comprados.");
                }
                eliminar();
            }

        } catch (Exception ex) {
            System.out.println("Opcion incorrecta.");
            eliminar();
        }
    }

    public void modificar() {
        try {
            opConcesionario = new OperacionesConcesionario(concesionario);
            Scanner scan = new Scanner(System.in);
            int opcion;
            HashMap<String, Cliente> clientes = opConcesionario.listarClientes();
            ArrayList<Cliente> lista = new ArrayList<>();
            for (Cliente item : clientes.values()) {
                lista.add(item);
            }
            indicesClientes(lista);
            System.out.print("Elija el número del cliente a modificar: ");

            opcion = scan.nextInt();
            if (opcion > (lista.size() + 1)) {
                System.out.println("Opcion incorrecta.");
                modificar();
            } else if (opcion == lista.size() + 1) {

            } else {
                Cliente cliente = lista.get(opcion - 1);
                System.out.println("");
                while (opcion != 4) {
                    System.out.println("1 - Modificar nombre.");
                    System.out.println("2 - Modificar dirección.");
                    System.out.println("3 - Modificar teléfono.");
                    System.out.println("4 - Guardar cambios.");
                    System.out.print("Elija una opcion: ");
                    opcion = scan.nextInt();

                    scan = new Scanner(System.in);
                    switch (opcion) {
                        case (1):
                            System.out.print("Nuevo nombre: ");
                            String nuevoNombre = scan.nextLine();
                            if (!Validar.validateName(nuevoNombre)) {
                                throw new EinvalidPropertyException("Nombre incorrecto.");
                            }
                            cliente.setNombre(nuevoNombre);
                            break;
                        case (2):
                            System.out.print("Nueva dirección: ");
                            String nuevaDireccion = scan.nextLine();
                            if (!Validar.validateDireccion(nuevaDireccion)) {
                                throw new EinvalidPropertyException("Dirección incorrecta.");
                            }
                            cliente.setDireccion(nuevaDireccion);
                            break;
                        case (3):
                            System.out.print("Indique nuevo teléfono: ");
                            String nuevoTelefono = scan.nextLine();
                            if (!Validar.validateTelefono(nuevoTelefono)) {
                                throw new EinvalidPropertyException("Teléfono incorrecto.");
                            }
                            int telefonoNuevo = Integer.parseInt(nuevoTelefono);
                            if (verificarTlfRep(telefonoNuevo)) {
                                throw new EinvalidPropertyException("Teléfono duplicado.");
                            }
                            cliente.setTelefono(telefonoNuevo);
                            break;
                    }
                }
                opConcesionario.modificarCliente(cliente);
                System.out.println("Cliente modificado correctamente.");
            }

        } catch (EinvalidPropertyException ex) {
            System.out.println("Error: " + ex.getMessage());
            modificar();
        }
    }

    public void indicesClientes(ArrayList<Cliente> lista) {
        System.out.println("");
        System.out.println("*****LISTA CLIENTES*****");
        System.out.println("");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).toString());
        }
        System.out.println(lista.size() + 1 + " - Salir");   // Para que sea dinámico haremos que la opcion salir sea una posicion mas que el tamaño de la lista
        System.out.println("");
    }

    public void listarClientes() {
        System.out.println("");
        System.out.println("*****LISTA CLIENTES*****");
        System.out.println("");
        HashMap<String, Cliente> clientes = opConcesionario.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            for (Cliente cliente : clientes.values()) {
                System.out.println(cliente.toString());
                System.out.println("");
            }
        }
        System.out.println("");
    }

    public boolean verificarDniRep(String dni) {
        HashMap<String, Cliente> clientes = opConcesionario.listarClientes();
        for (Cliente cliente : clientes.values()) {
            if (cliente.getDni().equals(dni)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarTlfRep(int telefono) {
        HashMap<String, Cliente> clientes = opConcesionario.listarClientes();
        for (Cliente cliente : clientes.values()) {
            if (cliente.getTelefono() == telefono) {
                return true;
            }
        }
        return false;
    }
}