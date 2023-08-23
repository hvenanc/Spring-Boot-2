package med.vol.api.entities.dto.paciente;

import med.vol.api.entities.Paciente;

public record ListarPacientes(Long id, String nome, String email, String cpf) {

    public ListarPacientes(Paciente paciente) {
        this(paciente.getId(),paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
