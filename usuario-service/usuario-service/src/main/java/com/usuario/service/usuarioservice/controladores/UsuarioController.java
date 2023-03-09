package com.usuario.service.usuarioservice.controladores;

import com.usuario.service.usuarioservice.entidades.Usuario;
import com.usuario.service.usuarioservice.modelos.Coche;
import com.usuario.service.usuarioservice.modelos.Moto;
import com.usuario.service.usuarioservice.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        List<Usuario> usuarioList=usuarioService.getAll();
        if (usuarioList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
            return ResponseEntity.ok(usuarioList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable int id){
        Usuario usuario=usuarioService.getUsuarioById(id);
        if (usuario.equals(null)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);

    }
    @PostMapping("")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        Usuario usuarioGuardado=usuarioService.save(usuario);
        return ResponseEntity.ok(usuarioGuardado);
    }
    @GetMapping("/coches/{usuarioId}")
    public ResponseEntity<List<Coche>> listarCoches(@PathVariable int usuarioId){
        Usuario usuario=usuarioService.getUsuarioById(usuarioId);
        if (usuario.equals(null)){
            return ResponseEntity.notFound().build();
        }
        List<Coche> coches=usuarioService.getListCoche(usuarioId);
        return ResponseEntity.ok(coches);
    }

    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotos(@PathVariable int usuarioId){
        Usuario usuario=usuarioService.getUsuarioById(usuarioId);
        if (usuario.equals(null)){
            return ResponseEntity.notFound().build();
        }
        List<Moto> motos=usuarioService.getListMotos(usuarioId);
        return ResponseEntity.ok(motos);
    }
    @PostMapping("/coches/{usuarioId}")
    public ResponseEntity<Coche> guardarCoche(@PathVariable int usuarioId, @RequestBody Coche coche){
        Coche nuevoCoche=usuarioService.saveCoche(usuarioId,coche);
        return ResponseEntity.ok(nuevoCoche);
    }

    @PostMapping("/motos/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable int usuarioId,@RequestBody Moto moto){
        Moto nuevaMoto=usuarioService.saveMoto(usuarioId,moto);
        return ResponseEntity.ok(nuevaMoto);
    }

    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<?> listarTodosVehiculos(@PathVariable int usuarioId){
        return ResponseEntity.ok(usuarioService.getUsuarioCocheAndMotos(usuarioId));
    }
}
