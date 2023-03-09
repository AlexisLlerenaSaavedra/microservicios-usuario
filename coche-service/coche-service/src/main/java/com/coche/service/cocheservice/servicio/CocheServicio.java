package com.coche.service.cocheservice.servicio;

import com.coche.service.cocheservice.entidades.Coche;
import com.coche.service.cocheservice.repositorio.CocheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocheServicio {
    @Autowired
    CocheRepository cocheRepository;

    public List<Coche> getAll(){
        return cocheRepository.findAll();
    }

    public Coche getCocheById(int id){
        return cocheRepository.findById(id).orElse(null);
    }
    public Coche save(Coche coche){
        Coche cocheGuardado=cocheRepository.save(coche);
        return cocheGuardado;
    }

    public List<Coche> byUsuarioId(int usuarioId){
        return cocheRepository.findByUsuarioId(usuarioId);
    }
}
