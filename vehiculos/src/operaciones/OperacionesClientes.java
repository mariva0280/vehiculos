package operaciones;


import Validaciones.Validar;
import objetos.Concesionario;
import objetos.Cliente;


import java.util.*;

public class OperacionesClientes {
    int opcion;
    private final Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;

    public OperacionesClientes(Concesionario concesionario) {
        this.concesionario = concesionario;
    }
    public void menuClientes(){
        Scanner scan = new Scanner(System.in);
        System.out.println("");
        System.out.println("");
        while (opcion != 5) {
            System.out.println("*****MENU CLIENTES*****");
            System.out.println("1 - Dar de alta");
            System.out.println("2 - Dar de baja");
            System.out.println("3 - Modificar");
            System.out.println("4 - Listado Clientes");
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
                        listarClientes();
                        break;
                    case (5):
                        break;
                    default:
                        System.out.println("Opción inválida.");
                        break;
                }
            } catch (Exception ex) {
                scan.nextLine();
            }
        }
    }

    private void agregar() {
        opConcesionario = new OperacionesConcesionario(concesionario);
        Cliente cliente = new Cliente();
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Introduzca el nombre del cliente: ");
            String nombre = (scan.nextLine());
            if(!Validar.validateName(nombre)){
                throw new Exception("Nombre invcorrecto.");
            }
            cliente.setNombre(nombre);

            System.out.println("Introduzca la direccion del cliente: ");
            String direccion = (scan.nextLine());
            if(!Validar.validateDireccion(direccion)){
                throw new Exception("Dirección incorrecta.");
            }
            cliente.setDireccion(direccion);

            System.out.println("Introduzca el DNI del cliente: ");
            String dni =(scan.nextLine());
            if(!Validar.validateDni(dni)){
                throw new Exception("DNI incorrecto.");
            }
            if(verificarDniRep(dni)){
                throw new Exception("DNI duplicado.");
            }
            cliente.setDni(dni);

            System.out.println("Introduzca el telefono del cliente: ");
            String telefonoStr =scan.nextLine();
            if(!Validar.validateTelefono(telefonoStr)){
                throw new  Exception("Teléfono incorrecto.");
            }
            int telefono = Integer.parseInt(telefonoStr);
            if(verificarTlfRep(telefono)){
                throw new Exception("Teléfono duplicado.");
            }
            cliente.setTelefono(telefono);

            opConcesionario.agregarCliente(cliente);
            System.out.println("Cliente agregado correctamente.");
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            agregar();
        }
    }

    private void eliminar() {
        opConcesionario = new OperacionesConcesionario(concesionario);
        Scanner scan = new Scanner(System.in);
        int opcion;
        HashMap<String,Cliente> clientes = opConcesionario.listarClientes();
        // HACEMOS ARRAYLIST PARA TRABAJAR CON INDICES EN VEZ DE CON DNI
        ArrayList<Cliente> lista = new ArrayList<>();
        //COMPLETAMOS EL ARRAYLIST DE INDICES CON LOS VALORES CLIENTES DEL HASHMAP
        for (Cliente item : clientes.values()) {
            lista.add(item);
        }
        //PASAMOS ARRAYLIST AL METODO INDICECLIENTES Y REUTILIZAR CODIGO
        indicesClientes(lista);
        System.out.print("Elija el cliente a eliminar: ");
        try {
            opcion = scan.nextInt();
            if (opcion > (lista.size() + 1)) {   // Si la opcion es mayor que lista.size + 1 significa que nos salimos de las posibles opciones del menu
                System.out.println("Opcion incorrecta!!");
                eliminar();
            } else if (opcion == lista.size() + 1) {    // Si la opcion es una posicion mas que el tamaño de la lista significa que es la opcion "salir" y no hacemos nada

            } else {
                opConcesionario.eliminarCliente(lista.get(opcion - 1));
                Cliente cliente = lista.get(opcion - 1);
                if(cliente.getCochesReservados().isEmpty() && cliente.getCochesComprados().isEmpty()) {
                    //opConcesionario.eliminarCliente(cliente);
                    System.out.println("");
                    System.out.println("");
                    System.out.println("Cliente eliminado correctamente!!");
                    System.out.println("");
                }else {
                    System.out.println("");
                    System.out.println("No se puede eliminar el cliente. Tiene coches reservados o comprados");
                    System.out.println("");
                }
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
        HashMap<String,Cliente> clientes = opConcesionario.listarClientes();
        ArrayList<Cliente> lista = new ArrayList<>();
        for (Cliente item : clientes.values()) {
            lista.add(item);
        }
        indicesClientes(lista);
        System.out.print("Elija el cliente a modificar: ");

        try {
            opcion = scan.nextInt();
            if (opcion > (lista.size() + 1)) {
                System.out.println("Opcion incorrecta!!");
                modificar();
            } else if (opcion == lista.size() + 1) {

            } else {
                Cliente cliente = lista.get(opcion - 1);
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
                            if (!Validar.validateName(nuevoNombre)) {
                                throw new Exception("Nombre incorrecto.");
                            }
                            cliente.setNombre(nuevoNombre);
                            break;
                        case (2):
                            System.out.print("Nueva dirección: ");
                            String nuevaDireccion = scan.nextLine();
                            if (!Validar.validateDireccion(nuevaDireccion)) {
                                throw new Exception("Dirección incorrecta.");
                            }
                            cliente.setDireccion(nuevaDireccion);
                            break;
                        case (3):
                            System.out.print("Indique nuevo teléfono: ");
                            String nuevoTelefono = scan.nextLine();
                            if (!Validar.validateTelefono(nuevoTelefono)) {
                                throw new Exception("Teléfono incorrecto.");
                            }
                            int telefonoNuevo = Integer.parseInt(nuevoTelefono);
                            if(verificarTlfRep(telefonoNuevo)){
                                throw new Exception("Teléfono duplicado");
                            }
                            cliente.setTelefono(telefonoNuevo);
                            break;
                    }
                }
                opConcesionario.modificarCliente(cliente);
                System.out.println("Cliente modificado correctamente!!");
            }

        } catch (Exception ex) {
            System.out.println("Eroor: " + ex.getMessage());
            modificar();
        }
    }

    private void indicesClientes(ArrayList<Cliente> lista) {
        System.out.println("");
        System.out.println("");
        System.out.println("-----------------------------------------------");
        System.out.println("------------LISTA CLIENTES-------------------");
        System.out.println("-----------------------------------------------");
        //HashMap<String, Cliente> clientes = concesionario.listarClientes();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).toString());
            System.out.println("-----------------------------------------------");
        }
        System.out.println(lista.size() + 1 + " - Salir");   // Para que sea dinámico haremos que la opcion salir sea una posicion mas que el tamaño de la lista
        System.out.println("");
    }
    private void listarClientes() {
        System.out.println("");
        System.out.println("");
        System.out.println("-----------------------------------------------");
        System.out.println("------------LISTA CLIENTES-------------------");
        System.out.println("-----------------------------------------------");
        HashMap<String,Cliente> clientes = opConcesionario.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("No hay vendedores registrados.");
        } else {
            for (Cliente cliente : clientes.values()) {
                System.out.println(cliente.toString());
                System.out.println("----------------------------------------");
            }
        }
        System.out.println("");
    }
    private boolean verificarDniRep(String dni){
        HashMap<String,Cliente> clientes = opConcesionario.listarClientes();
        for(Cliente cliente : clientes.values()){
            if(cliente.getDni().equals(dni)){
                return true;
            }
        }
        return false;
    }
    private boolean verificarTlfRep(int telefono){
        HashMap<String,Cliente> clientes = opConcesionario.listarClientes();
        for(Cliente cliente : clientes.values()){
            if(cliente.getTelefono() == telefono){
                return true;
            }
        }
        return false;
    }
}
