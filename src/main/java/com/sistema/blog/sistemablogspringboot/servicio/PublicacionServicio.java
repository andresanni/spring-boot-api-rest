package com.sistema.blog.sistemablogspringboot.servicio;

import java.util.List;

import com.sistema.blog.sistemablogspringboot.dto.PublicacionDTO;

public interface PublicacionServicio {
	
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
	public List<PublicacionDTO> obtenerTodasPublicaciones(int numeroPagina,int medidaPagina);
	public PublicacionDTO obtenerPublicacionPorId(Long id);
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO,Long id);
	public void eliminarPublicacion(Long id);
}
