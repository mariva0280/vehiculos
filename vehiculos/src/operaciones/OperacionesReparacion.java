package operaciones;

import objetos.Coche;
import objetos.Concesionario;
import objetos.Reparacion;
import objetos.TipoReparacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/*public class OperacionesReparacion {
    private final Concesionario concesionario;
    int opcion;

    public OperacionesReparacion(Concesionario concesionario) {
        this.concesionario = concesionario;
    }
    public void menuReparaciones(){
        Scanner scan = new Scanner(System.in);
        while (opcion != 4) {
            System.out.println("1 - Cambiar estado coche");
            System.out.println("2 - Reparar coche");
            System.out.println("3 - Consultar reparacion");
            System.out.println("4 - Salir");
            System.out.println("");
            System.out.print("Elija una opcion: ");
            try {
                opcion = scan.nextInt();
                switch (opcion) {
                    case (1):
                        cambiarEstadoResolucion();
                        break;
                    case (2):
                        repararCoche();
                        break;
                    case (3):
                        consultaReparacion();
                        break;
                }
            } catch (Exception ex) {
                scan.nextLine();
            }
        }
    }
    public void cambiarEstadoResolucion() {
         resuelta = !resuelta;
    }
    public ArrayList<Coche> getCochesEnReparacion(ArrayList<Coche> coches) {
        ArrayList<Coche> cochesEnReparacion = new ArrayList<>();

        for (Coche coche : coches) {
            ArrayList<Reparacion> reparaciones = coche.getReparaciones();
            for (Reparacion reparacion : reparaciones) {
                if (!reparacion.isResuelta()) {
                    cochesEnReparacion.add(coche);
                    break;
                }
            }
        }

        return cochesEnReparacion;
    }
    private void repararCoche() {
    }
    private void consultaReparacion(){}

    public String toString() {
        String estadoResulta = resuelta ? "Resulta" : "No resuelta";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        String fechaFormat = dateFormat.format(fecha);
        return "Tipo: " + tipoReparacion + " ,descripcion: " + descripcion + " ,fecha: " + fechaFormat + " ,com.concesionario.domain.Estado: " + estadoResulta;

    }

}*/
