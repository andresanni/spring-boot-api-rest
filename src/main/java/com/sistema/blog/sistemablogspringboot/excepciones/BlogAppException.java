package com.sistema.blog.sistemablogspringboot.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class BlogAppException extends RuntimeException {
	
	private static final long SerialVersionUID= 1L;
	private HttpStatus estado;
	private String mensaje;
	
	public BlogAppException(HttpStatus estado, String mensaje) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
		
	}
	
	public BlogAppException(HttpStatus estado, String mensaje, String mensaje1) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
		this.mensaje = mensaje1;
	}

	public BlogAppException() {
		super();
	}

	public HttpStatus getEstado() {
		return estado;
	}

	public void setEstado(HttpStatus estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
