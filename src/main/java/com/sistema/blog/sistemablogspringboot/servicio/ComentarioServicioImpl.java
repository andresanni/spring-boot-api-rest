package com.sistema.blog.sistemablogspringboot.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.blog.sistemablogspringboot.dto.ComentarioDTO;

import com.sistema.blog.sistemablogspringboot.entidades.Comentario;
import com.sistema.blog.sistemablogspringboot.entidades.Publicacion;
import com.sistema.blog.sistemablogspringboot.excepciones.ResourceNotFoundException;
import com.sistema.blog.sistemablogspringboot.repositorio.ComentarioRepositorio;
import com.sistema.blog.sistemablogspringboot.repositorio.PublicacionRepositorio;

@Service
public class ComentarioServicioImpl implements ComentarioServicio {
	
	@Autowired
	private ComentarioRepositorio comentarioRepositorio;
	@Autowired
	private PublicacionRepositorio publicacionRepositorio;
	
	@Override
	public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO) {
		
		Comentario comentario = convertirDtoEnEntidad(comentarioDTO);
		Publicacion publicacion = publicacionRepositorio.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
		
		
		comentario.setPublicacion(publicacion);
		Comentario nuevoComentario = comentarioRepositorio.save(comentario);
		
		return convertirEntidadEnDto(nuevoComentario);
	}
	
	//Convertores de DTO  entidad y viceversa
	private ComentarioDTO convertirEntidadEnDto(Comentario entidad) {

		ComentarioDTO comentarioDTO = new ComentarioDTO();

		comentarioDTO.setId(entidad.getId());
		comentarioDTO.setCuerpo(entidad.getCuerpo());
		comentarioDTO.setEmail(entidad.getEmail());
		comentarioDTO.setNombre(entidad.getNombre());

		return comentarioDTO;
	}

	private Comentario convertirDtoEnEntidad(ComentarioDTO comentarioDTO) {

		Comentario comentario = new Comentario();

		comentario.setId(comentarioDTO.getId());
		comentario.setCuerpo(comentarioDTO.getCuerpo());
		comentario.setEmail(comentarioDTO.getEmail());
		comentario.setNombre(comentarioDTO.getNombre());

		return comentario;
	}
}
