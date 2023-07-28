

package Operaciones;

import Objetos.*;
import Exception.EinvalidPropertyException;

import java.lang.reflect.Array;
import java.util.*;

public class OperacionesVentas {
    private Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;

    public OperacionesVentas(Concesionario concesionario) {

        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(concesionario);
    }

    public void menuVentas() {
        Scanner scan = new Scanner(System.in);
        try {
            int opcion = 0;
            System.out.println("");
            while (opcion != 4) {
                System.out.println("*****MENU VENTAS*****");
                System.out.println("1 - Ventas.");
                System.out.println("2 - Lista clientes con coches vendidos.");
                System.out.println("3 - Lista coches vendidos por vendedor.");
                System.out.println("4 - Salir.");
                System.out.print("Elija una opción: ");

                opcion = scan.nextInt();
                switch (opcion) {
                    case (1):
                        vender();
                        break;
                    case (2):
                        listarClientePorModelo();
                        break;
                    case (3):
                        listarCochesPorVendedor();
                        break;
                    case (4):
                        break;
                }
            }
        } catch (Exception ex) {
            scan.nextLine();
        }
    }

    public void vender() {
        try {

            HashMap<String, Cliente> clientes = opConcesionario.listarClientes();
            HashMap<String, Vendedor> vendedores = opConcesionario.listarVendedores();
            HashMap<String, Coche> coches = opConcesionario.listarCoches();

            if (clientes.isEmpty() || clientes == null) {
                System.out.println("Debes  tener al menos un cliente dado de alta.");
                return;
            }
            if(vendedores.isEmpty() || vendedores == null) {
                System.out.println("Debes tener al menos un vendedor dado de alta.");
                return;
            }
            if(coches.isEmpty() || coches == null) {
                System.out.println("Debes tener al menos un coche dado de alta.");
                return;
            }

            Venta venta = new Venta();
            venta.setCliente(clientes.get(verClientes(clientes)));
            venta.setVendedor(vendedores.get(verVendedores(vendedores)));
            String matriculaCoche = verCoches(coches);
            if(matriculaCoche == null) {
                System.out.println("Venta cancelada, no ha seleccionado ningún coche.");
                return;
            }
            //Coche coche = coches.get(verCoches(coches));
            Coche coche = coches.get(matriculaCoche);
            venta.setCoche(coche);
            opConcesionario.agregarVenta(venta);
            opConcesionario.eliminarCoche(coche);
        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public String verClientes(HashMap<String, Cliente> clientes) {
        ArrayList<Cliente> lista = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        Cliente cliente = new Cliente();

        for (Cliente item : clientes.values()) {
            lista.add(item);
        }
        System.out.println("*****LISTA CLIENTES*****");
        System.out.println("");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).toString());
        }
        System.out.println(lista.size() + 1 + " - Salir.");
        System.out.println("");
        System.out.print("Elija de la lista el cliente que desea comprar el coche o pulse " + (lista.size() + 1) + " para salir: ");
        try {
            int opcion = scan.nextInt();
            if (opcion == lista.size() + 1) {
                return null;
            } else {
                cliente = lista.get(opcion - 1);
            }
            System.out.println("Cliente elegido correctamente.");

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return cliente.getDni();
    }

