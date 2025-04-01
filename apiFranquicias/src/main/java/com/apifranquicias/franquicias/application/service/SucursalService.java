package com.apifranquicias.franquicias.application.service;

import com.apifranquicias.franquicias.domain.model.Sucursal;
import com.apifranquicias.franquicias.domain.port.SucursalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SucursalService {

    private final SucursalRepository sucursalRepository;

    public Mono<Sucursal> crearSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public Mono<Sucursal> obtenerSucursal(Long id) {
        return sucursalRepository.findById(id);
    }

    public Flux<Sucursal> obtenerSucursalesPorFranquicia(Long franquiciaId){
        return sucursalRepository.findByFranquiciaId(franquiciaId);
    }

    public Flux<Sucursal> obtenerSucursales(){
        return  sucursalRepository.findAll();
    }

}
