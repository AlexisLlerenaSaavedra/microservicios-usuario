package com.usuario.service.usuarioservice.feingclients;

import com.usuario.service.usuarioservice.modelos.Coche;
import com.usuario.service.usuarioservice.modelos.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "coche-service",url = "http://localhost:8082")
@RequestMapping("/coche")
public interface CocheFeingClient {
    @PostMapping("")
    public Coche save(@RequestBody Coche coche);

    @GetMapping("/usuario/{usuarioId}")
    public List<Coche> listaDeCoches(@PathVariable int usuarioId);
}
