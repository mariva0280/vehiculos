package Objetos;

import java.util.Date;

public class Reparacion {
    private TipoReparacion tipoReparacion;
    private String descripcion;
    private Date fecha;
    private boolean resuelta;
    private Coche coche;
    private Mecanico mecanico;

    public Reparacion() {
    }

    public Reparacion(TipoReparacion tipoReparacion, String descripcion, Date fecha, boolean resuelta,Coche coche,Mecanico mecanico) {
        this.tipoReparacion = tipoReparacion;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.resuelta = resuelta;
        this.coche = coche;
        this.mecanico = mecanico;
    }

    public TipoReparacion getTipoReparacion() {
        return tipoReparacion;
    }

    public void setTipoReparacion(TipoReparacion tipoReparacion) {
        this.tipoReparacion = tipoReparacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isResuelta() {
        return resuelta;
    }

    public void setResuelta(boolean resuelta) {
        this.resuelta = resuelta;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    @Override
    public String toString() {
        return "Coche: " + coche.getMatricula() + " " + coche.getMarca() + " tipo reparación: " + tipoReparacion + " descripción: " + descripcion + " fecha: " + fecha + " ¿la reparación está resuelta?: " + resuelta;
    }
}
