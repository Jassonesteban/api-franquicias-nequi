package com.apifranquicias.franquicias.api.controller;

import com.apifranquicias.franquicias.application.service.SucursalService;
import com.apifranquicias.franquicias.domain.model.Franquicia;
import com.apifranquicias.franquicias.domain.model.Sucursal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sucursales")
@RequiredArgsConstructor
public class SucursalController {

    private final SucursalService sucursalService;

    @PostMapping
    public Mono<ResponseEntity<Sucursal>> agregarSucursal(@RequestBody Sucursal sucursal){
        return sucursalService.crearSucursal(sucursal)
                .map(ResponseEntity::ok);
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<Sucursal>>> getAll(){
        return sucursalService.obtenerSucursales()
                .collectList()
                .map(sucursal -> ResponseEntity.ok().body(Flux.fromIterable(sucursal)));
    }

    @GetMapping("/franquicia/{franquiciaId}")
    public Flux<Sucursal> obtenerSucursalesPorFranquicia(@PathVariable Long franquiciaId){
        return sucursalService.obtenerSucursalesPorFranquicia(franquiciaId);
    }

    @PutMapping("/{id}/nombre")
    public Mono<Sucursal> actualizarNombreSucursal(@PathVariable Long id, @RequestBody String nuevoNombre) {
        return sucursalService.obtenerSucursal(id)
                .flatMap(sucursal -> {
                    sucursal.setNombre(nuevoNombre);
                    return sucursalService.crearSucursal(sucursal);
                });
    }

}
