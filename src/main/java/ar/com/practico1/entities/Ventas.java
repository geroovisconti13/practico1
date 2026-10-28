package ar.com.practico1.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ventas")
public class Ventas {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate fecha;
    private Integer cantvendida;
    private Double precio;

    @JoinColumn(name="id_plantacion", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Plantaciones plantacion;

    @JoinColumn(name="id_comprador", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Compradores compradores;


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

    public Integer getCantvendida() {
        return cantvendida;
    }

    public void setCantvendida(Integer cantvendida) {
        this.cantvendida = cantvendida;
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

    public Compradores getCompradores() {
        return compradores;
    }

    public void setCompradores(Compradores compradores) {
        this.compradores = compradores;
    }
}
