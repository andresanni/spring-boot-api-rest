package com.sistema.blog.sistemablogspringboot.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.blog.sistemablogspringboot.entidades.Publicacion;

@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long>{

}
