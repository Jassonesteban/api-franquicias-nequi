package com.apifranquicias.franquicias.application.service;

import com.apifranquicias.franquicias.domain.model.Producto;
import com.apifranquicias.franquicias.domain.port.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public Mono<Producto> crearProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public Mono<Producto> obtenerProductoPorId(Long id){
        return productoRepository.findById(id);
    }

    public Flux<Producto> obtenerProductosPorSucursal(Long sucursalId){
        return productoRepository.findBySucursalId(sucursalId);
    }

    public Flux<Producto> getAll(){
        return productoRepository.findAll();
    }

    public Mono<Void> eliminarProductoSucursal(Long productoId, Long sucursalId){
        return productoRepository.findById(productoId)
                .flatMap(producto -> {
                    if(producto.getSucursalId().equals(sucursalId)){
                        return productoRepository.deleteById(productoId);
                    } else {
                        return  Mono.error(new IllegalArgumentException(("El producto no pertenece a la sucursal")));
                    }
                });
    }

    public Mono<Producto> actualizarStock(Long productoId, Integer cantidad) {
        return productoRepository.findById(productoId)
                .flatMap(producto -> {
                    producto.setStock(cantidad);
                    return productoRepository.save(producto);
                })
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Producto no encontrado")));
    }

}
