package com.sistema.blog.sistemablogspringboot.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.sistemablogspringboot.dto.ComentarioDTO;
import com.sistema.blog.sistemablogspringboot.servicio.ComentarioServicio;

@RestController
@RequestMapping("/api/")
public class ComentarioControlador {

	@Autowired
	private ComentarioServicio comentarioServicio;

	@PostMapping("/publicaciones/{publicacionid}/comentarios")
	public ResponseEntity<ComentarioDTO> crearComentario(@PathVariable(value = "publicacionid") Long publicacionId,
			@RequestBody ComentarioDTO comentarioDto) {

		return new ResponseEntity<>(comentarioServicio.crearComentario(publicacionId, comentarioDto),
				HttpStatus.CREATED);
	}

	@GetMapping("/publicaciones/{publicacionid}/comentarios")
	public List<ComentarioDTO> obtenerComentariosPorPublicacionId(
			@PathVariable(value = "publicacionid") Long publicacionId) {

		return comentarioServicio.obtenerComentarioPorPublicacionId(publicacionId);
	}

	@GetMapping("/publicaciones/{publicacionid}/comentarios/{id}")
	public ResponseEntity<ComentarioDTO> obtenerComentarioPorId(
			@PathVariable(value = "publicacionid") Long publicacionId, @PathVariable(value = "id") Long comentarioId) {
		ComentarioDTO comentarioDTO = comentarioServicio.obtenerPorId(comentarioId, publicacionId);
		return new ResponseEntity<ComentarioDTO>(comentarioDTO, HttpStatus.OK);
	}

	@PutMapping("/publicaciones/{publicacionid}/comentarios/{id}")
	public ResponseEntity<ComentarioDTO> actualizarComentario(@PathVariable(value = "publicacionid") Long publicacionId,
			@PathVariable(value = "id") Long comentarioId, @RequestBody ComentarioDTO solicitudComentartio) {

		return new ResponseEntity<ComentarioDTO>(
				comentarioServicio.actualizarComentario(publicacionId, comentarioId, solicitudComentartio),
				HttpStatus.OK);
	}
	
	@DeleteMapping("/publicaciones/{publicacionid}/comentarios/{id}")
	public ResponseEntity<String> eliminarComentario(@PathVariable(value="publicacionid")Long publicacionId,
			@PathVariable(value="id") Long comentarioId){
		comentarioServicio.eliminarComentario(publicacionId, comentarioId);
		
		return new ResponseEntity<String>("Comentario eliminado con exito", HttpStatus.OK);
	}
}
