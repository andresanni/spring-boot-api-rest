package com.sistema.blog.sistemablogspringboot.servicio;

import java.util.List;

import com.sistema.blog.sistemablogspringboot.dto.ComentarioDTO;

public interface ComentarioServicio {
	
	public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO);
	public List<ComentarioDTO> obtenerComentarioPorPublicacionId(long publicacionid);
	public ComentarioDTO obtenerPorId(Long comentarioId, Long publicacionId);
	public ComentarioDTO actualizarComentario (Long publicacionId, Long comentarioId, ComentarioDTO solicitudComentario);
	public void eliminarComentario(Long publicacionId, Long comentarioId);
}
