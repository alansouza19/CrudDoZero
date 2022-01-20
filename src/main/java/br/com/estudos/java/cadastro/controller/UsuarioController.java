package br.com.estudos.java.cadastro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.java.cadastro.model.Usuario;
import br.com.estudos.java.cadastro.model.UsuarioEntity;
import br.com.estudos.java.cadastro.repository.UsuarioRepository;
import br.com.estudos.java.cadastro.services.UsuarioService;

@RestController
@RequestMapping(value="/cadastro")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/usuario")
	public List<UsuarioEntity> listaUsuarios(){		
		return usuarioService.buscarTodos();
	}
	
	@PostMapping("/cadastroUsuario")
	public ResponseEntity<?> salvarUsuario(@RequestBody Usuario usuario) throws Exception {
		try {
			usuarioService.criar(usuario);
			return ResponseEntity.ok(usuarioService.criar(usuario));
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
		}
		 
	}

}