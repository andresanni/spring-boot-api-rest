package com.sistema.blog.sistemablogspringboot.servicio;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sistema.blog.sistemablogspringboot.dto.ComentarioDTO;

import com.sistema.blog.sistemablogspringboot.entidades.Comentario;
import com.sistema.blog.sistemablogspringboot.entidades.Publicacion;
import com.sistema.blog.sistemablogspringboot.excepciones.BlogAppException;
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

	@Override
	public List<ComentarioDTO> obtenerComentarioPorPublicacionId(long publicacionid) {
	
		List<Comentario> comentarios = comentarioRepositorio.findByPublicacionId(publicacionid);
		
		return comentarios.stream().map(comentario -> convertirEntidadEnDto(comentario)).collect(Collectors.toList());
	}

	@Override
	public ComentarioDTO obtenerPorId(Long comentarioId, Long publicacionId) {
		
		//Primero obetenemos el comentario y la publicacion a traves de su id y lanzando la excepcion en caso de que no existan
		Comentario comentario =comentarioRepositorio.findById(comentarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));
		Publicacion publicacion = publicacionRepositorio.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
		
		//Ahora validamos que el comentario corresponda a esa publicacion, ya que puede existir pero no pertenecer a la publicacion que nos trajimos
		if(!publicacion.getId().equals(comentario.getPublicacion().getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST,"El comentario indicado no pertenece a la publicacion indicada");
		}
		
		return convertirEntidadEnDto(comentario);
	}
	
	
}
