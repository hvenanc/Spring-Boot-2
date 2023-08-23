package med.vol.api.entities.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarMedico(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
