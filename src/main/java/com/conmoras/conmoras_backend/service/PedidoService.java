package com.conmoras.conmoras_backend.service;

import com.conmoras.conmoras_backend.dto.PedidoDTO;
import com.conmoras.conmoras_backend.entity.Pedido;
import com.conmoras.conmoras_backend.entity.Pedido.EstadoPedido;
import com.conmoras.conmoras_backend.entity.Producto;
import com.conmoras.conmoras_backend.repository.PedidoRepository;
import com.conmoras.conmoras_backend.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    // ── Listar todos ──────────────────────────────────────────────
    public List<PedidoDTO> listarTodos() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ── Buscar por ID ─────────────────────────────────────────────
    public PedidoDTO buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + id));
        return toDTO(pedido);
    }

    // ── Crear ─────────────────────────────────────────────────────
    public PedidoDTO crear(PedidoDTO dto) {
        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + dto.getProductoId()));

        // Validar stock disponible
        if (producto.getCantidad() < dto.getCantidad()) {
            throw new RuntimeException("Stock insuficiente. Disponible: " + producto.getCantidad());
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(dto.getCliente());
        pedido.setProducto(producto);
        pedido.setCantidad(dto.getCantidad());
        pedido.setEstado(EstadoPedido.PENDIENTE); // Siempre inicia en PENDIENTE

        return toDTO(pedidoRepository.save(pedido));
    }

    // ── Actualizar estado ─────────────────────────────────────────
    public PedidoDTO actualizarEstado(Long id, PedidoDTO dto) {
        Pedido existente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + id));

        existente.setEstado(dto.getEstado());
        return toDTO(pedidoRepository.save(existente));
    }

    // ── Eliminar ──────────────────────────────────────────────────
    public void eliminar(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido no encontrado: " + id);
        }
        pedidoRepository.deleteById(id);
    }

    // ── Mapper ────────────────────────────────────────────────────
    private PedidoDTO toDTO(Pedido p) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(p.getId());
        dto.setCliente(p.getCliente());
        dto.setProductoId(p.getProducto().getId());
        dto.setProductoNombre(p.getProducto().getNombre());
        dto.setCantidad(p.getCantidad());
        dto.setEstado(p.getEstado());
        return dto;
    }
}

