package com.sistema.blog.sistemablogspringboot.entidades;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "comentarios")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cuerpo;
	private String email;
	private String nombre;

	@ManyToOne(targetEntity = Publicacion.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "publicacion_id", nullable = false)
	@JsonBackReference
	private Publicacion publicacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public Comentario(Long id, String cuerpo, String email, String nombre, Publicacion publicacion) {
		super();
		this.id = id;
		this.cuerpo = cuerpo;
		this.email = email;
		this.nombre = nombre;
		this.publicacion = publicacion;
	}

	public Comentario() {
		super();
	}

}
