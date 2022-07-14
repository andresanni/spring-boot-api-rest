package com.sistema.blog.sistemablogspringboot.servicio;

import java.util.List;

import com.sistema.blog.sistemablogspringboot.dto.PublicacionDTO;
import com.sistema.blog.sistemablogspringboot.dto.PublicacionRespuesta;

public interface PublicacionServicio {
	
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
	public PublicacionRespuesta obtenerTodasPublicaciones(int numeroPagina,int medidaPagina,String ordenarPor);
	public PublicacionDTO obtenerPublicacionPorId(Long id);
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO,Long id);
	public void eliminarPublicacion(Long id);
}
