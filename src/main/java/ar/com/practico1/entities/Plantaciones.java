package ar.com.practico1.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="plantaciones")
public class Plantaciones {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String periodo;
    private LocalDate fecha;
    private String porcentaje_lote;
    private LocalDate fecha_cierre;

    @JoinColumn(name="id_cultivo", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Cultivos cultivo;

    @JoinColumn(name="id_lote", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Lotes lote;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getPorcentaje_lote() {
        return porcentaje_lote;
    }

    public void setPorcentaje_lote(String porcentaje_lote) {
        this.porcentaje_lote = porcentaje_lote;
    }

    public LocalDate getFecha_cierre() {
        return fecha_cierre;
    }

    public void setFecha_cierre(LocalDate fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }

    public Cultivos getCultivo() {
        return cultivo;
    }

    public void setCultivo(Cultivos cultivo) {
        this.cultivo = cultivo;
    }

    public Lotes getLote() {
        return lote;
    }

    public void setLote(Lotes lote) {
        this.lote = lote;
    }
}
