package com.apifranquicias.franquicias.api.controller;

import com.apifranquicias.franquicias.application.service.FranquiciaService;
import com.apifranquicias.franquicias.domain.model.Franquicia;
import com.apifranquicias.franquicias.domain.model.ProductoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/franquicias")
@RequiredArgsConstructor
public class FranquiciaController {

    private final FranquiciaService franquiciaService;

    @PostMapping
    public Mono<ResponseEntity<Franquicia>> agregarFranquicia(@RequestBody Franquicia franquicia){
        return franquiciaService.crearFranquicia(franquicia)
                .map(ResponseEntity::ok);
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<Franquicia>>> getAll(){
        return franquiciaService.obtenerFranquicias()
                .collectList()
                .map(franquicias -> ResponseEntity.ok().body(Flux.fromIterable(franquicias)));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Franquicia>> obtenerFranquicia(@PathVariable Long id){
        return franquiciaService.obtenerFranquicia(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/nombre")
    public Mono<Franquicia> actualizarNombreFranquicia(@PathVariable Long id, @RequestBody String nuevoNombre) {
        return franquiciaService.obtenerFranquicia(id)
                .flatMap(franquicia -> {
                    franquicia.setNombre(nuevoNombre);
                    return franquiciaService.crearFranquicia(franquicia);
                });
    }

    @GetMapping("/{idFranquicia}/productoMaxStock")
    public Flux<ProductoDTO> obtenerProductoConMasStockPorSucursal(@PathVariable Long idFranquicia) {
        return franquiciaService.obtenerProductoConMasStockPorSucursal(idFranquicia);
    }


}
