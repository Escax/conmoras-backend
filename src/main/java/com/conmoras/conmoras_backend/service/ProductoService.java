package com.conmoras.conmoras_backend.service;


import com.conmoras.conmoras_backend.dto.ProductoDTO;
import com.conmoras.conmoras_backend.entity.Producto;
import com.conmoras.conmoras_backend.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepository;

    // ── Listar todos ─────────────────────────────────────────────
    public List<ProductoDTO> listarTodos() {
        return productoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ── Buscar por ID ─────────────────────────────────────────────
    public ProductoDTO buscarPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
        return toDTO(producto);
    }

    // ── Crear ─────────────────────────────────────────────────────
    public ProductoDTO crear(ProductoDTO dto) {
        Producto producto = toEntity(dto);
        return toDTO(productoRepository.save(producto));
    }

    // ── Actualizar ────────────────────────────────────────────────
    public ProductoDTO actualizar(Long id, ProductoDTO dto) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));

        existente.setNombre(dto.getNombre());
        existente.setCategoria(dto.getCategoria());
        existente.setCantidad(dto.getCantidad());
        existente.setFechaIngreso(dto.getFechaIngreso());
        existente.setFechaVencimiento(dto.getFechaVencimiento());

        return toDTO(productoRepository.save(existente));
    }

    // ── Eliminar ──────────────────────────────────────────────────
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado: " + id);
        }
        productoRepository.deleteById(id);
    }

    // ── Mappers internos ──────────────────────────────────────────
    private ProductoDTO toDTO(Producto p) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setCategoria(p.getCategoria());
        dto.setCantidad(p.getCantidad());
        dto.setFechaIngreso(p.getFechaIngreso());
        dto.setFechaVencimiento(p.getFechaVencimiento());
        return dto;
    }

    private Producto toEntity(ProductoDTO dto) {
        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setCategoria(dto.getCategoria());
        p.setCantidad(dto.getCantidad());
        p.setFechaIngreso(dto.getFechaIngreso());
        p.setFechaVencimiento(dto.getFechaVencimiento());
        return p;
    }
}
