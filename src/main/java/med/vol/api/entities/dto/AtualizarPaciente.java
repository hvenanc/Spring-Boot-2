package med.vol.api.entities.dto;

import jakarta.validation.constraints.NotNull;
import med.vol.api.entities.Endereco;

public record AtualizarPaciente(

        @NotNull
        Long id,

        String nome,

        String telefone,

        DadosEndereco endereco) {
}
