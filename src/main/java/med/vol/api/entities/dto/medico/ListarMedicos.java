package med.vol.api.entities.dto.medico;

import med.vol.api.entities.Especialidade;
import med.vol.api.entities.Medico;

public record ListarMedicos(Long id, String nome, String email, Especialidade especialidade, String crm) {

    public ListarMedicos(Medico medico) {
        this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getEspecialidade(), medico.getCrm());
    }
}
