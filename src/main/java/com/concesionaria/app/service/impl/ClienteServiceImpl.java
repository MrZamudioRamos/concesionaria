package com.concesionaria.app.service.impl;

import com.concesionaria.app.domain.Cliente;
import com.concesionaria.app.repository.ClienteRepository;
import com.concesionaria.app.service.ClienteService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Cliente}.
 */
@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente save(Cliente cliente) {
        log.debug("Request to save Cliente : {}", cliente);
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> partialUpdate(Cliente cliente) {
        log.debug("Request to partially update Cliente : {}", cliente);

        return clienteRepository
            .findById(cliente.getId())
            .map(existingCliente -> {
                if (cliente.getDni() != null) {
                    existingCliente.setDni(cliente.getDni());
                }
                if (cliente.getNombre() != null) {
                    existingCliente.setNombre(cliente.getNombre());
                }
                if (cliente.getNumeroCompras() != null) {
                    existingCliente.setNumeroCompras(cliente.getNumeroCompras());
                }
                if (cliente.getTier() != null) {
                    existingCliente.setTier(cliente.getTier());
                }

                return existingCliente;
            })
            .map(clienteRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        log.debug("Request to get all Clientes");
        return clienteRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> findOne(Long id) {
        log.debug("Request to get Cliente : {}", id);
        return clienteRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cliente : {}", id);
        clienteRepository.deleteById(id);
    }
}
