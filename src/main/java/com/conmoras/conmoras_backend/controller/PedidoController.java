package com.conmoras.conmoras_backend.controller;

import com.conmoras.conmoras_backend.dto.PedidoDTO;
import com.conmoras.conmoras_backend.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
@Tag(name = "Pedidos", description = "Gestión de pedidos de clientes")
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Listar todos los pedidos")
    public ResponseEntity<List<PedidoDTO>> listar() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo pedido")
    public ResponseEntity<PedidoDTO> crear(@RequestBody PedidoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.crear(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar estado del pedido")
    public ResponseEntity<PedidoDTO> actualizarEstado(
            @PathVariable Long id,
            @RequestBody PedidoDTO dto) {
        return ResponseEntity.ok(pedidoService.actualizarEstado(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pedido")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
