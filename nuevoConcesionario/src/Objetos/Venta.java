/*ESTA CLASE LA HEMOS CREADO EN OBJETOS USANDO OTROS OBJETOS EN ESTE CASO COCHE, CLIENTE Y VENDEDOR,PORQUE SON
 LOS TRES OBJETOS QUE INTERFIEREN EN LA VENTA DE UN  COCHE,ES COMO SI
FUESE UNA HERENCIA, PERO AL ESTAR USANDO LOS OBJETOS YA CREADOS NOS RETORNA LOS DATOS QUE CONTIENEN ESOS OBJETOS
 */
package Objetos;

public class Venta {
    private Coche coche;
    private Cliente cliente;
    private Vendedor vendedor;

    public Venta() {
    }

    public Venta(Coche coche, Cliente cliente, Vendedor vendedor) {
        this.coche = coche;
        this.cliente = cliente;
        this.vendedor = vendedor;
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

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return " El coche: " + coche + " ,ha sido comprado por: " + cliente + " , se lo ha vendido: " + vendedor;
    }
}