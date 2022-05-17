package com.example.cadastroClientes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastroClientes.model.Cliente;
import com.example.cadastroClientes.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/cliente")
@CrossOrigin(origins = "*")
@Api(value="Cadastro Cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@ApiOperation(value="Lista de clientes")
	@GetMapping("/lstAll")
	public ResponseEntity<List<Cliente>> getAllClientes() {
		List<Cliente> lstClientes = clienteService.findAll();
		if (lstClientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Cliente>>(lstClientes, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value="Cadastrar novo cliente")
	@PostMapping("/save")
	public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
		return new ResponseEntity<Cliente>(clienteService.salvarCliente(cliente), HttpStatus.CREATED);
	}
	
	@GetMapping("/clientes/{id}")
	@ApiOperation(value="Retorna Cliente")
	public ResponseEntity<Cliente> getOneCliente(@PathVariable(value = "id") long id) {
		Cliente cliente = clienteService.findById(id);
		if (cliente != null) {
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/clientes")
	@ApiOperation(value="Atualizar clientes")
	public ResponseEntity<Cliente> atualizarCliente(@RequestBody Cliente cliente) {
		Cliente clientePut = clienteService.update(cliente);
		cliente.setId(clientePut.getId());
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@DeleteMapping("/clientes/{id}")
	@ApiOperation(value="Apagar cliente")
	public ResponseEntity<Cliente> apagarCliente(@PathVariable(value = "id") long id) {
		Cliente cliente = clienteService.delete(id);
		if (cliente != null) {
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
