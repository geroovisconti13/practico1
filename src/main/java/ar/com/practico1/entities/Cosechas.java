package ar.com.practico1.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cosechas")
public class Cosechas {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate fecha;
    private String rendimiento;

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

    public String getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(String rendimiento) {
        this.rendimiento = rendimiento;
    }

    public Plantaciones getPlantacion() {
        return plantacion;
    }

    public void setPlantacion(Plantaciones plantacion) {
        this.plantacion = plantacion;
    }
}

