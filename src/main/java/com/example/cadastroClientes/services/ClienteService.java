package com.example.cadastroClientes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cadastroClientes.model.Cliente;
import com.example.cadastroClientes.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public Cliente salvarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public List<Cliente> findAll() {
		List<Cliente> lstClientes = clienteRepository.findAll();

		return lstClientes;
	}

	public Cliente findById(long id) {
		Optional<Cliente> clienteOp = clienteRepository.findById(id);
		Cliente cliente = null;
		if (clienteOp.isPresent()) {
			cliente = clienteOp.get();
		}  
		return cliente;
	}
	
	public Cliente update(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente delete(long id) {
		Optional<Cliente> clienteOp = clienteRepository.findById(id);
		Cliente cliente = null;
		if (clienteOp.isPresent()) {
			cliente = clienteOp.get();
			clienteRepository.delete(clienteOp.get());
		}
		return cliente;

	}
	
}