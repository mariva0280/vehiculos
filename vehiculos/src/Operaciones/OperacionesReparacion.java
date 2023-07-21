package Operaciones;
import Objetos.*;
import Operaciones.OperacionesConcesionario;
import Exception.EinvalidPropertyException;

import java.text.SimpleDateFormat;
import java.util.*;

import  Validaciones.Validar;

public class OperacionesReparacion {
    private  Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;

    public OperacionesReparacion(Concesionario concesionario) {
        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(concesionario);
    }

    public void menuReparaciones() {
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        while (opcion != 4) {
            System.out.println("1 - Agregar coche a reparar.");
            System.out.println("2 - Modificar estado de la reparación.");
            System.out.println("3 - Consultar reparaciones de un coche.");
            System.out.println("4 - Salir.");
            System.out.println("");
            System.out.print("Elija una opción: ");
            try {
                opcion = scan.nextInt();
                switch (opcion) {
                    case 1:
                        agregarReparar();
                        break;
                    case 2:
                        modificarEstado();
                        break;
                    case 3:
                        consultarReparacionesCoche();
                        break;
                }
            } catch (Exception ex) {
                scan.nextLine();
            }
        }
    }
    public void agregarReparar() {
        //LUEGO SE BORRA LA LLAMADA Y EL METODO LO PONEMOS PARA PROBAR
        llenarConcesionario();
        HashMap<String, Coche> coches = opConcesionario.listarCoches();
        HashMap<String,Mecanico> mecanicos = opConcesionario.listarMecanicos();

        Reparacion reparacion = new Reparacion();
        reparacion.setCoche(coches.get(verCoches(coches)));
        reparacion.setMecanico(mecanicos.get(verMecanicos(mecanicos)));
        Scanner scan = new Scanner(System.in);
        System.out.print("Indique el tipo de reparación: ");
        TipoReparacion tipoReparacion = TipoReparacion.valueOf(scan.nextLine().toUpperCase());
        System.out.print("Descripción de la reparación: ");
        String descripcion = scan.nextLine();
        System.out.print("Fecha de la reparación (dd/MM/yyyy): ");
        String fechaStr = scan.nextLine();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = dateFormat.parse(fechaStr);
            reparacion.setTipoReparacion(tipoReparacion);
            reparacion.setDescripcion(descripcion);
            reparacion.setFecha(fecha);
            System.out.println("Reparación añadida correctamente.");
        } catch (Exception ex) {
            System.out.println("Error al leer la fecha. La reparación no se agregó.");
            return;
        }

