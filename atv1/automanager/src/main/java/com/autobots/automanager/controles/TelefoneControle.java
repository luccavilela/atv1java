package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {
	@Autowired 
	private ClienteRepositorio repositorioCliente;
	
	@PostMapping("/adicionar/{clienteId}")
    public void adicionarTelefone(@PathVariable Long clienteId, @RequestBody Telefone telefone) {
        Cliente cliente = repositorioCliente.getById(clienteId);
        		
        cliente.getTelefones().add(telefone);
        
        repositorioCliente.save(cliente);
    }
	
	@GetMapping("/telefones/{clienteId}")
	public List<Telefone> obterTelefonesCliente(@PathVariable Long clienteId) {
	    Cliente cliente = repositorioCliente.getById(clienteId);

	    return cliente.getTelefones();

	}
	
	@PutMapping("/atualizar/{clienteId}/{telefoneId}")
	public void atualizarTelefone(@PathVariable Long clienteId, @PathVariable Long telefoneId, @RequestBody Telefone novoTelefone) {
		Cliente cliente = repositorioCliente.getById(clienteId);
		
	    List<Telefone> telefones = cliente.getTelefones();
	    for (Telefone telefone : telefones) {
	    	
	        if (telefone.getId().equals(telefoneId)) {
	            telefone.setDdd(novoTelefone.getDdd());
	            telefone.setNumero(novoTelefone.getNumero());
	            
	            break;
	        }
	    }
	    
	    repositorioCliente.save(cliente);
	}
	
	@DeleteMapping("/excluir/{clienteId}/{telefoneId}")
	public void deletarTelefone(@PathVariable Long clienteId, @PathVariable Long telefoneId) {
	    Cliente cliente = repositorioCliente.getById(clienteId);
	    
	    List<Telefone> telefones = cliente.getTelefones();
	    telefones.removeIf(telefone -> telefone.getId().equals(telefoneId));
	    
	    repositorioCliente.save(cliente);
	}
	
}
