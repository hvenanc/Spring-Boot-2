package med.vol.api.entities.dto.paciente;

import jakarta.validation.constraints.NotNull;
import med.vol.api.entities.Endereco;
import med.vol.api.entities.dto.DadosEndereco;

public record AtualizarPaciente(

        @NotNull
        Long id,

        String nome,

        String telefone,

        DadosEndereco endereco) {
}
