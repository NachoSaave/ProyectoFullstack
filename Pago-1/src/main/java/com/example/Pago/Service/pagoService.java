package com.example.Pago.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.Pago.Model.Pago;
import com.example.Pago.Model.DTO.*;
import com.example.Pago.Repository.pagoRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class pagoService {

    private final pagoRepository repository;

    // CREAR PAGO
    public PagoResponseDTO proceso(Pago pago) {

        log.info("Procesando pago de cliente: " + pago.getClienteNombre());

        pago.setEstado("APROBADO");

        Pago saved = repository.save(pago);

        return toDTO(saved);
    }

    //  CONVERTIR ENTITY → DTO
    public PagoResponseDTO toDTO(Pago saved) {

        PagoResponseDTO dto = new PagoResponseDTO();

        dto.setId(saved.getId());
        dto.setSaleId(saved.getSaleId());
        dto.setMonto(saved.getMonto());
        dto.setMetodoPago(saved.getMetodoPago());
        dto.setEstado(saved.getEstado());

        ClienteDTO cliente = new ClienteDTO();
        cliente.setId(saved.getClienteId());
        cliente.setNombre(saved.getClienteNombre());
        cliente.setTelefono(saved.getClienteTelefono());

        dto.setCliente(cliente);

        return dto;
    }

    //  LISTAR
    public List<PagoResponseDTO> listar() {

        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    //  BUSCAR POR ID
    public PagoResponseDTO buscarPorId(Long id) {

        Pago pago = repository.findById(id).orElse(null);

        if (pago == null) return null;

        return toDTO(pago);
    }
}