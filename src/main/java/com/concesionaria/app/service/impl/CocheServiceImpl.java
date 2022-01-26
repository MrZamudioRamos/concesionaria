package com.concesionaria.app.service.impl;

import com.concesionaria.app.domain.Coche;
import com.concesionaria.app.repository.CocheRepository;
import com.concesionaria.app.service.CocheService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Coche}.
 */
@Service
@Transactional
public class CocheServiceImpl implements CocheService {

    private final Logger log = LoggerFactory.getLogger(CocheServiceImpl.class);

    private final CocheRepository cocheRepository;

    public CocheServiceImpl(CocheRepository cocheRepository) {
        this.cocheRepository = cocheRepository;
    }

    @Override
    public Coche save(Coche coche) {
        log.debug("Request to save Coche : {}", coche);
        return cocheRepository.save(coche);
    }

    @Override
    public Optional<Coche> partialUpdate(Coche coche) {
        log.debug("Request to partially update Coche : {}", coche);

        return cocheRepository
            .findById(coche.getId())
            .map(existingCoche -> {
                if (coche.getMarca() != null) {
                    existingCoche.setMarca(coche.getMarca());
                }
                if (coche.getModelo() != null) {
                    existingCoche.setModelo(coche.getModelo());
                }
                if (coche.getColor() != null) {
                    existingCoche.setColor(coche.getColor());
                }
                if (coche.getNumeroSerie() != null) {
                    existingCoche.setNumeroSerie(coche.getNumeroSerie());
                }
                if (coche.getPrecio() != null) {
                    existingCoche.setPrecio(coche.getPrecio());
                }
                if (coche.getExposicion() != null) {
                    existingCoche.setExposicion(coche.getExposicion());
                }

                return existingCoche;
            })
            .map(cocheRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Coche> findAll(Pageable pageable) {
        log.debug("Request to get all Coches");
        return cocheRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Coche> findByColor(String color) {
        log.debug("Request to get all Coches amarillos");
        if ("".equals(color)) {
            cocheRepository.findAll();
        }
        return cocheRepository.findByColor(color);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Coche> findOne(Long id) {
        log.debug("Request to get Coche : {}", id);
        return cocheRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Coche : {}", id);
        cocheRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Coche> cochesPaginadosPorModelo(String modelo, Pageable pageable) {
        log.debug("Request to get all Coches");
        if ("".equals(modelo)) {
            cocheRepository.findAll();
        }
        return cocheRepository.cochesPaginadosPorModelo(modelo, pageable);
    }
}
