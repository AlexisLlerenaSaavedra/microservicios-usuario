package com.usuario.service.usuarioservice.feingclients;

import com.usuario.service.usuarioservice.modelos.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "moto-service",url = "http://localhost:8083")
@RequestMapping("/moto")
public interface MotoFeingClient {
    @PostMapping("")
    public Moto save(@RequestBody Moto moto);

    @GetMapping("/usuario/{usuarioId}")
    public List<Moto> listaDeMotos(@PathVariable int usuarioId);

}
