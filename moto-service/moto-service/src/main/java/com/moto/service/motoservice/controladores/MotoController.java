package com.moto.service.motoservice.controladores;

import com.moto.service.motoservice.entidades.Moto;
import com.moto.service.motoservice.servicios.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoController {
    @Autowired
    MotoService motoService;

    @GetMapping("")
    public ResponseEntity<List<Moto>> listarMotos(){
        List<Moto> motoList=motoService.getAll();
        if (motoList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motoList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> obtenerMoto(@PathVariable int id){
        Moto moto=motoService.getMotoById(id);
        if (moto.equals(null)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moto);

    }

    @PostMapping("")
    public ResponseEntity<Moto> CocheUsuario(@RequestBody Moto moto){
        Moto motoGuardado=motoService.save(moto);
        return ResponseEntity.ok(motoGuardado);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> listarChocheUsuarioById(@PathVariable int usuarioId){
        List<Moto> motos=motoService.byUsuarioId(usuarioId);
        if (motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }


}
