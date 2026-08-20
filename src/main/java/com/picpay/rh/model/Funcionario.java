package com.picpay.rh.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.picpay.rh.model.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"id", "nome", "email", "telefone", "departamento", "cargo", "salario", "cidade", "status"})
public class Funcionario {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Schema(example = "Kevin Jun")
    private String nome;

    @Email
    @NotBlank(message = "O email é obrigatório")
    @Schema(example = "kevin.jun@email.com")
    private String email;

    @Pattern(regexp = "^[1-9]{2}9\\d{8}$", message = "O telefone deve conter exatamente 11 números, incluindo o DDD.")
    @NotBlank(message = "O telefone é obrigatório")
    @Schema(example = "11999999999")
    private String telefone;

    @NotBlank(message = "O cargo é obrigatório")
    @Schema(example = "Desenvolvedor")
    private String cargo;

    @NotBlank(message = "O departamento é obrigatório")
    @Schema(example = "TI")
    private String departamento;

    @NotNull(message = "O salário é obrigatório")
    @Schema(example = "5000.00")
    private Double salario;

    @NotBlank(message = "A cidade é obrigatória")
    @Schema(example = "São Paulo")
    private String cidade;

    @NotNull(message = "O status é obrigatório")
    @Schema(example = "EM_ANALISE")
    private Status status;

}
