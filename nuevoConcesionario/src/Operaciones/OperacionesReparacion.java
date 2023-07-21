package Operaciones;
import Objetos.*;
import java.util.*;

public class OperacionesReparacion {
    private  Concesionario concesionario;
    private OperacionesConcesionario opConcesionario;

    public OperacionesReparacion(Concesionario concesionario, OperacionesConcesionario operacionesConcesionario) {
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
                        //modificarEstado();
                        break;
                    case 3:
                        //consultarReparacionesCoche();
                        break;
                }
            } catch (Exception ex) {
                scan.nextLine();
            }
        }
    }
    public void agregarReparar() {
        HashMap<String, Coche> coches = opConcesionario.listarCoches();

        Reparacion reparacion = new Reparacion();
        reparacion.setCoche(coches.get(verCoches(coches)));

        opConcesionario.agregarReparacion(reparacion);

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
        System.out.print("Elija el coche a reparar: " + (lista.size()+1) + " para salir: ");
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
}


