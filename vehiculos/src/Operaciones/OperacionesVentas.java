/*
ESTA CLASE ESTA TERMINADA Y FUNCIONA
 */

package Operaciones;

import Objetos.*;
import Exception.EinvalidPropertyException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class OperacionesVentas {
    private Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;

    public OperacionesVentas(Concesionario concesionario) {

        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(concesionario);
    }

    public void menuVentas(){
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
                System.out.print("Elija una opcion: ");

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
        }catch (Exception ex) {
            scan.nextLine();
        }
    }

    public void vender() {

        HashMap<String, Cliente> clientes = opConcesionario.listarClientes();
        HashMap<String, Vendedor> vendedores = opConcesionario.listarVendedores();
        HashMap<String, Coche> coches = opConcesionario.listarCoches();

        Venta venta = new Venta();
        venta.setCliente(clientes.get(verClientes(clientes)));
        venta.setVendedor(vendedores.get(verVendedores(vendedores)));
        Coche coche = coches.get(verCoches(coches));
        venta.setCoche(coche);
        opConcesionario.agregarVenta(venta);
        opConcesionario.eliminarCoche(coche);
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
        System.out.print("Elija de la lista el cliente que desea comprar el coche o pulse " + (lista.size()+1)+ " para salir: ");
        try {
            int opcion = scan.nextInt();
            if(opcion == lista.size() + 1) {

            }else {
                cliente = lista.get(opcion - 1);
            }
            System.out.println("Cliente elegido correctamente.");

        }catch (Exception ex){
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
        System.out.print("Elija el vendedor de la lista que va a realizar la venta o pulse " + (lista.size()+1) + " para salir: ");
        try {
            int opcion = scan.nextInt();
            if(opcion == lista.size() + 1) {

            }else {
                vendedor = lista.get(opcion - 1);
            }
            System.out.println("Vendedor elegido correctamente.");


        }catch (Exception ex){
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
        System.out.print("Elija el coche deseado por el cliente o pulse " + (lista.size()+1)+" para salir: ");
        try {
            int opcion = scan.nextInt();
            if(opcion == lista.size() + 1) {

            }else {
                coche = lista.get(opcion - 1);
            }
            System.out.println("Coche añadido al cliente correctamente.");


        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return coche.getMatricula();
    }
    //LO DEJAMOS DE MOMENTO PERO NO VA A SER NECESARIO
    /*public void listarCochesVendidos() {
        System.out.println("");
        System.out.println("------------LISTA COCHES VENDIDOS-------------------");
        System.out.println("");
        HashMap<String,Venta> ventas = opConcesionario.listarVentas();
        if (ventas.isEmpty()) {
            System.out.println("No hay coches vendidos.");
        } else {
            for (Venta venta : ventas.values()) {
                System.out.println(venta.getCoche().toString());
                System.out.println("----------------------------------------");
            }
        }
        System.out.println("");
    }*/
    public void listarClientePorModelo() {
        HashMap<String,Venta> ventas = opConcesionario.listarVentas();
        ArrayList<Venta> lista = new ArrayList<>();
        for (Venta item : ventas.values()) {
            lista.add(item);
        }
        System.out.println("*****LISTA DE COCHES VENDIDOS*****");
        for(int i = 0; i < lista.size(); i++){
            System.out.println((i + 1) + " - " + lista.get(i).getCoche().toString());
        }
        System.out.println("");
        System.out.print("Escoga el coche de la lista o pulse " + (lista.size()+1) + " para salir: ");

        Scanner scan = new Scanner(System.in);

        try{
            int opcion = scan.nextInt();
            System.out.println("Cliente que ha comprado el coche: " + lista.get(opcion-1).getCliente().toString());
        }catch (Exception ex){
            System.out.println("Error: " +ex.getMessage());
        }
    }
    public void listarCochesPorVendedor() {
        HashMap<String,Venta> ventas = opConcesionario.listarVentas();
        ArrayList<Venta> lista = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        for (Venta item : ventas.values()) {
            lista.add(item);
        }
        System.out.println("*****LISTA DE VENDEDORES*****");
        for(int i = 0; i < lista.size(); i++){
            System.out.println((i + 1) + " - " + lista.get(i).getVendedor().toString());
        }
        System.out.println("");
        System.out.print("Escoga el vendedor de la lista para ver los coches que ha vendido o pulse " + (lista.size()+1) + " para salir: ");

        try{
            int opcion = scan.nextInt();
            int numCoche = 0;
            System.out.println("El vendedor ha vendido: ");
            for(Venta venta : lista) {
                if(venta.getVendedor().equals(lista.get(opcion - 1).getVendedor())){
                    System.out.println(venta.getCoche().toString());
                    numCoche ++;
                }
            }
            System.out.print("El total de ingresos por ventas del vendedor es: " + numCoche * 200 + " €.");
            System.out.println("");
        }catch (Exception ex){
            System.out.println("Error: " +ex.getMessage());
        }
    }
    

    /*public void totalesVendedores(){
        HashMap<String, Venta> listaVentas = opConcesionario.listarVentas();
        List<Venta> lista = new ArrayList<>();
        for(Venta item : listaVentas.values()){
            lista.add(item);
        }
        Vendedor vendedor = new Vendedor();
        Double total = 0.0;

        for(Venta venta : lista){
            vendedor = venta.getVendedor();
            System.out.println("El vendedor " + vendedor.getNombre());
            for(int i = 0; i < lista.size(); i++){
                if(lista.get(i).getVendedor().getDni().compareTo(vendedor.getDni()) == 0){
                    total += 200;
                    lista.remove(i);
                }
            }
            System.out.println("Ha obtenido " + total + "€.");
        }

    }*/
}
