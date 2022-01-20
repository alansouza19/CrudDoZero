package br.com.estudos.java.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.java.cadastro.model.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{


}
