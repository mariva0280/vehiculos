package Operaciones;

import Objetos.*;
import Exception.EinvalidPropertyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OperacionesVentas {
    private Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;

    public OperacionesVentas(Concesionario concesionario) {

        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(concesionario);
    }
    // METODO PARA VENDER UN COCHE
    public void vender() {
        /* ESTE METODO LLENAR CONCESIONARIO ESTÁ HECHO PARA PROBARLO EL PROGRAMA SIN TENER QUE
        ESTAR SIEMPRE INGRESANDO TODOS LOS DATOS PARA QUE FUNCIONE SE BORRARÁ CUANDO SEA NECESARIO
         */
        llenarConcesionario();

        HashMap<String, Cliente> clientes = opConcesionario.listarClientes();
        HashMap<String, Vendedor> vendedores = opConcesionario.listarVendedores();
        HashMap<String, Coche> coches = opConcesionario.listarCoches();


        Venta venta = new Venta();
        venta.setCliente(clientes.get(verClientes(clientes)));
        venta.setVendedor(vendedores.get(verVendedores(vendedores)));
        venta.setCoche(coches.get(verCoches(coches)));
        opConcesionario.agregarVenta(venta);
        /*
        LA INSTANCIA AQUI A OPERACIONES INFORMES ES PARA QUE PODAMOS USAR EL MENU INFORMES DESDE EL MAIN
        Y PODAMOS PROBAR LO QUE HAY EN OPERACIONES INFORMES SE BORRARÁ CUANDO ESTE TERMINADO Y FUNCIONANDO
         */
        OperacionesInformes opInformes = new OperacionesInformes(concesionario);
        opInformes.menuInformes();

    }
    /*
    ESTOS METODOS VERCLIENTES,VERVENDEDORES Y VERCOCHES SON PARA ESCOGER DE LA LISTA EL CLIENTE QUE VA A COMPRAR
    EL COCHE, EL VENDEDOR QUE LO VENDE Y EL COCHE QUE ESCOGE DE LA LISTA PARA VENDER
     */
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
        System.out.println(lista.size() + 1 + " - Salir.");   // Para que sea dinámico haremos que la opcion salir sea una posicion mas que el tamaño de la lista
        System.out.println("");
        System.out.print("Elija el cliente: ");
        System.out.println((lista.size()+1) + " - Salir.");
        try {
            int opcion = scan.nextInt();
            if(opcion == lista.size() + 1) {

            }else {
                cliente = lista.get(opcion - 1);
            }


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
        System.out.println(lista.size() + 1 + " - Salir");   // Para que sea dinámico haremos que la opcion salir sea una posicion mas que el tamaño de la lista
        System.out.println("");
        System.out.print("Elija el vendedor: ");
        System.out.print((lista.size()+1) + " - Salir.");
        try {
            int opcion = scan.nextInt();
            if(opcion == lista.size() + 1) {

            }else {
                vendedor = lista.get(opcion - 1);
            }


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
        System.out.println(lista.size() + 1 + " - Salir.");   // Para que sea dinámico haremos que la opcion salir sea una posicion mas que el tamaño de la lista
        System.out.println("");
        System.out.print("Elija el coche deseado por el cliente: ");
        System.out.print((lista.size()+1) + " - Salir.");
        try {
            int opcion = scan.nextInt();
            if(opcion == lista.size() + 1) {

            }else {
                coche = lista.get(opcion - 1);
            }


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
    /*
    ESTE METODO ESCOGEMOS DE LA LISTA DE COCHES VENDIDOS Y NOS IMPRIME LOS DATOS DEL CLIENTE QUE LO HA COMPRADO
     */
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
        System.out.print("Escoga el coche: ");
        System.out.print((lista.size()+1)+ " - Salir.");
        Scanner scan = new Scanner(System.in);

        try{
            int opcion = scan.nextInt();
            System.out.println("Cliente que ha comprado el coche: " + lista.get(opcion-1).getCliente().toString());
        }catch (Exception ex){
            System.out.println("Error: " +ex.getMessage());
        }
    }
    /*
    ESTE METODO ESCOGES EL VENDEDOR Y TE MUESTRA LA LISTA DE COCHES VENDIDOS POR VENDEDOR Y EL DINERO QUE HA GANADO
    CON LAS VENTAS
     */
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
        System.out.print("Escoga el vendedor:  ");
        System.out.print((lista.size()+1) + " - Salir.");


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
            System.out.print("El total de ingresos por ventas del vendedor es: " + numCoche * 200);
        }catch (Exception ex){
            System.out.println("Error: " +ex.getMessage());
        }
    }

    /*
    ESTE METODO COMO TE HE PUESTO ARRIBA ES PARA PROBAR TODAS LAS FUNCIONES NUEVAS PORQUE HASTA QUE NO TENGAMOS
    TODAS LAS FUNCIONALIDADES NO ESTARA DINAMICO Y NO FUNCIONARA COMPLETO
     */
    public void llenarConcesionario(){

        Coche coche1 = new Coche("Seat","Ibiza","7250CGR",8000,10000, TipoVehiculo.TURISMO, Estado.STOCK);
        Coche coche2 = new Coche("BMW","X-2","7251LGR",28000,35000,TipoVehiculo.TODOTERRENO,Estado.STOCK);
        Coche coche3 = new Coche("CITROEN","Berlingo","7252FGR",18000,20000,TipoVehiculo.INDUSTRIAL,Estado.STOCK);

        opConcesionario.agregarCoche(coche1);
        opConcesionario.agregarCoche(coche2);
        opConcesionario.agregarCoche(coche3);
        Cliente cliente = new Cliente("MARIA","TOLEDO","12345678A",123123123);
        Vendedor vendedor = new Vendedor("PEDRO","MADRID","12345678B",456456456);
        opConcesionario.agregarCliente(cliente);
        opConcesionario.agregarVendedor(vendedor);
    }

}
