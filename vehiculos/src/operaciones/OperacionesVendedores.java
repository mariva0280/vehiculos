package operaciones;

import objetos.Concesionario;
import objetos.Vendedor;

import java.util.*;

public class OperacionesVendedores {

    int opcion;
    private final Concesionario concesionario;
    public OperacionesVendedores(Concesionario concesionario) {

        this.concesionario = concesionario;
    }
    public void menuVendedores(){
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
        Vendedor vendedor = new Vendedor();
        Scanner scan = new Scanner(System.in);
        try {
            System.out.print("Introduzca el nombre del vendedor: ");
            vendedor.setNombre(scan.nextLine());
            System.out.println("Introduzca la direccion del vendedor: ");
            vendedor.setDireccion(scan.nextLine());
            System.out.println("Introduzca el DNI del vendedor: ");
            vendedor.setDni(scan.nextLine());
            System.out.println("Introduzca el telefono del vendedor: ");
            vendedor.setTelefono(scan.nextInt());
            concesionario.agregarVendedor(vendedor);
            System.out.println("Vendedor añadido correctamente.");
        } catch (Exception ex) {
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
            System.out.print("Introduzca el DNI del vendedor: ");
            String dni = scan.nextLine();
            concesionario.eliminarVendedor(dni);
            System.out.println("Vendedor eliminado correctamente.");
        }catch (Exception ex) {
            System.out.println(" ");
            System.out.println("DNI incorrecto");
            eliminar();
        }
        test();

    }
    private void modificar(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduzca el DNI del vendedor a modificar");
        String dni = scan.nextLine();

        HashMap<String,Vendedor> vendedores = concesionario.listarVendedores();
        if(vendedores.containsKey(dni)) {
            Vendedor vendedor = vendedores.get(dni);

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
                    vendedor.setNombre(nuevoNombre);
                    System.out.println("Nombre modificado correctamente.");
                    break;
                case 2:
                    System.out.print("Introduzca la nueva direccion: ");
                    String nuevaDireccion = scan.nextLine();
                    vendedor.setDireccion(nuevaDireccion);
                    System.out.println("Direccion modificada correctamente.");
                    break;
                case 3:
                    System.out.println("Introduzca el nuevo telefono: ");
                    int nuevoTelefono = scan.nextInt();
                    vendedor.setTelefono(nuevoTelefono);
                    System.out.println("El telefono ha sido modificado correctamente.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        }else {
            System.out.println("No se encontro ningun vendedor con el DNI proporcionado");
        }
        test();
    }

    private void test() {
        System.out.println("");
        System.out.println("");
        System.out.println("-----------------------------------------------");
        System.out.println("------------LISTA VENDEDORES-------------------");
        System.out.println("-----------------------------------------------");
        HashMap<String,Vendedor> vendedores = concesionario.listarVendedores();
        if (vendedores.isEmpty()){
            System.out.println("No hay vendedores registrados.");
        } else {
            for(Vendedor vendedor : vendedores.values()) {
                System.out.println(vendedor.toString());
                //System.out.println(vendedor.getNombre() + " || " + vendedor.getDireccion() + " || " + vendedor.getDni() + " || " + vendedor.getTelefono());
                System.out.println("----------------------------------------");
            }
        }
        System.out.println("");
    }
}
