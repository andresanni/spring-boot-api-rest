package com.sistema.blog.sistemablogspringboot.servicio;

import com.sistema.blog.sistemablogspringboot.dto.ComentarioDTO;

public interface ComentarioServicio {
	
	public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO);
	
}
