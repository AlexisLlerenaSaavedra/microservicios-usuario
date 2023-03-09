package com.coche.service.cocheservice.controladores;

import com.coche.service.cocheservice.entidades.Coche;
import com.coche.service.cocheservice.servicio.CocheServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coche")
public class CocheController {
    @Autowired
    private CocheServicio cocheServicio;

    @GetMapping("")
    public ResponseEntity<List<Coche>> listarCoches(){
        List<Coche> cocheList=cocheServicio.getAll();
        if (cocheList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cocheList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Coche> obtenerCoche(@PathVariable int id){
        Coche coche=cocheServicio.getCocheById(id);
        if (coche.equals(null)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(coche);

    }

    @PostMapping("")
    public ResponseEntity<Coche> CocheUsuario(@RequestBody Coche coche){
        Coche cocheGuardado=cocheServicio.save(coche);
        return ResponseEntity.ok(cocheGuardado);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Coche>> listarChocheUsuarioById(@PathVariable int usuarioId){
        List<Coche> coches=cocheServicio.byUsuarioId(usuarioId);
        if (coches.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(coches);
    }




}
