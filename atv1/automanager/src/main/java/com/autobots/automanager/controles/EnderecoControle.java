package com.autobots.automanager.controles;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.EnderecoAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {
	@Autowired 
	private ClienteRepositorio repositorioCliente;
	
	@Autowired 
	private EnderecoRepositorio repositorioEndereco;
	
	@GetMapping("/endereco/{clienteId}")
	public Endereco obterEnderecoCliente(@PathVariable Long clienteId) {
	    Cliente cliente = repositorioCliente.getById(clienteId);

	    return cliente.getEndereco();
	}
	
	@PutMapping("/atualizar/{enderecoId}")
    public void atualizarEndereco(@PathVariable Long enderecoId, @RequestBody Endereco novoEndereco) {
        Endereco endereco = repositorioEndereco.getById(enderecoId);
        EnderecoAtualizador atualizador = new EnderecoAtualizador();
        atualizador.atualizar(endereco, novoEndereco);
        
        repositorioEndereco.save(endereco);
    }
	
}


