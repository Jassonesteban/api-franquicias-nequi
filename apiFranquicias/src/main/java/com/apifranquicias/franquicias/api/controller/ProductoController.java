package com.apifranquicias.franquicias.api.controller;

import com.apifranquicias.franquicias.application.service.ProductoService;
import com.apifranquicias.franquicias.domain.model.Franquicia;
import com.apifranquicias.franquicias.domain.model.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public Mono<ResponseEntity<Producto>> agregarProducto(@RequestBody Producto producto){
        return productoService.crearProducto(producto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/sucursal/{sucursalId}")
    public Flux<Producto> obtenerProductosPorSucursal(@PathVariable Long sucursalId){
        return productoService.obtenerProductosPorSucursal(sucursalId);
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<Producto>>> getAll(){
        return productoService.getAll()
                .collectList()
                .map(productos -> ResponseEntity.ok().body(Flux.fromIterable(productos)));
    }

    @DeleteMapping("/{productoId}/sucursal/{sucursalId}")
    public Mono<ResponseEntity<Object>> eliminarProductoPorSucursal(@PathVariable Long productoId, @PathVariable Long sucursalId){
        return productoService.eliminarProductoSucursal(productoId, sucursalId)
                .then(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }

    @PutMapping("/{id}/nombre")
    public Mono<Producto> actualizarNombreProducto(@PathVariable Long id, @RequestBody String nuevoNombre) {
        return productoService.obtenerProductoPorId(id)
                .flatMap(producto -> {
                    producto.setNombre(nuevoNombre);
                    return productoService.crearProducto(producto);
                });
    }

    @PutMapping("/{productoId}/stock")
    public Mono<ResponseEntity<Producto>> actualizarStockProducto(@PathVariable Long productoId, @RequestBody Integer cantidad) {
        return productoService.actualizarStock(productoId, cantidad)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }



}
