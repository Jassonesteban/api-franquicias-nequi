package com.apifranquicias.franquicias.domain.port;

import com.apifranquicias.franquicias.domain.model.Producto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductoRepository extends ReactiveCrudRepository<Producto, Long> {
    Flux<Producto> findBySucursalId(Long sucursalId);
    Mono<Producto> findByNombre(String nombre);
    Mono<Producto> findTopBySucursalIdOrderByStockDesc(Long sucursalId);
}
