package com.picpay.rh.controller;

import com.picpay.rh.model.Funcionario;
import com.picpay.rh.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController implements FuncionarioSwagger {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Funcionario> cadastrar(@Valid @RequestBody Funcionario funcionario) {
        Funcionario funcionarioSalvo = funcionarioService.addFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Funcionario>> consultarTodos() {
        List<Funcionario> lista = funcionarioService.getFuncionarios();
        return ResponseEntity.ok(lista);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> consultarPorId(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.getFuncionarioById(id);
        return ResponseEntity.ok(funcionario);
    }

    @Override
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Funcionario>> consultarPorStatus(@PathVariable String status) {
        List<Funcionario> lista = funcionarioService.getFuncionariosByStatus(status);
        return ResponseEntity.ok(lista);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@Valid @PathVariable Long id, @RequestBody Funcionario funcionario) {
        Funcionario funcionarioAtualizado = funcionarioService.patchFuncionario(id, funcionario);
        return ResponseEntity.ok(funcionarioAtualizado);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deleteFuncionario(id);
        return ResponseEntity.noContent().build();
    }

}
