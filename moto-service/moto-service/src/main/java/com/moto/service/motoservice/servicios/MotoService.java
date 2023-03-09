package com.moto.service.motoservice.servicios;

import com.moto.service.motoservice.controladores.MotoController;
import com.moto.service.motoservice.entidades.Moto;
import com.moto.service.motoservice.repositorios.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {
    @Autowired
    MotoRepository motoRepository;

    public List<Moto> getAll(){
        return motoRepository.findAll();
    }

    public Moto getMotoById(int id){
        return motoRepository.findById(id).orElse(null);
    }

    public Moto save(Moto coche){
        Moto motoGuardado=motoRepository.save(coche);
        return motoGuardado;
    }

    public List<Moto> byUsuarioId(int usuarioId){
        return motoRepository.findByUsuarioId(usuarioId);
    }







}
