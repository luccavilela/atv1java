package com.autobots.automanager.controles;

import java.util.List;

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

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {
	@Autowired 
	private ClienteRepositorio repositorioCliente;
	
	@GetMapping("/endereco/{clienteId}")
	public Endereco obterEnderecoCliente(@PathVariable Long clienteId) {
	    Cliente cliente = repositorioCliente.getById(clienteId);

	    return cliente.getEndereco();
	}
	
	@PutMapping("/atualizar/{clienteId}")
    public void atualizarEndereco(@PathVariable Long clienteId, @RequestBody Endereco novoEndereco) {
        Cliente cliente = repositorioCliente.getById(clienteId);
        EnderecoAtualizador atualizador = new EnderecoAtualizador();
        atualizador.atualizar(cliente.getEndereco(), novoEndereco);
        repositorioCliente.save(cliente);
        
    }
	
}


