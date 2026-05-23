package com.conmoras.conmoras_backend.dto;

import com.conmoras.conmoras_backend.entity.Pedido.EstadoPedido;

public class PedidoDTO {
    private Long id;
    private String cliente;
    private Long productoId;
    private String productoNombre;
    private Integer cantidad;
    private EstadoPedido estado;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }

    public String getProductoNombre() { return productoNombre; }
    public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }
}