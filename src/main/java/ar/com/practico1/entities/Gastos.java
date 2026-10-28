package ar.com.practico1.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name= "gastos")
public class Gastos {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate fecha;
    private String concepto;
    private Integer unidades;
    private Double precio;

    @JoinColumn(name="id_plantacion", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Plantaciones plantacion;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Plantaciones getPlantacion() {
        return plantacion;
    }

    public void setPlantacion(Plantaciones plantacion) {
        this.plantacion = plantacion;
    }
}
