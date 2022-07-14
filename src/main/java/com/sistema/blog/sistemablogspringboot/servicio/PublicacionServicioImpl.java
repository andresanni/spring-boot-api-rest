package com.sistema.blog.sistemablogspringboot.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sistema.blog.sistemablogspringboot.dto.PublicacionDTO;
import com.sistema.blog.sistemablogspringboot.dto.PublicacionRespuesta;
import com.sistema.blog.sistemablogspringboot.entidades.Publicacion;
import com.sistema.blog.sistemablogspringboot.excepciones.ResourceNotFoundException;
import com.sistema.blog.sistemablogspringboot.repositorio.PublicacionRepositorio;

@Service
public class PublicacionServicioImpl implements PublicacionServicio {

	@Autowired
	private PublicacionRepositorio publicacionRepositorio;

	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {

		// Convertimos DTO proveniente de la petición a entidad
		Publicacion publicacion = convertirDtoEnEntidad(publicacionDTO);

		// Almacenamos el objeto el la BBDD
		Publicacion nuevaPublicacion = publicacionRepositorio.save(publicacion);

		// Convertimos entidad a DTO para enviar como respuesta
		PublicacionDTO publicacionRespuesta = convertirEntidadEnDto(nuevaPublicacion);

		return publicacionRespuesta;
	}

	@Override
	public PublicacionRespuesta obtenerTodasPublicaciones(int numeroPagina,int medidaPagina,String ordenarPor) {
		
		Pageable pageable = PageRequest.of(numeroPagina, medidaPagina,Sort.by(ordenarPor));
		Page<Publicacion> publicaciones = publicacionRepositorio.findAll(pageable);
		
		List<Publicacion> publicacionesLista = publicaciones.getContent();
		
		List <PublicacionDTO> listaPublicaciones =  publicacionesLista.stream().map(publicacion -> convertirEntidadEnDto(publicacion))
				.collect(Collectors.toList());
		PublicacionRespuesta publicacionRespuesta = new PublicacionRespuesta();
		publicacionRespuesta.setContenido(listaPublicaciones);
		publicacionRespuesta.setNumeroPagina(publicaciones.getNumber());
		publicacionRespuesta.setMedidaPagina(publicaciones.getSize());
		publicacionRespuesta.setTotalElementos(publicaciones.getTotalElements());
		publicacionRespuesta.setTotalPaginas(publicaciones.getTotalPages());
		publicacionRespuesta.setUltimo(publicaciones.isLast());
		
		return publicacionRespuesta;
	}

	// Metodos para mapear DTOS en entidades y viceversa para usar en todos los
	// servicios
	private PublicacionDTO convertirEntidadEnDto(Publicacion entidad) {

		PublicacionDTO publicacionDTO = new PublicacionDTO();

		publicacionDTO.setId(entidad.getId());
		publicacionDTO.setTitulo(entidad.getTitulo());
		publicacionDTO.setDescripcion(entidad.getDescripcion());
		publicacionDTO.setContenido(entidad.getContenido());

		return publicacionDTO;
	}

	private Publicacion convertirDtoEnEntidad(PublicacionDTO publicacionDTO) {

		Publicacion publicacion = new Publicacion();

		publicacion.setId(publicacionDTO.getId());
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());

		return publicacion;
	}

	@Override
	public PublicacionDTO obtenerPublicacionPorId(Long id) {
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

		return convertirEntidadEnDto(publicacion);
	}

	@Override
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id) {
		
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
		
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());
		
		Publicacion publicacionActualizada = publicacionRepositorio.save(publicacion);
		
		return convertirEntidadEnDto(publicacionActualizada);
	}

	@Override
	public void eliminarPublicacion(Long id) {
		
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
		
		publicacionRepositorio.delete(publicacion);
	}
}
