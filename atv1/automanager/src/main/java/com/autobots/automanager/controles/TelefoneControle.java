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
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {
	@Autowired 
	private ClienteRepositorio repositorioCliente;
	
	@Autowired
	private TelefoneRepositorio repositorioTelefone;
	
	
	
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

	@PutMapping("/atualizar/{telefoneId}")
	public void atualizarTelefone(@PathVariable Long telefoneId, @RequestBody Telefone novoTelefone) {
		Telefone telefone = repositorioTelefone.getById(telefoneId);
		TelefoneAtualizador atualizador = new TelefoneAtualizador();
		atualizador.atualizar(telefone, novoTelefone);
	    
	    
	    repositorioTelefone.save(telefone);
	}
	
	
	@DeleteMapping("/excluir/{clienteId}/{telefoneId}")
	public void deletarTelefone(@PathVariable Long clienteId, @PathVariable Long telefoneId) {
	    Cliente cliente = repositorioCliente.getById(clienteId);
	    
	    List<Telefone> telefones = cliente.getTelefones();
	    telefones.removeIf(telefone -> telefone.getId().equals(telefoneId));
	    
	    repositorioCliente.save(cliente);
	}
	
}
