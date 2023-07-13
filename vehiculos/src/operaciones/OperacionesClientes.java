package operaciones;

import Proyecto.ProyectoConcesionario;
import objetos.Concesionario;
import objetos.Cliente;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class OperacionesClientes {
    int opcion;
    private final Concesionario concesionario;

    public OperacionesClientes(Concesionario concesionario) {
        this.concesionario = concesionario;
    }
    public void menuClientes(){
        Scanner scan = new Scanner(System.in);
        while (opcion != 4) {
            System.out.println("1 - Dar de alta");
            System.out.println("2 - Dar de baja");
            System.out.println("3 - Modificar");
            System.out.println("4 - Salir");
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
                }
            } catch (Exception ex) {
                scan.nextLine();
            }
        }
    }

    private void agregar() {
        Cliente cliente = new Cliente();
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Introduzca el nombre del cliente: ");
            cliente.setNombre(scan.nextLine());
            System.out.println("Introduzca la direccion del cliente: ");
            cliente.setDireccion(scan.nextLine());
            System.out.println("Introduzca el DNI del cliente: ");
            cliente.setDni(scan.nextLine());
            System.out.println("Introduzca el telefono del cliente: ");
            cliente.setTelefono(scan.nextInt());
            concesionario.agregarCliente(cliente);
            System.out.println("Cliente agregado correctamente.");
        } catch (Exception ex){
            System.out.println(" ");
            System.out.println("Telefono incorrecto");
            scan.nextLine();
            agregar();
        }
        test();
    }
    private void eliminar(){
        Scanner scan = new Scanner(System.in);
        try {
            System.out.print("Introduzca el DNI del cliente: ");
            String dni = scan.nextLine();
            HashMap<String,Cliente> clientes = concesionario.listarClientes();
            if(clientes.containsKey(dni)){
                Cliente cliente = clientes.get(dni);

                if(cliente.getCochesReservados().isEmpty() && cliente.getCochesComprados().isEmpty()){
                    concesionario.eliminarCliente(cliente);
                    System.out.println("Cliente eliminado correctamente.");
                }else {
                    System.out.println("No se puede eliminar el cliente. Tiene coches reservados o comprados.");
                }
            }else{
                System.out.println("No se encontro ningun cliente con el DNI proporcionado.");
            }
        }catch (Exception ex) {
            System.out.println(" ");
            System.out.println("DNI incorrecto");
            eliminar();
        }
        test();

    }
    private void modificar(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduzca el DNI del cliente a modificar");
        String dni = scan.nextLine();

        HashMap<String,Cliente> clientes = concesionario.listarClientes();
        if(clientes.containsKey(dni)) {
            Cliente cliente = clientes.get(dni);

            System.out.println("Seleccione el dato a modificar.");
            System.out.println("1 - Nombre");
            System.out.println("2 - Direccion");
            System.out.println("3 - Telefono");

            int opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Introduzca el nuevo nombre: ");
                    String nuevoNombre = scan.nextLine();
                    cliente.setNombre(nuevoNombre);
                    System.out.println("Nombre modificado correctamente.");
                    break;
                case 2:
                    System.out.print("Introduzca la nueva direccion: ");
                    String nuevaDireccion = scan.nextLine();
                    cliente.setDireccion(nuevaDireccion);
                    System.out.println("Direccion modificada correctamente.");
                    break;
                case 3:
                    System.out.println("Introduzca el nuevo telefono: ");
                    int nuevoTelefono = scan.nextInt();
                    cliente.setTelefono(nuevoTelefono);
                    System.out.println("El telefono ha sido modificado correctamente.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        }else {
            System.out.println("No se encontro ningun cliente con el DNI proporcionado");
        }
        test();
    }
    private void test() {
        System.out.println("");
        System.out.println("");
        System.out.println("-----------------------------------------------");
        System.out.println("------------LISTA CLIENTES-------------------");
        System.out.println("-----------------------------------------------");
        HashMap<String, Cliente> clientes = concesionario.listarClientes();
        if (clientes.isEmpty()){
            System.out.println("No hay clientes registrados.");
        } else {
            for(Cliente cliente : clientes.values()) {
                //System.out.println(cliente.toString());
                System.out.println(cliente.getNombre() + " || " + cliente.getDireccion() + " || " + cliente.getDni() + " || " + cliente.getTelefono());
                System.out.println("----------------------------------------");
            }
        }
        System.out.println("");
    }
}
