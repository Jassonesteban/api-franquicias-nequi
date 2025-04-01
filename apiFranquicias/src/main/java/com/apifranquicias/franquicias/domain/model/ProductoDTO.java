package com.apifranquicias.franquicias.domain.model;

public class ProductoDTO {

    private String nombreProducto;
    private int stock;
    private String nombreSucursal;

    public ProductoDTO(Producto producto, Sucursal sucursal) {
        this.nombreProducto = producto.getNombre();
        this.stock = producto.getStock();
        this.nombreSucursal = sucursal.getNombre();
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }
}
