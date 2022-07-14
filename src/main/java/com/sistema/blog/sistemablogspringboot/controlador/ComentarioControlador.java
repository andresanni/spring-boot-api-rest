package com.sistema.blog.sistemablogspringboot.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<ComentarioDTO> crearComentario(@PathVariable(value ="publicacionid") Long publicacionId,
											@RequestBody ComentarioDTO comentarioDto){
		
		return new ResponseEntity<>(comentarioServicio.crearComentario(publicacionId, comentarioDto),HttpStatus.CREATED);
	}
}
