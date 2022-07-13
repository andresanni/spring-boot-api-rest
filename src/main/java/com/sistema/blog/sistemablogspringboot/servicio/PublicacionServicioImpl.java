package com.sistema.blog.sistemablogspringboot.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.blog.sistemablogspringboot.dto.PublicacionDTO;
import com.sistema.blog.sistemablogspringboot.entidades.Publicacion;
import com.sistema.blog.sistemablogspringboot.repositorio.PublicacionRepositorio;

@Service
public class PublicacionServicioImpl implements PublicacionServicio{
	
	@Autowired
	private PublicacionRepositorio publicacionRepositorio;
	
	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
		
		//Convertimos DTO proveniente de la petición a entidad
		Publicacion publicacion = new Publicacion();
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());
		
		//Almacenamos el objeto el la BBDD
		Publicacion nuevaPublicacion = publicacionRepositorio.save(publicacion);
		
		//Convertimos entidad a DTO para enviar como respuesta
		PublicacionDTO publicacionRespuesta = new PublicacionDTO();
		publicacionRespuesta.setId(nuevaPublicacion.getId());
		publicacionRespuesta.setTitulo(nuevaPublicacion.getTitulo());
		publicacionRespuesta.setDescripcion(nuevaPublicacion.getDescripcion());
		publicacionRespuesta.setContenido(nuevaPublicacion.getContenido());
		
		return publicacionRespuesta;
	}

}
