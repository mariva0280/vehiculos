/*
ESTA CLASE LA HEMOS CREADO EN OBJETOS USANDO OTROS OBJETOS EN ESTE CASO COCHE Y CLIENTE,PORQUE SON LOS DOS
 OBJETOS QUE NECESITAMOS PARA REALIZAR UNA RESERVA ,ES COMO SI
FUESE UNA HERENCIA, PERO AL ESTAR USANDO LOS OBJETOS YA CREADOS NOS RETORNA LOS DATOS QUE CONTIENEN ESOS OBJETOS
 */

package Objetos;

public class Reserva {
    private Coche coche;
    private Cliente cliente;

    public Reserva() {
    }

    public Reserva(Coche coche, Cliente cliente) {
        this.coche = coche;
        this.cliente = cliente;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}