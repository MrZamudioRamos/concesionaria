package com.concesionaria.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Venta.
 */
@Entity
@Table(name = "venta")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "tipo_pago")
    private String tipoPago;

    @Column(name = "total")
    private Double total;

    @OneToMany(mappedBy = "venta")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "coches", "venta" }, allowSetters = true)
    private Set<Coche> ventas = new HashSet<>();

    @ManyToOne
    private Empleado empleado;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    @JsonIgnoreProperties(value = { "coches", "venta" }, allowSetters = true)
    private Coche coche;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Venta id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public Venta fecha(LocalDate fecha) {
        this.setFecha(fecha);
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipoPago() {
        return this.tipoPago;
    }

    public Venta tipoPago(String tipoPago) {
        this.setTipoPago(tipoPago);
        return this;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Double getTotal() {
        return this.total;
    }

    public Venta total(Double total) {
        this.setTotal(total);
        return this;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Set<Coche> getVentas() {
        return this.ventas;
    }

    public void setVentas(Set<Coche> coches) {
        if (this.ventas != null) {
            this.ventas.forEach(i -> i.setVenta(null));
        }
        if (coches != null) {
            coches.forEach(i -> i.setVenta(this));
        }
        this.ventas = coches;
    }

    public Venta ventas(Set<Coche> coches) {
        this.setVentas(coches);
        return this;
    }

    public Venta addVenta(Coche coche) {
        this.ventas.add(coche);
        coche.setVenta(this);
        return this;
    }

    public Venta removeVenta(Coche coche) {
        this.ventas.remove(coche);
        coche.setVenta(null);
        return this;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Venta empleado(Empleado empleado) {
        this.setEmpleado(empleado);
        return this;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Venta cliente(Cliente cliente) {
        this.setCliente(cliente);
        return this;
    }

    public Coche getCoche() {
        return this.coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public Venta coche(Coche coche) {
        this.setCoche(coche);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Venta)) {
            return false;
        }
        return id != null && id.equals(((Venta) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Venta{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", tipoPago='" + getTipoPago() + "'" +
            ", total=" + getTotal() +
            "}";
    }
}
