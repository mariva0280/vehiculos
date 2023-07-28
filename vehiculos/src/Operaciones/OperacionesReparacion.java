package Operaciones;
import Objetos.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class OperacionesReparacion {
    private  Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;

    public OperacionesReparacion(Concesionario concesionario) {
        this.concesionario = concesionario;
        this.opConcesionario = new OperacionesConcesionario(concesionario);
    }

    public void menuReparaciones() {
        Scanner scan = new Scanner(System.in);
        try {
            int opcion = 0;
            while (opcion != 4) {
                System.out.println("*****MENU REPARACIONES*****");
                System.out.println("1 - Agregar coche a reparar.");
                System.out.println("2 - Modificar estado de la reparación.");
                System.out.println("3 - Consultar reparaciones de un coche.");
                System.out.println("4 - Salir.");
                System.out.println("");
                System.out.print("Elija una opción: ");

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
            }
        }catch (Exception ex) {
            scan.nextLine();
        }

    }
    public void agregarReparar() {

        HashMap<String, Coche> coches = opConcesionario.listarCoches();
        HashMap<String,Mecanico> mecanicos = opConcesionario.listarMecanicos();

        if(coches.isEmpty() || coches == null){
            System.out.println("Debe tener al menos un coche dado de alta.");
            return;
        }

        if(mecanicos.isEmpty() || mecanicos == null){
            System.out.println("Debe tener al menos un mecánico dado de alta.");
            return;
        }
        String matriculaCoche = verCoches(coches);
        if(matriculaCoche == null){
            System.out.println("No ha seleccionado ningún coche.Volviendo al menú reparaciones.");
            return;
        }
        String dniMecanico = verMecanicos(mecanicos);
        if(dniMecanico == null){
            System.out.println("No ha seleccionado ningún mecánico.Volviendo al menú reparaciones.");
            return;
        }

        Reparacion reparacion = new Reparacion();
        //Mecanico mecanico = mecanicos.get(verMecanicos(mecanicos));
        Mecanico mecanico = mecanicos.get(dniMecanico);
        //Coche coche = coches.get(verCoches(coches));
        Coche coche = coches.get(matriculaCoche);
        reparacion.setCoche(coche);
        reparacion.setMecanico(mecanico);
        Scanner scan = new Scanner(System.in);
        System.out.print("Indique el tipo de reparación:(eléctrica,mecánica,chapa_pintura, revision): ");
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
        opConcesionario.eliminarCoche(coche);
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
            System.out.print("Elija la reparación a la que va a modificar el estado: ");
            opcion = scan.nextInt();
            if(opcion == lista.size()+1){
                System.out.println("Volviendo al menú reparaciones.");
            } else if (opcion >=1 && opcion <= lista.size()) {
                Reparacion reparacionSeleccionada = lista.get(opcion - 1);
                Coche coche = reparacionSeleccionada.getCoche();
                coche.setEstado(Estado.STOCK);
                opConcesionario.agregarCoche(coche);
                opConcesionario.cambiarEstadoReparacion(reparacionSeleccionada);
                opConcesionario.eliminarReparacion(reparacionSeleccionada);
                System.out.println("Reparación modificada correctamente.");
            }else {
                System.out.println("Opción incorrecta, vuelva a intentarlo.");
            }

        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            //modificarEstado();
            menuReparaciones();
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
        if(lista.isEmpty()){
            System.out.println("No hay coches disponibles para reparar.");
            return null;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).toString());
        }
        System.out.println(lista.size() + 1 + " - Salir");
        System.out.println("");
        System.out.print("Elija el coche a reparar o pulse " + (lista.size()+1) + " para salir: ");
        try {
            int opcion = scan.nextInt();
            if(opcion == lista.size() + 1) {
                return null;
            }else {
                coche = lista.get(opcion - 1);
            }


        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return coche.getMatricula();
    }
    public String verMecanicos(HashMap<String, Mecanico> mecanicos) {
        Mecanico mecanico = new Mecanico();
        try {
            ArrayList<Mecanico> lista = new ArrayList<>();
            Scanner scan = new Scanner(System.in);
            for (Mecanico item : mecanicos.values()) {
                lista.add(item);
            }

            System.out.println("*****LISTA MECÁNICOS*****");
            System.out.println("");
            if (lista.isEmpty()) {
                System.out.println("No hay mecánicos para realizar la reparación.");
                return null;
            }
            for (int i = 0; i < lista.size(); i++) {
                mecanico = lista.get(i);
                System.out.println((i + 1) + " - Nombre: " + mecanico.getNombre() + ", Teléfono: " + mecanico.getTelefono() + ", DNI: " + mecanico.getDni());
            }
            System.out.println(lista.size() + 1 + " - Salir.");   // Para que sea dinámico haremos que la opcion salir sea una posicion mas que el tamaño de la lista
            System.out.println("");
            System.out.print("Elija de la lista el mecánico que va a realizar la reparación o pulse " + (lista.size() + 1) + " para salir: ");


            int opcion = scan.nextInt();
            if (opcion == lista.size() + 1) {
                return null;
            } else {
                mecanico = lista.get(opcion - 1);
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return mecanico.getDni();
    }

    /*public String verMecanicos(HashMap<String, Mecanico> mecanicos) {
        ArrayList<Mecanico> lista = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        Mecanico mecanico = new Mecanico();

        for (Mecanico item : mecanicos.values()) {
            lista.add(item);
        }

        System.out.println("*****LISTA MECÁNICOS*****");
        System.out.println("");
        if(lista.isEmpty()){
            System.out.println("No hay mecánicos para realizar la reparación.");
            return null;
        }
        for (int i = 0; i < lista.size(); i++) {
            mecanico = lista.get(i);
            System.out.println((i + 1) + " - Nombre: " +  mecanico.getNombre() + ", Teléfono: " + mecanico.getTelefono() + ", DNI: " + mecanico.getDni());
        }
        System.out.println(lista.size() + 1 + " - Salir.");   // Para que sea dinámico haremos que la opcion salir sea una posicion mas que el tamaño de la lista
        System.out.println("");
        System.out.print("Elija de la lista el mecánico que va a realizar la reparación o pulse " + (lista.size() + 1) + " para salir: ");

        try {
            int opcion = scan.nextInt();
            if(opcion == lista.size() + 1) {
                return null;
            }else {
                mecanico = lista.get(opcion - 1);
            }

        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return mecanico.getDni();
    }*/
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
}