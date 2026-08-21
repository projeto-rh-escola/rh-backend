package com.picpay.rh.service;

import com.picpay.rh.exception.DadoDuplicadoException;
import com.picpay.rh.exception.FuncionarioNaoEncontradoException;
import com.picpay.rh.handler.FieldError;
import com.picpay.rh.model.Funcionario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private List<Funcionario> funcionarios = new ArrayList<>();


    public Funcionario addFuncionario(Funcionario funcionario) {
        validarUnicidade(funcionario, null);
        funcionario.setId(gerarId());
        funcionarios.add(funcionario);
        return funcionario;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public Funcionario getFuncionarioById(Long id) {
        return funcionarios.stream()
                .filter(funcionario -> funcionario.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new FuncionarioNaoEncontradoException(id));
    }

    public List<Funcionario> getFuncionariosByStatus(String status) {
        return funcionarios.stream()
                .filter(funcionario -> funcionario.getStatus().toString().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public Funcionario patchFuncionario(Long id, Funcionario funcionario) {
        if (funcionario.getId() != null && !funcionario.getId().equals(id)) {
            throw new IllegalArgumentException("O ID do funcionário não pode ser alterado.");
        }

        Funcionario funcionarioExistente = getFuncionarioById(id);
        validarUnicidade(funcionario, funcionarioExistente);

        if (funcionario.getNome() != null) {
            funcionarioExistente.setNome(funcionario.getNome());
        }
        if (funcionario.getEmail() != null) {
            funcionarioExistente.setEmail(funcionario.getEmail());
        }
        if (funcionario.getTelefone() != null) {
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

    public void deleteFuncionario(Long id) {
        Funcionario funcionario = getFuncionarioById(id);
        funcionarios.remove(funcionario);
    }

    private Long gerarId() {
        if (funcionarios.isEmpty()) {
            return 1L;
        } else {
            return funcionarios.get(funcionarios.size() - 1).getId() + 1;
        }
    }

    /**
     * Valida nome, email e telefone. Acumula todos os conflitos antes de lançar exceção.
     * No cadastro novo, funcionarioExistente é null. Na atualização, ignora campos que não mudaram.
     */
    private void validarUnicidade(Funcionario funcionarioNovo, Funcionario funcionarioExistente) {
        Long idExistente = funcionarioExistente != null ? funcionarioExistente.getId() : null;
        List<FieldError> erros = new ArrayList<>();

        if (funcionarioNovo.getNome() != null && (funcionarioExistente == null || !funcionarioNovo.getNome().equals(funcionarioExistente.getNome()))
                && existeConflito(f -> funcionarioNovo.getNome().equals(f.getNome()), idExistente)) {
            erros.add(new FieldError("nome", "O nome " + funcionarioNovo.getNome() + " já está em uso."));
        }
        if (funcionarioNovo.getEmail() != null && (funcionarioExistente == null || !funcionarioNovo.getEmail().equals(funcionarioExistente.getEmail()))
                && existeConflito(f -> funcionarioNovo.getEmail().equals(f.getEmail()), idExistente)) {
            erros.add(new FieldError("email", "O email " + funcionarioNovo.getEmail() + " já está cadastrado por outro candidato."));
        }
        if (funcionarioNovo.getTelefone() != null && (funcionarioExistente == null || !funcionarioNovo.getTelefone().equals(funcionarioExistente.getTelefone()))
                && existeConflito(f -> funcionarioNovo.getTelefone().equals(f.getTelefone()), idExistente)) {
            erros.add(new FieldError("telefone", "O telefone " + funcionarioNovo.getTelefone() + " já está em uso."));
        }

        if (!erros.isEmpty()) {
            throw new DadoDuplicadoException(erros);
        }
    }

    /**
     * Verifica se existe algum funcionário na lista que atenda à condição, excluindo o próprio idExistente.
     */
    private boolean existeConflito(Predicate<Funcionario> condicao, Long idExistente) {
        return funcionarios.stream()
                .filter(f -> idExistente == null || !f.getId().equals(idExistente))
                .anyMatch(condicao);
    }
}
