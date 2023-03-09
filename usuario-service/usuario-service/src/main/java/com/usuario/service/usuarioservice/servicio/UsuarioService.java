package com.usuario.service.usuarioservice.servicio;

import com.usuario.service.usuarioservice.configuracion.RestTemplateConfig;
import com.usuario.service.usuarioservice.entidades.Usuario;
import com.usuario.service.usuarioservice.feingclients.CocheFeingClient;
import com.usuario.service.usuarioservice.feingclients.MotoFeingClient;
import com.usuario.service.usuarioservice.modelos.Coche;
import com.usuario.service.usuarioservice.modelos.Moto;
import com.usuario.service.usuarioservice.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private CocheFeingClient cocheFeingClient;

    @Autowired
    private MotoFeingClient motoFeingClient;

    public List<Coche> getListCoche(int usuarioId){
        List<Coche> coches=restTemplate.getForObject("http://localhost:8082/coche/usuario/"+usuarioId, List.class);
        return coches;
    }

    public List<Moto> getListMotos(int usuarioId){
        List<Moto> motos=restTemplate.getForObject("http://localhost:8083/moto/usuario/"+usuarioId, List.class);
        return motos;
    }

    public Coche saveCoche(int usuarioId,Coche coche){
        coche.setUsuarioId(usuarioId);
        Coche nuevoCoche=cocheFeingClient.save(coche);
        return nuevoCoche;
    }
    public Moto saveMoto(int usuarioId,Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto nuevaMoto=motoFeingClient.save(moto);
        return nuevaMoto;
    }
    public Map<String,Object> getUsuarioCocheAndMotos(int usuarioId){
        Map<String,Object> resultado=new HashMap<>();
        Usuario usuario=usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario.equals(null)){
            resultado.put("Mensaje","El usuario no existe");
        }
        resultado.put("Usuario",usuario);
        List<Coche> listaCoche=cocheFeingClient.listaDeCoches(usuarioId);
        if (listaCoche.isEmpty()){
            resultado.put("Coches","Este usuario no tiene coches");
        }
        resultado.put("Coches",listaCoche);

        List<Moto> listaMoto=motoFeingClient.listaDeMotos(usuarioId);
        if (listaMoto.isEmpty()){
            resultado.put("Motos","Este ususrio no tiene motos");
        }
        resultado.put("Moto",listaMoto);
        return resultado;
    }

    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id){
        return usuarioRepository.findById(id).orElse(null);
    }
    public Usuario save(Usuario usuario){
        Usuario usuarioGuardado=usuarioRepository.save(usuario);
        return usuarioGuardado;
    }

}
