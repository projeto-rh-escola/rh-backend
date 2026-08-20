package com.picpay.rh.service;

import com.picpay.rh.exception.DadoDuplicadoException;
import com.picpay.rh.exception.FuncionarioNaoEncontradoException;
import com.picpay.rh.model.Funcionario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FuncionarioService {

    private ArrayList<Funcionario> funcionarios = new ArrayList<>();

    public FuncionarioService() {
        this.funcionarios = new ArrayList<>();
    }

    public Funcionario addFuncionario(Funcionario novoFuncionario) {
        validarNomeUnico(novoFuncionario.getNome());
        validarEmailUnico(novoFuncionario.getEmail());
        validarTelefoneUnico(novoFuncionario.getTelefone());
        novoFuncionario.setId(gerarId());
        funcionarios.add(novoFuncionario);
        return novoFuncionario;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public Funcionario getFuncionarioById(Long id) {
        return funcionarios.stream()
                .filter(funcionario -> funcionario.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new FuncionarioNaoEncontradoException(id));
    }

    public ArrayList<Funcionario> getFuncionariosByStatus(String status) {
        return funcionarios.stream()
                .filter(funcionario -> funcionario.getStatus().toString().equalsIgnoreCase(status))
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }

    public Funcionario patchFuncionario(Long id, Funcionario funcionario) {
        if (funcionario.getId() != null && !funcionario.getId().equals(id)) {
            throw new IllegalArgumentException("O ID do funcionário não pode ser alterado.");
        }

        Funcionario funcionarioExistente = getFuncionarioById(id);
        if (funcionario.getNome() != null) {
            validarNomeUnico(funcionario.getNome());
            funcionarioExistente.setNome(funcionario.getNome());
        }
        if (funcionario.getEmail() != null) {
            validarEmailUnico(funcionario.getEmail());
            funcionarioExistente.setEmail(funcionario.getEmail());
        }
        if (funcionario.getTelefone() != null) {
            validarTelefoneUnico(funcionario.getTelefone());
            funcionarioExistente.setTelefone(funcionario.getTelefone());
        }
        if (funcionario.getCargo() != null) {
            funcionarioExistente.setCargo(funcionario.getCargo());
        }
        if (funcionario.getDepartamento() != null) {
            funcionarioExistente.setDepartamento(funcionario.getDepartamento());
        }
        if (funcionario.getSalario() != null) {
            funcionarioExistente.setSalario(funcionario.getSalario());
        }
        if (funcionario.getCidade() != null) {
            funcionarioExistente.setCidade(funcionario.getCidade());
        }
        if (funcionario.getStatus() != null) {
            funcionarioExistente.setStatus(funcionario.getStatus());
        }
        return funcionarioExistente;

    }

    public Funcionario deleteFuncionario(Long id) {
        Funcionario funcionario = getFuncionarioById(id);
        funcionarios.remove(funcionario);
        return funcionario;
    }

    private Long gerarId() {
        if (funcionarios.isEmpty()) {
            return 1L;
        } else {
            return funcionarios.get(funcionarios.size() - 1).getId() + 1;
        }
    }

    private void validarEmailUnico(String email) {
        boolean emailExiste = funcionarios.stream()
                .anyMatch(f -> email.equals(f.getEmail()));

        if (emailExiste) {
            throw new DadoDuplicadoException("O email " + email + " já está cadastrado por outro candidato.");
        }
    }

    private void validarNomeUnico(String nome) {
        boolean usuarioExiste = funcionarios.stream()
                .anyMatch(f -> nome.equals(f.getNome()));

        if (usuarioExiste) {
            throw new DadoDuplicadoException("O nome " + nome + " já está em uso.");
        }
    }

    private void validarTelefoneUnico(String telefone) {
        boolean telefoneExiste = funcionarios.stream()
                .anyMatch(f -> telefone.equals(f.getTelefone()));

        if (telefoneExiste) {
            throw new DadoDuplicadoException("O telefone " + telefone + " já está em uso.");
        }
    }
}
