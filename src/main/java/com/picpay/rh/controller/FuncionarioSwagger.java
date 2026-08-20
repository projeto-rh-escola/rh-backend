package com.picpay.rh.controller;

import com.picpay.rh.handler.ErroPadrao;
import com.picpay.rh.model.Funcionario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Funcionário", description = "Operações relacionadas a funcionários para o RH")
public interface FuncionarioSwagger {

    @Operation(summary = "Cadastrar funcionario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Funcionário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para cadastro", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroPadrao.class))),
            @ApiResponse(responseCode = "409", description = "Dado duplicado (Email, Nome ou Telefone já em uso)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroPadrao.class)))
    })
    ResponseEntity<Funcionario> cadastrar(@Valid @RequestBody Funcionario funcionario);

    @Operation(summary = "Obter todos os funcinários")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Funcionários encontrados com sucesso")
    })
    ResponseEntity<List<Funcionario>> consultarTodos();

    @Operation(summary = "Obter funcionário por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroPadrao.class)))
    })
    ResponseEntity<Funcionario> consultarPorId(@PathVariable Long id);

    @Operation(summary = "Obter funcionário por status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Funcionários encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionários não encontrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroPadrao.class)))
    })
    ResponseEntity<List<Funcionario>> consultarPorStatus(@PathVariable String status);

    @Operation(summary = "Atualizar alguns campos de um funcionário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroPadrao.class))),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroPadrao.class))),
            @ApiResponse(responseCode = "409", description = "Dado duplicado (Email, Nome ou Telefone já em uso)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroPadrao.class)))
    })
    ResponseEntity<Funcionario> atualizarFuncionario(@Valid @PathVariable Long id, @RequestBody Funcionario funcionario);

    @Operation(summary = "Deletar funcionário por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Funcionário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroPadrao.class)))
    })
    ResponseEntity<Void> deletarFuncionario(@PathVariable Long id);

}