    public String verVendedores(HashMap<String, Vendedor> vendedores) {
        ArrayList<Vendedor> lista = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        Vendedor vendedor = new Vendedor();

        for (Vendedor item : vendedores.values()) {
            lista.add(item);
        }
        System.out.println("*****LISTA VENDEDORES*****");
        System.out.println("");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).toString());
        }
        System.out.println(lista.size() + 1 + " - Salir");
        System.out.println("");
        System.out.print("Elija el vendedor de la lista que va a realizar la venta o pulse " + (lista.size() + 1) + " para salir: ");
        try {
            int opcion = scan.nextInt();
            if (opcion == lista.size() + 1) {
                return null;
            } else {
                vendedor = lista.get(opcion - 1);
            }
            System.out.println("Vendedor elegido correctamente.");


        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return vendedor.getDni();
    }

    public String verCoches(HashMap<String, Coche> coches) {
        ArrayList<Coche> lista = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        Coche coche = new Coche();

        for (Coche item : coches.values()) {
            lista.add(item);
        }
        System.out.println("*****LISTA COCHES*****");
        System.out.println("");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).toString());
        }
        System.out.println(lista.size() + 1 + " - Salir.");
        System.out.println("");
        System.out.print("Elija el coche deseado por el cliente o pulse " + (lista.size() + 1) + " para salir: ");
        try {
            int opcion = scan.nextInt();
            if (opcion == lista.size() + 1) {
                return null;
            } else {
                coche = lista.get(opcion - 1);
            }
            System.out.println("Coche añadido al cliente correctamente.");


        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return coche.getMatricula();
    }
    /*
    EN ESTOS DOS METODOS PASABA LO MISMO ERROR POR ESTAR FUERA DEL ARRAY MISMO ERROR QUE EN RESERVA, SOLUCIÓN IGUAL EN EL TRY DE AMBOS METODOS ESTA
    EL CAMBIO.
     */
    public void listarClientePorModelo() {
        HashMap<String, Venta> ventas = opConcesionario.listarVentas();
        ArrayList<Venta> lista = new ArrayList<>();
        for (Venta item : ventas.values()) {
            lista.add(item);
        }
        if(lista.isEmpty()){
            System.out.println("No hay coches vendidos.");
            return;
        }
        System.out.println("*****LISTA DE COCHES VENDIDOS*****");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).getCoche().toString());
        }
        System.out.println("");
        System.out.print("Escoja el coche de la lista o pulse " + (lista.size() + 1) + " para salir: ");

        Scanner scan = new Scanner(System.in);

        try {
            int opcion = scan.nextInt();
            if (opcion == lista.size() + 1) {
                System.out.println("Saliendo de la lista de coches vendidos por cliente.");
            } else if (opcion >= 1 && opcion <= lista.size()) {
                System.out.println("Cliente que ha comprado el coche: " + lista.get(opcion - 1).getCliente().toString());
            } else {
                System.out.println("Opción incorrecta, vuelva a intentarlo.");
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void listarCochesPorVendedor() {
        HashMap<String, Venta> ventas = opConcesionario.listarVentas();
        ArrayList<Venta> lista = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        for (Venta item : ventas.values()) {
            lista.add(item);
        }
        if(lista.isEmpty()){
            System.out.println("No hay coches vendidos.");
            return;
        }
        System.out.println("*****LISTA DE VENDEDORES*****");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).getVendedor().toString());
        }
        System.out.println("");
        System.out.print("Escoja el vendedor de la lista para ver los coches que ha vendido o pulse " + (lista.size() + 1) + " para salir: ");

        try {
            int opcion = scan.nextInt();
            if (opcion == lista.size() + 1) {
                System.out.println("Saliendo de la lista de coches vendidos por vendedor.");
            } else if (opcion >= 1 && opcion <= lista.size()) {
                int numCoche = 0;
                System.out.println("El vendedor ha vendido: ");
                for (Venta venta : lista) {
                    if (venta.getVendedor().equals(lista.get(opcion - 1).getVendedor())) {
                        System.out.println(venta.getCoche().toString());
                        numCoche++;
                    }
                }
                System.out.print("El total de ingresos por ventas del vendedor es: " + numCoche * 200 + " €.");
                System.out.println("");
            } else {
                System.out.println("Opción incorrecta, vuelva a intentarlo.");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public HashMap<String, Double> calcularTotalVentasPorVendedor() {
        HashMap<String, Double> volumenTotalVentas = new HashMap<>();

        HashMap<String, Venta> ventas = opConcesionario.listarVentas();

        for (Venta venta : ventas.values()) {
            String dniVendedor = venta.getVendedor().getDni();
            double precioCoche = venta.getCoche().getPrecioVenta();
            double volumenVenta = 200.0;

            if (volumenTotalVentas.containsKey(dniVendedor)) {
                double volumenTotal = volumenTotalVentas.get(dniVendedor);
                volumenTotal += volumenVenta;
                volumenTotalVentas.put(dniVendedor, volumenTotal);
            } else {
                volumenTotalVentas.put(dniVendedor, volumenVenta);
            }
        }

        return volumenTotalVentas;
    }

    public void totalesVendedores() {
        HashMap<String, Double> volumenTotalVentas = calcularTotalVentasPorVendedor();
        TreeSet<Map.Entry<String, Double>> treeSet = new TreeSet<>(new Comparator<Map.Entry<String, Double>>() {
            //@Override
            public int compare(Map.Entry<String, Double> entry1, Map.Entry<String, Double> entry2) {
                // Ordenar de mayor a menor (reverse order)
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });
        treeSet.addAll(volumenTotalVentas.entrySet());

        System.out.println("*****LISTA DE VENDEDORES POR VOLUMEN DE VENTAS*****");
        for (Map.Entry<String, Double> entry : treeSet) {
            String dniVendedor = entry.getKey();
            double totalVentas = entry.getValue();
            System.out.println("Vendedor DNI: " + dniVendedor + ", Volumen de ventas: " + totalVentas + "€");
        }
    }
}
