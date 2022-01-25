package com.concesionaria.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Coche.
 */
@Entity
@Table(name = "coche")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Coche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "color")
    private String color;

    @Column(name = "numero_serie")
    private String numeroSerie;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "exposicion")
    private Boolean exposicion;

    @OneToMany(mappedBy = "coche")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "ventas", "empleado", "cliente", "coche" }, allowSetters = true)
    private Set<Venta> coches = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "ventas", "empleado", "cliente", "coche" }, allowSetters = true)
    private Venta venta;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Coche id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return this.marca;
    }

    public Coche marca(String marca) {
        this.setMarca(marca);
        return this;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public Coche modelo(String modelo) {
        this.setModelo(modelo);
        return this;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return this.color;
    }

    public Coche color(String color) {
        this.setColor(color);
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumeroSerie() {
        return this.numeroSerie;
    }

    public Coche numeroSerie(String numeroSerie) {
        this.setNumeroSerie(numeroSerie);
        return this;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public Coche precio(Double precio) {
        this.setPrecio(precio);
        return this;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Boolean getExposicion() {
        return this.exposicion;
    }

    public Coche exposicion(Boolean exposicion) {
        this.setExposicion(exposicion);
        return this;
    }

    public void setExposicion(Boolean exposicion) {
        this.exposicion = exposicion;
    }

    public Set<Venta> getCoches() {
        return this.coches;
    }

    public void setCoches(Set<Venta> ventas) {
        if (this.coches != null) {
            this.coches.forEach(i -> i.setCoche(null));
        }
        if (ventas != null) {
            ventas.forEach(i -> i.setCoche(this));
        }
        this.coches = ventas;
    }

    public Coche coches(Set<Venta> ventas) {
        this.setCoches(ventas);
        return this;
    }

    public Coche addCoches(Venta venta) {
        this.coches.add(venta);
        venta.setCoche(this);
        return this;
    }

    public Coche removeCoches(Venta venta) {
        this.coches.remove(venta);
        venta.setCoche(null);
        return this;
    }

    public Venta getVenta() {
        return this.venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Coche venta(Venta venta) {
        this.setVenta(venta);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coche)) {
            return false;
        }
        return id != null && id.equals(((Coche) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Coche{" +
            "id=" + getId() +
            ", marca='" + getMarca() + "'" +
            ", modelo='" + getModelo() + "'" +
            ", color='" + getColor() + "'" +
            ", numeroSerie='" + getNumeroSerie() + "'" +
            ", precio=" + getPrecio() +
            ", exposicion='" + getExposicion() + "'" +
            "}";
    }
}
