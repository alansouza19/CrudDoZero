package br.com.estudos.java.cadastro.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.estudos.java.cadastro.model.EnderecoCep;
import br.com.estudos.java.cadastro.model.Usuario;
import br.com.estudos.java.cadastro.model.UsuarioEntity;
import br.com.estudos.java.cadastro.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public List<UsuarioEntity> listaUsuarios(){		
		return usuarioRepository.findAll();
	}
	
	public Usuario criar(Usuario usuario) throws Exception {
		validaCep(usuario.getCep().trim()); 
		
		EnderecoCep enderCep = buscaCep(usuario.getCep());
			
		
		
		UsuarioEntity user = new UsuarioEntity();
		
		user.setNome(usuario.getNome());
		user.setDocumento(usuario.getDocumento());
		user.setEstado(enderCep.getUf());
		user.setCidade(enderCep.getLocalidade());
		user.setBairro(enderCep.getBairro());
		
		usuarioRepository.save(user);
		return usuario;
	}
	public LocalDate converterDataFromString(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		  LocalDate localDate = LocalDate.parse(data, formatter);
		  return localDate;
	}

	
	public List<UsuarioEntity> buscarTodos() {
		return usuarioRepository.findAll();
	}

	public void validaCep(String enderCep) throws Exception {
		if(enderCep.equals("")||
				enderCep.equals("00000000")|| enderCep.equals("11111111")||
				enderCep.equals("22222222")|| enderCep.equals("33333333")||
				enderCep.equals("44444444")|| enderCep.equals("55555555")||
				enderCep.equals("66666666")|| enderCep.equals("77777777")||
				enderCep.equals("88888888")|| enderCep.equals("99999999")||
				enderCep.length() != 8
				) {
			throw new Exception("Informe um CEP válido");
		}
	}
	public EnderecoCep buscaCep(String cep) throws Exception {
		String url = "https://viacep.com.br/ws/" + cep + "/json/";
		RestTemplate rest = new RestTemplate();
		EnderecoCep enderecoCep = rest.getForObject(url, EnderecoCep.class);
		
		if(enderecoCep.isErro()) {
			throw new Exception("Sr.Usuário por gentileza informe um CEP válido");
		}
		
		return enderecoCep;
	}
}
