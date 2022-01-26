package com.concesionaria.app.service;

import com.concesionaria.app.domain.Coche;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Coche}.
 */
public interface CocheService {
    /**
     * Save a coche.
     *
     * @param coche the entity to save.
     * @return the persisted entity.
     */
    Coche save(Coche coche);

    /**
     * Partially updates a coche.
     *
     * @param coche the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Coche> partialUpdate(Coche coche);

    /**
     * Get all the coches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Coche> findAll(Pageable pageable);

    /**
     * Get all the coches by modelo paginados.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Coche> cochesPaginadosPorModelo(String modelo, Pageable pageable);

    /**
     * Get all the coches by color amarillo.
     *
     * @param string color pagination information.
     * @return the list of entities.
     */
    List<Coche> findByColor(String color);

    /**
     * Get the "id" coche.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Coche> findOne(Long id);

    /**
     * Delete the "id" coche.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
