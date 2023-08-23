package med.vol.api.entities.dto.medico;

import jakarta.validation.constraints.NotNull;
import med.vol.api.entities.dto.DadosEndereco;

public record AtualizarMedico(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