        opConcesionario.agregarReparacion(reparacion);
    }

    public void modificarEstado(){
        opConcesionario = new OperacionesConcesionario(concesionario);
        Scanner scan = new Scanner(System.in);
        int opcion;
        HashMap<String,Reparacion> reparaciones = opConcesionario.listarReparaciones();
        ArrayList<Reparacion> lista = new ArrayList<>();
        for(Reparacion reparacion : reparaciones.values()){
            lista.add(reparacion);
        }
        try{
            System.out.println("*****LISTA REPARACIONES*****");
            System.out.println("");
            for(int i = 0; i < lista.size(); i++){
                System.out.println((i + 1) + lista.get(i).toString());
            }
            System.out.println((lista.size()+1) + " - Salir: ");
            System.out.println("");
            System.out.print("Elija la reparación a la que va a mofificar el estado: ");
            opcion = scan.nextInt();
            if(opcion == lista.size()+1){
                System.out.println("Volviendo al menú reparaciones.");
            } else if (opcion >=1 && opcion <= lista.size()) {
                Reparacion reparacionSeleccionada = lista.get(opcion - 1);
                opConcesionario.cambiarEstadoReparacion(reparacionSeleccionada);
                System.out.println("Reparación modificada correctamente.");
            }else {
                System.out.println("Opción incorrecta, vuelva a intentarlo.");
            }

        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            modificarEstado();
        }
    }
    public void consultarReparacionesCoche() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese la matrícula del coche para consultar sus reparaciones.");
        String matricula = scan.nextLine();
        HashMap<String,Reparacion> reparaciones = opConcesionario.listarReparaciones();
        ArrayList<Reparacion> reparacionesCoche = new ArrayList<>();

        for(Reparacion reparacion : reparaciones.values()){
            if(reparacion.getCoche().getMatricula().equals(matricula)){
                reparacionesCoche.add(reparacion);
            }
        }
        if(reparacionesCoche.isEmpty()){
            System.out.println("No hay reparaciones para este coche.");
        }else {
            reparacionesCoche.sort(Comparator.comparing(Reparacion::getFecha).reversed());

            System.out.println("Reparaciones del coche: " + matricula + ":");
            for(Reparacion reparacion: reparacionesCoche){
                System.out.println(reparacion.toString());
            }
        }
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
        System.out.println(lista.size() + 1 + " - Salir");   // Para que sea dinámico haremos que la opcion salir sea una posicion mas que el tamaño de la lista
        System.out.println("");
        System.out.print("Elija el coche a reparar o pulse " + (lista.size()+1) + " para salir: ");
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
    private void agregarM() {
        opConcesionario = new OperacionesConcesionario(concesionario);
        Mecanico mecanico = new Mecanico();
        Scanner scan = new Scanner(System.in);
        try {
            System.out.print("Introduzca el nombre del cliente: ");
            String nombre = (scan.nextLine());
            if(!Validar.validateName(nombre)){
                throw new EinvalidPropertyException("Nombre incorrecto.");
            }
            mecanico.setNombre(nombre);

            System.out.print("Introduzca la dirección del cliente: ");
            String direccion = (scan.nextLine());
            if(!Validar.validateDireccion(direccion)){
                throw new EinvalidPropertyException("Dirección incorrecta.");
            }
            mecanico.setDireccion(direccion);

            System.out.print("Introduzca el DNI del cliente: ");
            String dni =(scan.nextLine());
            if(!Validar.validateDni(dni)){
                throw new EinvalidPropertyException("DNI incorrecto.");
            }
            mecanico.setDni(dni);

            System.out.print("Introduzca el teléfono del cliente: ");
            String telefonoStr =scan.nextLine();
            if(!Validar.validateTelefono(telefonoStr)){
                throw new EinvalidPropertyException("Teléfono incorrecto.");
            }
            int telefono = Integer.parseInt(telefonoStr);
            mecanico.setTelefono(telefono);

            opConcesionario.agregarMecanico(mecanico);
            System.out.println("Mecánico  agregado correctamente.");
        } catch (EinvalidPropertyException ex){
            System.out.println("Error: " + ex.getMessage());
            agregarM();
        }
    }
    public String verMecanicos(HashMap<String, Mecanico> mecanicos) {
        ArrayList<Mecanico> lista = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        Mecanico mecanico = new Mecanico();

        for (Mecanico item : mecanicos.values()) {
            lista.add(item);
        }
        System.out.println("*****LISTA MECÁNICOS*****");
        System.out.println("");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).toString());
        }
        System.out.println(lista.size() + 1 + " - Salir.");   // Para que sea dinámico haremos que la opcion salir sea una posicion mas que el tamaño de la lista
        System.out.println("");
        System.out.print("Elija de la lista el mecánico que va a realizar la reparación o pulse " + (lista.size() + 1) + " para salir: ");

        try {
            int opcion = scan.nextInt();
            if(opcion == lista.size() + 1) {

            }else {
                mecanico = lista.get(opcion - 1);
            }

        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return mecanico.getDni();
    }
    public void listarReparaciones(){
        HashMap<String,Reparacion> reparaciones = opConcesionario.listarReparaciones();
        if(reparaciones.isEmpty()){
            System.out.println("No hay reparaciones que mostrar.");
        }else {
            System.out.println("*****LISTA DE REPARACIONES*****");
            System.out.println("");
            int index = 1;
            for(Reparacion reparacion : reparaciones.values()){
                System.out.println(index + " - " + reparacion.toString());
                index++;
            }
        }
    }
    public void llenarConcesionario(){

        Coche coche1 = new Coche("Seat","Ibiza","7250CGR",8000,10000, TipoVehiculo.TURISMO, Estado.STOCK);
        Coche coche2 = new Coche("BMW","X-2","7251LGR",28000,35000,TipoVehiculo.TODOTERRENO,Estado.STOCK);
        Coche coche3 = new Coche("CITROEN","Berlingo","7252FGR",18000,20000,TipoVehiculo.INDUSTRIAL,Estado.STOCK);

        opConcesionario.agregarCoche(coche1);
        opConcesionario.agregarCoche(coche2);
        opConcesionario.agregarCoche(coche3);
        Cliente cliente = new Cliente("MARIA","TOLEDO","12345678A",123123123);
        Vendedor vendedor = new Vendedor("PEDRO","MADRID","12345678B",456456456);
        Mecanico mecanico = new Mecanico("JUAN","AVILA","12345678C","789789789");
        opConcesionario.agregarCliente(cliente);
        opConcesionario.agregarVendedor(vendedor);
        opConcesionario.agregarMecanico(mecanico);
    }

}



