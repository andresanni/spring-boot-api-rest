package com.sistema.blog.sistemablogspringboot.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.sistemablogspringboot.entidades.Publicacion;

public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long>{

}
