package com.concesionaria.app.service.impl;

import com.concesionaria.app.domain.Venta;
import com.concesionaria.app.repository.VentaRepository;
import com.concesionaria.app.service.VentaService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Venta}.
 */
@Service
@Transactional
public class VentaServiceImpl implements VentaService {

    private final Logger log = LoggerFactory.getLogger(VentaServiceImpl.class);

    private final VentaRepository ventaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public Venta save(Venta venta) {
        log.debug("Request to save Venta : {}", venta);
        return ventaRepository.save(venta);
    }

    @Override
    public Optional<Venta> partialUpdate(Venta venta) {
        log.debug("Request to partially update Venta : {}", venta);

        return ventaRepository
            .findById(venta.getId())
            .map(existingVenta -> {
                if (venta.getFecha() != null) {
                    existingVenta.setFecha(venta.getFecha());
                }
                if (venta.getTipoPago() != null) {
                    existingVenta.setTipoPago(venta.getTipoPago());
                }
                if (venta.getTotal() != null) {
                    existingVenta.setTotal(venta.getTotal());
                }

                return existingVenta;
            })
            .map(ventaRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Venta> findAll(Pageable pageable) {
        log.debug("Request to get all Ventas");
        return ventaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venta> findOne(Long id) {
        log.debug("Request to get Venta : {}", id);
        return ventaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Venta : {}", id);
        ventaRepository.deleteById(id);
    }
}
