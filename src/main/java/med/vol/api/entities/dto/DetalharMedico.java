package med.vol.api.entities.dto;

import med.vol.api.entities.Endereco;
import med.vol.api.entities.Especialidade;
import med.vol.api.entities.Medico;

public record DetalharMedico(Long id, String nome, String crm, String telefone, String email,
                             Especialidade especialidade, Endereco endereco){

    public DetalharMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getTelefone(), medico.getEmail(),
                medico.getEspecialidade(),medico.getEndereco());
    }
}
