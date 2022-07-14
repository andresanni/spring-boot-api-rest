package com.sistema.blog.sistemablogspringboot.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.blog.sistemablogspringboot.entidades.Comentario;

@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario, Long>{

}
