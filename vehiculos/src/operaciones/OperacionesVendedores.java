package operaciones;

import Validaciones.ValidarClientesVendedores;
import objetos.Concesionario;
import objetos.Vendedor;

import java.util.*;

public class OperacionesVendedores {

    int opcion;
    private final Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;
    public OperacionesVendedores(Concesionario concesionario) {

        this.concesionario = concesionario;
    }
    public void menuVendedores(){
        Scanner scan = new Scanner(System.in);
        System.out.println("");
        System.out.println("");
        while (opcion != 5) {
            System.out.println("*****MENU VENDEDORES*****");
            System.out.println("1 - Dar de alta");
            System.out.println("2 - Dar de baja");
            System.out.println("3 - Modificar");
            System.out.println("4 - Listado Vendedores");
            System.out.println("5 - Salir");
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

    private void agregar() {
        opConcesionario = new OperacionesConcesionario(concesionario);
        Vendedor vendedor = new Vendedor();
        Scanner scan = new Scanner(System.in);
        try {
            System.out.print("Introduzca el nombre del vendedor: ");
            String nombre = scan.nextLine();
            if(!ValidarClientesVendedores.validateName(nombre)){
                throw new Exception("Nombre incorrecto.");
            }
            vendedor.setNombre(nombre);

            System.out.println("Introduzca la direccion del vendedor: ");
            String direccion = scan.nextLine();
            if(!ValidarClientesVendedores.validateDireccion(direccion)){
                throw new Exception("Dirección incorrecta.");
            }
            vendedor.setDireccion(direccion);

            System.out.println("Introduzca el DNI del vendedor: ");
            String dni = scan.nextLine();
            if(!ValidarClientesVendedores.validateDni(dni)){
                throw new Exception("DNI incorrecto.");
            }
            if(verificarDniRep(dni)){
                throw new Exception("DNI duplicado.");
            }
            vendedor.setDni(dni);

            System.out.println("Introduzca el telefono del vendedor: ");
            String telefonoStr = scan.nextLine();
            if(!ValidarClientesVendedores.validateTelefono(telefonoStr)){
                throw new Exception("Teléfono incorrecto.");
            }
            int telefono = Integer.parseInt(telefonoStr);
            if(verificarTlfRep(telefono)){
                throw new Exception("Teléfono duplicado");
            }
            vendedor.setTelefono(telefono);
            opConcesionario.agregarVendedor(vendedor);
            System.out.println("Vendedor añadido correctamente.");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            agregar();
        }
        System.out.println("");
        System.out.println("");
    }

    private void eliminar() {
        opConcesionario = new OperacionesConcesionario(concesionario);
        Scanner scan = new java.util.Scanner(System.in);
        int opcion;
        HashMap<String, Vendedor> vendedores = opConcesionario.listarVendedores();
        // Creamos un arrayList llamado lista para poder trabajar con índices
        ArrayList<Vendedor> lista = new ArrayList<>();
        // Aquí llenamos la lista con los valores (vendedores) del hashmap
        for (Vendedor item : vendedores.values()) {
            lista.add(item);
        }
        // Pasamos la lista al método indicesVendedores y así reutilizar código
        indicesVendedores(lista);
        System.out.print("Elija el vendedor a eliminar: ");
        try {
            opcion = scan.nextInt();
            if (opcion > (lista.size() + 1)) {   // Si la opcion es mayor que lista.size + 1 significa que nos salimos de las posibles opciones del menu
                System.out.println("Opcion incorrecta!!");
                eliminar();
            } else if (opcion == lista.size() + 1) {    // Si la opcion es una posicion mas que el tamaño de la lista significa que es la opcion "salir" y no hacemos nada

            } else {
                opConcesionario.eliminarVendedor(lista.get(opcion - 1));// Pasamos al metodo eliminarVendedor del concesionario, el vendedor elegido en la lista
                System.out.println("");
                System.out.println("");
                System.out.println("Vendedor eliminado correctamente!!");
                System.out.println("");
                eliminar();
            }

        } catch (Exception ex) {
            System.out.println("Opcion incorrecta!!");
            eliminar();
        }
    }

    private void modificar() {
        opConcesionario = new OperacionesConcesionario(concesionario);
        Scanner scan = new Scanner(System.in);
        int opcion;
        HashMap<String, Vendedor> vendedores = opConcesionario.listarVendedores();
        ArrayList<Vendedor> lista = new ArrayList<>();
        for (Vendedor item : vendedores.values()) {
            lista.add(item);
        }
        indicesVendedores(lista);
        System.out.print("Elija el vendedor a modificar: ");

        try {
            opcion = scan.nextInt();
            if (opcion > (lista.size() + 1)) {
                System.out.println("Opcion incorrecta!!");
                modificar();
            } else if (opcion == lista.size() + 1) {

            } else {
                Vendedor vendedor = lista.get(opcion - 1);
                System.out.println("");
                while (opcion != 4) {
                    System.out.println("");
                    System.out.println("");
                    System.out.println("1 - Modificar nombre");
                    System.out.println("2 - Modificar dirección");
                    System.out.println("3 - Modificar teléfono");
                    System.out.println("4 - Guardar cambios");
                    System.out.print("Elija una opcion: ");
                    opcion = scan.nextInt();

                    scan = new Scanner(System.in);
                    switch (opcion) {
                        case (1):
                            System.out.print("Nuevo nombre: ");
                            String nuevoNombre = scan.nextLine();
                            if(!ValidarClientesVendedores.validateName(nuevoNombre)){
                                throw new Exception("Nombre incorrecto.");
                            }
                            vendedor.setNombre(nuevoNombre);
                            break;
                        case (2):
                            System.out.print("Nueva dirección: ");
                            String nuevaDireccion = scan.nextLine();
                            if(!ValidarClientesVendedores.validateDireccion(nuevaDireccion)){
                                throw new Exception("Dirección incorrecta.");
                            }
                            vendedor.setDireccion(nuevaDireccion);
                            break;
                        case (3):
                            System.out.print("Indique nuevo teléfono: ");
                            String nuevoTelefono = scan.nextLine();
                            if(!ValidarClientesVendedores.validateTelefono(nuevoTelefono)){
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
                System.out.println("Vendedor modificado correctamente!!");
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            modificar();
        }
    }
    private void indicesVendedores(ArrayList<Vendedor> lista) {
        System.out.println("");
        System.out.println("");
        System.out.println("-----------------------------------------------");
        System.out.println("------------LISTA VENDEDORES-------------------");
        System.out.println("-----------------------------------------------");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).toString());
            System.out.println("-----------------------------------------------");
        }
        System.out.println(lista.size() + 1 + " - Salir");   // Para que sea dinámico haremos que la opcion salir sea una posicion mas que el tamaño de la lista
        System.out.println("");
    }
    private void listarVendedores() {
        System.out.println("");
        System.out.println("");
        System.out.println("-----------------------------------------------");
        System.out.println("------------LISTA VENDEDORES-------------------");
        System.out.println("-----------------------------------------------");
        HashMap<String, Vendedor> vendedores = opConcesionario.listarVendedores();

        if (vendedores.isEmpty()) {
            System.out.println("No hay vendedores registrados.");
        } else {
            for (Vendedor vendedor : vendedores.values()) {
                System.out.println(vendedor.toString());
                System.out.println("----------------------------------------");
            }
        }
        System.out.println("");
    }
    private boolean verificarDniRep(String dni){
        HashMap<String,Vendedor> vendedores = opConcesionario.listarVendedores();
        for(Vendedor vendedor : vendedores.values()){
            if(vendedor.getDni().equals(dni)){
                return true;
            }
        }
        return false;
    }
    private boolean verificarTlfRep(int telefono){
        HashMap<String,Vendedor> vendedores = opConcesionario.listarVendedores();
        for(Vendedor vendedor : vendedores.values()){
            if(vendedor.getTelefono() == telefono){
                return true;
            }
        }
        return false;
    }
}
