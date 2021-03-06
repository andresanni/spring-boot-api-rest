package com.sistema.blog.sistemablogspringboot.controlador;



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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.sistemablogspringboot.dto.PublicacionDTO;
import com.sistema.blog.sistemablogspringboot.dto.PublicacionRespuesta;
import com.sistema.blog.sistemablogspringboot.servicio.PublicacionServicio;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionControlador {

	@Autowired
	private PublicacionServicio publicacionServicio;

	@PostMapping("/crear")
	public ResponseEntity<PublicacionDTO> guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO) {
		return new ResponseEntity<>(publicacionServicio.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public PublicacionRespuesta getAll(@RequestParam(value="pageNum",defaultValue="0",required=false)int pageNum,
									   @RequestParam(value="pageSize",defaultValue="10",required=false)int pageSize,
									   @RequestParam(value="sortBy",defaultValue="id",required=false)String ordenarPor
									   ) {
		return publicacionServicio.obtenerTodasPublicaciones(pageNum,pageSize,ordenarPor);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PublicacionDTO> getById(@PathVariable Long id) {

		return new ResponseEntity<>(publicacionServicio.obtenerPublicacionPorId(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PublicacionDTO> actualizaPublicacion(@RequestBody PublicacionDTO publicacionDTO,
			@PathVariable Long id) {

		return new ResponseEntity<>(publicacionServicio.actualizarPublicacion(publicacionDTO, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarPublicacion(@PathVariable Long id) {
		publicacionServicio.eliminarPublicacion(id);
		return new ResponseEntity<String>("Publicacion Eliminada", HttpStatus.OK);
	}
}
