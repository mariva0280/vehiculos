package Objetos;

import java.util.Date;

public class Reparacion {
    private TipoReparacion tipoReparacion;
    private String descripcion;
    private Date fecha;
    private boolean resuelta;

    public Reparacion() {
    }

    public Reparacion(TipoReparacion tipoReparacion, String descripcion, Date fecha, boolean resuelta) {
        this.tipoReparacion = tipoReparacion;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.resuelta = resuelta;
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

    @Override
    public String toString() {
        return "Reparacion{" +
                "tipoReparacion=" + tipoReparacion +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", resuelta=" + resuelta +
                '}';
    }
}