package com.sistema.blog.sistemablogspringboot.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.sistemablogspringboot.dto.PublicacionDTO;
import com.sistema.blog.sistemablogspringboot.servicio.PublicacionServicio;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionControlador {
	
	@Autowired
	private PublicacionServicio publicacionServicio;
	
	@PostMapping("/crear")
	public ResponseEntity<PublicacionDTO> guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO){
		return new ResponseEntity<>(publicacionServicio.crearPublicacion(publicacionDTO),HttpStatus.CREATED);
	}
}
