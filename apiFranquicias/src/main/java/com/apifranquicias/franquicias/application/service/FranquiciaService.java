package com.apifranquicias.franquicias.application.service;

import com.apifranquicias.franquicias.domain.model.Franquicia;
import com.apifranquicias.franquicias.domain.model.ProductoDTO;
import com.apifranquicias.franquicias.domain.port.FranquiciaRepository;
import com.apifranquicias.franquicias.domain.port.ProductoRepository;
import com.apifranquicias.franquicias.domain.port.SucursalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranquiciaService {

    private final FranquiciaRepository franquiciaRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;

    public Mono<Franquicia> crearFranquicia(Franquicia franquicia){
        return franquiciaRepository.save(franquicia);
    }

    public Mono<Franquicia> obtenerFranquicia(Long id){
        return franquiciaRepository.findById(id);
    }

    public Flux<Franquicia> obtenerFranquicias(){
        return franquiciaRepository.findAll();
    }

    public Flux<ProductoDTO> obtenerProductoConMasStockPorSucursal(Long idFranquicia) {
        return sucursalRepository.findByFranquiciaId(idFranquicia)
                .flatMap(sucursal ->
                        productoRepository.findTopBySucursalIdOrderByStockDesc(sucursal.getId())
                                .map(producto -> new ProductoDTO(producto, sucursal))
                );
    }

}
