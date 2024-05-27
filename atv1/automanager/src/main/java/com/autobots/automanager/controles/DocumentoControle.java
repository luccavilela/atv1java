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
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@RestController
@RequestMapping("/documento")
public class DocumentoControle {
	@Autowired 
	private ClienteRepositorio repositorioCliente;
	
	@PostMapping("/adicionar/{clienteId}")
    public void adicionarDocumento(@PathVariable Long clienteId, @RequestBody Documento documento) {
        Cliente cliente = repositorioCliente.getById(clienteId);
        		
        cliente.getDocumentos().add(documento);
        
        repositorioCliente.save(cliente);
    }
	
	@GetMapping("/documentos/{clienteId}")
	public List<Documento> obterDocumentosCliente(@PathVariable Long clienteId) {
	    Cliente cliente = repositorioCliente.getById(clienteId);

	    return cliente.getDocumentos();

	}
	
	@PutMapping("/atualizar/{clienteId}/{documentoId}")
	public void atualizarDocumento(@PathVariable Long clienteId, @PathVariable Long documentoId, @RequestBody Documento novoDocumento) {
		Cliente cliente = repositorioCliente.getById(clienteId);
		
	    List<Documento> documentos = cliente.getDocumentos();
	    for (Documento documento : documentos) {
	    	
	        if (documento.getId().equals(documentoId)) {
	        	documento.setTipo(novoDocumento.getTipo());
	            documento.setNumero(novoDocumento.getNumero());
	            
	            
	            break;
	        }
	    }
	    
	    repositorioCliente.save(cliente);
	}
	
	@DeleteMapping("/excluir/{clienteId}/{documentoId}")
	public void deletarDocumento(@PathVariable Long clienteId, @PathVariable Long documentoId) {
	    Cliente cliente = repositorioCliente.getById(clienteId);
	    
	    List<Documento> documentos = cliente.getDocumentos();
	    documentos.removeIf(documento -> documento.getId().equals(documentoId));
	    
	    repositorioCliente.save(cliente);
	}
}
