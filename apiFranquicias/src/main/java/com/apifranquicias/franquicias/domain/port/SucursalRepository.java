package com.apifranquicias.franquicias.domain.port;

import com.apifranquicias.franquicias.domain.model.Sucursal;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SucursalRepository extends ReactiveCrudRepository<Sucursal, Long> {
    Mono<Sucursal> findByNombre(String nombre);
    Flux<Sucursal> findByFranquiciaId(Long franquiciaId);
}
